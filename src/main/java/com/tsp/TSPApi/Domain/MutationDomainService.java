package com.tsp.TSPApi.Domain;

import com.tsp.TSPApi.Entities.Constants;
import com.tsp.TSPApi.Entities.Domain.City;
import com.tsp.TSPApi.Entities.Domain.Tour;
import com.tsp.TSPApi.Helpers.IGeneticsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MutationDomainService implements IMutationDomainService{

    @Autowired
    private IGeneticsHelper _geneticsHelper;

    public void mutate(Tour tour){

        if(Constants.APPLY_EXCHANGE_MUTATION){
            exchangeMutation(tour);
        }

        if(Constants.APPLY_DISPLACEMENT_MUTATION){
            displacementMutation(tour);
        }

        if(Constants.APPLY_INVERSION_MUTATION){
            inversionMutation(tour);
        }
    }

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

        for(int startGene = 0; startGene < tour.getSize(); startGene++){
            if(!_geneticsHelper.mutationHappened()){
                continue;
            }

            int endGene = _geneticsHelper.selectGene(tour.getSize());

            if(startGene > endGene){
                swapGenes(startGene,endGene);
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

            int displacementGene = _geneticsHelper.selectGene(subRoute2.size()-1);
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

    public void inversionMutation(Tour tour){

        for(int startGene = 0; startGene < tour.getSize(); startGene++) {
            if(!_geneticsHelper.mutationHappened()){
                continue;
            }

            int endGene = _geneticsHelper.selectGene(tour.getSize());

            if (startGene > endGene) {
                swapGenes(startGene, endGene);
            }

            //Extract and invert subroute
            int j = startGene + 1;
            for (int i = tour.getSize() - 1; i > 0; i--) {
                if (i > startGene && i < endGene) {
                    City auxCity = tour.getCity(i);
                    tour.saveCity(i, tour.getCity(j));
                    tour.saveCity(j, auxCity);
                    j++;
                    if (j > endGene / 2) {
                        break;
                    }
                }
            }
        }
    }

    private void swapGenes(int geneA, int geneB){
        int aux = geneA;
        geneA = geneB;
        geneB = aux;
    }
}
