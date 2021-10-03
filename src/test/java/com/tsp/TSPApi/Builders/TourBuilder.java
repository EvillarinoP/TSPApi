package com.tsp.TSPApi.Builders;

import com.tsp.TSPApi.Entities.Domain.City;
import com.tsp.TSPApi.Helpers.IBuilder;
import com.tsp.TSPApi.Entities.Domain.Tour;

import java.util.ArrayList;

public class TourBuilder implements IBuilder<Tour> {

    Tour tour;

    public TourBuilder(){
        tour = new Tour();
    }

    public TourBuilder withFitness(double fitness){
        tour.setFitness(fitness);
        return this;
    }

    public TourBuilder withCities(ArrayList<City> cities){
        tour.setCities(cities);
        return this;
    }

    public Tour build(){
        return tour;
    }
}
