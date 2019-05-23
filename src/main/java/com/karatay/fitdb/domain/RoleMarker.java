package com.karatay.fitdb.domain;

public interface RoleMarker {

    default boolean isAdmin() {
        return false;
    }

    default boolean isClient() {
        return false;
    }

    default boolean isInstructor() {
        return false;
    }
}
