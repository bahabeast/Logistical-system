package com.ione.entity.enums;

public enum     DeliveryStatus {
    PENDING,
    GOING_TO_LOAD,
    LOADING,
    IN_TRANSIT,
    DELIVERED,
    UNLOADING,
    SUCCEED,
    FAILED;

    public boolean canTransitionTo(DeliveryStatus next, Role role) {
        if (next == null || role == null) return false;

        return switch (role) {
            case CUSTOMER -> switch (this) {
                case PENDING -> next == GOING_TO_LOAD;
                case DELIVERED -> next == UNLOADING;
                case UNLOADING -> next == SUCCEED || next == FAILED;
                default -> false;
            };
            case DRIVER -> switch (this) {
                case GOING_TO_LOAD -> next == LOADING;
                case LOADING -> next == IN_TRANSIT;
                case IN_TRANSIT -> next == DELIVERED;
                default -> false;
            };
        };
    }
}
