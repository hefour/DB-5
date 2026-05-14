package com.collaball.domain.auth.service;

import com.collaball.common.api.code.ErrorCode;
import com.collaball.common.exception.BusinessException;
import com.collaball.common.jwt.JwtProvider;
import com.collaball.domain.auth.dto.LoginRequest;
import com.collaball.domain.auth.dto.SendEmailRequest;
import com.collaball.domain.auth.dto.SignupRequest;
import com.collaball.domain.auth.dto.TokenResponse;
import com.collaball.domain.auth.dto.VerifyCodeRequest;
import com.collaball.domain.auth.entity.EmailVerification;
import com.collaball.domain.auth.repository.EmailVerificationRepository;
import com.collaball.domain.user.entity.User;
import com.collaball.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String SOONGSIL_DOMAIN = "@soongsil.ac.kr";
    private static final int CODE_LENGTH = 6;
    private static final int CODE_EXPIRY_MINUTES = 5;

    private final UserRepository userRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final EmailService emailService;

    @Transactional
    public void sendVerificationEmail(SendEmailRequest request) {
        String email = request.email();

        if (!email.endsWith(SOONGSIL_DOMAIN)) {
            throw new BusinessException(ErrorCode.INVALID_EMAIL_DOMAIN);
        }
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }

        String code = generateCode();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(CODE_EXPIRY_MINUTES);

        emailVerificationRepository.findByEmail(email).ifPresentOrElse(
                ev -> ev.updateCode(code, expiresAt),
                () -> emailVerificationRepository.save(
                        EmailVerification.builder()
                                .email(email)
                                .code(code)
                                .expiresAt(expiresAt)
                                .build()
                )
        );

        emailService.sendVerificationCode(email, code);
    }

    @Transactional
    public void verifyCode(VerifyCodeRequest request) {
        EmailVerification ev = emailVerificationRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException(ErrorCode.VERIFICATION_CODE_NOT_FOUND));

        if (LocalDateTime.now().isAfter(ev.getExpiresAt())) {
            throw new BusinessException(ErrorCode.VERIFICATION_CODE_EXPIRED);
        }
        if (!ev.getCode().equals(request.code())) {
            throw new BusinessException(ErrorCode.VERIFICATION_CODE_MISMATCH);
        }

        ev.markVerified();
    }

    @Transactional
    public void signup(SignupRequest request) {
        EmailVerification ev = emailVerificationRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException(ErrorCode.EMAIL_NOT_VERIFIED));

        if (!ev.isVerified()) {
            throw new BusinessException(ErrorCode.EMAIL_NOT_VERIFIED);
        }

        if (userRepository.existsByEmail(request.email())) {
            throw new BusinessException(ErrorCode.DUPLICATE_EMAIL);
        }

        userRepository.save(User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .name(request.name())
                .department(request.department())
                .build());

        emailVerificationRepository.delete(ev);
    }

    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException(ErrorCode.LOGIN_FAILED));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BusinessException(ErrorCode.LOGIN_FAILED);
        }

        return new TokenResponse(
                jwtProvider.generateAccessToken(user),
                jwtProvider.generateRefreshToken(user.getEmail())
        );
    }

    private String generateCode() {
        SecureRandom random = new SecureRandom();
        int number = random.nextInt(900000) + 100000;
        return String.valueOf(number);
    }
}
