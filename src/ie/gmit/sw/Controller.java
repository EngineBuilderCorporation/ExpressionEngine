package ie.gmit.sw;

import javafx.beans.value.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import java.net.URL;
import java.util.*;

/**
 * Created by Ross Byrne on 23/01/17.
 */
public class Controller implements Initializable {

    @FXML private Label resultLabel;
    @FXML private Label errorLabel;
    @FXML private TextField param1;
    @FXML private TextField param2;
    @FXML private ComboBox<String> operator;
    @FXML private Button evaluateBtn;

    // GUI element initialisation code goes here
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // set the options for the operators comboBox
        operator.setItems(FXCollections.observableArrayList(
            ">",
            "<",
            "=="
        ));

        // select the first option
        operator.getSelectionModel().selectFirst();

        // add changed listener
        operator.valueProperty().addListener((ov, t, t1) -> {

            //System.out.println(t1);
        });

    } // initialize()


    // button on click event handler
    @FXML void evaluateBtn_OnAction(){

        Ruleable rule = new RuleImpl();

        // clear error label
        errorLabel.setText("");

        try {
            // apply operator to both parameters
            switch (operator.getSelectionModel().getSelectedItem()) {
                case ">":

                    resultLabel.setText(Boolean.toString(Double.parseDouble(param1.getText()) > Double.parseDouble(param2.getText())));
                    break;
                case "<":

                    resultLabel.setText(Boolean.toString(Double.parseDouble(param1.getText()) < Double.parseDouble(param2.getText())));
                    break;
                case "==":

                    resultLabel.setText(Boolean.toString(Double.parseDouble(param1.getText()) == Double.parseDouble(param2.getText())));
                    break;
            } // switch

            // values are good, save

            // save parameter 1
            Parameterable param = new ParameterImpl();
            param.setParameter(Double.parseDouble(param1.getText()), ParameterType.NUMBER);
            rule.addParameter(param);

            // save operator
            param = new ParameterImpl();
            param.setParameter(operator.getSelectionModel().getSelectedItem().toString(), ParameterType.OPERATOR);
            rule.addParameter(param);

            // save parameter 2
            param = new ParameterImpl();
            param.setParameter(Double.parseDouble(param2.getText()), ParameterType.NUMBER);
            rule.addParameter(param);

            int size = rule.getRuleParameters().size();

            // print out parameters in rule
            for (int i = 0; i < size; i++) {

                System.out.println("Queue size: " + rule.getRuleParameters().size() + ". " + rule.getRuleParameters().remove().getParameter().toString());
            }

        } catch (Exception e){

            // reset values
            param1.setText("");
            param2.setText("");
            errorLabel.setText("Error with parameters, enter numbers!");

        } // try

    } // evaluateBtn_OnAction()


} // class
