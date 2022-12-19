package com.app.dto;

public class PricesDto {
	
	private int id;
	private double cost;
	private double discount;
	private double deliveryCharge;
	
//	public PricesDto(int id, double cost, double discount, double deliveryCharge) {
//		super();
//		this.id = id;
//		this.cost = cost;
//		this.discount = discount;
//		this.deliveryCharge = deliveryCharge;
//	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getDeliveryCharge() {
		return deliveryCharge;
	}
	public void setDeliveryCharge(double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}

}
