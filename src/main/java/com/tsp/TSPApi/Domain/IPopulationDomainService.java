package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Population;
import com.tsp.TSPApi.Tour;

public interface IPopulationDomainService {
    Population InitializePopulation(int populationSize);

    Tour getTour(int index);

    void saveTour(int index, Tour tour);

    Tour getFittestTour();

    int getTotalDistance();
}
