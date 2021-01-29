package com.wcc.distance.finder.service.distancecalculator.impl;

import com.wcc.distance.finder.constant.DistanceFinderConstant;
import com.wcc.distance.finder.exception.DistanceFinderCustomException;
import com.wcc.distance.finder.model.request.Location;
import com.wcc.distance.finder.model.response.DistanceCalculator;
import com.wcc.distance.finder.repository.entitiy.DistanceFinderEntity;
import com.wcc.distance.finder.repository.service.DistanceFinderDBService;
import com.wcc.distance.finder.service.distancecalculator.DistanceCalculatorService;
import com.wcc.distance.finder.utility.DistanceFinderUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Distance Calculator Service class to calculate the distance between two postcodes
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */
@Service
public class DistanceCalculatorServiceImpl implements DistanceCalculatorService {
    @Autowired
    DistanceFinderDBService distanceFinderDBService;
    @Autowired
    DistanceCalculator distanceCalculator;

    /**
     * This method calculates the distance in km units between two postcodes
     *
     * @param sourcePostCode      Source postcode
     * @param destinationPostCode Destination postcode
     * @return Calculated Distance in km units
     * @throws DistanceFinderCustomException throws if postcode details not found in the database
     */
    public DistanceCalculator getDistanceBetweenTwoPostCode(String sourcePostCode, String destinationPostCode) throws DistanceFinderCustomException {
        List<DistanceFinderEntity> distanceFinderEntitiesList = getLatitudeAndLongitudeInfo(sourcePostCode, destinationPostCode);
        Optional<DistanceFinderEntity> optionalSourceDistanceFinderEntity = distanceFinderEntitiesList.stream()
                .filter(postCodeResponse -> postCodeResponse.getPostCode().equalsIgnoreCase(sourcePostCode)).findAny();
        DistanceFinderEntity sourcePostCodeEntityResponse = optionalSourceDistanceFinderEntity.orElseThrow(() -> new DistanceFinderCustomException(DistanceFinderConstant.SOURCE_POSTCODE_NOT_FOUND_ERROR.getValue()));
        Optional<DistanceFinderEntity> optionalDestinationDistanceFinderEntity = distanceFinderEntitiesList.stream()
                .filter(postCodeResponse -> postCodeResponse.getPostCode().equalsIgnoreCase(destinationPostCode)).findAny();
        DistanceFinderEntity destinationPostCodeEntityResponse = optionalDestinationDistanceFinderEntity.orElseThrow(() -> new DistanceFinderCustomException(DistanceFinderConstant.DESTINATIONS_POSTCODE_NOT_FOUND_ERROR.getValue()));
        double calculatedDistance = DistanceFinderUtility.calculateDistance(sourcePostCodeEntityResponse, destinationPostCodeEntityResponse);
        distanceCalculator.setStatus(DistanceFinderConstant.SUCCESS.getValue());
        distanceCalculator.setDistance(String.valueOf(calculatedDistance).concat(DistanceFinderConstant.KM.getValue()));
        distanceCalculator.setSourceLocation(new Location(sourcePostCodeEntityResponse.getPostCode(), sourcePostCodeEntityResponse.getLatitude(), sourcePostCodeEntityResponse.getLongitude()));
        distanceCalculator.setDestinationLocation(new Location(destinationPostCodeEntityResponse.getPostCode(), destinationPostCodeEntityResponse.getLatitude(), destinationPostCodeEntityResponse.getLongitude()));
        return distanceCalculator;

    }

    private List<DistanceFinderEntity> getLatitudeAndLongitudeInfo(String sourcePostCode, String destinationPostCode) {
        List<String> postCodeList = new ArrayList<>();
        postCodeList.add(sourcePostCode);
        postCodeList.add(destinationPostCode);
        return distanceFinderDBService.getLatitudeAndLongitudeInfoFromDB(postCodeList);
    }


}
