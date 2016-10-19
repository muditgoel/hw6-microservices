package io.spring.cloud.samples.commerce.ui.services.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties(prefix = "price")
@RefreshScope
public class PriceProperties {
	  private static final Random RANDOM = new Random();

	  List<String> messages = new ArrayList<>();

	  public List<String> getMessages() {
	    return messages;
	  }

	  public void setMessages(List<String> messages) {
	    this.messages = messages;
	  }

	  public void addMessage(String message) {
	    this.messages.add(message);
	  }

	  public Price getRandomPriceFromProperty() {
	    List<Price> temp = new ArrayList();
	    temp.add(new Price("9999L", "NA"));
	    return new Price("9999L", "NA");
//	    if(messages == null || messages.size() == 0) {
//	      return new Item(9999L, "NA", "NA", "NA");
//	    }
//	    else {
//	      int index = RANDOM.nextInt(messages.size());
//	      return new Item((long) index, messages.get(index));
//	    }
	  }
	  public HashMap<String,String> getRandomPricesFromProperty() {
		    HashMap<String,String> temp = new HashMap<String,String>();
		    temp.put("9999L", "NA");
		    return temp;
//		    if(messages == null || messages.size() == 0) {
//		      return new Item(9999L, "NA", "NA", "NA");
//		    }
//		    else {
//		      int index = RANDOM.nextInt(messages.size());
//		      return new Item((long) index, messages.get(index));
//		    }
		  }

}
