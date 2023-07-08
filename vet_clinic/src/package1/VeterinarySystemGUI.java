package package1;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VeterinarySystemGUI {
    private DatabaseManager databaseManager;

    public VeterinarySystemGUI(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void displayMainScreen(Stage primaryStage) {
        Button appointmentsButton = new Button("Appointments");
        appointmentsButton.setOnAction(event -> displayAppointmentsScreen(primaryStage));

        Button patientsButton = new Button("Patients");
        patientsButton.setOnAction(event -> displayPatientsScreen(primaryStage));

        Button inventoryButton = new Button("Inventory");
        inventoryButton.setOnAction(event -> displayInventoryScreen(primaryStage));

        Button salesRecordButton = new Button("Sales Record");
        salesRecordButton.setOnAction(event -> displaySalesRecordScreen(primaryStage));

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> Platform.exit());

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(appointmentsButton, patientsButton, inventoryButton, exitButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Veterinary Information System");
        primaryStage.show();
    }
    public void displayPatientsScreen(Stage primaryStage) {
        Button addPatientButton = new Button("Add Patient");
        addPatientButton.setOnAction(event -> displayAddPatientScreen(primaryStage));

        Button updatePatientButton = new Button("Update Patient");
        updatePatientButton.setOnAction(event -> displayUpdatePatientScreen(primaryStage));

        Button searchPatientButton = new Button("Search Patient");
        searchPatientButton.setOnAction(event -> displaySearchPatientScreen(primaryStage));

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayMainScreen(primaryStage));

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(addPatientButton, updatePatientButton, searchPatientButton, backButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Patients");
        primaryStage.show();
    }
    public void displayAddPatientScreen(Stage primaryStage) {
        TextField chipIDField = new TextField();
        chipIDField.setPromptText("Chip ID");
    
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
    
        TextField breedField = new TextField();
        breedField.setPromptText("Breed");
    
        TextField typeField = new TextField();
        typeField.setPromptText("Type");
    
        TextField colourField = new TextField();
        colourField.setPromptText("Colour");
    
        DatePicker dateOfBirthPicker = new DatePicker();
    
        TextField ageField = new TextField();
        ageField.setPromptText("Age");
    
        TextField ownerNameField = new TextField();
        ownerNameField.setPromptText("Owner Name");
    
        TextField contactNumberField = new TextField();
        contactNumberField.setPromptText("Contact Number");
    
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            int chipID = Integer.parseInt(chipIDField.getText());
            String name = nameField.getText();
            String breed = breedField.getText();
            String type = typeField.getText();
            String colour = colourField.getText();
            LocalDate dateOfBirth = dateOfBirthPicker.getValue();
            int age = Integer.parseInt(ageField.getText());
            String ownerName = ownerNameField.getText();
            String contactNumber = contactNumberField.getText();
    
            
            Date sqlDateOfBirth = Date.valueOf(dateOfBirth);
    
            Patients patient = new Patients(chipID, name, breed, type, colour, sqlDateOfBirth, age, ownerName, contactNumber);
            databaseManager.createPatientRecord(patient);
    
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Patient Added");
            alert.setHeaderText(null);
            alert.setContentText("Patient has been added successfully.");
            alert.showAndWait();
    
            displayPatientsScreen(primaryStage);
        });
    

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayPatientsScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Chip ID:"), 0, 0);
        gridPane.add(chipIDField, 1, 0);
        gridPane.add(new Label("Name:"), 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(new Label("Breed:"), 0, 2);
        gridPane.add(breedField, 1, 2);
        gridPane.add(new Label("Type:"), 0, 3);
        gridPane.add(typeField, 1, 3);
        gridPane.add(new Label("Colour:"), 0, 4);
        gridPane.add(colourField, 1, 4);
        gridPane.add(new Label("Date of Birth:"), 0, 5);
        gridPane.add(dateOfBirthPicker, 1, 5);
        gridPane.add(new Label("Age:"), 0, 6);
        gridPane.add(ageField, 1, 6);
        gridPane.add(new Label("Owner Name:"), 0, 7);
        gridPane.add(ownerNameField, 1, 7);
        gridPane.add(new Label("Contact Number:"), 0, 8);
        gridPane.add(contactNumberField, 1, 8);
        gridPane.add(saveButton, 0, 9);
        gridPane.add(backButton, 1, 9);

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Patient");
        primaryStage.show();
    }

    public void displayUpdatePatientScreen(Stage primaryStage) {
        TextField chipIDField = new TextField();
        chipIDField.setPromptText("Chip ID");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            int chipID = Integer.parseInt(chipIDField.getText());
            Patients patient = databaseManager.searchPatient(chipID);
            if (patient != null) {
                displayEditPatientScreen(primaryStage, patient);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Patient not found.");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayPatientsScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Chip ID:"), 0, 0);
        gridPane.add(chipIDField, 1, 0);
        gridPane.add(searchButton, 0, 1);
        gridPane.add(backButton, 1, 1);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Update Patient");
        primaryStage.show();
    }

    public void displayEditPatientScreen(Stage primaryStage, Patients patient) {
        TextField chipIDField = new TextField(Integer.toString(patient.getChipID()));
        chipIDField.setPromptText("Chip ID");
    
        TextField nameField = new TextField(patient.getName());
        nameField.setPromptText("Name");
    
        TextField breedField = new TextField(patient.getBreed());
        breedField.setPromptText("Breed");
    
        TextField typeField = new TextField(patient.getType());
        typeField.setPromptText("Type");
    
        TextField colourField = new TextField(patient.getColour());
        colourField.setPromptText("Colour");
    
        DatePicker dateOfBirthPicker = new DatePicker();
    
        
        LocalDate localDateOfBirth = patient.getDateOfBirth().toLocalDate();
        dateOfBirthPicker.setValue(localDateOfBirth);
    
        TextField ageField = new TextField(Integer.toString(patient.getAge()));
        ageField.setPromptText("Age");
    
        TextField ownerNameField = new TextField(patient.getOwnerName());
        ownerNameField.setPromptText("Owner Name");
    
        TextField contactNumberField = new TextField(patient.getContactNumber());
        contactNumberField.setPromptText("Contact Number");
    
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            int chipID = Integer.parseInt(chipIDField.getText());
            String name = nameField.getText();
            String breed = breedField.getText();
            String type = typeField.getText();
            String colour = colourField.getText();
            LocalDate dateOfBirth = dateOfBirthPicker.getValue();
            int age = Integer.parseInt(ageField.getText());
            String ownerName = ownerNameField.getText();
            String contactNumber = contactNumberField.getText();
    
            
            Date sqlDateOfBirth = Date.valueOf(dateOfBirth);
    
            patient.setChipID(chipID);
            patient.setName(name);
            patient.setBreed(breed);
            patient.setType(type);
            patient.setColour(colour);
            patient.setDateOfBirth(sqlDateOfBirth);
            patient.setAge(age);
            patient.setOwnerName(ownerName);
            patient.setContactNumber(contactNumber);
    
            databaseManager.updatePatientRecord(patient);
    
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Patient Updated");
            alert.setHeaderText(null);
            alert.setContentText("Patient record has been updated successfully.");
            alert.showAndWait();
    
            displayPatientsScreen(primaryStage);
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayPatientsScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Chip ID:"), 0, 0);
        gridPane.add(chipIDField, 1, 0);
        gridPane.add(new Label("Name:"), 0, 1);
        gridPane.add(nameField, 1, 1);
        gridPane.add(new Label("Breed:"), 0, 2);
        gridPane.add(breedField, 1, 2);
        gridPane.add(new Label("Type:"), 0, 3);
        gridPane.add(typeField, 1, 3);
        gridPane.add(new Label("Colour:"), 0, 4);
        gridPane.add(colourField, 1, 4);
        gridPane.add(new Label("Date of Birth:"), 0, 5);
        gridPane.add(dateOfBirthPicker, 1, 5);
        gridPane.add(new Label("Age:"), 0, 6);
        gridPane.add(ageField, 1, 6);
        gridPane.add(new Label("Owner Name:"), 0, 7);
        gridPane.add(ownerNameField, 1, 7);
        gridPane.add(new Label("Contact Number:"), 0, 8);
        gridPane.add(contactNumberField, 1, 8);
        gridPane.add(saveButton, 0, 9);
        gridPane.add(backButton, 1, 9);

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Edit Patient");
        primaryStage.show();
    }

    public void displaySearchPatientScreen(Stage primaryStage) {
        TextField chipIDField = new TextField();
        chipIDField.setPromptText("Chip ID");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            int chipID = Integer.parseInt(chipIDField.getText());
            Patients patient = databaseManager.searchPatient(chipID);
            if (patient != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Patient Found");
                alert.setHeaderText(null);
                alert.setContentText("Chip ID: " + patient.getChipID() + "\n" +
                        "Name: " + patient.getName() + "\n" +
                        "Breed: " + patient.getBreed() + "\n" +
                        "Type: " + patient.getType() + "\n" +
                        "Colour: " + patient.getColour() + "\n" +
                        "Date of Birth: " + patient.getDateOfBirth().toString() + "\n" +
                        "Age: " + patient.getAge() + "\n" +
                        "Owner Name: " + patient.getOwnerName() + "\n" +
                        "Contact Number: " + patient.getContactNumber());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Patient not found.");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayPatientsScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Chip ID:"), 0, 0);
        gridPane.add(chipIDField, 1, 0);
        gridPane.add(searchButton, 0, 1);
        gridPane.add(backButton, 1, 1);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search Patient");
        primaryStage.show();
    }

    public void displayAppointmentsScreen(Stage primaryStage) {
        Button addAppointmentButton = new Button("Add Appointment");
        addAppointmentButton.setOnAction(event -> displayAddAppointmentScreen(primaryStage));

        Button updateAppointmentButton = new Button("Update Appointment");
        updateAppointmentButton.setOnAction(event -> displayUpdateAppointmentScreen(primaryStage));

        Button searchAppointmentButton = new Button("Search Appointment");
        searchAppointmentButton.setOnAction(event -> displaySearchAppointmentScreen(primaryStage));

        Button viewCalendarButton = new Button("View Calendar");
        viewCalendarButton.setOnAction(event -> displayCalendarScreen(primaryStage));

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayMainScreen(primaryStage));

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(addAppointmentButton, updateAppointmentButton, searchAppointmentButton,
                viewCalendarButton, backButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Appointments");
        primaryStage.show();
    }

    public void displayAddAppointmentScreen(Stage primaryStage) {
        TextField appointmentIDField = new TextField();
        appointmentIDField.setPromptText("Appointment ID");

        TextField vetNameField = new TextField();
        vetNameField.setPromptText("Veterinarian Name");

        TextField vetIDField = new TextField();
        vetIDField.setPromptText("Veterinarian ID");

        TextField chipIDField = new TextField();
        chipIDField.setPromptText("Chip ID");

        TextField petOwnerNameField = new TextField();
        petOwnerNameField.setPromptText("Pet Owner Name");

        DatePicker appointmentDatePicker = new DatePicker();

        TextField appointmentTimeField = new TextField();
        appointmentTimeField.setPromptText("Appointment Time (HH:MM)");

        TextField reasonField = new TextField();
        reasonField.setPromptText("Reason");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            int appointmentID = Integer.parseInt(appointmentIDField.getText());
            String vetName = vetNameField.getText();
            int vetID = Integer.parseInt(vetIDField.getText());
            int chipID = Integer.parseInt(chipIDField.getText());
            String petOwnerName = petOwnerNameField.getText();
            LocalDate appointmentDate = appointmentDatePicker.getValue();
            String appointmentTimeString = appointmentTimeField.getText();
            Time appointmentTime = Time.valueOf(appointmentTimeString + ":00");
            String reason = reasonField.getText();

            new appointments(appointmentID, vetName, vetID, chipID, petOwnerName,
                    Date.valueOf(appointmentDate), appointmentTime, reason);
            databaseManager.createAppointment();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Added");
            alert.setHeaderText(null);
            alert.setContentText("Appointment has been added successfully.");
            alert.showAndWait();

            displayAppointmentsScreen(primaryStage);
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayAppointmentsScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Appointment ID:"), 0, 0);
        gridPane.add(appointmentIDField, 1, 0);
        gridPane.add(new Label("Veterinarian Name:"), 0, 1);
        gridPane.add(vetNameField, 1, 1);
        gridPane.add(new Label("Veterinarian ID:"), 0, 2);
        gridPane.add(vetIDField, 1, 2);
        gridPane.add(new Label("Chip ID:"), 0, 3);
        gridPane.add(chipIDField, 1, 3);
        gridPane.add(new Label("Pet Owner Name:"), 0, 4);
        gridPane.add(petOwnerNameField, 1, 4);
        gridPane.add(new Label("Appointment Date:"), 0, 5);
        gridPane.add(appointmentDatePicker, 1, 5);
        gridPane.add(new Label("Appointment Time (HH:MM):"), 0, 6);
        gridPane.add(appointmentTimeField, 1, 6);
        gridPane.add(new Label("Reason:"), 0, 7);
        gridPane.add(reasonField, 1, 7);
        gridPane.add(saveButton, 0, 8);
        gridPane.add(backButton, 1, 8);

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Appointment");
        primaryStage.show();
    }

    public void displayUpdateAppointmentScreen(Stage primaryStage) {
        TextField appointmentIDField = new TextField();
        appointmentIDField.setPromptText("Appointment ID");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            int appointmentID = Integer.parseInt(appointmentIDField.getText());
            appointments appointment = databaseManager.searchAppointment(appointmentID);
            if (appointment != null) {
                displayEditAppointmentScreen(primaryStage, appointment);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Appointment not found.");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayAppointmentsScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Appointment ID:"), 0, 0);
        gridPane.add(appointmentIDField, 1, 0);
        gridPane.add(searchButton, 0, 1);
        gridPane.add(backButton, 1, 1);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Update Appointment");
        primaryStage.show();
    }

    public void displayEditAppointmentScreen(Stage primaryStage, appointments appointment) {
        TextField appointmentIDField = new TextField(Integer.toString(appointment.getAppointmentID()));
        appointmentIDField.setPromptText("Appointment ID");

        TextField vetNameField = new TextField(appointment.getVetName());
        vetNameField.setPromptText("Veterinarian Name");

        TextField vetIDField = new TextField(Integer.toString(appointment.getVetID()));
        vetIDField.setPromptText("Veterinarian ID");

        TextField chipIDField = new TextField(Integer.toString(appointment.getChipID()));
        chipIDField.setPromptText("Chip ID");

        TextField petOwnerNameField = new TextField(appointment.getPetOwnerName());
        petOwnerNameField.setPromptText("Pet Owner Name");

        DatePicker appointmentDatePicker = new DatePicker();

       
        LocalDate localAppointmentDate = appointment.getAppointmentDate().toLocalDate();
        appointmentDatePicker.setValue(localAppointmentDate);

        TextField appointmentTimeField = new TextField(appointment.getAppointmentTime().toString());
        appointmentTimeField.setPromptText("Appointment Time (HH:MM)");

        TextField reasonField = new TextField(appointment.getReason());
        reasonField.setPromptText("Reason");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            int appointmentID = Integer.parseInt(appointmentIDField.getText());
            String vetName = vetNameField.getText();
            int vetID = Integer.parseInt(vetIDField.getText());
            int chipID = Integer.parseInt(chipIDField.getText());
            String petOwnerName = petOwnerNameField.getText();
            LocalDate appointmentDate = appointmentDatePicker.getValue();
            String appointmentTimeString = appointmentTimeField.getText();
            Time appointmentTime = Time.valueOf(appointmentTimeString + ":00");
            String reason = reasonField.getText();

            appointment.setAppointmentId(appointmentID);
            appointment.setVetName(vetName);
            appointment.setVetID(vetID);
            appointment.setChipID(chipID);
            appointment.setPetOwnerName(petOwnerName);
            appointment.setAppointmentDate(Date.valueOf(appointmentDate));
            appointment.setAppointmentTime(appointmentTime);
            appointment.setReason(reason);

            databaseManager.updateAppointment();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointment Updated");
            alert.setHeaderText(null);
            alert.setContentText("Appointment record has been updated successfully.");
            alert.showAndWait();

            displayAppointmentsScreen(primaryStage);
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayAppointmentsScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Appointment ID:"), 0, 0);
        gridPane.add(appointmentIDField, 1, 0);
        gridPane.add(new Label("Veterinarian Name:"),0, 1);
        gridPane.add(vetNameField, 1, 1);
        gridPane.add(new Label("Veterinarian ID:"), 0, 2);
        gridPane.add(vetIDField, 1, 2);
        gridPane.add(new Label("Chip ID:"), 0, 3);
        gridPane.add(chipIDField, 1, 3);
        gridPane.add(new Label("Pet Owner Name:"), 0, 4);
        gridPane.add(petOwnerNameField, 1, 4);
        gridPane.add(new Label("Appointment Date:"), 0, 5);
        gridPane.add(appointmentDatePicker, 1, 5);
        gridPane.add(new Label("Appointment Time (HH:MM):"), 0, 6);
        gridPane.add(appointmentTimeField, 1, 6);
        gridPane.add(new Label("Reason:"), 0, 7);
        gridPane.add(reasonField, 1, 7);
        gridPane.add(saveButton, 0, 8);
        gridPane.add(backButton, 1, 8);

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Edit Appointment");
        primaryStage.show();
    }

    public void displaySearchAppointmentScreen(Stage primaryStage) {
        TextField appointmentIDField = new TextField();
        appointmentIDField.setPromptText("Appointment ID");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            int appointmentID = Integer.parseInt(appointmentIDField.getText());
            appointments appointment = databaseManager.searchAppointment(appointmentID);
            if (appointment != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Appointment Found");
                alert.setHeaderText(null);
                alert.setContentText("Appointment ID: " + appointment.getAppointmentID() + "\n" +
                        "Veterinarian Name: " + appointment.getVetName() + "\n" +
                        "Veterinarian ID: " + appointment.getVetID() + "\n" +
                        "Chip ID: " + appointment.getChipID() + "\n" +
                        "Pet Owner Name: " + appointment.getPetOwnerName() + "\n" +
                        "Appointment Date: " + appointment.getAppointmentDate().toString() + "\n" +
                        "Appointment Time: " + appointment.getAppointmentTime().toString() + "\n" +
                        "Reason: " + appointment.getReason());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Appointment not found.");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayAppointmentsScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Appointment ID:"), 0, 0);
        gridPane.add(appointmentIDField, 1, 0);
        gridPane.add(searchButton, 0, 1);
        gridPane.add(backButton, 1, 1);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search Appointment");
        primaryStage.show();
    }


    public void displayInventoryScreen(Stage primaryStage) {
        Button addItemButton = new Button("Add Item");
        addItemButton.setOnAction(event -> displayAddItemScreen(primaryStage));

        Button searchItemButton = new Button("Search Item");
        searchItemButton.setOnAction(event -> displaySearchItemScreen(primaryStage));

        Button updateItemButton = new Button("Update Item");
        updateItemButton.setOnAction(event -> displayUpdateItemScreen(primaryStage));

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayMainScreen(primaryStage));

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(addItemButton, searchItemButton, updateItemButton, backButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Inventory");
        primaryStage.show();
    }

    public void displayAddItemScreen(Stage primaryStage) {
        TextField itemNameField = new TextField();
        itemNameField.setPromptText("Item Name");

        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");

        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        TextField priceField = new TextField();
        priceField.setPromptText("Price");

        DatePicker expirationDatePicker = new DatePicker();

        TextField supplierIDField = new TextField();
        supplierIDField.setPromptText("Supplier ID");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            String itemName = itemNameField.getText();
            String description = descriptionField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            float price = Float.parseFloat(priceField.getText());
            LocalDate expirationDate = expirationDatePicker.getValue();
            int supplierID = Integer.parseInt(supplierIDField.getText());

            // Convert LocalDate to java.sql.Date
            Date sqlExpirationDate = Date.valueOf(expirationDate);

            inventory item = new inventory(itemName, description, quantity, price, sqlExpirationDate, supplierID);
            databaseManager.createInventory(item);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Item Added");
            alert.setHeaderText(null);
            alert.setContentText("Item has been added successfully.");
            alert.showAndWait();

            displayInventoryScreen(primaryStage);
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayInventoryScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Item Name:"), 0, 0);
        gridPane.add(itemNameField, 1, 0);
        gridPane.add(new Label("Description:"), 0, 1);
        gridPane.add(descriptionField, 1, 1);
        gridPane.add(new Label("Quantity:"), 0, 2);
        gridPane.add(quantityField, 1, 2);
        gridPane.add(new Label("Price:"), 0, 3);
        gridPane.add(priceField, 1, 3);
        gridPane.add(new Label("Expiration Date:"), 0, 4);
        gridPane.add(expirationDatePicker, 1, 4);
        gridPane.add(new Label("Supplier ID:"), 0, 5);
        gridPane.add(supplierIDField, 1, 5);
        gridPane.add(saveButton, 0, 6);
        gridPane.add(backButton, 1, 6);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Item");
        primaryStage.show();
    }

    public void displaySearchItemScreen(Stage primaryStage) {
        TextField itemNameField = new TextField();
        itemNameField.setPromptText("Item Name");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            String itemName = itemNameField.getText();
            List<inventory> items = databaseManager.searchInventory(itemName);
            if (!items.isEmpty()) {
                StringBuilder result = new StringBuilder();
                for (inventory item : items) {
                    result.append("Item Name: ").append(item.getItemName()).append("\n")
                            .append("Description: ").append(item.getDescription()).append("\n")
                            .append("Quantity: ").append(item.getQuantity()).append("\n")
                            .append("Price: ").append(item.getPrice()).append("\n")
                            .append("Expiration Date: ").append(item.getExpirationDate().toString()).append("\n")
                            .append("Supplier ID: ").append(item.getSupplierID()).append("\n\n");
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Search Result");
                alert.setHeaderText(null);
                alert.setContentText(result.toString());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("No items found.");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayInventoryScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Item Name:"), 0, 0);
        gridPane.add(itemNameField, 1, 0);
        gridPane.add(searchButton, 0, 1);
        gridPane.add(backButton, 1, 1);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Search Item");
        primaryStage.show();
    }

    public void displayUpdateItemScreen(Stage primaryStage) {
        TextField itemNameField = new TextField();
        itemNameField.setPromptText("Item Name");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            String itemName = itemNameField.getText();
            inventory item = databaseManager.searchInventory(itemName);
            if (item != null){
                displayEditItemScreen(primaryStage, item);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Item not found.");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayInventoryScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Item Name:"), 0, 0);
        gridPane.add(itemNameField, 1, 0);
        gridPane.add(searchButton, 0, 1);
        gridPane.add(backButton, 1, 1);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Update Item");
        primaryStage.show();
    }

    public void displayEditItemScreen(Stage primaryStage, inventory item) {
        TextField itemNameField = new TextField(item.getItemName());
        itemNameField.setPromptText("Item Name");

        TextField descriptionField = new TextField(item.getDescription());
        descriptionField.setPromptText("Description");

        TextField quantityField = new TextField(Integer.toString(item.getQuantity()));
        quantityField.setPromptText("Quantity");

        TextField priceField = new TextField(Float.toString(item.getPrice()));
        priceField.setPromptText("Price");

        DatePicker expirationDatePicker = new DatePicker();

        
        LocalDate localExpirationDate = item.getExpirationDate().toLocalDate();
        expirationDatePicker.setValue(localExpirationDate);

        TextField supplierIDField = new TextField(Integer.toString(item.getSupplierID()));
        supplierIDField.setPromptText("Supplier ID");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            String itemName = itemNameField.getText();
            String description = descriptionField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            float price = Float.parseFloat(priceField.getText());
            LocalDate expirationDate = expirationDatePicker.getValue();
            int supplierID = Integer.parseInt(supplierIDField.getText());

           
            Date sqlExpirationDate = Date.valueOf(expirationDate);

            item.setItemName(itemName);
            item.setDescription(description);
            item.setQuantity(quantity);
            item.setPrice(price);
            item.setExpirationDate(sqlExpirationDate);
            item.setSupplierID(supplierID);

            databaseManager.updateInventory(item);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Item Updated");
            alert.setHeaderText(null);
            alert.setContentText("Item record has been updated successfully.");
            alert.showAndWait();

            displayInventoryScreen(primaryStage);
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayInventoryScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Item Name:"), 0, 0);
        gridPane.add(itemNameField, 1, 0);
        gridPane.add(new Label("Description:"), 0, 1);
        gridPane.add(descriptionField, 1, 1);
        gridPane.add(new Label("Quantity:"), 0, 2);
        gridPane.add(quantityField, 1, 2);
        gridPane.add(new Label("Price:"), 0, 3);
        gridPane.add(priceField, 1, 3);
        gridPane.add(new Label("Expiration Date:"), 0, 4);
        gridPane.add(expirationDatePicker, 1, 4);
        gridPane.add(new Label("Supplier ID:"), 0, 5);
        gridPane.add(supplierIDField, 1, 5);
        gridPane.add(saveButton, 0, 6);
        gridPane.add(backButton, 1, 6);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Edit Item");
        primaryStage.show();
    }

    public void displaySalesRecordScreen(Stage primaryStage) {
        Button addSaleButton = new Button("Add Sale");
        addSaleButton.setOnAction(event -> displayAddSaleScreen(primaryStage));

        Button viewSaleButton = new Button("View Sale");
        viewSaleButton.setOnAction(event -> displayViewSaleScreen(primaryStage));

        Button updateSaleButton = new Button("Update Sale");
        updateSaleButton.setOnAction(event -> displayUpdateSaleScreen(primaryStage));

        Button deleteSaleButton = new Button("Delete Sale");
        deleteSaleButton.setOnAction(event -> displayDeleteSaleScreen(primaryStage));

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayMainScreen(primaryStage));

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(addSaleButton, viewSaleButton, updateSaleButton, deleteSaleButton, backButton);

        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sales Record");
        primaryStage.show();
    }

    public void displayAddSaleScreen(Stage primaryStage) {
        DatePicker saleDatePicker = new DatePicker();

        TextField itemIDField = new TextField();
        itemIDField.setPromptText("Item ID");

        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        TextField priceField = new TextField();
        priceField.setPromptText("Price");

        TextField paymentMethodField = new TextField();
        paymentMethodField.setPromptText("Payment Method");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            LocalDate saleDate = saleDatePicker.getValue();
            int itemID = Integer.parseInt(itemIDField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            float price = Float.parseFloat(priceField.getText());
            String paymentMethod = paymentMethodField.getText();

            
            Date sqlSaleDate = Date.valueOf(saleDate);

            new sales(sqlSaleDate, itemID, quantity, price, paymentMethod);
            databaseManager.createSale();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sale Added");
            alert.setHeaderText(null);
            alert.setContentText("Sale has been added successfully.");
            alert.showAndWait();

            displaySalesRecordScreen(primaryStage);
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displaySalesRecordScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Sale Date:"), 0, 0);
        gridPane.add(saleDatePicker, 1, 0);
        gridPane.add(new Label("Item ID:"), 0, 1);
        gridPane.add(itemIDField, 1, 1);
        gridPane.add(new Label("Quantity:"), 0, 2);
        gridPane.add(quantityField, 1, 2);
        gridPane.add(new Label("Price:"), 0, 3);
        gridPane.add(priceField, 1, 3);
        gridPane.add(new Label("Payment Method:"), 0, 4);
        gridPane.add(paymentMethodField, 1, 4);
        gridPane.add(saveButton, 0, 5);
        gridPane.add(backButton, 1, 5);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Add Sale");
        primaryStage.show();
    }

    public void displayViewSaleScreen(Stage primaryStage) {
        TextField saleIDField = new TextField();
        saleIDField.setPromptText("Sale ID");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            sales sale = databaseManager.viewSales();
            if (sale != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sale Information");
                alert.setHeaderText(null);
                alert.setContentText(
                        "Sale Date: " + sale.getSaleDate().toString() + "\n" +
                        "Item ID: " + sale.getItemID() + "\n" +
                        "Quantity: " + sale.getQuantity() + "\n" +
                        "Price: " + sale.getPrice() + "\n" +
                        "Payment Method: " + sale.getPaymentMethod());
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Sale not found.");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displaySalesRecordScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Sale ID:"), 0, 0);
        gridPane.add(saleIDField, 1, 0);
        gridPane.add(searchButton, 0, 1);
        gridPane.add(backButton, 1, 1);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("View Sale");
        primaryStage.show();
    }

    public void displayUpdateSaleScreen(Stage primaryStage) {
        TextField saleIDField = new TextField();
        saleIDField.setPromptText("Sale ID");

        Button searchButton = new Button("Search");
        searchButton.setOnAction(event -> {
            sales sale = databaseManager.viewSales();
            if (sale !=null) {
                displayEditSaleScreen(primaryStage, sale);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Sale not found.");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displaySalesRecordScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Sale ID:"), 0, 0);
        gridPane.add(saleIDField, 1, 0);
        gridPane.add(searchButton, 0, 1);
        gridPane.add(backButton, 1, 1);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Update Sale");
        primaryStage.show();
    }

    public void displayEditSaleScreen(Stage primaryStage, sales sale) {
        DatePicker saleDatePicker = new DatePicker();

        // Convert java.sql.Date to LocalDate
        LocalDate localSaleDate = sale.getSaleDate().toLocalDate();
        saleDatePicker.setValue(localSaleDate);

        TextField itemIDField = new TextField(Integer.toString(sale.getItemID()));
        itemIDField.setPromptText("Item ID");

        TextField quantityField = new TextField(Integer.toString(sale.getQuantity()));
        quantityField.setPromptText("Quantity");

        TextField priceField = new TextField(Float.toString(sale.getPrice()));
        priceField.setPromptText("Price");

        TextField paymentMethodField = new TextField(sale.getPaymentMethod());
        paymentMethodField.setPromptText("Payment Method");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
            LocalDate saleDate = saleDatePicker.getValue();
            int itemID = Integer.parseInt(itemIDField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            float price = Float.parseFloat(priceField.getText());
            String paymentMethod = paymentMethodField.getText();

            // Convert LocalDate to java.sql.Date
            Date sqlSaleDate = Date.valueOf(saleDate);

            sale.setSaleDate(sqlSaleDate);
            sale.setItemID(itemID);
            sale.setQuantity(quantity);
            sale.setPrice(price);
            sale.setPaymentMethod(paymentMethod);

            databaseManager.updateSale();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sale Updated");
            alert.setHeaderText(null);
            alert.setContentText("Sale record has been updated successfully.");
            alert.showAndWait();

            displaySalesRecordScreen(primaryStage);
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displaySalesRecordScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Sale Date:"), 0, 0);
        gridPane.add(saleDatePicker, 1, 0);
        gridPane.add(new Label("Item ID:"), 0, 1);
        gridPane.add(itemIDField, 1, 1);
        gridPane.add(new Label("Quantity:"), 0, 2);
        gridPane.add(quantityField, 1, 2);
        gridPane.add(new Label("Price:"), 0, 3);
        gridPane.add(priceField, 1, 3);
        gridPane.add(new Label("Payment Method:"), 0, 4);
        gridPane.add(paymentMethodField, 1, 4);
        gridPane.add(saveButton, 0, 5);
        gridPane.add(backButton, 1, 5);

        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Edit Sale");
        primaryStage.show();
    }

    public void displayDeleteSaleScreen(Stage primaryStage) {
        TextField saleIDField = new TextField();
        saleIDField.setPromptText("Sale ID");

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            int saleID = Integer.parseInt(saleIDField.getText());
            boolean result = databaseManager.deleteSale(saleID);
            if (result) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Sale Deleted");
                alert.setHeaderText(null);
                alert.setContentText("Sale record has been deleted successfully.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Sale not found.");
                alert.showAndWait();
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displaySalesRecordScreen(primaryStage));

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20));
        gridPane.add(new Label("Sale ID:"), 0, 0);
        gridPane.add(saleIDField, 1, 0);
        gridPane.add(deleteButton, 0, 1);
        gridPane.add(backButton, 1, 1);

        Scene scene = new Scene(gridPane, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Delete Sale");
        primaryStage.show();
    }

    public void displayCalendarScreen(Stage primaryStage) {
        List<appointments> appointments = databaseManager.getAppointments();
    
        GridPane calendarGrid = new GridPane();
        calendarGrid.setAlignment(Pos.CENTER);
        calendarGrid.setHgap(10);
        calendarGrid.setVgap(10);
        calendarGrid.setPadding(new Insets(20));
    
        
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = currentDate.plusDays(6);
        int col = 0;
        for (LocalDate date = currentDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            Label dateLabel = new Label(date.format(DateTimeFormatter.ofPattern("EEE, dd MMM")));
            calendarGrid.add(dateLabel, col, 0);
            col++;
        }
    
        
        col = 0;
        for (LocalDate date = currentDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            for (appointments appointment : appointments) {
                Date appointmentDate = appointment.getAppointmentDate();
                LocalDate localDate = appointmentDate.toLocalDate();
                if (localDate.equals(date)) {
                    Button appointmentButton = new Button(appointmentDate.toString());
                    appointmentButton.setOnAction(event -> showAppointmentDetails(appointment));
                    calendarGrid.add(appointmentButton, col, localDate.getDayOfMonth());
                }
            }
            col++;
        }
    
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> displayAppointmentsScreen(primaryStage));
    
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(calendarGrid, backButton);
    
        VBox.setVgrow(calendarGrid, Priority.ALWAYS);
    
        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Appointment Calendar");
        primaryStage.show();
    }
    private void showAppointmentDetails(appointments appointment) {
        appointments fetchedAppointment = databaseManager.searchAppointment(appointment.getAppointmentID());

    if (fetchedAppointment == null) {
        
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Appointment Not Found");
        alert.setContentText("The appointment details could not be retrieved.");
        alert.showAndWait();
        return;
    }
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Appointment Details");
        alert.setHeaderText("Appointment ID: " + fetchedAppointment.getAppointmentID());;
        alert.setContentText("Vet Name:" + appointment.getVetName()+ "\n" +
                             "Vet ID:"  + appointment.getVetID()+"\n" +
                             "Chip ID:" + appointment.getChipID()+"\n" +
                             "Owners Name: " + appointment.getPetOwnerName()+"\n"+
                             "Reason" + appointment.getReason()+"\n");
    
        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);
    
        alert.showAndWait();
    }
}


    

