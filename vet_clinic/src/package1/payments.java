package package1;

import java.sql.Date;

public class payments {
    
    private int paymentID;
	private Date paymentDate;
	private float paymentAmount;
	private String paymentMethod;
    
	public payments(int paymentID, Date paymentDate, float paymentAmount, String paymentMethod) {
		this.paymentID=paymentID;
		this.paymentDate=paymentDate;
		this.paymentAmount=paymentAmount;
		this.paymentMethod=paymentMethod;
	}
	
	public int getPaymentID() {
		return paymentID;
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
