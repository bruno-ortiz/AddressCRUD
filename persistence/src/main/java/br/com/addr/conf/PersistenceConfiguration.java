package br.com.addr.conf;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:datasource-config.properties")
public class PersistenceConfiguration {

    @Bean(destroyMethod = "close")
    public HikariDataSource defaultDatasource(DatasourceConfig datasourceConfig) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(datasourceConfig.getDriver());
        hikariConfig.setJdbcUrl(datasourceConfig.getJdbcUrl());
        hikariConfig.setUsername(datasourceConfig.getUsername());
        hikariConfig.setPassword(datasourceConfig.getPassword());
        hikariConfig.setMaximumPoolSize(datasourceConfig.getMaximumPoolSize());
        hikariConfig.setMinimumIdle(datasourceConfig.getMinimumIdle());
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public DatasourceConfig datasourceConfig(Environment env) {
        return new DatasourceConfig(
                env.getProperty("addresscrud.datasource.driver"),
                env.getProperty("addresscrud.datasource.jdbcUrl"),
                env.getProperty("addresscrud.datasource.username"),
                env.getProperty("addresscrud.datasource.password"),
                env.getProperty("addresscrud.datasource.pool.maximumPoolSize", Integer.class),
                env.getProperty("addresscrud.datasource.pool.minimumIdle", Integer.class)
        );
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        Resource res = new ClassPathResource("bootstrap.sql");
        DataSource ds = event.getApplicationContext().getBean(DataSource.class);
        new ResourceDatabasePopulator(res).execute(ds);
    }

}