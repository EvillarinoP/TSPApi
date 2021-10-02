package com.tsp.TSPApi.Entities;

import com.tsp.TSPApi.Entities.City;
import lombok.Getter;

import java.util.ArrayList;

public class TourManager {

    @Getter
    private static ArrayList<City> destinationCities = new ArrayList<>();

    public static void addCity(City city) {
        destinationCities.add(city);
    }

    public static City getCity(int index){
        return destinationCities.get(index);
    }

    public static int numberOfCities(){
        return destinationCities.size();
    }
}