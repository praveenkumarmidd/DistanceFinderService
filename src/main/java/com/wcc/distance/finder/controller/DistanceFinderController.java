package com.wcc.distance.finder.controller;

import com.wcc.distance.finder.constant.DistanceFinderConstant;
import com.wcc.distance.finder.exception.DistanceFinderCustomException;
import com.wcc.distance.finder.model.request.Location;
import com.wcc.distance.finder.model.response.DistanceCalculator;
import com.wcc.distance.finder.model.response.DistanceFinderResponse;
import com.wcc.distance.finder.model.response.PostReform;
import com.wcc.distance.finder.service.distancecalculator.DistanceCalculatorService;
import com.wcc.distance.finder.service.postcodereform.PostCodeReformService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for calculating and manipulating postcode latitude and longitude
 *
 * @author praveen kumar m
 * @version 1.0
 * @since 23-Jan-2021
 */

@RestController
@RequestMapping(path = "/distancefinder")
@Api(value = "Distance Finder Service")
public class DistanceFinderController {
    private static final String STATUS = "Postcode is successfully Updated/Created";
    @Autowired
    DistanceCalculatorService distanceCalculatorService;
    @Autowired
    PostCodeReformService postCodeReformService;

    @ApiOperation(value = "Calculate the distance between two postcodes")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully calculated the distance between two services"),
            @ApiResponse(code = 400, message = "Invalid Request params "),
            @ApiResponse(code = 401, message = "Not Authorized to view the resources"),
            @ApiResponse(code = 404, message = "The resource you where trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal Server error occurred")
    })
    @GetMapping(path = "/distanceCalculator", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DistanceFinderResponse<DistanceCalculator>> distanceCalculator(
            @ApiParam(value = "sourcePostCode", example = "AB21 9HZ", required = true)
            @RequestParam(value = "sourcePostCode") String sourcePostCode,
            @ApiParam(value = "destinationPostCode", example = "AB21 9JY", required = true)
            @RequestParam(value = "destinationPostCode") String destinationPostCode) throws DistanceFinderCustomException {
        if (StringUtils.isEmpty(sourcePostCode) || StringUtils.isEmpty(destinationPostCode)) {
            throw new DistanceFinderCustomException("Source Postcode or Destination Postcode is Null or Empty");
        } else {
            DistanceCalculator distanceFinderEntities = distanceCalculatorService.getDistanceBetweenTwoPostCode(sourcePostCode, destinationPostCode);
            DistanceFinderResponse<DistanceCalculator> responseDistanceFinderResponse = new DistanceFinderResponse<>(distanceFinderEntities);
            return new ResponseEntity<>(responseDistanceFinderResponse, HttpStatus.OK);
        }
    }

    /**
     * @param location
     * @return
     */
    @ApiOperation(value = "Update/Create the Postcode for given latitude and longitude")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully Update/Create Latitude and Longitue for the given postcode"),
            @ApiResponse(code = 401, message = "Not Authorized to view the resources"),
            @ApiResponse(code = 404, message = "The resource you where trying to reach is not found"),
            @ApiResponse(code = 500, message = "Internal Server error occurred")
    })
    @PutMapping(path = "/postcodeReform", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DistanceFinderResponse<PostReform>> postCodeReform(@RequestBody Location location) {
        postCodeReformService.postCodeLatLogUpdate(location);
        DistanceFinderResponse<PostReform> responseDistanceFinderResponse = new DistanceFinderResponse<>(new PostReform(DistanceFinderConstant.SUCCESS.getValue(), STATUS));
        return new ResponseEntity<>(responseDistanceFinderResponse, HttpStatus.OK);
    }
}
