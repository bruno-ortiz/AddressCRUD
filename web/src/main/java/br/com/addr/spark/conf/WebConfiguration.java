package br.com.addr.spark.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:web-config.properties")
public class WebConfiguration {


    @Bean
    public SparkConfiguration sparkConfiguration(Environment env) {
        return new SparkConfiguration(
                env.getProperty("addresscrud.server.port", Integer.class),
                env.getProperty("addresscrud.server.threadPool.min", Integer.class),
                env.getProperty("addresscrud.server.threadPool.max", Integer.class),
                env.getProperty("addresscrud.server.threadPool.idleTimeout", Integer.class)
        );
    }

}
