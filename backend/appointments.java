package vet_clinic;
import java.sql.Time;
import java.sql.Date;


public class appointments {
	private String vetName;
	private int vetID;
	private int chipID;
	private String petOwnerName;
	private Date appointmentDate;
	private Time appointmentTime;
	private String reason;
	
	
	public appointments(String vetName, int vetID, int chipID,String petOwnerName, Date appointmentDate, Time appointmentTime, String reason) {
		 this.vetName= vetName;
		 this.vetID=vetID;
		 this.chipID=chipID;
		 this.petOwnerName=petOwnerName;
		 this.appointmentDate=appointmentDate;
		 this.appointmentTime=appointmentTime;		 
		 this.reason=reason;
		 
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
	public int getChipID() {
		return chipID;
	}
	public void setChipID(int chipID) {
		this.chipID=chipID;
	}
	public String getPetOwnerName() {
		return petOwnerName;
	}
	public void setPetOwnerName(String petOwnerName) {
		this.petOwnerName=petOwnerName;
	}
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointment_date(Date appointmentDate) {
		this.appointmentDate=appointmentDate;
	}
	public Time getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(Time appointmentTime) {
		this.appointmentTime=appointmentTime;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason=reason;
	}
	
}
