package com.ione.entity.enums;

public enum DeliveryStatus {
    PENDING("PENDING"),
    GOING_TO_LOAD("GOING TO LOAD"),
    LOADING("LOADING"),
    IN_TRANSIT("IN TRANSIT"),
    DELIVERED("DELIVERED"),
    UNLOADING("UNLOADING"),
    SUCCEED("SUCCEED"),
    FAILED("FAILED");
    private final String displayName;

    DeliveryStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static DeliveryStatus fromDisplayName(String name) {
        for (DeliveryStatus status : values()) {
            if (status.displayName.equalsIgnoreCase(name)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown delivery status: " + name);
    }
}
