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

        sp.setPrefSize(700, 100);

        // add new columns
        addColumn(0, new Label("1"));
        addColumn(1, new Label("2"));
        addColumn(2, new Label("3"));
        addColumn(3, new Label("4"));
        addColumn(4, new Label("5"));
        addColumn(5, new Label("6"));
        addColumn(6, new Label("7"));

        UIExpressionTree root = new UIExpressionTree(this);

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

            // update the contents of a column

            Label l = new Label("Hello");

//            Node n = l;
//
//            if(n instanceof Label){
//
//                System.out.println("Hot dog, it's a Label");
//            }

            // add a new column at index 4
            addColumn(4, l);

        });

    } // initialize()

    // replaces the contents of a column
    public void updateColumn(int index, Node node, double width){

        // get node from column
        Node n = getNodeByColumnIndex(new Integer(index), grid);

        // remove node from children to avoid dupes (throws exception otherwise)
        grid.getChildren().remove(n);

        // add node to next column to shift its index up by one
        grid.add(node, index, 0);

        // set the width of the column
        grid.getColumnConstraints().get(index).setPrefWidth(width);

    }

    // adds a column at selected index with provided node
    public void addColumn(int index, Node node){

        // add a column in at a certain index
        try {

            // loop through each column, starting at the end (right hand side)
            // loop until selected index, shifting to right, by one index
            for(int i = grid.getColumnConstraints().size() - 1; i >= index; i--){

                // get node from column
                Node n = getNodeByColumnIndex(new Integer(i), grid);

                // remove node from children to avoid dupes (throws exception otherwise)
                grid.getChildren().remove(n);

                // add node to next column to shift its index up by one
                grid.add(n, i + 1, 0);

            } // for

            // create new column constraint
            ColumnConstraints col = new ColumnConstraints(200);

            // add it to the new end column in grid
            grid.getColumnConstraints().addAll(col);

            // insert new node into selected index
            grid.add(node, index, 0);

            double gridSize = 0;

            // update the width of each column
            for(int i = 0; i < grid.getColumnConstraints().size(); i++){

                // get column constraint
                ColumnConstraints columnConstraints = grid.getColumnConstraints().get(i);

                // set the columns width
                columnConstraints.setPrefWidth(100);
                columnConstraints.setHgrow(Priority.NEVER);

                // center the contents of column
                columnConstraints.setHalignment(HPos.CENTER);

                // calculate the size of the grid
                gridSize += columnConstraints.getPrefWidth();

            } // for

            // update the width of the grid
            grid.setPrefWidth(gridSize);

        } catch (Exception e){

            e.printStackTrace();
        } // try

    } // addColumn()

    // gets a node by its index from a gridpane
    public Node getNodeByColumnIndex (final Integer column, GridPane gridPane) {

        Node result = null;
        ObservableList<Node> children = gridPane.getChildren(); // get the children nodes

        for (Node node : children) {

            // if the index of child node is equal to the index selected
            if(column.equals(gridPane.getColumnIndex(node))) {

                // save the node
                result = node;
                break;
            } // if
        } // for

        return result;

    } // getNodeByColumnIndex()

    // method to get the index of a node
    public Integer getNodesColumnIndex(Node node){

        Integer index = null;

        // get the colymn index of the node, as Integer
        index = grid.getColumnIndex(node);

        return index;

    } // getNodesColumnIndex()


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
