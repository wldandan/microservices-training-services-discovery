package com.microservice.training.gateway;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("REVIEW")
public interface ReviewService {
	@RequestMapping(value="/reviews", method=RequestMethod.GET)
	public String getReviews();
}
