package com.wcc.distance.finder.service.postcodereform;

import com.wcc.distance.finder.exception.DistanceFinderCustomException;
import com.wcc.distance.finder.model.request.Location;
import com.wcc.distance.finder.repository.service.DistanceFinderDBService;
import com.wcc.distance.finder.service.postcodereform.impl.PostCodeReformServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class PostcodeReformTest {

    @InjectMocks
    PostCodeReformServiceImpl postCodeReformService;
    @Mock
    DistanceFinderDBService distanceFinderDBService;

    @Test
    public void postCodeLatLogUpdateTest() {
        Location location = new Location("AB21 9HT", 57.188606440000000, -2.181247873000000);
        Mockito.doNothing().when(distanceFinderDBService).updatePostCodeLatLog(location);
        postCodeReformService.postCodeLatLogUpdate(location);
        Mockito.verify(distanceFinderDBService, times(1)).updatePostCodeLatLog(location);
    }
}
