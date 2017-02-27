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


    public UIExpressionTree(Controller controller, int startIndex){

        // set the width of the operator
        oper.setPrefWidth(90);

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

            // if operator is not set, set it
            if(!operSet) {

                switch (newVal){

                    case ">":
                    case "<":

                        // create textFields as parameters
                        createTextFields();
                        break;
                    case "AND":
                    case "OR":

                        // create new operators as parameters
                        createOperators();
                        break;
                    case "==":
                        // make a new parameters as comboBoxs giving option for numbers of new expressions
                        break;
                } // switch

            } else { // if operator is already set

                // remove other operator options
                oper.setItems(FXCollections.observableArrayList(oldVal));

            } // if

        });

        // add left bracket
        controller.addColumn(startIndex, leftBracket);

        // add empty space for paramX
        //controller.addColumn(startIndex + 1, new Label(""));

        // add the operator combo box to grid
        controller.addColumn(startIndex + 1, oper);

        // add empty space for paramY
        //controller.addColumn(startIndex + 3, new Label(""));

        // add right bracket
        controller.addColumn(startIndex + 2, rightBracket);

    } // constructor

    // Recursive method to build the expression tree so it can be evaluated
    public Expressionable buildExpressionTree(){

        Expressionable ex = null;

        if(paramX instanceof TextField){ // if the parameters are textFields

            ex = new Expression();

            // get TextField from ParamX
            TextField t = (TextField)getParamX();

            // create a parameter from value in paramX
            Parameterable paramX = new ParameterImpl();
            paramX.setParameter(t.getText(), ParameterType.NUMBER);

            // get TextField from paramY
            t = (TextField)getParamY();

            // create parameter from paramY value
            Parameterable paramY = new ParameterImpl();
            paramY.setParameter(t.getText(), ParameterType.NUMBER);

            // create parameter for operator
            Parameterable operator = new ParameterImpl();
            operator.setParameter(getOper().getSelectionModel().getSelectedItem(), ParameterType.OPERATOR);

            // create expression from parameters
            ex.setExpression(paramX, paramY, operator);

        } else if(paramX instanceof UIExpressionTree) { // if the parameters are UIExpressionTree objects

            ex = new Expression();

            UIExpressionTree uiEx1 = null;
            UIExpressionTree uiEx2 = null;

            // get UIExpressionTree objects from paramX and paramY
            uiEx1 = (UIExpressionTree)paramX;
            uiEx2 = (UIExpressionTree)paramY;

            // create parameter for paramX, paramY and operator
            Parameterable paramX = new ParameterImpl();
            Parameterable paramY = new ParameterImpl();
            Parameterable operator = new ParameterImpl();

            // get expression from paramX and paramY
            Expressionable ex1 = uiEx1.buildExpressionTree();
            Expressionable ex2 = uiEx2.buildExpressionTree();

            // add expressions to parameters x and y
            paramX.setParameter(ex1, ParameterType.EXPRESSION);
            paramY.setParameter(ex2, ParameterType.EXPRESSION);

            // add operator to operator parameter
            operator.setParameter(getOper().getSelectionModel().getSelectedItem(), ParameterType.OPERATOR);

            // build expression
            ex.setExpression(paramX, paramY, operator);

        } else {

            // something went wrong

        } // if

        return ex;

    } // buildExpressionTree()


    // Creates TextFields and sets them to paramX and paramY
    // then adds them into the grid on the right and left hand side of the operator
    private void createTextFields(){

        // create parameters as TextFields
        TextField t = new TextField();
        t.setPrefWidth(90);

        paramX = t;

        t = new TextField();
        t.setPrefWidth(90);

        paramY = t;

        int index;

        // get the index of the operator in the grid
        index = controller.getNodesColumnIndex(oper);

        // update the column left of operator to be paramX
        controller.addColumn(index, (Node)paramX);

        // get the index of the operator in the grid
        index = controller.getNodesColumnIndex(oper);

        // update the column right for operator with paramY
        controller.addColumn(index + 1, (Node)paramY);

        // flag the operator as set
        operSet = true;

    } // createTextFields()

    // Creates UIExpressionTree Objects and sets them to paramX and paramY
    // then adds them into the grid on the right and left hand side of the operator
    private void createOperators(){

        int index;

        // get the index of the operator
        index = controller.getNodesColumnIndex(oper);

        // create parameters as UIExpressionTree Objects to create a tree structure
        paramX = new UIExpressionTree(controller, index);

        // get the index of the operator
        index = controller.getNodesColumnIndex(oper);

        paramY = new UIExpressionTree(controller, index + 1);

        // flag the operator as set
        operSet = true;

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
