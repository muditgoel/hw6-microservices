package io.spring.cloud.samples.commerce.ui.services.items;

public class Price {
	private String price;
	private String itemdId;
	
	public Price() {
	}

	public Price(String price, String itemdId) {
		this.price = price;
		this.itemdId = itemdId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getItemdId() {
		return itemdId;
	}
	public void setItemdId(String itemdId) {
		this.itemdId = itemdId;
	}
	
}
