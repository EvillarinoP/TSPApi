package com.tsp.TSPApi.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class RouteResponse {

    @Getter
    @Setter
    private int finalDistance;

    @Getter
    @Setter
    private ArrayList<City> cities;
}
