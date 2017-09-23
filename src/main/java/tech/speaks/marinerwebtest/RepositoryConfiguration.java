package tech.speaks.marinerwebtest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = "tech.speaks.marinerwebtest")
@EnableJpaRepositories(basePackages = "tech.speaks.marinerwebtest")
@EnableTransactionManagement
public class RepositoryConfiguration {
}
