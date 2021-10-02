package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Tour;

public interface IMutationDomainService {
    void mutate(Tour tour);
}
