package com.tsp.TSPApi.Application;

import com.tsp.TSPApi.Entities.RouteResponse;
import com.tsp.TSPApi.TourManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class RouteApplicationServiceTests {

    @Autowired
    private IRouteApplicationService _routeApplicationService;

    @Test
    public void CalculateRouteGA_CalculationRequested_ReturnsRouteResponse(){
        // Act
        RouteResponse actual = _routeApplicationService.calculateRouteGA();

        // Assert
        assertNotNull(actual);
    }

    @Test
    public void CalculateRouteGA_CalculationRequested_TourManagerHasCities(){
        // Act
        _routeApplicationService.calculateRouteGA();

        // Assert
        assertTrue(TourManager.numberOfCities()>0);
    }
}
