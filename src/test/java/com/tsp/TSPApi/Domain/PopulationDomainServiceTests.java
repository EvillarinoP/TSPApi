package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.City;
import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;
import com.tsp.TSPApi.Entities.Domain.TourManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PopulationDomainServiceTests {

    @Autowired
    private IPopulationDomainService _populationDomainService;

    private static final int NUM_CITIES = 5;

    @BeforeAll
    public static void BeforeAll(){
        for (int i=1; i<=NUM_CITIES; i++) {
            TourManager.addCity(new City(i+"", 0, 0));
        }
    }

    @Test
    public void InitializePopulation_GivenPositivePopulationSize_PopulationHasNonEmptyTours(){
        // Arrange
        int popSize = 10;

        // Act
        Population population = _populationDomainService.InitializePopulation(popSize);
        
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
        assertThrows(IllegalArgumentException.class, () -> _populationDomainService.InitializePopulation(popSize));
    }
}
