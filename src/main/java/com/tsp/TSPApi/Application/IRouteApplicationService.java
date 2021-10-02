package com.tsp.TSPApi.Application;

import com.tsp.TSPApi.Entities.DTOs.RouteResponse;

public interface IRouteApplicationService {
    RouteResponse calculateRouteGA();
}
