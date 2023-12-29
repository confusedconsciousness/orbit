package com.kishan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.kishan.module.GuiceModule;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class App extends Application<OrbitConfiguration> {

    public static void main(final String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return "orbit";
    }

    @Override
    public void initialize(final Bootstrap<OrbitConfiguration> bootstrap) {
        bootstrap.addBundle(guiceBundle(createGuiceModule(new ObjectMapper())));
        bootstrap.addBundle(new SwaggerBundle<>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(OrbitConfiguration configuration) {
                return configuration.getSwaggerBundleConfiguration();
            }
        });

    }

    @Override
    public void run(final OrbitConfiguration orbitConfiguration,
                    final Environment environment) {
        // TODO: implement application
    }

    private GuiceModule createGuiceModule(ObjectMapper objectMapper) {
        return new GuiceModule(objectMapper);
    }

    GuiceBundle guiceBundle(Module... modules) {
        return GuiceBundle.builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .modules(modules).build(Stage.DEVELOPMENT);
    }
}
