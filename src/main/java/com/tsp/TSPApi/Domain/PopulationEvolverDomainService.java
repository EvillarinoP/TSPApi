package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Population;
import com.tsp.TSPApi.Entities.Tour;
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
            // Select parents
            Random r = new Random();
            Tour parent1 = population.getTour(r.nextInt(population.getSize()));
            Tour parent2 = population.getTour(r.nextInt(population.getSize()));
            // Crossover parents
            Tour child = _tourMixerDomainService.crossover(parent1, parent2);
            // Add child to new population
            newPopulation.saveTour(i, child);
        }

        // Mutate the new population a bit to add some new genetic material
        //TODO: Sacar este uso al application service???
        for (int i = 0; i < newPopulation.getSize(); i++) {
            _mutationDomainService.mutate(newPopulation.getTour(i));
        }

        return newPopulation;
    }
}
