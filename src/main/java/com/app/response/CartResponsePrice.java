package com.app.response;

import java.util.List;

public class CartResponsePrice {
	
	private List<CartResponseItems> cartResponseItems;
	
	private double totalPrice;
	private double totalDeliveryCharge;
	private double grandTotal;
	
	
	
	public CartResponsePrice(List<CartResponseItems> cartResponseItems, double totalPrice, double totalDeliveryCharge,
			double grandTotal) {
		super();
		this.cartResponseItems = cartResponseItems;
		this.totalPrice = totalPrice;
		this.totalDeliveryCharge = totalDeliveryCharge;
		this.grandTotal = grandTotal;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public double getTotalDeliveryCharge() {
		return totalDeliveryCharge;
	}
	public void setTotalDeliveryCharge(double totalDeliveryCharge) {
		this.totalDeliveryCharge = totalDeliveryCharge;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}

	public List<CartResponseItems> getCartResponseItems() {
		return cartResponseItems;
	}
	public void setCartResponseItems(List<CartResponseItems> cartResponseItems) {
		this.cartResponseItems = cartResponseItems;
	}
}
