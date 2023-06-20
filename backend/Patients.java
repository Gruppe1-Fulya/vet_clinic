package vet_clinic;

import java.sql.Date;

public class Patients {
	private int chipID;
	private String name;
	private String breed;
	private String type;
	private String colour;
	private Date dateOfBirth; //date data type
	private int age;
	private String ownerName;
	private String contactNumber;
	
	public Patients(int chipID, String name, String breed, String type, String colour, Date dateOfBirth, int age, String ownerName, String contactNumber) {
		this.chipID=chipID;
		this.name=name;
		this.breed=breed;
		this.type=type;
		this.colour=colour;
		this.dateOfBirth=dateOfBirth;
		this.age=age;
		this.ownerName=ownerName;
		this.contactNumber=contactNumber;
	
	}
	
	public int getChipID() {
		return chipID;
	}
	public String getName() {
		return name;
	}
    public void setName(String name) {
    	this.name=name;
    }
    public String getBreed() {
    	return breed;
    }
    public void setBreed(String breed) {
    	this.breed=breed;
    }
    public String getType() {
    	return type;
    }
    public void setType(String type) {
    	this.type=type;
    }
    public String getColour() {
    	return colour;
    }
    public void setColour(String colour) {
    	this.colour=colour;
    }
    public Date getDateOfBirth() { //date data type
    	return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
    	this.dateOfBirth=dateOfBirth;
    }
    public int getAge() {
    	return age;
    }
    public void setAge(int age) {
    	this.age=age;
    }
    public String getOwnerName() {
    	return ownerName;
    }
    public void setOwnerName(String ownerName) {
    	this.ownerName=ownerName;
    }
    public String getContactNumber() {
    	return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
    	this.contactNumber=contactNumber;
    }
}
