package io.spring.cloud.samples.commerce.ui.services.items;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@EnableConfigurationProperties(ItemProperties.class)
public class ItemService {

    @Autowired
    ItemProperties itemProperties;

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackItem")
    public List<Item> retrieveItem() {
    	Item[] forNow = restTemplate.getForObject("http://item/items", Item[].class);
    	return Arrays.asList(forNow);
    }
    
    @HystrixCommand(fallbackMethod = "fallbackItemByCategory")
    public List<Item> retrieveItemFromCategory(String cat) {
    	System.out.println("URL used : http://item/category/" + cat);
    	Item[] forNow = restTemplate.getForObject("http://item/category/" + cat, Item[].class);
    	return Arrays.asList(forNow);
    }

    @HystrixCommand(fallbackMethod = "fallbackItemById")
    public Item retrieveItemFromId(String id) {
    	Item[] forNow = restTemplate.getForObject("http://item/item/" + id, Item[].class);
     	return forNow[0];
    }

    @HystrixCommand
    private List<Item> fallbackItem() {
        return itemProperties.getRandomFortuneFromProperty();
    }
    
    @HystrixCommand
    private List<Item> fallbackItemByCategory(String cat) {
        return itemProperties.getRandomFortuneFromProperty();
    }

    @HystrixCommand
    private Item fallbackItemById(String cat) {
        return itemProperties.getRandomItemFromProperty();
    }

}
