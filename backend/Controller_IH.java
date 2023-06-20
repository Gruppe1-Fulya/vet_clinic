package vet_clinic;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Date;

public class Controller_IH {
	
	private final String url = "jdbc:mysql://localhost:3306/vet_clinic"; 
    private final String username = "admin"; 
    private final String password = "123456"; 
    
    Scanner scan=new Scanner(System.in);
    
	public void createInventory(inventory item) {
	
		System.out.println("Item ID:");
		int nItemID=scan.nextInt();
		
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
		
		inventory newInventory=new inventory(nItemID,nItemName,nDescription, nQuantity,nPrice,nExpirationDate, nSupplierID);
		
	    saveInventory(newInventory);
	
	}
	
    private void saveInventory(inventory item) {
		
		try (Connection conn = DriverManager.getConnection(url,username,password)){
			String query = "INSERT INTO inventory (item_id,item_name, description, quantity, price, expiration_date, supplier_id) VALUES (?)";
			
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, item.getItemID());
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
    
    public inventory searchInventory(int item_id) {
        
    	inventory item= null;
    	
    	System.out.println("Enter a Item ID:");
        int sItemID= scan.nextInt();
                
        try(Connection conn= DriverManager.getConnection(url, username,password)){
        	String query= "SELECT * FROM inventory WHERE item_id="+ sItemID;
        	
        	PreparedStatement statement= conn.prepareStatement(query);
        	statement.setInt(1,sItemID);
        	
        	ResultSet resultSet= statement.executeQuery();
        	
        	if(resultSet.next()) {
        		int retrievedItemID= resultSet.getInt("item_id");
        		String itemName = resultSet.getString("item_name");
        		String description=resultSet.getString("description");
        		int quantity= resultSet.getInt("quantity");
        		float price= resultSet.getFloat("price");
        		Date expiration_date =resultSet.getDate("expiration_date");
        		int supplier_id= resultSet.getInt("supplier_id");
        		        		
        		item= new inventory(retrievedItemID, itemName, description, quantity, price, expiration_date,supplier_id);
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
    	
    	int itemID=item.getItemID();
    	inventory uItem= searchInventory(itemID);
    	
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
    	int newSupplier_id=scan.nextInt();
    	uItem.setSupplier_id(newSupplier_id);
    	
    	
    	
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
    
    

}
