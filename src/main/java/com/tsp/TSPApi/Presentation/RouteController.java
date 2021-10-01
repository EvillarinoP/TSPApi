package com.tsp.TSPApi.Presentation;

import java.util.List;

import com.tsp.TSPApi.Application.IRouteApplicationService;
import com.tsp.TSPApi.Entities.City;

import com.tsp.TSPApi.Entities.RouteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
