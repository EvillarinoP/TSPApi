package com.tsp.TSPApi.OLDCODE;

public class Population {

    // Holds population of tours
    private final Tour[] tours;

    // Construct a population
    public Population(int populationSize, boolean init) {
        tours = new Tour[populationSize];
        if (init) {
            init();
        }
    }

    private void init() {
        for (int i = 0; i < populationSize(); i++) {
            Tour newTour = new Tour();
            newTour.generateIndividual();
            saveTour(i, newTour);
        }
    }

    // Saves a tour
    public void saveTour(int index, Tour tour) {
        tours[index] = tour;
    }

    // Gets a tour from population
    public Tour getTour(int index) {
        return tours[index];
    }

    public int getTotalDistance() {
        int totalDistance = 0;

        for (int i = 1; i < populationSize(); i++) {
            totalDistance += getTour(i) .getDistance();
        }

        return totalDistance;
    }

    // Gets the best tour in the population
    public Tour getFittest() {
        Tour fittest = tours[0];
        // Loop through individuals to find fittest
        for (int i = 1; i < populationSize(); i++) {
            if (fittest.getFitness() <= getTour(i).getFitness()) {
                fittest = getTour(i);
            }
        }
        return fittest;
    }

    // Gets population size
    // TODO: Quitar esto. No se usa en otro sitio fuera de esta clase.
    public int populationSize() {
        return tours.length;
    }
}
