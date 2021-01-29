package com.wcc.distance.finder.utility;

import com.wcc.distance.finder.repository.entitiy.DistanceFinderEntity;

/**
 * DistanceFinderUtility Service utility class
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
public class DistanceFinderUtility {
    private final static double EARTH_RADIUS = 6371;

    /**
     * This method calculates the distance between two postcodes
     * @param sourcePostCodeSet
     * @param destinationPostCodeSet
     * @return
     */
    public static double calculateDistance(DistanceFinderEntity sourcePostCodeSet, DistanceFinderEntity destinationPostCodeSet) {
        // Using Haversine formula! See Wikipedia;
        double lon1Radians = Math.toRadians(sourcePostCodeSet.getLongitude());
        double lon2Radians = Math.toRadians(destinationPostCodeSet.getLongitude());
        double lat1Radians = Math.toRadians(sourcePostCodeSet.getLatitude());
        double lat2Radians = Math.toRadians(destinationPostCodeSet.getLatitude());
        double a = haversine(lat1Radians, lat2Radians)
                + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (EARTH_RADIUS * c);
    }

    private static double haversine(double deg1, double deg2) {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }

    private static double square(double x) {
        return x * x;
    }
}
