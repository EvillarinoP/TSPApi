package com.tsp.TSPApi;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.tsp.TSPApi.Application.IRouteApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;


@SpringBootTest
public class TspApiApplicationTests {

    @Autowired
    private IRouteApplicationService _service;

    @Test
    public void ObtainResults(){

            _service.calculateRouteGA();
    }
}
