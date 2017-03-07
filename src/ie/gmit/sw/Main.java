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

        // testing expression tree

//        Parameterable paramx = new ParameterImpl();
//        Parameterable paramy = new ParameterImpl();
//        Parameterable oper = new ParameterImpl();
//
//        Ruleable rule = new RuleImpl();
//
//        Expressionable ex5 = new Expression();
//        paramx.setParameter(10, ParameterType.NUMBER);
//        paramy.setParameter(5, ParameterType.NUMBER);
//        oper.setParameter("<", ParameterType.OPERATOR);
//        ex5.setExpression(paramx, paramy, oper);
//
//        paramx = new ParameterImpl();
//        paramy = new ParameterImpl();
//        oper = new ParameterImpl();
//
//        Expressionable ex4 = new Expression();
//        paramx.setParameter(10, ParameterType.NUMBER);
//        paramy.setParameter(10, ParameterType.NUMBER);
//        oper.setParameter("==", ParameterType.OPERATOR);
//        ex4.setExpression(paramx, paramy, oper);
//
//        paramx = new ParameterImpl();
//        paramy = new ParameterImpl();
//        oper = new ParameterImpl();
//
//        Expressionable ex3 = new Expression();
//        paramx.setParameter(100, ParameterType.NUMBER);
//        paramy.setParameter(50, ParameterType.NUMBER);
//        oper.setParameter(">", ParameterType.OPERATOR);
//        ex3.setExpression(paramx, paramy, oper);
//
//        paramx = new ParameterImpl();
//        paramy = new ParameterImpl();
//        oper = new ParameterImpl();
//
//        Expressionable ex2 = new Expression();
//        paramx.setParameter(ex3, ParameterType.EXPRESSION);
//        paramy.setParameter(ex4, ParameterType.EXPRESSION);
//        oper.setParameter("AND", ParameterType.OPERATOR);
//        ex2.setExpression(paramx, paramy, oper);
//
//        paramx = new ParameterImpl();
//        paramy = new ParameterImpl();
//        oper = new ParameterImpl();
//
//        Expressionable ex1 = new Expression();
//        paramx.setParameter(ex2, ParameterType.EXPRESSION);
//        paramy.setParameter(ex5, ParameterType.EXPRESSION);
//        oper.setParameter("OR", ParameterType.OPERATOR);
//        ex1.setExpression(paramx, paramy, oper);
//
//        rule.setExpression(ex1);
//
//        // evaluate tree
//        boolean result = rule.computeRule();
//
//        System.out.println("Result: " + result);

        //  ======================================================
        //                  Test Command Pattern
        //  ======================================================

       /* NotificationController notify = new NotificationController();

        Email email = new Email();
        MobileMessage mobileMessage = new MobileMessage();
        WebMessage webMessage = new WebMessage();

        EmailCommand emailCommand = new EmailCommand(email);
        MobileMessageCommand mobileCommand = new MobileMessageCommand(mobileMessage);
        WebMessageCommand webMessageCommand = new WebMessageCommand(webMessage);

        notify.setCommand(emailCommand);
        notify.setCommand(mobileCommand);
        notify.setCommand(webMessageCommand);

        notify.buttonPresssed();*/

        // start the JavaFX application
        launch(args);

    } // main()
}
