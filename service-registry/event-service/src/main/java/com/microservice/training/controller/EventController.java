package com.microservice.training.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class EventController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @RequestMapping("/instance")
    public String getInfo() {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        return "The Instance Info is (HOST:" + instance.getHost() + "- PORT" + instance.getPort()+")";
    }

    @RequestMapping("/category")
    public String getCategory() {
        String result = getResultFromRemote("CATEGORY");
        if (result==null || result.isEmpty()){
            result = "nothing from remote server";
        }
        return String.format("The event category includes %s", result);
    }

    private String getResultFromRemote(String service) {
        ServiceInstance instance = loadBalancer.choose(service);
        if (null != instance){
            String instanceInfo = "RemoteService Host:" + instance.getHost() + ", Port:" + instance.getPort();
            String serviceResult = (new RestTemplate()).getForObject(instance.getUri()+"/category",String.class);
            return serviceResult+"(" + instanceInfo + ")";
        }
        return "";
    }
}