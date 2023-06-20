package vet_clinic;
import java.sql.Date;

public class inventory {
	private int itemID;
	private String itemName;
	private String description;
	private int quantity;
	private float price;
	private Date expirationDate;
	private int supplierID;
	
	public inventory (int itemID,String itemName, String description, int quantity, float price, Date expirationDate, int supplierID) {
		this.itemID=itemID;
		this.itemName=itemName;
		this.description=description;
		this.quantity=quantity;
		this.price=price;
		this.expirationDate=expirationDate;
		this.supplierID=supplierID;
	}
	
	public int getItemID() {
		return itemID;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName=itemName;
	}
	public String getDescription(){
		return description;
	}	
	public void setDescription(String description) {
		this.description=description;
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
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate=expirationDate;
	}
	public int getSupplierID() {
		return supplierID;
	}
	public void setSupplier_id(int supplierID) {
		this.supplierID=supplierID;
	}

}
