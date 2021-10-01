package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.City;
import com.tsp.TSPApi.Helpers.TourManager;
import com.tsp.TSPApi.Tour;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class TourDomainService implements ITourDomainService{

    @Getter
    private ArrayList<City> tour;

    public double getFitness(Tour tour) {
        return 1/(double)getDistance(tour);
    }

    public int getDistance(Tour tour){

        int tourDistance = 0;
        // Loop through our tour's cities
        for (int cityIndex=0; cityIndex < tour.getSize(); cityIndex++) {
            // Get city we're travelling from
            City fromCity = tour.getCity(cityIndex);
            // City we're travelling to
            City destinationCity;
            // Check we're not on our tour's last city, if we are set our
            // tour's final destination city to our starting city
            if(cityIndex+1 < tour.getSize()){
                destinationCity = tour.getCity(cityIndex+1);
            }
            else{
                destinationCity = tour.getCity(0);
            }
            // Get the distance between the two cities
            tourDistance += fromCity.distanceTo(destinationCity);
        }

        return tourDistance;
    }

    public Tour generateTour(){
        ArrayList<City> cities = new ArrayList<City>();
        for(int i = 0; i < TourManager.numberOfCities(); i++){
            cities.add(TourManager.getCity(i));
        }
        Collections.shuffle(cities);
        return new Tour(cities);
    }

    public void saveCity(int index){
        this.tour.set(index,TourManager.getCity(index));
    }
}
