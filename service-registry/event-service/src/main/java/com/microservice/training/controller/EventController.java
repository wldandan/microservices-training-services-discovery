package com.microservice.training.controller;

import com.microservice.training.gateway.ReviewService;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EventController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private ReviewService reviewService;


    @RequestMapping("/instance")
    public String getInfo() {
        ServiceInstance instance = client.getLocalServiceInstance();
        return "[Host:" + instance.getHost() + " - Port:" + instance.getPort()+"]";
    }

    @RequestMapping("/reviews")
    public String getReviews() {
        logger.info("Request review-service by using Feign Client");

        try {
            return String.format("%s (by Feign Client)", reviewService.getReviews());
        } catch(HystrixRuntimeException ex) {
            return String.format("Service is not available['%s']",ex.getLocalizedMessage());
        }
    }
}