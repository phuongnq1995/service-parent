package org.phuongnq.service.parent.integration;

import org.phuongnq.service.parent.ServiceParentApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;

public class LocalDevApplication {
    public static void main(String[] args) {
        SpringApplication.from(ServiceParentApplication::main)
                .with(LocalDevTestcontainersConfig.class)
                .run(args);
    }

    @TestConfiguration(proxyBeanMethods = false)
    static class LocalDevTestcontainersConfig {
        @Bean
        @RestartScope
        @ServiceConnection
        public GenericContainer postgreSQLContainer() {
            return new PostgreSQLContainer("postgres:11.1")
                    .withDatabaseName("integration-tests-db")
                    .withUsername("sa")
                    .withPassword("sa")
                    .withReuse(true);
        }
    }
}
