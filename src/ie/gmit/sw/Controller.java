package ie.gmit.sw;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.net.URL;
import java.util.*;

/**
 * Created by Ross Byrne on 23/01/17.
 */
public class Controller implements Initializable {

    @FXML private Label resultLabel;
    @FXML private Label errorLabel;
    @FXML private GridPane grid;
    @FXML private ComboBox<String> EvaluationSelection;
    @FXML private ComboBox<String> commandSelection;
    @FXML private Label messageLabel;

    private Ruleable rule = null;
    private UIExpressionTree uiRoot = null;
    private NotificationController notify;

    private boolean selectionChoice;

    // GUI element initialisation code goes here
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int startIndex = 0;

        // initialise the root for the UIExpressionTree
        uiRoot = new UIExpressionTree(this, startIndex, false);

        // Create notify object
        notify = new NotificationController();

        // ===== True / False Selection Box =====

        // set the options for the evaluation comboBox
        EvaluationSelection.setItems(FXCollections.observableArrayList(
                "Select",
                "True",
                "False"
        ));

        EvaluationSelection.getSelectionModel().selectFirst();

        // Set boolean to correct value
        EvaluationSelection.valueProperty().addListener((ov, oldVal, newVal) -> {

            switch (newVal){

                case "True":

                   setSelectionChoice(true);
                    break;
                case "False":

                   setSelectionChoice(false);
                    break;
            } // switch

        });

        // Command pattern list
        commandSelection.setItems(FXCollections.observableArrayList(
                "Select",
                "Send Email",
                "Send Web Message",
                "Send Mobile Message"
        ));

        commandSelection.getSelectionModel().selectFirst();

    } // initialize()

    // replaces the contents of a column
    public void updateColumn(int index, Node node){

        // get node from column
        Node n = getNodeByColumnIndex(new Integer(index), grid);

        // remove node from children to avoid dupes (throws exception otherwise)
        grid.getChildren().remove(n);

        // add node to next column to shift its index up by one
        grid.add(node, index, 0);

        // update the columns widths
        updateGrid();

    } // updateColumn()

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

            // update the width of the grid
            updateGrid();

        } catch (Exception e){

            e.printStackTrace();
        } // try

    } // addColumn()

    public void updateGrid(){

        double gridSize = 0;

        // update the width of each column
        for(int i = 0; i < grid.getColumnConstraints().size(); i++){

            // get the node in the column
            Node node = getNodeByColumnIndex(new Integer(i), grid);

            // get column constraint
            ColumnConstraints columnConstraints = grid.getColumnConstraints().get(i);

            // set the columns width

            if(node instanceof Label){ // if the node is a label
                Label l = (Label)node;

                if (l.getText() == "(" || l.getText() == ")"){

                    // set width of column
                    columnConstraints.setPrefWidth(26);

                } else {

                    // set width of column
                    columnConstraints.setPrefWidth(100);
                } // if

            } else {

                // set width of column
                columnConstraints.setPrefWidth(100);
            }

            // set column to never grow
            columnConstraints.setHgrow(Priority.NEVER);

            // center the contents of column
            columnConstraints.setHalignment(HPos.CENTER);

            // calculate the size of the grid
            gridSize += columnConstraints.getPrefWidth();

        } // for

        // update the width of the grid
        grid.setPrefWidth(gridSize);

    } // updateGrid()

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

        messageLabel.setText("");

        boolean compareResult;

        rule = new RuleImpl();

        // clear error label
        errorLabel.setText("");

        try {

            // build an expression tree from the UI
            rule.setExpression(uiRoot.buildExpressionTree());

            compareResult = rule.computeRule();

            if(EvaluationSelection.getSelectionModel().getSelectedItem() != "Select")
            {

                // Compare the two booleans here
                if(compareResult == selectionChoice)
                {
                    getCommand();
                }

            }

            // evaluate the expression
            resultLabel.setText(String.valueOf(compareResult));
            
        } catch (Exception e){

            //e.printStackTrace();
            // reset values
            errorLabel.setText("Error with parameters, enter numbers!");

        } // try

    } // evaluateBtn_OnAction()

    //==================================
    //        Getters and Setters
    //==================================

    public boolean isSelectionChoice() {
        return selectionChoice;
    }

    public void setSelectionChoice(boolean selectionChoice) {
        this.selectionChoice = selectionChoice;
    }

    private void sendEmail(){

        Email email = new Email();
        EmailCommand emailCommand = new EmailCommand(email);
        notify.setCommand(emailCommand);
        notify.buttonPresssed();
        messageLabel.setText("Email sending");
    }

    private void sendWebMessage(){

        WebMessage webMessage = new WebMessage();
        WebMessageCommand webMessageCommand = new WebMessageCommand(webMessage);
        notify.setCommand(webMessageCommand);
        notify.buttonPresssed();
        messageLabel.setText("Web Message sending");
    }

    private void sendMobileMessage(){

        MobileMessage mobileMessage = new MobileMessage();
        MobileMessageCommand mobileCommand = new MobileMessageCommand(mobileMessage);
        notify.setCommand(mobileCommand);
        notify.buttonPresssed();
        messageLabel.setText("Mobile message sending");
    }

    // Send message
    private void getCommand()
    {

        String nameOfSelection;

        // Returns the selection and stores in string
        nameOfSelection = commandSelection.getSelectionModel().getSelectedItem();

        switch (nameOfSelection)
        {

            case "Send Email":

                sendEmail();
                break;

            case "Send Web Message":

                sendWebMessage();
                break;

            case "Send Mobile Message":

                sendMobileMessage();
                break;

        }// End switch

    }// End getCommand

} // class
