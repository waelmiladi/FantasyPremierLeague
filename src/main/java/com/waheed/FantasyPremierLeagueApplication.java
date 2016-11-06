package com.waheed;

import com.hubspot.dropwizard.guice.GuiceBundle;
import com.waheed.module.FantasyPremierLeagueModule;
import io.dropwizard.Application;
import io.dropwizard.configuration.ConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class FantasyPremierLeagueApplication extends Application<FantasyPremierLeagueConfiguration> {

    private GuiceBundle<FantasyPremierLeagueConfiguration> guiceBundle;

    public static void main(final String[] args) throws Exception {
        new FantasyPremierLeagueApplication().run(args);
    }

    @Override
    public String getName() {
        return "FantasyPremierLeagueClient";
    }

    @Override
    public void initialize(final Bootstrap<FantasyPremierLeagueConfiguration> bootstrap) {
        ConfigurationSourceProvider factory = bootstrap.getConfigurationSourceProvider();
        guiceBundle = GuiceBundle.<FantasyPremierLeagueConfiguration>newBuilder()
                .addModule(new FantasyPremierLeagueModule())
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(FantasyPremierLeagueConfiguration.class)
                .build();

        bootstrap.addBundle(guiceBundle);
    }

    @Override
    public void run(final FantasyPremierLeagueConfiguration configuration, final Environment environment) {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

}
