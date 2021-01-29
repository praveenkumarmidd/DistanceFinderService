package com.wcc.distance.finder.repository.service;

import com.wcc.distance.finder.model.request.Location;
import com.wcc.distance.finder.repository.DistanceFinderRepository;
import com.wcc.distance.finder.repository.entitiy.DistanceFinderEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * DistanceFinderDBService class to serve transaction with h2 database
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
@Service
@Transactional
public class DistanceFinderDBService {
    @Resource
    private DistanceFinderRepository distanceFinderRepository;


    public List<DistanceFinderEntity> getLatitudeAndLongitudeInfoFromDB(List<String> postalCodes) {
        return distanceFinderRepository.findAllById(postalCodes);
    }

    public void updatePostCodeLatLog(Location location) {
        DistanceFinderEntity distanceFinderEntity = new DistanceFinderEntity();
        distanceFinderEntity.setPostCode(location.getPostcode());
        distanceFinderEntity.setLatitude(location.getLatitude());
        distanceFinderEntity.setLongitude(location.getLongitude());
        distanceFinderRepository.save(distanceFinderEntity);
    }
}
