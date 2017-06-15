package ru.javawebinar.topjava.model;

public abstract class NamedEntity extends BaseEntity {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
