package br.com.addr.spark;

import br.com.addr.spark.conf.SparkConfiguration;
import br.com.addr.spark.mappings.UrlMappings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static spark.Spark.*;

@Component
public class SparkBoot {

    private static final Logger log = LoggerFactory.getLogger(SparkBoot.class);

    private final SparkConfiguration sparkConfiguration;
    private final UrlMappings urlMappings;

    @Autowired
    public SparkBoot(SparkConfiguration sparkConfiguration, UrlMappings urlMappings) {
        this.sparkConfiguration = sparkConfiguration;
        this.urlMappings = urlMappings;
    }

    public void bootApplication() {
        port(sparkConfiguration.getPort());
        threadPool(sparkConfiguration.getThreadPoolMax(),
                sparkConfiguration.getThreadPoolMin(),
                sparkConfiguration.getThreadPoolTimeout());

        before((request, response) -> response.type("application/json"));

        urlMappings.mapEndpoints();

        log.info("Application started!");
    }

}
