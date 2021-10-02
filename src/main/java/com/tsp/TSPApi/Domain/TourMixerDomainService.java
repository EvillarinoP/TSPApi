package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Tour;
import com.tsp.TSPApi.Helpers.IGeneSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TourMixerDomainService implements ITourMixerDomainService{

    @Autowired
    private IGeneSelector _geneSelector;

    public Tour crossover(Tour parent1, Tour parent2) {

        Tour child = new Tour();

        int startPos    = _geneSelector.selectGene(parent1.getSize());
        int endPos      = _geneSelector.selectGene(parent1.getSize());

        for (int i = 0; i < child.getSize(); i++) {

            if (i > startPos && i < endPos) {
                child.saveCity(i, parent1.getCity(i));
            }
            else if (startPos > endPos && !(i < startPos && i > endPos)) {
                child.saveCity(i, parent1.getCity(i));
            }
        }

        for (int i = 0; i < parent2.getSize(); i++) {

            if (!child.containsCity(parent2.getCity(i))) {

                for (int j = 0; j < child.getSize(); j++) {

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
