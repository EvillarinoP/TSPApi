package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Population;

public interface IPopulationEvolverDomainService {

    Population evolvePopulation(Population population);
}
