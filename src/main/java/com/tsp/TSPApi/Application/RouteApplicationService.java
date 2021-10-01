package com.tsp.TSPApi.Application;

import com.tsp.TSPApi.Domain.IPopulationDomainService;
import com.tsp.TSPApi.Entities.Constants;
import com.tsp.TSPApi.Entities.Population;
import com.tsp.TSPApi.Entities.RouteResponse;
import com.tsp.TSPApi.Helpers.ITourManagerInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteApplicationService implements IRouteApplicationService {

    @Autowired
    ITourManagerInitializer _tourManagerInitializer;

    @Autowired
    IPopulationDomainService _populationDomainService;

    public RouteResponse calculateRouteGA(){

        _tourManagerInitializer.InitializeTourManager();

        Population population = _populationDomainService.InitializePopulation(Constants.POPULATION_SIZE);

        return new RouteResponse();
    }
}
