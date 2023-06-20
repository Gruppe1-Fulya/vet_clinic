package vet_clinic;
import java.sql.Date;

public class treatments {
	private String treatmentName;
	private String patientName;
	private int patientChipID;
	private String description;
	private float price;
	private Date treatmentDate;;
	private String category;
	private Boolean active;
	private String vetName;
	private int vetID;
	
	public treatments(String treatmentName,String patientName,int patientChipID, String description, float price, Date treatmentDate, String category, Boolean active,String vetName, int vetID) {
		this.treatmentName=treatmentName;
		this.patientName=patientName;
		this.patientChipID=patientChipID;
		this.description=description;
		this.price=price;
		this.treatmentDate=treatmentDate;
		this.category=category;
		this.active=active;
		this.vetName=vetName;
		this.vetID=vetID;
	}
	
	public String getTreatmentName() {
		return treatmentName;
	}
	public void setTreatment_name(String treatmentName) {
		this.treatmentName=treatmentName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName=patientName;
	}
	public int getPatientChipID() {
		return patientChipID;
	}
	public void setPatientChipID(int patientChipID) {
		this.patientChipID=patientChipID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price=price;
	}
	public Date getTreatmentDate() {
		return treatmentDate;
	}
	public void setTreatmentDate(Date treatmentDate) {
		this.treatmentDate=treatmentDate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category=category;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active=active;
	}
	public String getVetName() {
		return vetName;
	}
	public void setVetName(String vetName) {
		this.vetName=vetName;
	}
	public int getVetID() {
		return vetID;
	}
	public void setVetID(int vetID) {
		this.vetID=vetID;
	}
	

}
