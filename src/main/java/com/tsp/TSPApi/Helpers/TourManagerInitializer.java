package com.tsp.TSPApi.Helpers;

import com.tsp.TSPApi.Entities.City;
import com.tsp.TSPApi.Entities.Constants;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TourManagerInitializer implements ITourManagerInitializer{

    public void InitializeTourManager(){
        Random r = new Random(1);

        for (int i = 1; i<= Constants.NUM_CITIES; i++) {
            int x = r.nextInt(Constants.MAX_COORDINATE);
            int y = r.nextInt(Constants.MAX_COORDINATE);
            TourManager.addCity(new City(i+"", x, y));
            System.out.println("City["+i+"] with coordinates " + x + ":" + y);
        }
    }
}
