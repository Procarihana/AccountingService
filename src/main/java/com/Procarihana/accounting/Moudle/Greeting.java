package com.procarihana.accounting.moudle;

public class Greeting {
    private final Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Greeting(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
