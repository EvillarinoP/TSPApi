package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Tour;

public interface IMutationDomainService {
    void exchangeMutation(Tour tour);

    void displacementMutation(Tour tour);

    void inversionMutation(Tour tour);
}
