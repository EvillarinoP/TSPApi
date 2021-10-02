package com.tsp.TSPApi.Entities;

import com.tsp.TSPApi.Entities.City;
import com.tsp.TSPApi.Helpers.TourManager;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Tour {

    @Getter
    private ArrayList<City> cities;

    @Getter
    @Setter
    private double fitness = 0;

    @Getter
    @Setter
    private int distance = 0;

    public Tour(){

        cities = new ArrayList<City>(TourManager.numberOfCities());
    }

    public Tour(ArrayList<City> cities){

        this.cities = cities;
    }

    public City getCity(int tourPosition) {

        return cities.get(tourPosition);
    }

    public int getSize() {
        return cities.size();
    }

    public boolean containsCity(City city){
        return cities.contains(city);
    }

    public double getFitness() {
        if(fitness==0){
            fitness = 1/(double)getTourDistance();
        }
        return fitness;
    }

    public int getTourDistance(){
        if(distance==0) {
            int tourDistance = 0;

            for (int cityIndex = 0; cityIndex < getSize(); cityIndex++) {

                City fromCity = getCity(cityIndex);
                City destinationCity;

                destinationCity = cityIndex + 1 < getSize() ?
                                    getCity(cityIndex + 1) :
                                    getCity(0);

                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }

        return distance;
    }


    public void saveCity(int index, City city){
        this.cities.set(index,city);
    }

    @Override
    // TODO: Esto seguramente ya no sea necesario
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < cities.size(); i++) {
            geneString += getCity(i)+"|";
        }
        return geneString;
    }
}
