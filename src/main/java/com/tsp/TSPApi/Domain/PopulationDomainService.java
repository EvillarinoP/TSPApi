package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.Population;
import com.tsp.TSPApi.Entities.Domain.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PopulationDomainService implements IPopulationDomainService{

    @Autowired
    private ITourDomainService _tourDomainService;

    public Population InitializePopulation(int populationSize) throws IllegalArgumentException{

        if(populationSize < 0){
            throw new IllegalArgumentException("Population size cannot be negative");
        }

        Population population = new Population(populationSize);

        for (int i = 0; i < population.getSize(); i++) {
            population.saveTour(i, _tourDomainService.generateTour());
        }

        return population;
    }

    public int getTotalDistance(Population population) {
        int totalDistance = 0;

        for (int i = 1; i < population.getSize(); i++) {
             totalDistance += population.getTour(i).getTourDistance();
        }

        return totalDistance;
    }

    public Tour getFittestTour(Population population) {
        Tour fittest = population.getTour(0);

        for (int i = 1; i < population.getSize(); i++) {
            if (fittest.getFitness() <= population.getTour(i).getFitness()) {
                fittest = population.getTour(i);
            }
        }
        return fittest;
    }

    public ArrayList<Tour> getFittestIndividuals(Population population, int numberOfFittestIndividuals){
        if(numberOfFittestIndividuals > population.getSize()){
            throw new IllegalArgumentException("The number of fittest individuals is bigger than the population size.");
        }

        ArrayList<Tour> fittestIndividuals = new ArrayList<>();
        for (int i = 0; i < numberOfFittestIndividuals; i++) {
            fittestIndividuals.add(population.getTour(i));
        }

        for(int i = numberOfFittestIndividuals; i < population.getSize(); i++){
            Tour leastFittedTour = getLeastFittedTour(fittestIndividuals);
            if(population.getTour(i).getFitness() > leastFittedTour.getFitness()){
                fittestIndividuals.set(fittestIndividuals.indexOf(leastFittedTour),population.getTour(i));
            }
        }

        return fittestIndividuals;
    }

    private Tour getLeastFittedTour(ArrayList<Tour> tours){

        double minFitness = tours.get(0).getFitness();
        int minIndex = 0;

        for(int i = 1; i < tours.size(); i++){
            if(tours.get(i).getFitness() < minFitness){
                minFitness = tours.get(i).getFitness();
                minIndex = i;
            }
        }
        return tours.get(minIndex);
    }
}
