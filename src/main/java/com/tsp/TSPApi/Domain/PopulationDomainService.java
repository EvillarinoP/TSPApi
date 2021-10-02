package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PopulationDomainService implements IPopulationDomainService{

    @Autowired
    private ITourDomainService _tourDomainService;

    public Population InitializePopulation(int populationSize){
        Population population = new Population(populationSize);

        for (int i = 0; i < population.getSize(); i++) {
            population.saveTour(i, _tourDomainService.generateTour());
        }

        return population;
    }

    public int getTotalDistance(Population population) {
        int totalDistance = 0;

        for (int i = 1; i < population.getSize(); i++) {
             totalDistance += population.getTour(i).getTourDistance();
        }

        return totalDistance;
    }

    public Tour getFittestTour(Population population) {
        Tour fittest = population.getTour(0);

        for (int i = 1; i < population.getSize(); i++) {
            if (fittest.getFitness() <= population.getTour(i).getFitness()) {
                fittest = population.getTour(i);
            }
        }
        return fittest;
    }
}
