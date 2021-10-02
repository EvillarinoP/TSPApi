package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Population;

public interface IPopulationEvolverDomainService {

    Population evolvePopulation(Population population);
}
