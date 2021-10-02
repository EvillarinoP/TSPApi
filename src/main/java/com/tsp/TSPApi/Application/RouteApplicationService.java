package com.tsp.TSPApi.Application;

import com.tsp.TSPApi.Domain.IPopulationDomainService;
import com.tsp.TSPApi.Domain.IPopulationEvolverDomainService;
import com.tsp.TSPApi.Entities.Constants;
import com.tsp.TSPApi.Entities.Population;
import com.tsp.TSPApi.Entities.RouteResponse;
import com.tsp.TSPApi.Entities.Tour;
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

    public RouteResponse calculateRouteGA(){

        _tourManagerInitializer.InitializeTourManager();

        Population population = _populationDomainService.InitializePopulation(Constants.POPULATION_SIZE);

        for(int i = 0; i < Constants.NUMBER_GENERATIONS; i++){
            population = _populationEvolverDomainService.evolvePopulation(population);
        }

        Tour fittest = _populationDomainService.getFittestTour(population);

        return new RouteResponseBuilder()
                .withCities(_populationDomainService.getFittestTour(population).getCities())
                .withFinalDistance(_populationDomainService.getFittestTour(population).getDistance())
                .build();
    }
}
