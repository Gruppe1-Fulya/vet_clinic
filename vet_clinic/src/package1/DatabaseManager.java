package package1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class DatabaseManager {

    private final String url = "jdbc:mysql://localhost:3306/vet_clinic"; 
    private final String username = "admin"; 
    private final String password = "123456"; 
    
    Scanner scan=new Scanner(System.in);

    //Controller_IH
    
	public void createInventory(inventory item) {
	
		
		System.out.println("Item Name:");
		String nItemName=scan.nextLine();
		
		System.out.println("Description:");
		String nDescription=scan.nextLine();
		
		System.out.println("Quantity:");
		int nQuantity=scan.nextInt();
		
		System.out.println("Price:");
		float nPrice=scan.nextFloat();
		
		System.out.println("Expiration Date:(If it exists)");
		String nExpiration=scan.nextLine();
		Date nExpirationDate = Date.valueOf(nExpiration);
		
		System.out.println("Supplier ID:");
		int nSupplierID=scan.nextInt();
		
		inventory newInventory=new inventory(nItemName,nDescription, nQuantity,nPrice,nExpirationDate, nSupplierID);
		
	    saveInventory(newInventory);
	
	}
	
    private void saveInventory(inventory item) {
		
		try (Connection conn = DriverManager.getConnection(url,username,password)){
			String query = "INSERT INTO inventory (item_id,item_name, description, quantity, price, expiration_date, supplier_id) VALUES (?)";
			
			PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(2, item.getItemName());
            statement.setString(3, item.getDescription());
            statement.setInt(4, item.getQuantity());
            statement.setFloat(5, item.getPrice());
            statement.setDate(6, item.getExpirationDate());
            statement.setInt(7, item.getSupplierID());
            

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Inventory record saved successfully!");
            } else {
                System.out.println("Failed to save inventory record.");
            }
        } catch (SQLException e) {
            System.out.println("Error while saving inventory record: " + e.getMessage());
        }
    }
    
    public inventory searchInventory(String itemName) {
        
    	inventory item= null;
    	
    	System.out.println("Enter a Item Name:");
        String sItemName= scan.nextLine();
                
        try(Connection conn= DriverManager.getConnection(url, username,password)){
        	String query= "SELECT * FROM inventory WHERE item_name="+ sItemName;
        	
        	PreparedStatement statement= conn.prepareStatement(query);
        	statement.setString(1,sItemName);
        	
        	ResultSet resultSet= statement.executeQuery();
        	
        	if(resultSet.next()) {
        		
        		String description=resultSet.getString("description");
        		int quantity= resultSet.getInt("quantity");
        		float price= resultSet.getFloat("price");
        		Date expiration_date =resultSet.getDate("expiration_date");
        		int supplier_id= resultSet.getInt("supplier_id");
        		        		
        		item= new inventory(itemName, description, quantity, price, expiration_date,supplier_id);
        	}else {
        		System.out.println("No item found with the provided item ID.");
        		
        	}
        	
        	resultSet.close();
        	statement.close();
        } catch(SQLException e) {
        	System.out.println("Error while searching for item" + e.getMessage());
        }
        return item;
    }
	
  
    
    
    public void updateInventory(inventory item) {

        System.out.println("Enter Item Name:");
        String uItemName = scan.nextLine();
        scan.nextLine();
    	
    	inventory uItem= searchInventory(uItemName);
    	
    	if(item == null) {
    		System.out.println("Item not found.");
    		return;
    	}
    	
    	System.out.println("Item Name:");
    	String newItemName= scan.nextLine();
    	uItem.setItemName(newItemName);
    	
    	System.out.println("Description:");
    	String newDescription= scan.nextLine();
    	uItem.setDescription(newDescription);
    	
    	System.out.println("Quantity:");
    	int newQuantity=scan.nextInt();
    	uItem.setQuantity(newQuantity);
    	
    	System.out.println("Price:");
    	float newPrice=scan.nextFloat();
    	uItem.setPrice(newPrice);
    	
    	System.out.println("Exipration Date (If exists):");
    	String newExpiration_date=scan.nextLine();
    	Date newED = Date.valueOf(newExpiration_date);
    	uItem.setExpirationDate(newED);
    	
    	System.out.println("Supplier ID:");
    	int newSupplierID=scan.nextInt();
    	uItem.setSupplierID(newSupplierID);
    	
    	
    	
    }
    
    public void createSupplier(suppliers supplier) {
    	
    	System.out.println("Supplier ID:");
		int nSupplierID=scan.nextInt();
		
		System.out.println("Supplier Name:");
		String nSupplierName=scan.nextLine();
		
		System.out.println("Contact Person:");
		String nContactPerson=scan.nextLine();
		
		System.out.println("Contact Number:");
		String nContactNumber=scan.nextLine();
		
		System.out.println("E-mail:");
		String nEmail=scan.nextLine();
		
		System.out.println("Adress:");
		String nAdress=scan.nextLine();
		
		suppliers newSupplier=new suppliers(nSupplierID,nSupplierName,nContactPerson, nContactNumber,nEmail, nAdress);
		
	    saveSupplier(newSupplier);
	
    	
    }
    
    public void saveSupplier(suppliers supplier) {
       
    		
    		try (Connection conn = DriverManager.getConnection(url,username,password)){
    			String query = "INSERT INTO suppliers (supplier_id,supplier_name, contact_person, contact_number, email, adress) VALUES (?)";
    			
    			PreparedStatement statement = conn.prepareStatement(query);
                statement.setInt(1, supplier.getSupplierID());
                statement.setString(2, supplier.getSupplierName());
                statement.setString(3, supplier.getContactPerson());
                statement.setString(4, supplier.getContactNumber());
                statement.setString(5, supplier.getEmail());
                statement.setString(6, supplier.getAdress());
               
                

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Supplier saved successfully!");
                } else {
                    System.out.println("Failed to save Supplier Informations.");
                }
            } catch (SQLException e) {
                System.out.println("Error while saving Supplier Informations: " + e.getMessage());
            }
        
    }

    //Controller_KA

    public treatments createTreatment() {
        

        System.out.println("Enter Patient Name:");
        String patientName = scan.nextLine();

        System.out.println("Enter Patient Chip ID:");
        int patientChipID = scan.nextInt();
        scan.nextLine(); 

        System.out.println("Enter Treatment Name:");
        String treatmentName = scan.nextLine();

        System.out.println("Enter Description:");
        String description = scan.nextLine();

        System.out.println("Enter Price:");
        float price = scan.nextFloat();
        scan.nextLine(); 

        System.out.println("Enter Duration:");
        int duration = scan.nextInt();
        scan.nextLine();
        
        System.out.println("Enter Category:");
        String category = scan.nextLine();

        System.out.println("Enter Active (true/false):");
        boolean active = scan.nextBoolean();
        scan.nextLine(); 

        System.out.println("Treatment Created At (YYYY-MM-DD):");
        String dateString = scan.nextLine();
        Date treatmentDate = Date.valueOf(dateString);

        System.out.println("Treatment Updated At (YYYY-MM-DD):");
        String updateStr = scan.nextLine();
        Date update = Date.valueOf(updateStr);

        System.out.println("Enter Vet Name:");
        String vetName = scan.nextLine();

        System.out.println("Enter Vet ID:");
        int vetID = scan.nextInt();
        scan.nextLine(); 

        return new treatments( patientName, patientChipID,treatmentName, description, price, duration, category, active, treatmentDate, update, vetName, vetID);
    }

    public void saveTreatment(treatments treatment) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO treatments (treatmentName, patientName, patientChipID, description, price, treatmentDate, category, active, vetName, vetID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, treatment.getPatientName());
            statement.setInt(2, treatment.getPatientID());
            statement.setString(3, treatment.getTreatmentName());
            statement.setString(4, treatment.getDescription());
            statement.setFloat(5, treatment.getPrice());
            statement.setInt(6, treatment.getDuration());
            statement.setString(7, treatment.getCategory());
            statement.setBoolean(8, treatment.getActive());
            statement.setDate(9, treatment.getCreatedAt());
            statement.setDate(10, treatment.getUpdatedAt());
            statement.setString(11, treatment.getVetName());
            statement.setInt(12, treatment.getVetID());

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
    // Controller_NP
    public void createPatientRecord(Patients patient) {        
		
		
		System.out.println("Chip ID:\n");
		int nChipId=scan.nextInt();
        scan.nextLine();
		
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
			String query = "INSERT INTO Patients (chip_id, name, type, breed, colour, dateOfBirth, age, ownerName, contactNumber) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
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
        System.out.println("Please Enter Chip ID Of The Pet You Want To Update:\n ");
        int uChipID = scan.nextInt();
    	
    	Patients uPatient= searchPatient(uChipID);
    	
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
    //Controller_TV
    public void createAppointment() {
        System.out.println("Enter Appointment ID");
        int apID = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter Vet Name: ");
        String vetName = scan.nextLine();

        System.out.println("Enter Vet ID: ");
        int vetID = scan.nextInt();
        scan.nextLine(); 

        System.out.println("Enter Chip ID: ");
        int chipID = scan.nextInt();
        scan.nextLine(); 

        System.out.println("Enter Pet Owner Name: ");
        String petOwnerName = scan.nextLine();

        System.out.println("Enter Appointment Date (yyyy-mm-dd): ");
        String appointmentDateStr = scan.nextLine();
        Date appointmentDate= Date.valueOf(appointmentDateStr);

        System.out.println("Enter Appointment Time (hh:mm:ss): ");
        String appointmentTimeStr = scan.nextLine();
        Time appointmentTime = Time.valueOf(appointmentTimeStr);

        System.out.println("Enter Reason: ");
        String reason = scan.nextLine();

        

        appointments newAppointment = new appointments( apID, vetName, vetID, chipID, petOwnerName, appointmentDate, appointmentTime, reason);
        saveAppointment(newAppointment);
    }

    private void saveAppointment(appointments appointment) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO Appointments (vet_name, vet_id, chip_id, pet_owner_name, appointment_datetime, reason) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, appointment.getVetName());
            statement.setInt(2, appointment.getVetID());
            statement.setInt(3, appointment.getChipID());
            statement.setString(4, appointment.getPetOwnerName());
            statement.setDate(5, appointment.getAppointmentDate());
            statement.setTime(6, appointment.getAppointmentTime());
            statement.setString(7, appointment.getReason());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Appointment created successfully!");
            } else {
                System.out.println("Failed to create appointment.");
            }
        } catch (SQLException e) {
            System.out.println("Error while creating appointment: " + e.getMessage());
        }
    }

    public void updateAppointment() {
        System.out.println("Enter Appointment ID: ");
        int appointmentID = scan.nextInt();
        scan.nextLine(); 

        appointments appointment = searchAppointment(appointmentID);

        if (appointment == null) {
            System.out.println("Appointment not found.");
            return;
        }

        System.out.println("Enter new Vet Name: ");
        String newVetName = scan.nextLine();
        appointment.setVetName(newVetName);

        System.out.println("Enter new Vet ID: ");
        int newVetID = scan.nextInt();
        scan.nextLine(); 
        appointment.setVetID(newVetID);

        System.out.println("Enter new Chip ID: ");
        int newChipID = scan.nextInt();
        scan.nextLine(); 
        appointment.setChipID(newChipID);

        System.out.println("Enter new Pet Owner Name: ");
        String newPetOwnerName = scan.nextLine();
        appointment.setPetOwnerName(newPetOwnerName);

        System.out.println("Enter new Appointment Date (yyyy-mm-dd): ");
        String newAppointmentDateStr = scan.nextLine();
        Date newAppointmentDate = Date.valueOf(newAppointmentDateStr);
        appointment.setAppointmentDate(newAppointmentDate);

        System.out.println("Enter new Appointment Time (hh:mm:ss): ");
        String newAppointmentTimeStr = scan.nextLine();
        Time newAppointmentTime= Time.valueOf(newAppointmentTimeStr);
        appointment.setAppointmentTime(newAppointmentTime);

       

        System.out.println("Enter new Reason: ");
        String newReason = scan.nextLine();
        appointment.setReason(newReason);

        updateAppointmentInDatabase(appointment);
    }

    private void updateAppointmentInDatabase(appointments appointment) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE Appointments SET vet_name = ?, vet_id = ?, chip_id = ?, pet_owner_name = ?, " +
                    "appointment_datetime = ?, reason = ? WHERE appointment_id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, appointment.getVetName());
            statement.setInt(2, appointment.getVetID());
            statement.setInt(3, appointment.getChipID());
            statement.setString(4, appointment.getPetOwnerName());
            statement.setDate(5, appointment.getAppointmentDate());
            statement.setTime(6, appointment.getAppointmentTime());
            statement.setString(7, appointment.getReason());
           

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Appointment updated successfully!");
            } else {
                System.out.println("Failed to update appointment.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating appointment: " + e.getMessage());
        }
    }

    

    appointments searchAppointment(int appointmentID){
        appointments appointment = null;

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM Appointments WHERE appointment_id = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, appointmentID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int appointmentId= resultSet.getInt("appointment_id");
                String vetName = resultSet.getString("vet_name");
                int vetID = resultSet.getInt("vet_id");
                int chipID = resultSet.getInt("chip_id");
                String petOwnerName = resultSet.getString("pet_owner_name");
                Date appointmentDate = resultSet.getDate("appointment_date");
                Time appointmentTime = resultSet.getTime("appointment_time");
                String reason = resultSet.getString("reason");

                appointment = new appointments(appointmentId, vetName, vetID, chipID, petOwnerName, appointmentDate, appointmentTime, reason);
               
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error while searching for appointment: " + e.getMessage());
        }

        return appointment;
    }

    public void deleteAppointment() {
        System.out.println("Enter Appointment ID: ");
        int appointmentID = scan.nextInt();
        scan.nextLine(); 
    
        appointments appointment = searchAppointment(appointmentID);
    
        if (appointment == null) {
            System.out.println("Appointment not found.");
            return;
        }
    
        deleteAppointmentFromDatabase(appointmentID);
    }
    
    private void deleteAppointmentFromDatabase(int appointmentID) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "DELETE FROM Appointments WHERE appointment_id = ?";
    
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, appointmentID);
    
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Appointment deleted successfully!");
            } else {
                System.out.println("Failed to delete appointment.");
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting appointment: " + e.getMessage());
        }
    }
    //Controller_UG
    public void createSale() {
        System.out.println("Enter Sale Date (yyyy-mm-dd):");
        String saleDateStr = scan.nextLine();
        Date saleDate = Date.valueOf(saleDateStr);

        System.out.println("Enter Item ID:");
        int itemID = scan.nextInt();
        scan.nextLine(); // Consume the newline character

        System.out.println("Enter Quantity:");
        int quantity = scan.nextInt();
        scan.nextLine(); // Consume the newline character

        System.out.println("Enter Price:");
        float price = scan.nextFloat();
        scan.nextLine(); // Consume the newline character

        System.out.println("Enter Payment Method:");
        String paymentMethod = scan.nextLine();

        sales sale = new sales(saleDate, itemID, quantity, price, paymentMethod);
        saveSale(sale);
    }

    private void saveSale(sales sale) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO Sales (sale_date, item_id, quantity, price, payment_method) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDate(1, sale.getSaleDate());
            statement.setInt(2, sale.getItemID());
            statement.setInt(3, sale.getQuantity());
            statement.setFloat(4, sale.getPrice());
            statement.setString(5, sale.getPaymentMethod());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Sale saved successfully!");
            } else {
                System.out.println("Failed to save sale.");
            }
        } catch (SQLException e) {
            System.out.println("Error while saving sale: " + e.getMessage());
        }
    }
    public sales viewSales() {
        sales sales = null;
    
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM Sales";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
    
            while (resultSet.next()) {
                
                Date saleDate = resultSet.getDate("sale_date");
                int itemID = resultSet.getInt("item_id");
                int quantity = resultSet.getInt("quantity");
                float price = resultSet.getFloat("price");
                String paymentMethod = resultSet.getString("payment_method");
    
                sales = new sales( saleDate, itemID, quantity, price, paymentMethod);
            }
    
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error while viewing sales: " + e.getMessage());
        }
    
        return sales;
    }

    public void updateSale() {
        System.out.println("Enter Sale ID: ");
        int saleID = scan.nextInt();
        scan.nextLine(); 
    
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            
            String checkQuery = "SELECT * FROM Sales WHERE sale_id = ?";
            PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
            checkStatement.setInt(1, saleID);
            ResultSet checkResult = checkStatement.executeQuery();
    
            if (checkResult.next()) {
                
                System.out.println("Enter new Quantity:");
                int newQuantity = scan.nextInt();
                scan.nextLine();
    
                System.out.println("Enter new Price:");
                float newPrice = scan.nextFloat();
                scan.nextLine(); 
    
                System.out.println("Enter new Payment Method:");
                String newPaymentMethod = scan.nextLine();
    
                String updateQuery = "UPDATE Sales SET quantity = ?, price = ?, payment_method = ? WHERE sale_id = ?";
                PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                updateStatement.setInt(1, newQuantity);
                updateStatement.setFloat(2, newPrice);
                updateStatement.setString(3, newPaymentMethod);
                updateStatement.setInt(4, saleID);
    
                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Sale updated successfully!");
                } else {
                    System.out.println("Failed to update sale.");
                }
            } else {
                System.out.println("Sale not found with the provided Sale ID.");
            }
    
            checkResult.close();
            checkStatement.close();
        } catch (SQLException e) {
            System.out.println("Error while updating sale: " + e.getMessage());
        }
    }

    public boolean deleteSale(int saleID) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String checkQuery = "SELECT * FROM Sales WHERE sale_id = ?";
            PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
            checkStatement.setInt(1, saleID);
            ResultSet checkResult = checkStatement.executeQuery();
    
            if (checkResult.next()) {
                String deleteQuery = "DELETE FROM Sales WHERE sale_id = ?";
                PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
                deleteStatement.setInt(1, saleID);
    
                int rowsDeleted = deleteStatement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Sale deleted successfully!");
                    return true;
                } else {
                    System.out.println("Failed to delete sale.");
                    return false;
                }
            } else {
                System.out.println("Sale not found with the provided Sale ID.");
                return false;
            }
    
        } catch (SQLException e) {
            System.out.println("Error while deleting sale: " + e.getMessage());
            return false;
        }
    }

    public void searchSale() {
        System.out.println("Enter Item Name: ");
        String itemName = scan.nextLine();
    
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM Sales JOIN Inventory ON Sales.item_id = Inventory.item_id WHERE Inventory.item_name = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, itemName);
    
            ResultSet resultSet = statement.executeQuery();
    
            while (resultSet.next()) {
                int saleID = resultSet.getInt("sale_id");
                Date saleDate = resultSet.getDate("sale_date");
                int itemID = resultSet.getInt("item_id");
                int quantity = resultSet.getInt("quantity");
                float price = resultSet.getFloat("price");
                String paymentMethod = resultSet.getString("payment_method");
    
                System.out.println("Sale ID: " + saleID);
                System.out.println("Sale Date: " + saleDate);
                System.out.println("Item ID: " + itemID);
                System.out.println("Quantity: " + quantity);
                System.out.println("Price: " + price);
                System.out.println("Payment Method: " + paymentMethod);
                System.out.println("-----------------------------");
            }
    
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error while searching sales: " + e.getMessage());
        }
    }

    public void getTopSellingItems() {
        System.out.println("Enter the number of top-selling items to retrieve: ");
        int topItemCount = scan.nextInt();
        scan.nextLine(); 
    
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT Inventory.item_name, SUM(Sales.quantity) AS total_quantity " +
                    "FROM Sales JOIN Inventory ON Sales.item_id = Inventory.item_id " +
                    "GROUP BY Sales.item_id " +
                    "ORDER BY total_quantity DESC " +
                    "LIMIT ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, topItemCount);
    
            ResultSet resultSet = statement.executeQuery();
    
            System.out.println("Top " + topItemCount + " Selling Items:");
            int rank = 1;
            while (resultSet.next()) {
                String itemName = resultSet.getString("item_name");
                int totalQuantity = resultSet.getInt("total_quantity");
    
                System.out.println(rank + ". Item: " + itemName + ", Total Quantity Sold: " + totalQuantity);
                rank++;
            }
    
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error while retrieving top-selling items: " + e.getMessage());
        }
    }

    public void calculateTotalSales() {
        System.out.println("Enter the date (YYYY-MM-DD) for which you want to calculate total sales: ");
        String dateStr = scan.nextLine();
        Date saleDate = Date.valueOf(dateStr);
    
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT SUM(price) AS total_sales FROM Sales WHERE DATE(sale_date) = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDate(1, saleDate);
    
            ResultSet resultSet = statement.executeQuery();
    
            if (resultSet.next()) {
                float totalSales = resultSet.getFloat("total_sales");
    
                System.out.println("Total Sales for " + saleDate + ": " + totalSales);
            } else {
                System.out.println("No sales found for the specified date.");
            }
    
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error while calculating total sales: " + e.getMessage());
        }
    }
    //Controller_ZB
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
    
    public void updateCurrentDebt(int petChipID, float debtIncrement) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE payments SET currentDebt = currentDebt + ? WHERE petChipID = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setFloat(1, debtIncrement);
            statement.setInt(2, petChipID);
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Current debt updated successfully!");
            } else {
                System.out.println("Failed to update current debt.");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating current debt: " + e.getMessage());
        }
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

    public List<appointments> getAppointments() {
        return null;
    }
}
