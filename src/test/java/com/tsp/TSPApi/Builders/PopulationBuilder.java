package com.tsp.TSPApi.Builders;

import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Helpers.IBuilder;

public class PopulationBuilder implements IBuilder<Population> {

    Population population;

    public PopulationBuilder(){
        population = defaultPopulation();
    }

    public Population build(){
        return population;
    }

    private Population defaultPopulation(){

        int populationSize = 5;

        Population defaultPopulation = new Population(populationSize);
        defaultPopulation.saveTour(0,new TourBuilder().withFitness(0.1).build());
        defaultPopulation.saveTour(1,new TourBuilder().withFitness(0.3).build());
        defaultPopulation.saveTour(2,new TourBuilder().withFitness(0.8).build());
        defaultPopulation.saveTour(3,new TourBuilder().withFitness(0.1).build());
        defaultPopulation.saveTour(4,new TourBuilder().withFitness(0.5).build());

        return defaultPopulation;
    }
}
