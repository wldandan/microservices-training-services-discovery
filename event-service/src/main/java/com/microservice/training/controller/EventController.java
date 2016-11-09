package com.microservice.training.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;


@RestController
public class EventController {

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @RequestMapping("/instance")
    public String getInfo() {
        ServiceInstance instance = client.getLocalServiceInstance();
        return "[Host:" + instance.getHost() + " - Port:" + instance.getPort()+"]";
    }

    @RequestMapping("/reviews")
    public String getReviews() {
        String result = getResultFromRemote("REVIEW");
        if (result == null || result.isEmpty()){
            return "can not get reviews[ Review Server is not available ]";
        }
        return result;
    }

    private String getResultFromRemote(String service) {
        ServiceInstance instance = loadBalancer.choose(service);

        if (instance == null){
            return null;
        }

        URI uri = instance.getUri();
        if (uri != null){
            String originResult = (new RestTemplate()).getForObject(uri + "/reviews", String.class);
            return String.format("%s [from %s:%s]", originResult, instance.getHost(),instance.getPort());
        }
        return "";
    }
}