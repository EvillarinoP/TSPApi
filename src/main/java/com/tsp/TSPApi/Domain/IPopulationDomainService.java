package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;

import java.util.ArrayList;

public interface IPopulationDomainService {

    Tour getFittestTour(Population population);

    ArrayList<Tour> getFittestIndividuals(Population population, int numberOfFittestIndividuals);
}
