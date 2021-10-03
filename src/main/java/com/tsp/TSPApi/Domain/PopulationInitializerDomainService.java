package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Population;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PopulationInitializerDomainService implements IPopulationInitializerDomainService{

    @Autowired
    private ITourDomainService _tourDomainService;

    public Population InitializePopulation(int populationSize) throws IllegalArgumentException{

        if(populationSize < 0){
            throw new IllegalArgumentException("Population size cannot be negative");
        }

        Population population = new Population(populationSize);

        for (int i = 0; i < population.getSize(); i++) {
            population.saveTour(i, _tourDomainService.generateTour());
        }

        return population;
    }
}
