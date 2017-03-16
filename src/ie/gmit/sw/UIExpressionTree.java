package ie.gmit.sw;

import javafx.collections.FXCollections;
import javafx.scene.*;
import javafx.scene.control.*;

/**
 * Created by Ross Byrne on 26/02/17.
 */
public class UIExpressionTree {

    private Controller controller;
    private ComboBox<String> oper = new ComboBox<>();
    private Label operLabel;
    private Object paramX = null;
    private Object paramY = null;

    private boolean operSet = false;
    private Label leftBracket = new Label("(");
    private Label rightBracket = new Label(")");


    public UIExpressionTree(Controller controller, int startIndex, boolean update){

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
                        // make a new parameters as comboBoxes giving option for numbers of new expressions
                        createEqualsDropDowns();
                        break;
                } // switch

                // flag the operator as set
                operSet = true;

                // once operator is set, change for combo box to label

                // create new label with operator selected in combo box
                operLabel = new Label(oper.getSelectionModel().getSelectedItem());

                // replace the combo box with the label
                controller.updateColumn(controller.getNodesColumnIndex(oper), operLabel);

            } // if

        });

        // check if updating parameters or adding from scratch
        if(update == false) {

            // add left bracket
            controller.addColumn(startIndex, leftBracket);

            // add the operator combo box to grid
            controller.addColumn(startIndex + 1, oper);

            // add right bracket
            controller.addColumn(startIndex + 2, rightBracket);

        } else {

            // add left bracket
            controller.updateColumn(startIndex, leftBracket);

            // add the operator combo box to grid
            controller.addColumn(startIndex + 1, oper);

            // add right bracket
            controller.addColumn(startIndex + 2, rightBracket);

        } // if

    } // constructor

    // Recursive method to build the expression tree so it can be evaluated
    public Expressionable buildExpressionTree() throws Exception {

        Expressionable ex = null;

        if(paramX instanceof TextField){ // if the parameters are textFields

            ex = new Expression();

            // create parameter for paramX, paramY and operator
            Parameterable paramX = new ParameterImpl();
            Parameterable paramY = new ParameterImpl();
            Parameterable operator = new ParameterImpl();

            // get TextField from ParamX
            TextField t = (TextField)getParamX();

            // set the value of the parameter from the UI textField
            paramX.setParameter(t.getText(), ParameterType.NUMBER);

            // get TextField from paramY
            t = (TextField)getParamY();

            // set the value of the parameter from the UI textField
            paramY.setParameter(t.getText(), ParameterType.NUMBER);

            // Set the value of the operator from the selected item in combo box
            operator.setParameter(operLabel.getText(), ParameterType.OPERATOR);

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
            operator.setParameter(operLabel.getText(), ParameterType.OPERATOR);

            // build expression
            ex.setExpression(paramX, paramY, operator);

        } else {

            // something went wrong, throw an exception
            throw new Exception();

        } // if

        return ex;

    } // buildExpressionTree()


    // Creates TextFields and sets them to paramX and paramY
    // then adds them into the grid on the right and left hand side of the operator
    private void createTextFields(){

        boolean paramsNull;

        if(paramX == null && paramY == null){
            paramsNull = true;
        }
        else{
            paramsNull = false;
        }

        // create parameters as TextFields
        TextField t = new TextField();
        t.setPrefWidth(90);

        // set value for paramX
        paramX = t;

        // create textField
        t = new TextField();
        t.setPrefWidth(90);

        // set value for paramY
        paramY = t;

        int index;

        // get the index of the operator in the grid
        index = controller.getNodesColumnIndex(oper);

        // update the column left of operator to be paramX
        if(paramsNull == true)
            controller.addColumn(index, (Node)paramX);
        else
            controller.updateColumn(index - 1, (Node)paramX);

        // get the index of the operator in the grid
        index = controller.getNodesColumnIndex(oper);

        // update the column right for operator with paramY
        if(paramsNull == true)
            controller.addColumn(index + 1, (Node)paramY);
        else
            controller.updateColumn(index + 1, (Node)paramY);

    } // createTextFields()

    // Creates UIExpressionTree Objects and sets them to paramX and paramY
    // then adds them into the grid on the right and left hand side of the operator
    private void createOperators(){

        int index;
        boolean paramsNull;

        if(paramX == null && paramY == null){

            paramsNull = true;
        }
        else {
            paramsNull = false;
        }

        // get the index of the operator
        if(paramsNull == true)
            index = controller.getNodesColumnIndex(oper);
        else
            index = controller.getNodesColumnIndex(operLabel);

        // create parameters as UIExpressionTree Objects to create a tree structure
        if(paramsNull == true)
            paramX = new UIExpressionTree(controller, index, false);
        else
            paramX = new UIExpressionTree(controller, index - 1, true);

        // get the index of the operator
        if(paramsNull == true)
            index = controller.getNodesColumnIndex(oper);
        else
            index = controller.getNodesColumnIndex(operLabel);

        if(paramsNull == true)
            paramY = new UIExpressionTree(controller, index + 1, false);
        else
            paramY = new UIExpressionTree(controller, index + 1, true);

    } // createOperators()

    // create dropdowns for equals operator
    private void createEqualsDropDowns(){

        // create parameters as Dropdowns
        ComboBox<String> t = new ComboBox<>();

        // set the options for the comboBox
        t.setItems(FXCollections.observableArrayList(
                "Select...",
                "Expression",
                "Number"
        ));

        // select the first option
        t.getSelectionModel().selectFirst();

        // add changed listener
        t.valueProperty().addListener((ov, oldVal, newVal) -> {

            switch (newVal){

                case "Expression":

                    // create Expressions as parameters
                    createOperators();
                    break;
                case "Number":

                    // create textfields as parameters
                    createTextFields();
                    break;
            } // switch

        });

        // set value for paramX
        paramX = t;

        // create combo box
        t = new ComboBox<>();

        // add options
        t.setItems(FXCollections.observableArrayList(
                "Select...",
                "Expression",
                "Number"
        ));

        // select the first option
        t.getSelectionModel().selectFirst();

        // add changed listener
        t.valueProperty().addListener((ov, oldVal, newVal) -> {

            switch (newVal){

                case "Expression":


                    // create Expressions as parameters
                    createOperators();
                    break;
                case "Number":

                    createTextFields();
                    break;
            } // switch

        });

        // set value for paramY
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

    } // createEqualsDropDowns()

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


} // class
