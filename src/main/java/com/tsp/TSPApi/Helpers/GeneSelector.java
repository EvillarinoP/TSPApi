package com.tsp.TSPApi.Helpers;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class GeneSelector implements IGeneSelector {

    private Random r = new Random();

    public int selectGene(int maxSize){
        return (int) (Math.random() * maxSize);
    }
}
