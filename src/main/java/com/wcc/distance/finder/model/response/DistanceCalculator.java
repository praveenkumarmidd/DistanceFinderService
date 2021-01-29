package com.wcc.distance.finder.model.response;

import com.wcc.distance.finder.model.request.Location;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Distance Calculator service Response model class
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
@Component
@Setter
@Getter
@ApiModel(description = "Distance Calculator Response")
public class DistanceCalculator {
    private String status;
    private Location sourceLocation;
    private Location destinationLocation;
    private String distance;
}
