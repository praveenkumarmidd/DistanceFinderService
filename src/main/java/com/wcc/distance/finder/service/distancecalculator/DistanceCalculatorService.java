package com.wcc.distance.finder.service.distancecalculator;

import com.wcc.distance.finder.exception.DistanceFinderCustomException;
import com.wcc.distance.finder.model.response.DistanceCalculator;
/**
 * Distance Calculator Service Interface
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
public interface DistanceCalculatorService {
    DistanceCalculator getDistanceBetweenTwoPostCode(String sourcePostCode, String destinationPostCode) throws DistanceFinderCustomException;
}
