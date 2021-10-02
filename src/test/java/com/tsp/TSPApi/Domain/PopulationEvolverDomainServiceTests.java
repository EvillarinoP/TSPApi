package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Constants;
import com.tsp.TSPApi.Entities.Population;
import com.tsp.TSPApi.Helpers.ITourManagerInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PopulationEvolverDomainServiceTests {

    @Autowired
    private ITourManagerInitializer _tourManagerInitializer;

    @Autowired
    private IPopulationDomainService _populationDomainService;

    @Autowired
    private IPopulationEvolverDomainService _populationEvolverDomainService;

    @Test
    public void HappyPath(){
        // Arrange
        _tourManagerInitializer.InitializeTourManager();

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
