package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.City;
import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;
import com.tsp.TSPApi.Entities.Domain.TourManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PopulationInitializerDomainServiceTests {

    @Autowired
    private IPopulationInitializerDomainService _populationInitializerDomainService;

    private static final int NUM_CITIES = 5;

    @BeforeAll
    public static void BeforeAll(){
        TourManager.clearCities();
        for (int i=1; i<=NUM_CITIES; i++) {
            TourManager.addCity(new City(i+"", 0, 0));
        }
    }

    @Test
    public void InitializePopulation_GivenPositivePopulationSize_PopulationHasNonEmptyTours(){
        // Arrange
        int popSize = 10;

        // Act
        Population population = _populationInitializerDomainService.InitializePopulation(popSize);

        // Assert
        for (Tour tour : population.getTours()) {
            assertTrue(tour.getSize() > 0);
        }
    }

    @Test
    public void InitializePopulation_GivenNegativePopulationSize_ThrowsIllegalArgumentException(){
        // Arrange
        int popSize = -10;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> _populationInitializerDomainService.InitializePopulation(popSize));
    }
}
