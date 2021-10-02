package com.tsp.TSPApi.Entities.DTOs;

import com.tsp.TSPApi.Entities.Domain.City;
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
