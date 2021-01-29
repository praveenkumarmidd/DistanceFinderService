package com.wcc.distance.finder.service.postcodereform;

import com.wcc.distance.finder.model.request.Location;
/**
 * Postcode Reform Interface
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
public interface PostCodeReformService {
    void postCodeLatLogUpdate(Location location);
}
