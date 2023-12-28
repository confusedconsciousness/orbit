package com.kishan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import in.vectorpro.dropwizard.swagger.SwaggerBundleConfiguration;
import io.dropwizard.core.Configuration;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrbitConfiguration extends Configuration {
    // TODO: implement service configuration
    private SwaggerBundleConfiguration swaggerBundleConfiguration;
}
