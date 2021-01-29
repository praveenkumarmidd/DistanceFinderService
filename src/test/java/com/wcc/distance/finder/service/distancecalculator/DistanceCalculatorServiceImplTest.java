package com.wcc.distance.finder.service.distancecalculator;

import com.wcc.distance.finder.exception.DistanceFinderCustomException;
import com.wcc.distance.finder.model.response.DistanceCalculator;
import com.wcc.distance.finder.repository.entitiy.DistanceFinderEntity;
import com.wcc.distance.finder.repository.service.DistanceFinderDBService;
import com.wcc.distance.finder.service.distancecalculator.impl.DistanceCalculatorServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DistanceCalculatorServiceImplTest {
    private static final double DELTA = 1e-15;
    @InjectMocks
    private DistanceCalculatorServiceImpl distanceCalculatorServiceImpl;
    @Mock
    private DistanceFinderDBService distanceFinderDBService;
    @Spy
    DistanceCalculator distanceCalculator;
    private List<DistanceFinderEntity> distanceFinderEntitiesList;
    private DistanceFinderEntity sourceDistanceFinderEntity;
    private DistanceFinderEntity destinationDistanceFinderEntity;

    @Before
    public void init() {
        distanceFinderEntitiesList = new ArrayList<>();
        sourceDistanceFinderEntity= new DistanceFinderEntity();
        sourceDistanceFinderEntity.setLatitude(57.186709530000000);
        sourceDistanceFinderEntity.setLongitude(-2.182280841000000);
        sourceDistanceFinderEntity.setPostCode("AB21 9HS");
        destinationDistanceFinderEntity = new DistanceFinderEntity();
        destinationDistanceFinderEntity.setLatitude(57.185659760000000);
        destinationDistanceFinderEntity.setLongitude(-2.181431946000000);
        destinationDistanceFinderEntity.setPostCode("AB21 9HT");
    }

    @Test
    public void getDistanceBetweenTwoPostCodeTest() throws DistanceFinderCustomException {
        distanceFinderEntitiesList.add(sourceDistanceFinderEntity);
        distanceFinderEntitiesList.add(destinationDistanceFinderEntity);
        when(distanceFinderDBService.getLatitudeAndLongitudeInfoFromDB(anyList())).thenReturn(distanceFinderEntitiesList);
        DistanceCalculator distanceCalculator = distanceCalculatorServiceImpl.getDistanceBetweenTwoPostCode("AB21 9HS", "AB21 9HT");
        Assert.assertNotNull(distanceCalculator);
        Assert.assertNotNull(distanceCalculator.getSourceLocation());
        Assert.assertNotNull(distanceCalculator.getDestinationLocation());
        Assert.assertEquals("AB21 9HS", distanceCalculator.getSourceLocation().getPostcode());
        Assert.assertEquals(57.186709530000000, distanceCalculator.getSourceLocation().getLatitude(), DELTA);
        Assert.assertEquals(-2.182280841000000, distanceCalculator.getSourceLocation().getLongitude(), DELTA);
        Assert.assertEquals("AB21 9HT", distanceCalculator.getDestinationLocation().getPostcode());
        Assert.assertEquals(57.185659760000000, distanceCalculator.getDestinationLocation().getLatitude(), DELTA);
        Assert.assertEquals(-2.181431946000000, distanceCalculator.getDestinationLocation().getLongitude(), DELTA);
        Assert.assertEquals("0.1274451254981557 km", distanceCalculator.getDistance());
    }

    @Test(expected = DistanceFinderCustomException.class)
    public void getDistanceBetweenTwoPostCodeTestWithOnlySourcePostcode() throws DistanceFinderCustomException {
        distanceFinderEntitiesList.add(sourceDistanceFinderEntity);
        when(distanceFinderDBService.getLatitudeAndLongitudeInfoFromDB(anyList())).thenReturn(distanceFinderEntitiesList);
        DistanceCalculator distanceCalculator = distanceCalculatorServiceImpl.getDistanceBetweenTwoPostCode("AB21 9HS", "AB21 9HT");
    }
}
