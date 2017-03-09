package br.com.addr.conf;

public class DatasourceConfig {

    private final String driver;
    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final int maximumPoolSize;
    private final int minimumIdle;

    public DatasourceConfig(String driver, String jdbcUrl, String username, String password, int maximumPoolSize, int minimumIdle) {
        this.driver = driver;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.maximumPoolSize = maximumPoolSize;
        this.minimumIdle = minimumIdle;
    }

    String getDriver() {
        return driver;
    }

    String getJdbcUrl() {
        return jdbcUrl;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    int getMinimumIdle() {
        return minimumIdle;
    }
}