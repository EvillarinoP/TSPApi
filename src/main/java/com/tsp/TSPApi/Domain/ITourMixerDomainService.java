package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Tour;

public interface ITourMixerDomainService {
    Tour crossover(Tour parent1, Tour parent2);
}
