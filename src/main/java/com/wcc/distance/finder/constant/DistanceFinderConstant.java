package com.wcc.distance.finder.constant;

/**
 * Distance finder service enum constants
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
public enum DistanceFinderConstant {
    KM(" km"),
    SUCCESS("Success");
    private final String constants;

    DistanceFinderConstant(final String constants) {
        this.constants = constants;
    }

    public String getValue() {
        return constants;
    }

}
