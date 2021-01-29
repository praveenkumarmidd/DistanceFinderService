package com.wcc.distance.finder.repository;

import com.wcc.distance.finder.model.request.Location;
import com.wcc.distance.finder.repository.entitiy.DistanceFinderEntity;
import com.wcc.distance.finder.repository.service.DistanceFinderDBService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DistanceFinderDBServiceTest {
    @InjectMocks
    DistanceFinderDBService distanceFinderDBService;
    @Mock
    private DistanceFinderRepository distanceFinderRepository;


    @Test
    public void getLatitudeAndLongitudeInfoFromDBTest()
    {
        List<DistanceFinderEntity> distanceFinderEntitiesList = new ArrayList<>();
        DistanceFinderEntity sourceDistanceFinderEntity = new DistanceFinderEntity();
        sourceDistanceFinderEntity.setLatitude(57.186709530000000);
        sourceDistanceFinderEntity.setLongitude(-2.182280841000000);
        sourceDistanceFinderEntity.setPostCode("AB21 9HS");
        DistanceFinderEntity destinationDistanceFinderEntity = new DistanceFinderEntity();
        destinationDistanceFinderEntity.setLatitude(57.185659760000000);
        destinationDistanceFinderEntity.setLongitude(-2.181431946000000);
        destinationDistanceFinderEntity.setPostCode("AB21 9HT");
        distanceFinderEntitiesList.add(sourceDistanceFinderEntity);
        distanceFinderEntitiesList.add(destinationDistanceFinderEntity);
        when(distanceFinderRepository.findAllById(anyList())).thenReturn(distanceFinderEntitiesList);
        List<DistanceFinderEntity> distanceFinderEntityList=distanceFinderDBService.getLatitudeAndLongitudeInfoFromDB(anyList());
        Assert.assertNotNull(distanceFinderEntityList);
    }

    @Test
    public void updatePostCodeLatLogTest()
    {
        Location location = new Location("AB21 9HT", 57.188606440000000, -2.181247873000000);
        when(distanceFinderRepository.save(any(DistanceFinderEntity.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        distanceFinderDBService.updatePostCodeLatLog(location);
        Mockito.verify(distanceFinderRepository, times(1)).save(any(DistanceFinderEntity.class));
    }
}
