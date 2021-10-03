package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Population;

public interface IPopulationInitializerDomainService {
    Population InitializePopulation(int populationSize);
}
