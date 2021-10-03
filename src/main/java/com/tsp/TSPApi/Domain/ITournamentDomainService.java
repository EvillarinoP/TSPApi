package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;

public interface ITournamentDomainService {
    Tour[] tournament(Population population, int tournamentSize);
}
