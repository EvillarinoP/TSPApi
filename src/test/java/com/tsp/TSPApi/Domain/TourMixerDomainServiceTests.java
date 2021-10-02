package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.City;
import com.tsp.TSPApi.Entities.Tour;
import com.tsp.TSPApi.Entities.TourManager;
import com.tsp.TSPApi.Helpers.IGeneticsHelper;
import org.junit.jupiter.api.BeforeAll;
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
public class TourMixerDomainServiceTests {

    @Mock
    private IGeneticsHelper _geneticsHelperMock;

    @Mock
    private TourManager _tourManagerMock;

    @Autowired
    @InjectMocks
    private TourMixerDomainService _tourMixerDomainService;

    private final static int NUM_CITIES = 6;

    @BeforeAll
    public static void BeforeAll(){
        for (int i=1; i<=NUM_CITIES; i++) {
            TourManager.addCity(new City(i+"", 0, 0));
        }
    }

    @BeforeEach
    public void Setup(){
        openMocks(this);
    }

    @Test
    public void Crossover_StartingGeneSmallerThanEndingGene_ParentsAreCorrectlyMerged(){
        // Arrange
        int startGene = 1;
        int endGene = 4;

        City cityA = new City("CityA",0,0);
        City cityB = new City("CityB",3,1);
        City cityC = new City("CityC",2,1);
        City cityD = new City("CityD",0,5);
        City cityE = new City("CityE",1,3);
        City cityF = new City("CityF",2,4);

        ArrayList<City> cities1 = new ArrayList<City>(){
            {
                add(cityA); add(cityB); add(cityC); add(cityD); add(cityE); add(cityF);
            }
        };

        ArrayList<City> cities2 = new ArrayList<City>(){
            {
                add(cityD); add(cityF); add(cityA); add(cityC); add(cityE); add(cityB);
            }
        };

        Tour parent1 = new Tour(cities1);
        Tour parent2 = new Tour(cities2);

        when(_geneticsHelperMock.selectGene(NUM_CITIES)).thenReturn(startGene).thenReturn(endGene);

        // Act
        Tour child = _tourMixerDomainService.crossover(parent1, parent2);

        // Assert
        assertEquals(cityF,child.getCity(0));
        assertEquals(cityA,child.getCity(1));
        assertEquals(parent1.getCity(2),child.getCity(2));
        assertEquals(parent1.getCity(3),child.getCity(3));
        assertEquals(cityE,child.getCity(4));
        assertEquals(cityB,child.getCity(5));
    }

    @Test
    public void Crossover_StartingGeneGreaterThanEndingGene_ParentsAreCorrectlyMerged(){
        // Arrange
        int startGene = 4;
        int endGene = 1;

        City cityA = new City("CityA",0,0);
        City cityB = new City("CityB",3,1);
        City cityC = new City("CityC",2,1);
        City cityD = new City("CityD",0,5);
        City cityE = new City("CityE",1,3);
        City cityF = new City("CityF",2,4);

        ArrayList<City> cities1 = new ArrayList<City>(){
            {
                add(cityA); add(cityB); add(cityC); add(cityD); add(cityE); add(cityF);
            }
        };

        ArrayList<City> cities2 = new ArrayList<City>(){
            {
                add(cityD); add(cityF); add(cityA); add(cityC); add(cityE); add(cityB);
            }
        };

        Tour parent1 = new Tour(cities1);
        Tour parent2 = new Tour(cities2);

        when(_geneticsHelperMock.selectGene(NUM_CITIES)).thenReturn(startGene).thenReturn(endGene);

        // Act
        Tour child = _tourMixerDomainService.crossover(parent1, parent2);

        // Assert
        assertEquals(parent1.getCity(0),child.getCity(0));
        assertEquals(cityB,child.getCity(1));
        assertEquals(cityD,child.getCity(2));
        assertEquals(cityC,child.getCity(3));
        assertEquals(cityE,child.getCity(4));
        assertEquals(parent1.getCity(5),child.getCity(5));
    }
}
