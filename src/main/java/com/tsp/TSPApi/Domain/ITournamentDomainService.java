package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;

import java.util.ArrayList;

public interface ITournamentDomainService {
    ArrayList<Tour> tournament(Population population, int tournamentSize);
}
