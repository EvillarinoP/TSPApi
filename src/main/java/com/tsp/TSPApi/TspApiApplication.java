package com.tsp.TSPApi;

import com.tsp.TSPApi.Entities.City;

import java.util.Random;

import com.tsp.TSPApi.Entities.Constants;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TspApiApplication {

	public static int FINAL_DISTANCE = 0;

	public static void main(String[] args) {

		// Initialize population
		Population pop = new Population(Constants.POPULATION_SIZE, true);
		System.out.println("Initial distance: " + pop.getFittest().getDistance());

		GA geneticAlgorithm = new GA(Constants.MUTATION_RATE);

		for (int i = 1; i <= Constants.NUMBER_GENERATIONS; i++) {
			pop = geneticAlgorithm.evolvePopulation(pop);
			System.out.println("Generation " + i + ": shortest distance within population = " + pop.getFittest().getDistance() + ".  aggregate distance = " + pop.getTotalDistance());
		}

		// Print final results
		FINAL_DISTANCE = pop.getFittest().getDistance();
		System.out.println("Finished");
		System.out.println("Final distance: " + FINAL_DISTANCE);
		System.out.println("Solution:");
		System.out.println(pop.getFittest());

		//SpringApplication.run(TspApiApplication.class, args);
	}

}
