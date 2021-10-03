package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Builders.TourBuilder;
import com.tsp.TSPApi.Entities.Constants;
import com.tsp.TSPApi.Entities.Domain.City;
import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;
import com.tsp.TSPApi.Helpers.IGeneticsHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

@SpringBootTest
public class TournamentDomainServiceTests {

    @Mock
    private ITourMixerDomainService _tourMixerDomainServiceMock;

    @Mock
    private IGeneticsHelper _geneticsHelperMock;

    @Autowired
    @InjectMocks
    private TournamentDomainService _tournamentDomainService;

    @BeforeEach
    public void Setup(){
        openMocks(this);
    }

    @Test
    public void Tournament_GivenCorrectTournamentSize_SelectsFittestParents(){
        // Arrange
        int populationSize = 5;
        int tournamentSize = 3;

        Population population = new Population(populationSize);
        population.saveTour(0,new TourBuilder().withFitness(0.8).build());
        population.saveTour(1,new TourBuilder().withFitness(0.3).build());
        population.saveTour(2,new TourBuilder().withFitness(0.2).build());
        population.saveTour(3,new TourBuilder().withFitness(0.1).build());
        population.saveTour(4,new TourBuilder().withFitness(0.5).build());

        when(_geneticsHelperMock.selectGene(anyInt())).thenReturn(1);

        // Act
        ArrayList<Tour> parents = _tournamentDomainService.tournament(population,tournamentSize);

        // Assert
        assertEquals(population.getTour(1),parents.get(0));
        assertEquals(population.getTour(2),parents.get(1));
    }

    @Test
    public void Tournament_GivenIncorrectTournamentSize_ThrowsIllegalArgumentException(){
        // Arrange
        int tournamentSize = -1;
        Population population = new Population(1);

        // Act && Assert
        assertThrows(IllegalArgumentException.class,() -> _tournamentDomainService.tournament(population,tournamentSize));
    }
}
