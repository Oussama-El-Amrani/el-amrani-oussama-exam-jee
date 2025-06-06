package me.elamranioussama.exam_springboot_angular.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.bootify.exam_springboot_angular.domain")
@EnableJpaRepositories("io.bootify.exam_springboot_angular.repos")
@EnableTransactionManagement
public class DomainConfig {
}
