package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Builders.PopulationBuilder;
import com.tsp.TSPApi.Entities.Domain.City;
import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;
import com.tsp.TSPApi.Entities.Domain.TourManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PopulationDomainServiceTests {

    @Autowired
    private IPopulationDomainService _populationDomainService;

    private static final int NUM_CITIES = 5;

    @BeforeAll
    public static void BeforeAll(){
        TourManager.clearCities();
        for (int i=1; i<=NUM_CITIES; i++) {
            TourManager.addCity(new City(i+"", 0, 0));
        }
    }

    @Test
    public void GetFittestIndividuals_GivenACorrectNumberOfEliteIndividuals_ReturnsTheFittestIndividuals(){
        // Arrange
        int numberOfEliteIndividuals = 2;
        Population population = new PopulationBuilder().build();

        // Act
        ArrayList<Tour> eliteIndividuals = _populationDomainService.getFittestIndividuals(population,numberOfEliteIndividuals);

        // Assert
        assertTrue(eliteIndividuals.stream().anyMatch(x -> x == population.getTour(2)));
        assertTrue(eliteIndividuals.stream().anyMatch(x -> x == population.getTour(4)));
    }
}
