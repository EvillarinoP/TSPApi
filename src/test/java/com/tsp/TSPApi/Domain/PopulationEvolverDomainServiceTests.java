package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Builders.PopulationBuilder;
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

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class PopulationEvolverDomainServiceTests {

    @Mock
    private ITournamentDomainService _tournamentDomainServiceMocks;

    @Mock
    private ITourMixerDomainService _tourMixerDomainServiceMock;

    @Autowired
    private static ITourManagerInitializer _tourManagerInitializer;

    @Mock
    private IPopulationDomainService _populationDomainServiceMock;

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
    public void EvolvePopulation_PositiveNumberOfEliteIndividuals_ElitismIsCorrectlyApplied(){
        // Arrange
        int numberOfEliteIndividuals = 2;
        Population population = new PopulationBuilder().build();
        ArrayList<Tour> parents = new ArrayList<Tour>(){
            {
                add(new Tour());
                add(new Tour());
            }
        };

        ArrayList<Tour> fittestIndividuals = new ArrayList<Tour>(){
            {
                add(population.getTour(2));
                add(population.getTour(4));
            }
        };

        when(_tournamentDomainServiceMocks.tournament(any(Population.class),anyInt())).thenReturn(parents);
        when(_tourMixerDomainServiceMock.crossover(any(Tour.class),any(Tour.class))).thenReturn(new Tour());
        when(_populationDomainServiceMock.getFittestIndividuals(population, numberOfEliteIndividuals)).thenReturn(fittestIndividuals);

        // Act
        Population newPopulation = _populationEvolverDomainService.evolvePopulation(population,numberOfEliteIndividuals);

        // Assert
        // Elite individuals are the first individuals of the new generation.
        assertEquals(population.getTour(2),newPopulation.getTour(0));
        assertEquals(population.getTour(4),newPopulation.getTour(1));
    }

    @Test
    public void EvolvePopulation_ZeroEliteIndividuals_ElitismIsNotApplied(){
        // Arrange
        int numberOfEliteIndividuals = 0;
        Population population = new PopulationBuilder().build();
        ArrayList<Tour> parents = new ArrayList<Tour>(){
            {
                add(new Tour());
                add(new Tour());
            }
        };

        when(_tournamentDomainServiceMocks.tournament(any(Population.class),anyInt())).thenReturn(parents);
        when(_tourMixerDomainServiceMock.crossover(any(Tour.class),any(Tour.class))).thenReturn(new Tour());

        // Act
        Population newPopulation = _populationEvolverDomainService.evolvePopulation(population,numberOfEliteIndividuals);

        // Assert
        verify(_populationDomainServiceMock,times(0)).getFittestIndividuals(population,0);
    }
}
