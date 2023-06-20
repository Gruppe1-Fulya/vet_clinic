package vet_clinic;

import java.sql.Date;


public class payments {
	private int petChipID;
	private String relevantPerson;
	private float currentDebt;
	private Date paymentDate;
	private float paymentAmount;
	private String paymentMethod;
    
	public payments(int petChipID,String relevantPerson, float currentDebt, Date paymentDate, float paymentAmount, String paymentMethod) {
		this.petChipID=petChipID;
		this.relevantPerson=relevantPerson;
		this.currentDebt=currentDebt;
		this.paymentDate=paymentDate;
		this.paymentAmount=paymentAmount;
		this.paymentMethod=paymentMethod;
	}
	public int getPetChipID() {
		return petChipID;
	}
	public String getRelevantPerson() {
		return relevantPerson;
	}
	public void setRelevantPerson(String relevantPerson) {
		this.relevantPerson=relevantPerson;
	}
	public float getCurrentDebt() {
		return currentDebt;
	}
	public void setCurrentDebt(float currentDebt) {
		this.currentDebt=currentDebt;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate=paymentDate;
	}
	public float getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(float paymentAmount) {
		this.paymentAmount=paymentAmount;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod=paymentMethod;
	}
	
}
