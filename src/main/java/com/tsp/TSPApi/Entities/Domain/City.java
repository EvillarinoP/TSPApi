package com.tsp.TSPApi.Entities.Domain;

/**
 * City Entity
 */
public class City {

    private final String name;
    private final int x;
    private final int y;

    public City(String name){
        this.name = name;
        this.x = (int)(Math.random()*200);
        this.y = (int)(Math.random()*200);
    }

    public City(String name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public String getName(){return this.name;}

    public double distanceTo(City city){
        int xDistance = Math.abs(getX() - city.getX());
        int yDistance = Math.abs(getY() - city.getY());

        return Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
    }
}
