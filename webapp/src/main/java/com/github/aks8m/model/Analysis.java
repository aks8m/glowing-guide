package com.github.aks8m.model;

import java.time.LocalDateTime;

/**
 * 2019-01-20
 * aks8m - https://github.com/aks8m
 */
public class Analysis {

    private String name;
    private String id;
    private LocalDateTime localDateTime;

    public Analysis(String name, String id, LocalDateTime localDateTime) {
        this.name = name;
        this.id = id;
        this.localDateTime = localDateTime;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
