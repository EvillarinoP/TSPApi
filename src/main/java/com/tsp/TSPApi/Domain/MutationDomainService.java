package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.City;
import com.tsp.TSPApi.Entities.Tour;
import com.tsp.TSPApi.Helpers.IGeneticsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MutationDomainService implements IMutationDomainService{

    @Autowired
    private IGeneticsHelper _geneticsHelper;

    public void mutate(Tour tour) {

        for(int tourPos1=0; tourPos1 < tour.getSize(); tourPos1++){

            if(_geneticsHelper.mutationHappened()){

                int tourPos2 = _geneticsHelper.selectGeneToMutate(tour.getSize());

                City city1 = tour.getCity(tourPos1);
                City city2 = tour.getCity(tourPos2);

                tour.saveCity(tourPos2, city1);
                tour.saveCity(tourPos1, city2);
            }
        }
    }
}
