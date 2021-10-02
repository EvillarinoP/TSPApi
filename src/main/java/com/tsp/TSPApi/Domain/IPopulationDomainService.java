package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;

public interface IPopulationDomainService {
    Population InitializePopulation(int populationSize);

    Tour getFittestTour(Population population);

    int getTotalDistance(Population population);
}
