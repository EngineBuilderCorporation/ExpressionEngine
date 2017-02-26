package ie.gmit.sw;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;

/**
 * Created by Ross Byrne on 26/02/17.
 */
public class UIExpressionTree {

    private Controller controller;
    private ComboBox<String> oper = new ComboBox<>();
    private Object paramX;
    private Object paramY;

    private boolean operSet = false;
    private int operIndex;
    private int paramXIndex;
    private int paramYIndex;
    private Label leftBracket = new Label("(");
    private Label rightBracket = new Label(")");

    public UIExpressionTree(Controller controller){

        this.controller = controller;

        // set the options for the operators comboBox
        oper.setItems(FXCollections.observableArrayList(
                "Select...",
                ">",
                "<",
                "==",
                "AND",
                "OR"
        ));

        // select the first option
        oper.getSelectionModel().selectFirst();

        // add changed listener
        oper.valueProperty().addListener((ov, oldVal, newVal) -> {

            System.out.println(newVal);
            System.out.println("Operator's column index: " + controller.getNodesColumnIndex(oper));

            switch (newVal){

                case ">":
                case "<":


                    if(!operSet) {
                        createTextFields();
                    } else {

                        oper.setItems(FXCollections.observableArrayList(oldVal));
                    }
                    break;
            }

        });

        // add left bracket
        controller.addColumn(0, leftBracket);

        // add empty space for paramX


        // add the operator combo box to grid
        controller.addColumn(2, oper);

        // add empty space for paramY

        // add right bracket
        controller.addColumn(4, rightBracket);


    } // constructor

    private void createTextFields(){

        // create parameters as TextFields
        paramX = new TextField();
        paramY = new TextField();

        int index;

        // get the index of the operator in the grid
        index = controller.getNodesColumnIndex(oper);

        // update the column left of operator to be paramX
        controller.updateColumn(index - 1, (Node)paramX, 100);

        // update the column right for operator with paramY
        controller.updateColumn(index + 1, (Node)paramY, 100);

        // flag the operator as set
        operSet = true;

    } // createTextFields()

    private void createOperators(){



    } // createOperators()

    // getter and setters

    public void setParamX(Object paramX){

        this.paramX = paramX;
    }

    public Object getParamX(){

        return this.paramX;
    }

    public void setParamY(Object paramY){

        this.paramY = paramY;
    }

    public Object getParamY(){

        return this.paramY;
    }

    public ComboBox<String> getOper() {
        return oper;
    }

    public void setOper(ComboBox<String> oper) {
        this.oper = oper;
    }

    public int getOperIndex() {
        return operIndex;
    }

    public void setOperIndex(int operIndex) {
        this.operIndex = operIndex;
    }

    public int getParamXIndex() {
        return paramXIndex;
    }

    public void setParamXIndex(int paramXIndex) {
        this.paramXIndex = paramXIndex;
    }

    public int getParamYIndex() {
        return paramYIndex;
    }

    public void setParamYIndex(int paramYIndex) {
        this.paramYIndex = paramYIndex;
    }

} // class
