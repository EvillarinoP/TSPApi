package com.tsp.TSPApi.Builders;

import com.tsp.TSPApi.Entities.Domain.City;
import com.tsp.TSPApi.Helpers.IBuilder;

public class CityBuilder implements IBuilder<City> {

    City city;

    public CityBuilder(){
        city = new City("CityA",0,0);
    }

    public CityBuilder withName(String name){
        city.setName(name);
        return this;
    }

    public CityBuilder withX(int x){
        city.setX(x);
        return this;
    }

    public CityBuilder withY(int y){
        city.setY(y);
        return this;
    }

    public City build(){
        return city;
    }
}
