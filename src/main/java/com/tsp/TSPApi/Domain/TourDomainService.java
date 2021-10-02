package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.City;
import com.tsp.TSPApi.Entities.TourManager;
import com.tsp.TSPApi.Entities.Tour;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

@Component
public class TourDomainService implements ITourDomainService{

    @Getter
    private ArrayList<City> tour;

    public Tour generateTour(){
        ArrayList<City> cities = new ArrayList<>();
        for(int i = 0; i < TourManager.numberOfCities(); i++){
            cities.add(TourManager.getCity(i));
        }
        Collections.shuffle(cities);
        return new Tour(cities);
    }
}
