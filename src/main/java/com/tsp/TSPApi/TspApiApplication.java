package com.tsp.TSPApi;

import org.springframework.boot.SpringApplication;
import java.util.Random;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TspApiApplication {

	public static int FINAL_DISTANCE = 0;
	private final static int MAX_COORDINATE = 200;
	private final static int NUM_CITIES = 100;
	private final static int POPULATION_SIZE = 500;

	private final static double MUTATION_RATE = 0.005;
	private final static int NUMBER_GENERATIONS = 10000;

	public static void main(String[] args) {
		Random r = new Random(1);

		for (int i=1; i<=NUM_CITIES; i++) {
			int x = r.nextInt(MAX_COORDINATE);
			int y = r.nextInt(MAX_COORDINATE);
			TourManager.addCity(new City(i+"", x, y));
			System.out.println("City["+i+"] with coordinates " + x + ":" + y);
		}

		// Initialize population
		Population pop = new Population(POPULATION_SIZE, true);
		System.out.println("Initial distance: " + pop.getFittest().getDistance());

		GA geneticAlgorithm = new GA(MUTATION_RATE);

		for (int i = 1; i <= NUMBER_GENERATIONS; i++) {
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
