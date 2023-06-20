package vet_clinic;

public class suppliers {
	private int supplierID;
	private String supplierName;
	private String contactPerson;
	private String contactNumber;
	private String email;
	private String adress;
	
	public suppliers(int supplierID,String supplierName,String contactPerson,String contactNumber,String email, String adress) {
		this.supplierID=supplierID;
		this.supplierName=supplierName;
		this.contactPerson=contactPerson;
		this.contactNumber=contactNumber;
		this.email=email;
		this.adress=adress;
	}
	
	public int getSupplierID() {
		return supplierID;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName=supplierName;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson=contactPerson;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber=contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email=email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress=adress;
	}

}
