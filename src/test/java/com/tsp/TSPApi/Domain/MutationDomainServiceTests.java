package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.City;
import com.tsp.TSPApi.Entities.Tour;
import com.tsp.TSPApi.Helpers.IGeneticsHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class MutationDomainServiceTests {

    @Mock
    private IGeneticsHelper _geneticsHelperMock;

    @Autowired
    @InjectMocks
    private MutationDomainService _mutationDomainService;

    @BeforeEach
    public void Setup(){
        openMocks(this);
    }

    @Test
    public void Mutate_MutationHappens_CitiesAreCorrectlySwapped(){
        // Arrange
        City cityA = new City("CityA",0,0);
        City cityB = new City("CityB",3,1);
        ArrayList<City> cities = new ArrayList<City>(){
            {
                add(cityA); add(cityB);
            }
        };

        Tour tour = new Tour(cities);

        when(_geneticsHelperMock.mutationHappened()).thenReturn(true);

        // Act
        _mutationDomainService.mutate(tour);

        // Assert
        assertEquals(cityB,tour.getCity(0));
        assertEquals(cityA,tour.getCity(1));
    }

    @Test
    public void Mutate_MutationDoesNOTHappen_CitiesAreNOTSwapped(){
        // Arrange
        // TODO: crear builders??
        City cityA = new City("CityA",0,0);
        City cityB = new City("CityB",3,1);
        ArrayList<City> cities = new ArrayList<City>(){
            {
                add(cityA); add(cityB);
            }
        };

        Tour tour = new Tour(cities);

        when(_geneticsHelperMock.mutationHappened()).thenReturn(false);

        // Act
        _mutationDomainService.mutate(tour);

        // Assert
        assertEquals(cityA,tour.getCity(0));
        assertEquals(cityB,tour.getCity(1));
    }
}
