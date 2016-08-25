package com.miniqueue;

import com.miniqueue.resources.MiniQueueResource;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

public class MiniQueueApplication extends Application<MiniQueueConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MiniQueueApplication().run(args);
    }

    @Override
    public String getName() {
        return "MiniQueue";
    }

    @Override
    public void initialize(final Bootstrap<MiniQueueConfiguration> bootstrap) {
    }

    @Override
    public void run(final MiniQueueConfiguration configuration,
                    final Environment environment) {
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi;
        try {
            jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
            environment.jersey().register(new MiniQueueResource(jdbi));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
