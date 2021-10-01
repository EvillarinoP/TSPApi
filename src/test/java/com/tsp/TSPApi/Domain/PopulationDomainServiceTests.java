package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.City;
import com.tsp.TSPApi.Entities.Constants;
import com.tsp.TSPApi.Entities.PopulationOld;
import com.tsp.TSPApi.Helpers.ITourManagerInitializer;
import com.tsp.TSPApi.Tour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PopulationDomainServiceTests {

    @Autowired
    private IPopulationDomainService _populationDomainService;

    @Autowired
    private ITourManagerInitializer _tourManagerInitializer;

    @BeforeEach
    public void SetUp(){
        _tourManagerInitializer.InitializeTourManager();
        _populationDomainService.InitializePopulation(Constants.POPULATION_SIZE);
    }

    @Test
    public void Init_InitIsTrue_ToursAreSaved(){
        // Assert
        assertNotNull(_populationDomainService.getTour(0));
    }

    @Test
    public void SaveTour_TourIsSavedInCorrectPosition(){
        // Arrange
        ArrayList<City> cityArray = new ArrayList<City>();
        cityArray.add(new City("NEW_CITY",1,2));
        Tour newTour = new Tour(cityArray);

        // Act
        _populationDomainService.saveTour(0,newTour);

        // Assert
        assertEquals("NEW_CITY",_populationDomainService.getTour(0).getCity(0).getName());
        assertEquals(1,_populationDomainService.getTour(0).getCity(0).getX());
        assertEquals(2,_populationDomainService.getTour(0).getCity(0).getY());
    }
}
