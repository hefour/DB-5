package com.db_5.db_5_backend.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    // createdAt / updatedAt 자동 관리를 위한 JPA Auditing 활성화
}
