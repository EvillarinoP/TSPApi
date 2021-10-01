package com.tsp.TSPApi.Entities;

import com.tsp.TSPApi.Tour;
import lombok.Getter;
import lombok.Setter;

public class Population {

    @Getter
    @Setter
    private Tour[] tours;

    public Population(int populationSize){

        this.tours = new Tour[populationSize];
    }
}
