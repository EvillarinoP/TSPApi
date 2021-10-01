package com.tsp.TSPApi.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class RouteResponse {

    @Getter
    @Setter
    private int finalDistance;

    @Getter
    @Setter
    private List<City> cities;
}
