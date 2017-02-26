package ie.gmit.sw;

import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;


import javax.swing.text.*;
import javax.swing.text.TableView;
import javax.swing.text.html.ListView;
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
    @FXML private GridPane grid;
    @FXML private ScrollPane sp;

    // GUI element initialisation code goes here
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sp.setHmax(440);
        sp.setPrefSize(600, 100);
        //grid.setMinWidth(700);

        int newColIndex = 1;

        System.out.println("grid width: " + grid.getPrefWidth());

        try {

            // add a column in at a certain index
            for(int i = grid.getColumnConstraints().size() - 1; i >= newColIndex; i--){

                // get node from column
                Node n = getNodeByColumnIndex(new Integer(i), grid);

                // remove node to avoid dupes
                grid.getChildren().remove(n);

                // add node to next column to shift it up a index
                grid.add(n, i + 1, 0);

            } // for

           // grid.setPrefWidth(grid.getPrefWidth() + 200);

            // add new column constraint
            ColumnConstraints column1 = new ColumnConstraints(200);
            grid.getColumnConstraints().addAll(column1);


            // update the width of columns
            grid.add(new Label("Beans"), newColIndex, 0);

            // add a column in at a certain index
            for(int i = 0; i < grid.getColumnConstraints().size(); i++){

                ColumnConstraints columnConstraints = grid.getColumnConstraints().get(i);
                columnConstraints.setPrefWidth(100);
                columnConstraints.setHalignment(HPos.CENTER);

            } // for

        } catch (Exception e){

            e.printStackTrace();
        }


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

            System.out.println(t1);

            Label l = new Label("Hello");

            grid.getChildren().remove(getNodeByColumnIndex(new Integer(3), grid));
            grid.add(l, 3, 0);


        });

    } // initialize()

    public Node getNodeByColumnIndex (final Integer column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {

            if(column.equals(gridPane.getColumnIndex(node))) {

                result = node;
            }
        }

        return result;
    }

    public Integer getNodesColumnIndex(Node node){

        Integer index = null;

        // get the colymn index of the node, as Integer
        index = grid.getColumnIndex(node);

        return index;
    }


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
            Parameterable paramx = new ParameterImpl();
            paramx.setParameter(Double.parseDouble(param1.getText()), ParameterType.NUMBER);

            // save operator
            Parameterable op = new ParameterImpl();
            op.setParameter(operator.getSelectionModel().getSelectedItem().toString(), ParameterType.OPERATOR);

            // save parameter 2
            Parameterable paramy = new ParameterImpl();
            paramy.setParameter(Double.parseDouble(param2.getText()), ParameterType.NUMBER);

            Expression ex = new Expression();
            boolean b = ex.setExpression(paramx, paramy, op);
            rule.setExpression(ex);

        } catch (Exception e){

            // reset values
            param1.setText("");
            param2.setText("");
            errorLabel.setText("Error with parameters, enter numbers!");

        } // try

    } // evaluateBtn_OnAction()


} // class
