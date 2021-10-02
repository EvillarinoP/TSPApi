package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.City;
import com.tsp.TSPApi.Entities.Constants;
import com.tsp.TSPApi.Entities.Tour;
import org.springframework.stereotype.Component;

@Component
public class MutationDomainService implements IMutationDomainService{

    public void mutate(Tour tour) {
        // Loop through tour cities
        for(int tourPos1=0; tourPos1 < tour.getSize(); tourPos1++){
            // Apply mutation rate
            if(Math.random() < Constants.MUTATION_RATE){
                // Get a second random position in the tour
                int tourPos2 = (int) (tour.getSize() * Math.random());

                // Get the cities at target position in tour
                City city1 = tour.getCity(tourPos1);
                City city2 = tour.getCity(tourPos2);

                // Swap them around
                tour.saveCity(tourPos2, city1);
                tour.saveCity(tourPos1, city2);
            }
        }
    }
}
