package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PopulationEvolverDomainService implements IPopulationEvolverDomainService {

    @Autowired
    private IPopulationDomainService _populationDomainService;

    @Autowired
    private ITourMixerDomainService _tourMixerDomainService;

    @Autowired
    private IMutationDomainService _mutationDomainService;

    public Population evolvePopulation(Population population){
        Population newPopulation = new Population(population.getSize());

        for (int i = 0; i < newPopulation.getSize(); i++) {

            Random r = new Random();
            Tour parent1 = population.getTour(r.nextInt(population.getSize()));
            Tour parent2 = population.getTour(r.nextInt(population.getSize()));

            Tour child = _tourMixerDomainService.crossover(parent1, parent2);

            newPopulation.saveTour(i, child);
        }

        return newPopulation;
    }
}
