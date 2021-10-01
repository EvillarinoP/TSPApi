package com.tsp.TSPApi.Helpers;

import com.tsp.TSPApi.Entities.City;
import lombok.Getter;

import java.util.ArrayList;

public class TourManager {

    // Holds our cities
    @Getter
    private static ArrayList destinationCities = new ArrayList<City>();

    // Adds a destination city
    public static void addCity(City city) {
        destinationCities.add(city);
    }

    // Get a city
    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }

    // Get the number of destination cities
    public static int numberOfCities(){
        return destinationCities.size();
    }
}
