package com.tsp.TSPApi.OLDCODE;

import com.tsp.TSPApi.Entities.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GATests {

    @Test
    public void GA_HappyPath(){
        // Arrange
        Random r = new Random(1);

        for (int i=1; i<=Constants.NUM_CITIES; i++) {
            int x = r.nextInt(Constants.MAX_COORDINATE);
            int y = r.nextInt(Constants.MAX_COORDINATE);
            TourManager.addCity(new City(i+"", x, y));
            System.out.println("City["+i+"] with coordinates " + x + ":" + y);
        }

        Population pop = new Population(Constants.POPULATION_SIZE,true);
        GA geneticAlgorithm = new GA(Constants.MUTATION_RATE);

        // Act
        for (int i = 1; i <= Constants.NUMBER_GENERATIONS; i++) {
            pop = geneticAlgorithm.evolvePopulation(pop);
        }

        // Assert
        for(int i = 0; i < pop.populationSize(); i++){
            assertTrue(pop.getTour(i).tourSize() > 0);

        }
    }
}
