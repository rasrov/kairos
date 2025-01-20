package com.rasrov.kairos.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;

import java.util.concurrent.TimeUnit;

@ConfigurationProperties(prefix = "cache")
@Validated
public record CacheConfig(@NotNull Integer duration,
                          @NotNull Integer maxSize) {

    @Profile("pre")
    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(duration, TimeUnit.MINUTES)
                .maximumSize(maxSize);
    }
}
