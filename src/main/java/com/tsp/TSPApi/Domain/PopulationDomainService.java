package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Population;
import com.tsp.TSPApi.Tour;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PopulationDomainService implements IPopulationDomainService{

    @Autowired
    private ITourDomainService _tourDomainService;

    @Getter
    private Population population;

    public Population InitializePopulation(int populationSize){
        population = new Population(populationSize);

        for (int i = 0; i < populationSize(); i++) {
            saveTour(i, _tourDomainService.generateTour());
        }

        return population;
    }

    public void saveTour(int index, Tour tour) {
        population.getTours()[index] = tour;
    }

    public Tour getTour(int index){
        return population.getTours()[index];
    }

    public int getTotalDistance() {
        int totalDistance = 0;

        for (int i = 1; i < populationSize(); i++) {
            // TODO: totalDistance += getTour(i).getDistance();
        }

        return totalDistance;
    }

    public Tour getFittestTour() {
        Tour fittest = getTour(0);

        for (int i = 1; i < populationSize(); i++) {
            if (_tourDomainService.getFitness(fittest) <= _tourDomainService.getFitness(getTour(i))) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    private int populationSize() {
        return population.getTours().length;
    }
}
