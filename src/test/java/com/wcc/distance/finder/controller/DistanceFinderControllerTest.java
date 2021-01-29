package com.wcc.distance.finder.controller;

import com.wcc.distance.finder.model.request.Location;
import com.wcc.distance.finder.model.response.DistanceCalculator;
import com.wcc.distance.finder.service.distancecalculator.DistanceCalculatorService;
import com.wcc.distance.finder.service.postcodereform.PostCodeReformService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;


@RunWith(SpringRunner.class)
@WebMvcTest(value = DistanceFinderController.class)
public class DistanceFinderControllerTest {
    @MockBean
    DistanceCalculatorService distanceCalculatorService;
    @MockBean
    PostCodeReformService postCodeReformService;
    HttpHeaders httpHeaders;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        httpHeaders = new HttpHeaders() {{
            String auth = "Test" + ":" + "Test123";
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(StandardCharsets.US_ASCII));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }

    @Test
    public void distanceCalculatorTest() throws Exception {
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        distanceCalculator.setStatus("Success");
        Location sourceDistance = new Location("AB21 9HT", 57.185659760000000, -2.181431946000000);
        Location destinationDistance = new Location("AB21 9HS", 57.186709530000000, -2.182280841000000);
        distanceCalculator.setSourceLocation(sourceDistance);
        distanceCalculator.setDestinationLocation(destinationDistance);
        distanceCalculator.setDistance("0.5306611428886459 km");
        Mockito.when(distanceCalculatorService.getDistanceBetweenTwoPostCode(anyString(), anyString())).thenReturn(distanceCalculator);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/distancefinder/distanceCalculator").param("sourcePostCode", "AB21 9HT").param("destinationPostCode", "AB21 9HS").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE).headers(httpHeaders);
        MvcResult mockResponse = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertNotNull(mockResponse);
        Assert.assertEquals(200, mockResponse.getResponse().getStatus());
    }

    @Test
    public void distanceCalculatorTestWithEmptyRequestParams() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/distancefinder/distanceCalculator").param("sourcePostCode", "").param("destinationPostCode", "AB21 9HS").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE).headers(httpHeaders);
        MvcResult mockResponse = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertNotNull(mockResponse);
        Assert.assertEquals(500, mockResponse.getResponse().getStatus());
    }

    @Test
    public void postCodeReformTest() throws Exception {
        String location="{\n" +
                "    \"postCode\": \"AB21 9HT\",\n" +
                "    \"latitude\": 57.185659760000000,\n" +
                "    \"longitude\": -2.181431946000000\n" +
                "}";
        Mockito.doNothing().when(postCodeReformService).postCodeLatLogUpdate(any(Location.class));
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/distancefinder/postcodeReform").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON_VALUE).headers(httpHeaders).content(location);
        MvcResult mockResponse = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertNotNull(mockResponse);
        Assert.assertEquals(200, mockResponse.getResponse().getStatus());
    }
}
