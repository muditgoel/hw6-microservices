package io.spring.cloud.samples.commerce.ui.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.spring.cloud.samples.commerce.ui.services.items.Item;
import io.spring.cloud.samples.commerce.ui.services.items.ItemService;
import io.spring.cloud.samples.commerce.ui.services.items.Price;
import io.spring.cloud.samples.commerce.ui.services.items.PriceService;

@RestController
public class UiController {

	@Autowired
	ItemService service;

	@Autowired
	PriceService priceService;

	@RequestMapping("/items")
	public List<Item> retrieveItem() {
		List<Item> items = service.retrieveItem();
		HashMap<String, String> priceMap = priceService.retrievePrices();
		for (Item i : items) {
			if (priceMap.containsKey(String.valueOf(i.getId()))) {
				i.setPrice(priceMap.get(String.valueOf(i.getId())));
			} else {
				i.setPrice("NA");
			}
		}
		return items;
	}

	@RequestMapping("/category/{cat}")
	public List<Item> retrieveItemFromCategory(@PathVariable("cat") String category) {
		List<Item> items = service.retrieveItemFromCategory(category);
		HashMap<String, String> priceMap = priceService.retrievePrices();
		for (Item i : items) {
			if (priceMap.containsKey(String.valueOf(i.getId()))) {
				i.setPrice(priceMap.get(String.valueOf(i.getId())));
			} else {
				i.setPrice("NA");
			}
		}
		return items;
	}

	@RequestMapping("/item/{itemid}")
	public Item retrieveItemFromId(@PathVariable("itemid") String id) {
		Item i = service.retrieveItemFromId(id);
		i.setPrice(priceService.retrievePrice(Long.toString(i.getId())).getPrice());
		return i;
	}

}
