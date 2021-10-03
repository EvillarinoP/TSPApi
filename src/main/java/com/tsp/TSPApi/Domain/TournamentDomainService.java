package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;
import com.tsp.TSPApi.Helpers.IGeneticsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TournamentDomainService implements ITournamentDomainService{

    @Autowired
    private IGeneticsHelper _geneticsHelper;

    @Autowired
    private IPopulationDomainService _populationDomainService;

    public Tour[] tournament(Population population, int tournamentSize){

        if(tournamentSize <= 0){
            throw new IllegalArgumentException("Tournament size must be a positive number.");
        }

        Population tournamentPool = new Population(tournamentSize);

        int startOfPool = _geneticsHelper.selectGene(population.getSize() - tournamentSize);

        for(int i = 0; i < tournamentSize; i++){
            tournamentPool.saveTour(i, population.getTour(startOfPool + i));
        }

        return _populationDomainService.getFittestPair(tournamentPool);
    }
}
