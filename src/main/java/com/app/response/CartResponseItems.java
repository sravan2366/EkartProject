package com.app.response;

public class CartResponseItems {
	
	private String name;
	private String category;
	private String SellerName;
	private double price;
	private double deliveryCharge;
	private int quantity;
	private double total;
	private double cartOfferPrice;
	
	
	
	public CartResponseItems(String name, String category, String sellerName, double price, double deliveryCharge,
			int quantity, double total, double cartOfferPrice) {
		super();
		this.name = name;
		this.category = category;
		SellerName = sellerName;
		this.price = price;
		this.deliveryCharge = deliveryCharge;
		this.quantity = quantity;
		this.total = total;
		this.cartOfferPrice = cartOfferPrice;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSellerName() {
		return SellerName;
	}
	public void setSellerName(String sellerName) {
		SellerName = sellerName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDeliveryCharge() {
		return deliveryCharge;
	}
	public void setDeliveryCharge(double deliveryCharge) {
		this.deliveryCharge = deliveryCharge;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getCartOfferPrice() {
		return cartOfferPrice;
	}
	public void setCartOfferPrice(double cartOfferPrice) {
		this.cartOfferPrice = cartOfferPrice;
	}
	
	

}
