package vet_clinic;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Date;

public class Controller_ZB {
	
	private final String url = "jdbc:mysql://localhost:3306/vet_clinic"; 
    private final String username = "admin"; 
    private final String password = "123456"; 
    
    public patientInfo convertToPatientInfo(Patients patient) {
        int chipID = patient.getChipID();
        String ownerName = patient.getOwnerName();
        
        return new patientInfo(chipID, ownerName);
    }
    public void addPayment(int petChipID, String relevantPerson, float paymentAmount, String paymentMethod) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO payments (petChipID, relevantPerson, currentDebt, paymentDate, paymentAmount, paymentMethod) VALUES (?, ?, 0, CURDATE(), ?, ?)";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, petChipID);
            statement.setString(2, relevantPerson);
            statement.setFloat(3, paymentAmount);
            statement.setString(4, paymentMethod);
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Payment added successfully!");
            } else {
                System.out.println("Failed to add payment.");
            }
        } catch (SQLException e) {
            System.out.println("Error while adding payment: " + e.getMessage());
        }
    }
    
    public void updateCurrentDebt(int petChipID) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter the debt increment:");
        float debtIncrement = scan.nextFloat();
        
        System.out.println("Enter the payment date (yyyy-mm-dd):");
        String paymentDateStr = scan.next();
        Date paymentDate = Date.valueOf(paymentDateStr);
        
        System.out.println("Enter the payment amount:");
        float paymentAmount = scan.nextFloat();
        
        System.out.println("Enter the payment method:");
        String paymentMethod = scan.next();
        
        patientInfo patientInfo = searchPatientByChipID(petChipID); 
        
        if (patientInfo != null) {
            String relevantPerson = patientInfo.getOwnerName();
            
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String query = "UPDATE payments SET currentDebt = currentDebt + ?, paymentDate = ?, paymentAmount = ?, paymentMethod = ?, relevantPerson = ? WHERE petChipID = ?";
                
                PreparedStatement statement = conn.prepareStatement(query);
                statement.setFloat(1, debtIncrement);
                statement.setDate(2, paymentDate);
                statement.setFloat(3, paymentAmount);
                statement.setString(4, paymentMethod);
                statement.setString(5, relevantPerson);
                statement.setInt(6, petChipID);
                
                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Payment information updated successfully!");
                } else {
                    System.out.println("Failed to update payment information.");
                }
            } catch (SQLException e) {
                System.out.println("Error while updating payment information: " + e.getMessage());
            }
        } else {
            System.out.println("No patient found with the specified chip ID.");
        }
    }
    public patientInfo searchPatientByChipID(int chipID) {
        
    	patientInfo patientInfo = null;
    
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT chip_id, ownerName FROM Patients WHERE chip_id = ?";
        
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, chipID);
        
            ResultSet resultSet = statement.executeQuery();
        
            if (resultSet.next()) {
                int retrievedChipID = resultSet.getInt("chip_id");
                String ownerName = resultSet.getString("ownerName");
            
                patientInfo = new patientInfo(retrievedChipID, ownerName);
            }
            
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
              System.out.println("Error while searching for patient: " + e.getMessage());
          }
    
        return patientInfo;
    }
    
    public void viewPayments(int petChipID) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM payments WHERE petChipID = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, petChipID);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int chipID = resultSet.getInt("petChipID");
                String relevantPerson = resultSet.getString("relevantPerson");
                float currentDebt = resultSet.getFloat("currentDebt");
                Date paymentDate = resultSet.getDate("paymentDate");
                float paymentAmount = resultSet.getFloat("paymentAmount");
                String paymentMethod = resultSet.getString("paymentMethod");
                
                System.out.println("Pet Chip ID: " + chipID);
                System.out.println("Relevant Person: " + relevantPerson);
                System.out.println("Current Debt: " + currentDebt);
                System.out.println("Payment Date: " + paymentDate);
                System.out.println("Payment Amount: " + paymentAmount);
                System.out.println("Payment Method: " + paymentMethod);
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving payments: " + e.getMessage());
        }
    }

}
