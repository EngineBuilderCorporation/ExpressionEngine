package ie.gmit.sw;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Ross Byrne on 13/03/17.
 */
public class DataSourceController implements Initializable {

    @FXML private Button addBtn;
    @FXML private Button selectBtn;

    // initialise UI here
    public void initialize(URL location, ResourceBundle resources) {

    } // initialize()

    // the onClick listener for add button
    @FXML void addBtn_OnAction(){

        System.out.println("Add data source");

    } // selectBtn_OnAction()

    // the onClick listener for select button
    @FXML void selectBtn_OnAction(){

        System.out.println("Selected data source");
        // close the scene
        Stage stage = (Stage)addBtn.getScene().getWindow();
        stage.close();

    } // selectBtn_OnAction()


} // class
