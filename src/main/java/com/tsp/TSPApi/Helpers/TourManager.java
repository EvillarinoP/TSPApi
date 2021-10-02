package com.tsp.TSPApi.Helpers;

import com.tsp.TSPApi.Entities.City;
import lombok.Getter;

import java.util.ArrayList;

public class TourManager {

    @Getter
    private static ArrayList destinationCities = new ArrayList<City>();

    public static void addCity(City city) {
        destinationCities.add(city);
    }

    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }

    public static int numberOfCities(){
        return destinationCities.size();
    }
}
