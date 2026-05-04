# Collaball

## 서비스 개요
숭실대학교 학생 전용 팀 프로젝트 동료 평가 및 프로필 카드 서비스

## 기술 스택
- Java 17, Spring Boot 3.5.13, Gradle
- MySQL + Spring Data JPA
- Spring Security + JWT (jjwt 0.12.3)
- Lombok, Swagger (springdoc 2.3.0)
- 이메일 인증: Gmail SMTP

## 인증 정책
- @soongsil.ac.kr 도메인만 가입 허용, 아니면 즉시 예외
- 인증 코드를 이메일로 발송 후 확인 완료 시 가입 처리
- JWT 액세스/리프레시 토큰 방식

## 도메인 패키지 구조
├── domain/user
├── domain/project
├── domain/team
├── domain/task
├── domain/review
└── domain/profile

## DB 테이블 (7개)
USER, PROJECT, TEAM_MEMBER, TASK, TASK_ASSIGN, PEER_REVIEW, PROFILE_CARD

## 핵심 원칙
- 동료 평가가 핵심 지표 
- 태스크 관리는 기록용