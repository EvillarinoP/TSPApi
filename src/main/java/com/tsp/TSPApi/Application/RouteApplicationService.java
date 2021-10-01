package com.tsp.TSPApi.Application;

import com.tsp.TSPApi.Entities.RouteResponse;
import com.tsp.TSPApi.Helpers.ITourManagerInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteApplicationService implements IRouteApplicationService {

    @Autowired
    ITourManagerInitializer _tourManagerInitializer;

    public RouteResponse calculateRouteGA(){

        _tourManagerInitializer.InitializeTourManager();

        return new RouteResponse();
    }
}
