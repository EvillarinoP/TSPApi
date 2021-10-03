package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Constants;
import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PopulationEvolverDomainService implements IPopulationEvolverDomainService {

    @Autowired
    private IPopulationDomainService _populationDomainService;

    @Autowired
    private ITourMixerDomainService _tourMixerDomainService;

    @Autowired
    private ITournamentDomainService _tournamentDomainService;

    public Population evolvePopulation(Population population, int numberOfEliteIndividuals){
        Population newPopulation = new Population(population.getSize());

        if(numberOfEliteIndividuals > 0){
            ArrayList<Tour> eliteIndividuals = _populationDomainService.getFittestIndividuals(population,numberOfEliteIndividuals);

            for(int i = 0; i < numberOfEliteIndividuals; i++){
                newPopulation.saveTour(i,eliteIndividuals.get(i));
            }
        }

        for (int i = numberOfEliteIndividuals; i < newPopulation.getSize(); i++) {

            ArrayList<Tour> parents = _tournamentDomainService.tournament(population,Constants.TOURNAMENT_SIZE);

            Tour child = _tourMixerDomainService.crossover(parents.get(0), parents.get(1));

            newPopulation.saveTour(i, child);
        }

        return newPopulation;
    }
}
