package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Tour;

public interface ITourDomainService {
    double getFitness(Tour tour);

    int getDistance(Tour tour);

    void saveCity(int index);

    Tour generateTour();
}
