package package1;

import javafx.application.Application;
import javafx.stage.Stage;



public class VeterinarySystem extends Application {
    private DatabaseManager databaseManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        databaseManager = new DatabaseManager();

        VeterinarySystemGUI gui = new VeterinarySystemGUI(databaseManager);
        gui.displayMainScreen(primaryStage);
    }
}