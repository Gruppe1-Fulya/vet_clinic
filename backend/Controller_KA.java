package vet_clinic;

import java.util.Scanner;
import java.sql.*;



public class Controller_KA {
	private final String url = "jdbc:mysql://localhost:3306/vet_clinic"; 
    private final String username = "admin"; 
    private final String password = "123456"; 
    
	 Scanner scanner = new Scanner(System.in);
	 
	 public void createTreatment() {
	        

	        System.out.println("Enter Treatment Name:");
	        String treatmentName = scanner.nextLine();

	        System.out.println("Enter Patient Name:");
	        String patientName = scanner.nextLine();

	        System.out.println("Enter Patient Chip ID:");
	        int patientChipID = scanner.nextInt();
	        
	        System.out.println("Enter Description:");
	        String description = scanner.nextLine();

	        System.out.println("Enter Price:");
	        float price = scanner.nextFloat();
	        
	        System.out.println("Enter Treatment Date (YYYY-MM-DD):");
	        String dateString = scanner.nextLine();
	        Date treatmentDate = Date.valueOf(dateString);

	        System.out.println("Enter Category:");
	        String category = scanner.nextLine();

	        System.out.println("Enter Active (true/false):");
	        boolean active = scanner.nextBoolean();
	        
	        System.out.println("Enter Vet Name:");
	        String vetName = scanner.nextLine();

	        System.out.println("Enter Vet ID:");
	        int vetID = scanner.nextInt();
	       

	        treatments newTreatment= new treatments(treatmentName, patientName, patientChipID, description, price, treatmentDate, category, active, vetName, vetID);
	        saveTreatment(newTreatment);
	    }
	 
	 public void saveTreatment(treatments treatment) {
	        try (Connection conn = DriverManager.getConnection(url, username, password)) {
	            String query = "INSERT INTO treatments (treatmentName, patientName, patientChipID, description, price, treatmentDate, category, active, vetName, vetID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            PreparedStatement statement = conn.prepareStatement(query);
	            statement.setString(1, treatment.getTreatmentName());
	            statement.setString(2, treatment.getPatientName());
	            statement.setInt(3, treatment.getPatientChipID());
	            statement.setString(4, treatment.getDescription());
	            statement.setFloat(5, treatment.getPrice());
	            statement.setDate(6, treatment.getTreatmentDate());
	            statement.setString(7, treatment.getCategory());
	            statement.setBoolean(8, treatment.getActive());
	            statement.setString(9, treatment.getVetName());
	            statement.setInt(10, treatment.getVetID());

	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("Treatment added successfully!");
	            } else {
	                System.out.println("Failed to add treatment.");
	            }
	        } catch (SQLException e) {
	            System.out.println("Error while saving treatment: " + e.getMessage());
	        }
	    }
	 public void viewTreatments(int patientChipID) {
	        try (Connection conn = DriverManager.getConnection(url, username, password)) {
	            String query = "SELECT * FROM treatments WHERE patientChipID = ? ORDER BY treatmentDate DESC";
	            PreparedStatement statement = conn.prepareStatement(query);
	            statement.setInt(1, patientChipID);
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                String treatmentName = resultSet.getString("treatmentName");
	                String patientName = resultSet.getString("patientName");
	                int chipID = resultSet.getInt("patientChipID");
	                String description = resultSet.getString("description");
	                float price = resultSet.getFloat("price");
	                Date treatmentDate = resultSet.getDate("treatmentDate");
	                String category = resultSet.getString("category");
	                boolean active = resultSet.getBoolean("active");
	                String vetName = resultSet.getString("vetName");
	                int vetID = resultSet.getInt("vetID");
	                
	                System.out.println("Treatment Name: " + treatmentName);
	                System.out.println("Patient Name: " + patientName);
	                System.out.println("Patient Chip ID: " + chipID);
	                System.out.println("Description: " + description);
	                System.out.println("Price: " + price);
	                System.out.println("Treatment Date: " + treatmentDate);
	                System.out.println("Category: " + category);
	                System.out.println("Active: " + active);
	                System.out.println("Vet Name: " + vetName);
	                System.out.println("Vet ID: " + vetID);
	                System.out.println("------------------------");

	               
	            }
	        } catch (SQLException e) {
	            System.out.println("Error while retrieving treatments: " + e.getMessage());
	        }
	    }

	
	
	
    
}
