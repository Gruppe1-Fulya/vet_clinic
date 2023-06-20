package vet_clinic;

public class veterinarians {
	private int vetID;
	private String name;
	private String specialization;
	private String contactNumber;
	private String email;
	private String activePatients;
	
	public veterinarians(int vetID, String name, String specialization, String contactNumber,String email, String activePatients) {
		this.vetID=vetID;
		this.name=name;
		this.specialization=specialization;
		this.contactNumber=contactNumber;
		this.email=email;
		this.activePatients=activePatients;
	}
	
	public int getVetID() {
		return vetID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization=specialization;
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
	public String getActivePatients() {
		return activePatients;
	}
	public void setActivePatients(String activePatients) {
		this.activePatients=activePatients;
	}
	

}
