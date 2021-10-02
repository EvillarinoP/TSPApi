package com.tsp.TSPApi.Helpers;

import com.tsp.TSPApi.Entities.City;
import com.tsp.TSPApi.Entities.RouteResponse;

import java.util.ArrayList;

public class RouteResponseBuilder implements IBuilder<RouteResponse>{

    RouteResponse routeResponse;

    public RouteResponseBuilder(){
        routeResponse = new RouteResponse();
    }

    public RouteResponseBuilder withFinalDistance(int finalDistance){
        routeResponse.setFinalDistance(finalDistance);
        return this;
    }

    public RouteResponseBuilder withCities(ArrayList<City> cities){
        routeResponse.setCities(cities);
        return this;
    }

    @Override
    public RouteResponse build(){
        return routeResponse;
    }
}
