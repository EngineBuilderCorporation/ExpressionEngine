package ie.gmit.sw;

import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

/**
 * Created by Ross Byrne on 23/01/17.
 */
public class Main extends Application {

    // sets up the JavaFX GUI
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Expression Builder");
        primaryStage.setScene(new Scene(root, 800, 575));
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.show();

    } // start

    // main method launches the JavaFX application
    public static void main(String[] args) {

        // start the JavaFX application
        launch(args);

    } // main()
}
