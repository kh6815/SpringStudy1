package com.example.study.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration //설정파일
@EnableJpaAuditing //JAP 감시를 활성화 시키겠다.
public class JpaConfig {

}
