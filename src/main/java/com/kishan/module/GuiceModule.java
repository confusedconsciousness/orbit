package com.kishan.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.kishan.OrbitConfiguration;
import com.kishan.config.CrawlingConfiguration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class GuiceModule extends AbstractModule {

    private ObjectMapper objectMapper;

    @Provides
    @Singleton
    public SwaggerBundleConfiguration swaggerBundleConfiguration(OrbitConfiguration configuration) {
        return configuration.getSwaggerBundleConfiguration();
    }

    @Provides
    @Singleton
    public CrawlingConfiguration crawlingConfiguration(OrbitConfiguration configuration) {
        return configuration.getCrawlingConfiguration();
    }

}
