package package1;

import java.sql.Date;


public class treatments {
    private String patientName;
    private int patientChipID;
    private String treatmentName;
	private String description;
	private float price;
	private int duration;
	private String category;
	private Boolean active;
	private Date createdAt;
	private Date updatedAt;
    private String vetName;
	private int vetID;
	
	public treatments( String patientName, int patientChipID, String treatmentName, String description, float price, int duration, String category, Boolean active, Date createdAt, Date updatedAt,String vetName, int vetID) {
		this.patientName=patientName;
        this.patientChipID=patientChipID;
        this.treatmentName=treatmentName;
		this.description=description;
		this.price=price;
		this.duration=duration;
		this.category=category;
		this.active=active;
		this.createdAt=createdAt;
		this.updatedAt=updatedAt;
		this.vetID=vetID;
	}
	
    public String getPatientName(){
        return patientName;
    }
    public void setPatientName(String patientName){
        this.patientName= patientName;
    }
    public int getPatientID(){
        return patientChipID;
    }
    public void setPatientChipID(int patientChipID){
        this.patientChipID=patientChipID;
    }
	public String getTreatmentName() {
		return treatmentName;
	}
	public void setTreatment_name(String treatmentName) {
		this.treatmentName=treatmentName;
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
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration=duration;
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
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt=createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt=updatedAt;
	}
    public String getVetName(){
        return vetName;
    }
    public void setVetName(String vetName){
        this.vetName=vetName;
    }
	public int getVetID() {
		return vetID;
	}
	public void setVetID(int vetID) {
		this.vetID=vetID;
	}
    
}
