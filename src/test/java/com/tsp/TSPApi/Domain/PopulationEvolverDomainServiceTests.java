package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Constants;
import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;
import com.tsp.TSPApi.Entities.Domain.TourManager;
import com.tsp.TSPApi.Helpers.ITourManagerInitializer;
import com.tsp.TSPApi.Helpers.TourManagerInitializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class PopulationEvolverDomainServiceTests {

    @Autowired
    private static ITourManagerInitializer _tourManagerInitializer;

    @Autowired
    private IPopulationDomainService _populationDomainService;

    @Autowired
    @InjectMocks
    private PopulationEvolverDomainService _populationEvolverDomainService;

    @BeforeAll
    public static void Initialize(){
        TourManager.clearCities();
        _tourManagerInitializer = new TourManagerInitializer();
        _tourManagerInitializer.InitializeTourManager();
    }

    @BeforeEach
    public void Setup(){
        openMocks(this);
    }

    @Test
    public void EvolvePopulation_HappyPath_ReturnsNewPopulationWithNonEmptyTours(){
        // Arrange
        Population population = _populationDomainService.InitializePopulation(Constants.POPULATION_SIZE);

        // Act
        for(int i = 0; i < Constants.NUMBER_GENERATIONS; i++){
            population = _populationEvolverDomainService.evolvePopulation(population);
        }

        // Assert
        for(int i = 0; i < population.getSize(); i++){
            assertTrue(population.getTour(i).getSize() > 0);
        }
    }
}
