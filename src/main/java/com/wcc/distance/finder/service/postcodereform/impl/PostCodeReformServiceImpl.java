package com.wcc.distance.finder.service.postcodereform.impl;

import com.wcc.distance.finder.model.request.Location;
import com.wcc.distance.finder.repository.service.DistanceFinderDBService;
import com.wcc.distance.finder.service.postcodereform.PostCodeReformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Postcode Reform Service class
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
@Service
public class PostCodeReformServiceImpl implements PostCodeReformService {
    @Autowired
    DistanceFinderDBService distanceFinderDBService;

    /**
     * This method update/create the postcode latitude and longitude
     * @param location
     */
    public void postCodeLatLogUpdate(Location location) {
        distanceFinderDBService.updatePostCodeLatLog(location);
    }
}
