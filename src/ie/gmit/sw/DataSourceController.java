package ie.gmit.sw;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.stage.*;
import java.net.URL;
import java.util.*;

/**
 * Created by Ross Byrne on 13/03/17.
 */
public class DataSourceController implements Initializable {

    @FXML private TableView table;
    @FXML private TableColumn<String, String> tableCol;
    @FXML private Button selectBtn;


    // initialise UI here
    public void initialize(URL location, ResourceBundle resources) {

        // set cell factory
        tableCol.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));

        // create list of items
        ObservableList<String> items = FXCollections.observableArrayList("Itachi", "Test", "Test2");

        // add items to table
        table.setItems(items);

    } // initialize()

    // the onClick listener for select button
    @FXML void selectBtn_OnAction(){

        System.out.println("Selected data source: " + table.getSelectionModel().getSelectedItem());

        // close the scene
        Stage stage = (Stage)selectBtn.getScene().getWindow();
        stage.close();

    } // selectBtn_OnAction()

} // class
