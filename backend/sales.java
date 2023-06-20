package vet_clinic;

import java.sql.Date;


public class sales {
	
	private Date saleDate;
	private String itemName;
	private int itemID;
	private int quantity;
	private float price;
	private String paymentMethod;
	
	public sales(Date saleDate, String itemName, int itemID,int quantity,float price,String paymentMethod) {
		
		this.saleDate=saleDate;
		this.itemName=itemName;
		this.quantity=quantity;
		this.price=price;
		this.paymentMethod=paymentMethod;
	}
	
	
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate=saleDate;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName=itemName;
	}
	public int getItemID(){
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID=itemID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity=quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price=price;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod=paymentMethod;
	}
}
