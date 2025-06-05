package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HealthCheckApplication {
    public static void main(String[] args) {
        SpringApplication.run(HealthCheckApplication.class, args);
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "java-app");
    }
}