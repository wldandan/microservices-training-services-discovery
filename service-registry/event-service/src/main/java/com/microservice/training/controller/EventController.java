package com.microservice.training.controller;

import com.microservice.training.client.CategoryClient;
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
    private CategoryClient categoryClient;


    @RequestMapping("/instance")
    public String getInfo() {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return "The Instance Info is (HOST:" + instance.getHost() + "- PORT" + instance.getPort()+")";
    }

    @RequestMapping("/category")
    public String getCategory() {
        logger.info("Request category-service by using Feign Client");
        return String.format("The event category includes %s (by Feign Client)", categoryClient.getCategory());
    }
}