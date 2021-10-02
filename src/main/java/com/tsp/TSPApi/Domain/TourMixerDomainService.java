package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Tour;
import org.springframework.stereotype.Component;

@Component
public class TourMixerDomainService implements ITourMixerDomainService{
    public Tour crossover(Tour parent1, Tour parent2) {
        // Create new child tour
        Tour child = new Tour();

        // Get start and end sub tour positions for parent1's tour
        int startPos = (int) (Math.random() * parent1.getSize());
        int endPos = (int) (Math.random() * parent1.getSize());

        // Loop and add the sub tour from parent1 to our child
        for (int i = 0; i < child.getSize(); i++) {
            // If our start position is less than the end position
            if (startPos < endPos && i > startPos && i < endPos) {
                child.saveCity(i, parent1.getCity(i));
            } // If our start position is larger
            else if (startPos > endPos) {
                if (!(i < startPos && i > endPos)) {
                    child.saveCity(i, parent1.getCity(i));
                }
            }
        }

        // Loop through parent2's city tour
        for (int i = 0; i < parent2.getSize(); i++) {
            // If child doesn't have the city add it
            if (!child.containsCity(parent2.getCity(i))) {
                // Loop to find a spare position in the child's tour
                for (int j = 0; j < child.getSize(); j++) {
                    // Spare position found, add city
                    if (child.getCity(j) == null) {
                        child.saveCity(j, parent2.getCity(i));
                        break;
                    }
                }
            }
        }
        return child;
    }
}
