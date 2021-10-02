package com.tsp.TSPApi.Presentation;

import com.tsp.TSPApi.Application.IRouteApplicationService;

import com.tsp.TSPApi.Entities.DTOs.RouteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

    @Autowired
    private IRouteApplicationService _routeApplicationService;

    @GetMapping(value="/routeGA")
    public RouteResponse calculateRouteGA() {

        return _routeApplicationService.calculateRouteGA();
    }
}
