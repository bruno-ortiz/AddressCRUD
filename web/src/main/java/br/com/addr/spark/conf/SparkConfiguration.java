package br.com.addr.spark.conf;

public class SparkConfiguration {

    private final int port;
    private final int threadPoolMin;
    private final int threadPoolMax;
    private final int threadPoolTimeout;

    public SparkConfiguration(int port, int threadPoolMin, int threadPoolMax, int threadPoolTimeout) {
        this.port = port;
        this.threadPoolMin = threadPoolMin;
        this.threadPoolMax = threadPoolMax;
        this.threadPoolTimeout = threadPoolTimeout;
    }


    public int getPort() {
        return port;
    }

    public int getThreadPoolMin() {
        return threadPoolMin;
    }

    public int getThreadPoolMax() {
        return threadPoolMax;
    }

    public int getThreadPoolTimeout() {
        return threadPoolTimeout;
    }
}
