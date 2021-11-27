package com.projects;

// An abstract class allows you to create functionality that subclasses can implement or override.
public abstract class Teams {
    private String name;
    private String location;
    private String statistics;

    @Override
    public boolean equals(Object o) {
        return this.name.equals(((Teams)o).name);
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getStatistics() {
        return statistics;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatistics(String statistics) {
        this.statistics = statistics;
    }


}
