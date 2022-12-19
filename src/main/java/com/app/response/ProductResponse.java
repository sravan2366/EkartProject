package com.app.response;


public class ProductResponse {
	
	private String name;
	private String shortDesc;
	private String description;
	private String category;
	private double discount;
	private double deliveryCharge;
	
	public ProductResponse(String name, String shortDesc, String description, String category, double cost,
			double deliveryCharge, double discount) {
		
//		c.name , c.shortDesc, c.description, c.category, p.cost, p.deliveryCharge, p.discount
		
		super();
		this.name = name;
		this.shortDesc = shortDesc;
		this.description = description;
		this.category = category;
		this.cost = cost;
		this.deliveryCharge = deliveryCharge;
		this.setDiscount(discount);
	}
	private double cost;
	
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getDeliveryCharge() {
		return deliveryCharge;
	}
	public void setDeliveryCharge(double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}

}
