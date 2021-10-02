package com.tsp.TSPApi.Helpers;

import com.tsp.TSPApi.Entities.Constants;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GeneticsHelper implements IGeneticsHelper {

    private Random r = new Random();

    public int selectGene(int maxSize){
        return (int) (Math.random() * maxSize);
    }

    public boolean mutationHappened(){
        return Math.random() < Constants.MUTATION_RATE;
    }

    public int selectGeneToMutate(int tourSize){
        return (int) (tourSize * Math.random());
    }
}
