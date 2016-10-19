package io.spring.cloud.samples.commerce.ui.services.items;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@EnableConfigurationProperties(PriceProperties.class)
public class PriceService {
	   @Autowired
	    PriceProperties priceProperties;

	    @Autowired
	    RestTemplate restTemplate;

	    @HystrixCommand(fallbackMethod = "fallbackPrice")
	    public Price retrievePrice(String id) {
	    	Price priceForNow = restTemplate.getForObject("http://price/price/"+id, Price.class);
	    	return priceForNow;
	    }

	    @HystrixCommand(fallbackMethod = "fallbackPrices")
	    public HashMap<String, String> retrievePrices() {
	    	HashMap<String, String> priceForNow = restTemplate.getForObject("http://price/prices/", HashMap.class);
	    	return priceForNow;
	    }

	    @HystrixCommand
	    private Price fallbackPrice(String id) {
	        return priceProperties.getRandomPriceFromProperty();
	    }

	    @HystrixCommand
	    private HashMap<String, String> fallbackPrices() {
	        return priceProperties.getRandomPricesFromProperty();
	    }

}
