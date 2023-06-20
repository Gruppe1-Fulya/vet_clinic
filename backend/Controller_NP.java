package vet_clinic;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Date;

public class Controller_NP {
	
	private final String url = "jdbc:mysql://localhost:3306/vet_clinic"; 
    private final String username = "admin"; 
    private final String password = "123456"; 
    
    Scanner scan=new Scanner(System.in);
    
	public void createPatientRecord(Patients patient) {        
		
		
		System.out.println("Chip ID:\n");
		int nChipId=scan.nextInt();
		
		System.out.println("Pet Name:\n");
		String nPetName=scan.nextLine();
		
		System.out.println("Type:\n");
		String nType= scan.nextLine();
		
		System.out.println("Breed:\n");
		String nBreed=scan.nextLine();
		
		System.out.println("Colour\n");
		String nColour=scan.nextLine();
		
		System.out.println("Date Of Birth:\n");
		String nDateOfBirth=scan.nextLine(); // date data type
		Date nnDate = Date.valueOf(nDateOfBirth);
		System.out.println("Age:");
		int nAge=scan.nextInt();
		
		System.out.println("Owners Name:\n");
		String nOwnerName=scan.nextLine();
		
		System.out.println("Contact Number:\n");
		String nContactNumber=scan.nextLine();
		
		Patients newPatient = new Patients(nChipId, nPetName, nType, nBreed, nColour, nnDate, nAge, nOwnerName, nContactNumber);
		 
		savePatient(newPatient);
		
    }
	
	private void savePatient(Patients patient) {
		
		try (Connection conn = DriverManager.getConnection(url,username,password)){
			String query = "INSERT INTO Patients (chip_id, name, type, breed, colour,dateOfBirth, age,ownerName,contactNumber) VALUES (?)";
			
			PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, patient.getChipID());
            statement.setString(2, patient.getName());
            statement.setString(3, patient.getBreed());
            statement.setString(4, patient.getType());
            statement.setString(5, patient.getColour());
            statement.setDate(6, patient.getDateOfBirth());
            statement.setInt(7, patient.getAge());
            statement.setString(8, patient.getOwnerName());
            statement.setString(9, patient.getContactNumber());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Patient record saved successfully!");
            } else {
                System.out.println("Failed to save patient record.");
            }
        } catch (SQLException e) {
            System.out.println("Error while saving patient record: " + e.getMessage());
        }
    }

		
		
	
    
    
    public void updatePatientRecord(Patients patient) {
    	
    	int chipID = patient.getChipID();
    	Patients uPatient= searchPatient(chipID);
    	
    	if(patient == null) {
    		System.out.println("Patient not found.");
    		return;
    	}
    	
    	System.out.println("Pet Name:");
    	String newName= scan.nextLine();
    	uPatient.setName(newName);
    	
    	System.out.println("Type:");
    	String newType= scan.nextLine();
    	uPatient.setType(newType);
    	
    	System.out.println("Breed:");
    	String newBreed=scan.nextLine();
    	uPatient.setBreed(newBreed);
    	
    	System.out.println("Colour:");
    	String newColour=scan.nextLine();
    	uPatient.setColour(newColour);
    	
    	System.out.println("Date Of Birth:");
    	String newDateOfBirth=scan.nextLine();
    	Date newd = Date.valueOf(newDateOfBirth);
    	uPatient.setDateOfBirth(newd);
    	
    	System.out.println("Age:");
    	int newAge=scan.nextInt();
    	uPatient.setAge(newAge);
    	
    	System.out.println("Owners Name:");
    	String newOwnerName= scan.nextLine();
    	uPatient.setOwnerName(newOwnerName);
    	
    	System.out.println("Contact Number:");
    	String newContactNumber =scan.nextLine();
    	uPatient.setContactNumber(newContactNumber);
    	
    	
    }
    
    
    public Patients searchPatient(int patientId) {
        
    	Patients patient= null;
    	
    	System.out.println("Enter a Chip ID:");
        int sPatient= scan.nextInt();
                
        try(Connection conn= DriverManager.getConnection(url, username,password)){
        	String query= "SELECT * FROM Patients WHERE chip_id="+ sPatient;
        	
        	PreparedStatement statement= conn.prepareStatement(query);
        	statement.setInt(1,sPatient);
        	
        	ResultSet resultSet= statement.executeQuery();
        	
        	if(resultSet.next()) {
        		int retrievedChipId= resultSet.getInt("chip_id");
        		String name = resultSet.getString("name");
        		String type=resultSet.getString("type");
        		String breed= resultSet.getString("breed");
        		String colour= resultSet.getString("colour");
        		Date dateOfBirth =resultSet.getDate("dateOfBirth");
        		int age= resultSet.getInt("age");
        		String ownerName=resultSet.getString("ownerName");
        		String contactNumber= resultSet.getString("ContactNumber");
        		
        		patient= new Patients(retrievedChipId, name, breed, type, colour, dateOfBirth, age, ownerName, contactNumber);
        	}else {
        		System.out.println("No patient found with the provided Chip ID.");
        		
        	}
        	
        	resultSet.close();
        	statement.close();
        } catch(SQLException e) {
        	System.out.println("Error while searching for patient" + e.getMessage());
        }
        return patient;
    }
	

}
