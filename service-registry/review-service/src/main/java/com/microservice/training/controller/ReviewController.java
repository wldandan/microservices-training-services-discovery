package com.microservice.training.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ReviewController {

    @Autowired
    private DiscoveryClient client;

    private final Logger logger = Logger.getLogger(getClass());

    @RequestMapping("/reviews")
    public String getCategory() {
        logger.info(getInfo());
        return "The event reviews is pretty good";
    }

    public String getInfo() {
        ServiceInstance instance = client.getLocalServiceInstance();
        return "[Host:" + instance.getHost() + " - Port:" + instance.getPort()+"]";
    }

}
