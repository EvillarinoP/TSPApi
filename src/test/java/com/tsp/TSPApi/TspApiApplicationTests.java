package com.tsp.TSPApi;


import com.tsp.TSPApi.Application.IRouteApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TspApiApplicationTests {

    @Autowired
    private IRouteApplicationService _service;

    /*@Test
    public void ObtainResults(){

            _service.calculateRouteGA();
    }*/
}
