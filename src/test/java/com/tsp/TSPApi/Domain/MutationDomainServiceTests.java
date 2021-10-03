package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Builders.CityBuilder;
import com.tsp.TSPApi.Builders.TourBuilder;
import com.tsp.TSPApi.Entities.Domain.City;
import com.tsp.TSPApi.Entities.Domain.Tour;
import com.tsp.TSPApi.Helpers.IGeneticsHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
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
    public void ExchangeMutation_MutationHappens_CitiesAreCorrectlySwapped(){
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
        _mutationDomainService.exchangeMutation(tour);

        // Assert
        assertEquals(cityB,tour.getCity(0));
        assertEquals(cityA,tour.getCity(1));
    }

    @Test
    public void ExchangeMutation_MutationDoesNOTHappen_CitiesAreNOTSwapped(){
        // Arrange
        City cityA = new CityBuilder().withName("CityA").build();
        City cityB = new CityBuilder().withName("CityB").build();
        ArrayList<City> cities = new ArrayList<City>(){
            {
                add(cityA); add(cityB);
            }
        };

        Tour tour = new Tour(cities);

        when(_geneticsHelperMock.mutationHappened()).thenReturn(false);

        // Act
        _mutationDomainService.exchangeMutation(tour);

        // Assert
        assertEquals(cityA,tour.getCity(0));
        assertEquals(cityB,tour.getCity(1));
    }

    @Test
    public void DisplacementMutation_MutationHappens_MutationIsCorrectlyApplied(){
        // Arrange
        City cityA = new CityBuilder().withName("CityA").build();
        City cityB = new CityBuilder().withName("CityB").build();
        City cityC = new CityBuilder().withName("CityC").build();
        City cityD = new CityBuilder().withName("CityD").build();
        City cityE = new CityBuilder().withName("CityE").build();
        City cityF = new CityBuilder().withName("CityF").build();
        ArrayList<City> cities = new ArrayList<City>(){
            {
                add(cityA); add(cityB);add(cityC);add(cityD);add(cityE);add(cityF);
            }
        };

        Tour tour = new TourBuilder().withCities(cities).build();

        when(_geneticsHelperMock.mutationHappened()).thenReturn(true);
        when(_geneticsHelperMock.selectGene(anyInt())).thenReturn(1).thenReturn(4).thenReturn(1);

        // Act
        _mutationDomainService.displacementMutation(tour);

        // Assert
        assertEquals(cityA,tour.getCity(0));
        assertEquals(cityC,tour.getCity(1));
        assertEquals(cityD,tour.getCity(2));
        assertEquals(cityB,tour.getCity(3));
        assertEquals(cityE,tour.getCity(4));
        assertEquals(cityF,tour.getCity(5));
    }
}
