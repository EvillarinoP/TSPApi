package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Domain.City;
import com.tsp.TSPApi.Entities.Domain.Tour;
import com.tsp.TSPApi.Helpers.IGeneticsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MutationDomainService implements IMutationDomainService{

    @Autowired
    private IGeneticsHelper _geneticsHelper;

    public void exchangeMutation(Tour tour) {

        for(int tourPos1=0; tourPos1 < tour.getSize(); tourPos1++){

            if(_geneticsHelper.mutationHappened()){

                int tourPos2 = _geneticsHelper.selectGene(tour.getSize());

                City city1 = tour.getCity(tourPos1);
                City city2 = tour.getCity(tourPos2);

                tour.saveCity(tourPos2, city1);
                tour.saveCity(tourPos1, city2);
            }
        }
    }

    public void displacementMutation(Tour tour){

        int startGene = _geneticsHelper.selectGene(tour.getSize());
        int endGene = _geneticsHelper.selectGene(tour.getSize());

        if(startGene > endGene){
            int aux = startGene;
            startGene = endGene;
            endGene = aux;
        }

        ArrayList<City> subRoute1 = new ArrayList<>();
        ArrayList<City> subRoute2 = new ArrayList<>();

        for (int i = 0; i < tour.getSize(); i++) {
            if (i > startGene && i < endGene) {
                subRoute1.add(tour.getCity(i));
            }
            else {
                subRoute2.add(tour.getCity(i));
            }
        }

        int displacementGene = _geneticsHelper.selectGene(subRoute1.size());
        int insertedElements = 0;

        for(int i = 0; i < tour.getSize(); i++){

            if(i < displacementGene || i >= displacementGene + subRoute1.size()){
                tour.saveCity(i,subRoute2.get(i-insertedElements));
            }
            else{
                for(int j = 0; j < subRoute1.size(); j++){
                    tour.saveCity(i+j,subRoute1.get(j));
                }
                insertedElements = subRoute1.size();
                i += subRoute1.size() - 1;
            }
        }
    }
}
