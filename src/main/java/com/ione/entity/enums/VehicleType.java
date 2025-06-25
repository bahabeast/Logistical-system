package com.ione.entity.enums;

public enum VehicleType {
    TENTED("TENTED TRUCK"),
    REFRIGERATOR("REFRIGERATOR TRUCK"),
    ISOTHERM("ISOTHERMAL VAN"),
    OPEN("OPEN PLATFORM"),
    GAZELLE("GAZELLE"),
    CONTAINER("CONTAINER TRUCK"),
    TANKER("TANKER"),
    DUMP("DUMP TRUCK"),
    AWNING("AWNING TRUCK"),
    JUMBO("JUMBO TRUCK"),
    CAR_CARRIER("CAR CARRIER"),
    LIVESTOCK("LIVESTOCK TRUCK");
    private final String displayName;

    VehicleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static VehicleType fromDisplayName(String name) {
        for (VehicleType vehicleType : values()) {
            if (vehicleType.displayName.equalsIgnoreCase(name)) {
                return vehicleType;
            }
        }
        throw new IllegalArgumentException("Unknown vehicle type: " + name);
    }
}

