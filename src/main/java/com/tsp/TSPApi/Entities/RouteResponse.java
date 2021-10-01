package com.tsp.TSPApi.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class RouteResponse {

    @Getter
    @Setter
    public List<City> cities;

    @Getter
    @Setter
    public int finalDistance;
}
