package com.tsp.TSPApi.Application;

import com.tsp.TSPApi.Domain.IMutationDomainService;
import com.tsp.TSPApi.Domain.IPopulationDomainService;
import com.tsp.TSPApi.Domain.IPopulationEvolverDomainService;
import com.tsp.TSPApi.Entities.Constants;
import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.DTOs.RouteResponse;
import com.tsp.TSPApi.Helpers.ITourManagerInitializer;
import com.tsp.TSPApi.Helpers.RouteResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteApplicationService implements IRouteApplicationService {

    @Autowired
    ITourManagerInitializer _tourManagerInitializer;

    @Autowired
    IPopulationDomainService _populationDomainService;

    @Autowired
    IPopulationEvolverDomainService _populationEvolverDomainService;

    @Autowired
    IMutationDomainService _mutationDomainService;

    public RouteResponse calculateRouteGA(){

        _tourManagerInitializer.InitializeTourManager();

        Population population = _populationDomainService.InitializePopulation(Constants.POPULATION_SIZE);

        for(int i = 0; i < Constants.NUMBER_GENERATIONS; i++){
            population = _populationEvolverDomainService.evolvePopulation(population,Constants.NUMBER_OF_ELITE_INDIVIDUALS);

            for (int j = 0; j < population.getSize(); j++) {
                _mutationDomainService.mutate(population.getTour(j));
            }
        }

        return new RouteResponseBuilder()
                .withCities(_populationDomainService.getFittestTour(population).getCities())
                .withFinalDistance(_populationDomainService.getFittestTour(population).getTourDistance())
                .build();
    }
}
