package com.microservice.training.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("CATEGORY")
public interface CategoryClient {
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String getCategory();
}
