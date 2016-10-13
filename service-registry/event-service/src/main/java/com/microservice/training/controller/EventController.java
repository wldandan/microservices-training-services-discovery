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

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/instance")
    public String getInfo() {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return "The Instance Info is (HOST:" + instance.getHost() + "- InstanceID" + instance.getServiceId()+")";
    }

    @RequestMapping("/category")
    public String getCategory() {
        String result = getResultFromRemote("CATEGORY");
        if (result==null || result.isEmpty()){
            result = "nothing from remote server";
        }
        return String.format("The event category includes %s from remote server", result);
    }

    private String getResultFromRemote(String service) {
        List<ServiceInstance> instances = client.getInstances(service);
        if (instances != null && instances.size() > 0){
            URI uri = instances.get(0).getUri();
            if (uri != null){
                return (new RestTemplate()).getForObject(uri + "/category", String.class);
            }
        }
        return "";
    }
}