package io.github.zemise.springbootmybaits.domain;

public class Host {
    private String host;
    private String current_memory;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getCurrent_memory() {
        return current_memory;
    }

    public void setCurrent_memory(String current_memory) {
        this.current_memory = current_memory;
    }

    @Override
    public String toString() {
        return "Host{" +
                "host='" + host + '\'' +
                ", current_memory='" + current_memory + '\'' +
                '}';
    }
}
