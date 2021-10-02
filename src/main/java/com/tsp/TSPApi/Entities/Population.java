package com.tsp.TSPApi.Entities;

import lombok.Getter;
import lombok.Setter;

public class Population {

    @Getter
    @Setter
    private Tour[] tours;

    public Population(int populationSize){

        this.tours = new Tour[populationSize];
    }

    public int getSize(){
        return this.tours.length;
    }

    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }

    public Tour getTour(int index){
        return tours[index];
    }
}
