package vet_clinic;

import java.sql.Date;
import java.sql.Time;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Controller_TV {
	
	private final String url = "jdbc:mysql://localhost:3306/vet_clinic"; 
    private final String username = "admin"; 
    private final String password = "123456"; 
    
    Scanner scan=new Scanner(System.in);
	
    public void viewAppointments(Date startDate) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // Calculate the end date of the week
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_WEEK, 6);
            Date endDate = new Date(calendar.getTimeInMillis());

            // Prepare the SQL query
            String query = "SELECT appointment_date, appointment_time, vet_name, petowners_name, reason FROM Appointments " +
                    "WHERE appointment_date BETWEEN ? AND ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setDate(1, startDate);
            statement.setDate(2, endDate);

            // Execute the query and process the results
            ResultSet resultSet = statement.executeQuery();

            // Create a calendar grid to store appointments for each day and time slot
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            Calendar[][] calendarGrid = new Calendar[7][8]; // 7 days, 8 time slots

            // Initialize the calendar grid with empty slots
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    calendarGrid[i][j] = Calendar.getInstance();
                    calendarGrid[i][j].clear();
                }
            }

            // Fill the calendar grid with appointments
            while (resultSet.next()) {
                Date appointmentDate = resultSet.getDate("appointment_date");
                String appointmentTime = resultSet.getString("appointment_time");
                String vetName = resultSet.getString("vet_name");
                String petOwnersName = resultSet.getString("petowners_name");
                String reason = resultSet.getString("reason");

                // Find the day index in the calendar grid
                int dayIndex = getDayIndex(appointmentDate, startDate);

                // Find the time slot index in the calendar grid
                int timeSlotIndex = getTimeSlotIndex(appointmentTime);

                // Set the appointment details in the calendar grid
                calendarGrid[dayIndex][timeSlotIndex].setTime(appointmentDate);
                calendarGrid[dayIndex][timeSlotIndex].set(Calendar.HOUR_OF_DAY, Integer.parseInt(appointmentTime.substring(0, 2)));
                calendarGrid[dayIndex][timeSlotIndex].set(Calendar.MINUTE, Integer.parseInt(appointmentTime.substring(3)));

                // Display the appointment information
                System.out.println("Date: " + dateFormat.format(appointmentDate));
                System.out.println("Time: " + appointmentTime);
                System.out.println("Vet: " + vetName);
                System.out.println("Owner: " + petOwnersName);
                System.out.println("Reason: " + reason);
                System.out.println("--------------------");
            }

            // Display the calendar grid with appointments
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 8; j++) {
                    if (calendarGrid[i][j].getTimeInMillis() == 0) {
                        System.out.print("          |");
                    } else {
                        System.out.print(timeFormat.format(calendarGrid[i][j].getTime()) + " |");
                    }
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private int getDayIndex(Date appointmentDate, Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(appointmentDate);
        int appointmentDay = calendar.get(Calendar.DAY_OF_WEEK);

        calendar.setTime(startDate);
        int startDay = calendar.get(Calendar.DAY_OF_WEEK);

        return appointmentDay - startDay;
    }

    private int getTimeSlotIndex(String appointmentTime) {
        int hour = Integer.parseInt(appointmentTime.substring(0, 2));
        int minute = Integer.parseInt(appointmentTime.substring(3));

        if (hour < 9) {
            return 0; // Before 9 AM
        } else if (hour < 12 || (hour == 12 && minute == 0)) {
            return 1; // 9 AM - 12 PM
        } else if (hour < 15 || (hour == 15 && minute == 0)) {
            return 2; // 12 PM - 3 PM
        } else {
            return 3; // After 3 PM
        }
    }
    
    public void createAppointment(appointments appointment) {
        
    	System.out.println("Enter Veterinarian Name:");
    	String vetName=scan.nextLine();
    	
    	System.out.println("Enter Veterinarian ID:");
        int vetId = scan.nextInt();

        System.out.println("Enter Patient Chip ID:");
        int chipId = scan.nextInt();
        
        System.out.println("Enter Pet Owners Name:");
        String petOwnerName=scan.nextLine();

        System.out.println("Enter Appointment Date (DD-MM-YYYY):");
        String appointmentDate = scan.nextLine();
        Date nAppointmentDate = Date.valueOf(appointmentDate);
        
        System.out.println("Enter Appointment Time:");
        String appointmentTime= scan.nextLine();
        Time nAppointmentTime=Time.valueOf(appointmentTime);
        
        System.out.println("Appointment Reason:");
        String reason= scan.nextLine();

        

        appointments newAppointment = new appointments(vetName, vetId, chipId, petOwnerName, nAppointmentDate, nAppointmentTime, reason);

        if (isAvailableAppointment(newAppointment)) {
            saveAppointment(newAppointment);
        } else {
            System.out.println("Appointment slot is already booked for the veterinarian.");
        }
    }
    
    private boolean isAvailableAppointment(appointments appointment) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "SELECT * FROM Appointments WHERE vet_id = ? AND appointment_date = ? AND appointment_time= ??";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, appointment.getVetID());
            statement.setDate(2, appointment.getAppointmentDate());
            statement.setTime(3,appointment.getAppointmentTime());

            ResultSet resultSet = statement.executeQuery();
            
            
            return !resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error while checking appointment availability: " + e.getMessage());
            return false;
        }
    }
    
    private void saveAppointment(appointments appointment) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "INSERT INTO Appointments (vet_id, chip_id, appointment_date) VALUES (?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, appointment.getVetID());
            statement.setInt(2, appointment.getChipID());
            statement.setDate(3, appointment.getAppointmentDate());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Appointment created successfully!");
            } else {
                System.out.println("Failed to create appointment.");
            }
        } catch (SQLException e) {
            System.out.println("Error while saving appointment: " + e.getMessage());
        }
    }


    
    
    
    public void updateAppointment(appointments appointment) {
    	
    	System.out.println("Enter Veterinarian Name:");
    	String uVetName=scan.nextLine();
    	
    	System.out.println("Enter Veterinarian ID:");
        int uVetId = scan.nextInt();

        System.out.println("Enter Patient Chip ID:");
        int uChipId = scan.nextInt();
        
        System.out.println("Enter Pet Owners Name:");
        String uPetOwnerName=scan.nextLine();

        System.out.println("Enter Appointment Date (DD-MM-YYYY):");
        String uAppointmentDate = scan.nextLine();
        Date uDAppointmentDate = Date.valueOf(uAppointmentDate);
        
        System.out.println("Enter Appointment Time:");
        String uAppointmentTime= scan.nextLine();
        Time uTAppointmentTime=Time.valueOf(uAppointmentTime);
        
        System.out.println("Appointment Reason:");
        String uReason= scan.nextLine();
        
        appointments updatedAppointment = new appointments(uVetName, uVetId, uChipId,uPetOwnerName,uDAppointmentDate,uTAppointmentTime,uReason);

        if (updateExistingAppointment(updatedAppointment)) {
            System.out.println("Appointment updated successfully!");
        } else {
            System.out.println("Failed to update appointment.");
        }
    }
    
    private boolean updateExistingAppointment(appointments appointment) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String query = "UPDATE appointments SET vet_id = ?, chip_id = ?, appointment_date = ? WHERE appointment_id = ?";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, appointment.getVetName());
            statement.setInt(2, appointment.getVetID());
            statement.setInt(3, appointment.getChipID());
            statement.setString(4, appointment.getPetOwnerName());
            statement.setDate(5, appointment.getAppointmentDate());
            statement.setTime(6,appointment.getAppointmentTime());
            statement.setString(7,appointment.getReason());

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error while updating appointment: " + e.getMessage());
            return false;
        }
    }

    
    
    
    public void sendConfirmationNotification(appointments appointment) {
    }
    
    public void sendReminderNotification(appointments appointment) {
        
    }

}
