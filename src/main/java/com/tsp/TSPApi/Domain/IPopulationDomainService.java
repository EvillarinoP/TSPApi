package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Population;
import com.tsp.TSPApi.Entities.Tour;

public interface IPopulationDomainService {
    Population InitializePopulation(int populationSize);

    Tour getFittestTour(Population population);

    int getTotalDistance(Population population);
}
