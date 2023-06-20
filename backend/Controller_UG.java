package vet_clinic;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
public class Controller_UG {
	
	private final String url = "jdbc:mysql://localhost:3306/vet_clinic"; 
    private final String username = "admin"; 
    private final String password = "123456"; 
    
    Scanner scanner = new Scanner(System.in);
	
	public void addSale() {          

            System.out.println("Enter sale date (YYYY-MM-DD):");
            String saleDateStr = scanner.nextLine();
            Date saleDate = Date.valueOf(saleDateStr);

            System.out.println("Enter item name:");
            String itemName = scanner.nextLine();

            System.out.println("Enter item ID:");
            int itemID = scanner.nextInt();

            System.out.println("Enter quantity:");
            int quantity = scanner.nextInt();

            System.out.println("Enter total price:");
            float price = scanner.nextFloat();

            System.out.println("Enter payment method:");
            String paymentMethod = scanner.nextLine();
            
            sales newSale= new sales(saleDate,itemName,itemID, quantity,price, paymentMethod);
            
            saveSale(newSale);

           
    }
    
    public void saveSale(sales sale) {
    	 
    	try (Connection conn = DriverManager.getConnection(url,username,password)){
    		String query = "INSERT INTO sales (sale_date, item_name, item_id, quantity, total_price, payment_method) " +
                 "VALUES (?, ?, ?, ?, ?, ?)";

    		PreparedStatement statement = conn.prepareStatement(query);
            statement.setDate(1, sale.getSaleDate());
            statement.setString(2, sale.getItemName());
            statement.setInt(3, sale.getItemID());
            statement.setInt(4, sale.getQuantity());
            statement.setDouble(5, sale.getPrice());
            statement.setString(6, sale.getPaymentMethod());
            
         int rowsInserted = statement.executeUpdate();
         if (rowsInserted > 0) {
             System.out.println("Sale added successfully!");
         } else {
             System.out.println("Failed to add sale.");
         }
     } catch (SQLException e) {
         System.out.println("Error while adding sale: " + e.getMessage());
     }
    }
    
    public List<sales> getAllSales() {
        List<sales> sales = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM sales";

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Date saleDate = resultSet.getDate("sale_date");
                String itemName = resultSet.getString("item_name");
                int itemId = resultSet.getInt("item_id");
                int quantity = resultSet.getInt("quantity");
                float totalPrice = resultSet.getFloat("total_price");
                String paymentMethod = resultSet.getString("payment_method");

                sales sale = new sales( saleDate, itemName, itemId, quantity, totalPrice, paymentMethod);
                sales.add(sale);
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving sales: " + e.getMessage());
        }

        return sales;
    }
}
