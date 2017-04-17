<div align="center"><img src ="https://github.com/EngineBuilderCorporation/ExpressionEngine/blob/master/gmit-logo.jpg"/></div>

# Expression Engine

**Student Names:** Ross Byrne, Christy Madden <br />
**Student IDs:** G00310619, G00214065 <br />
**Module:** Applied Project <br />
**Supervisor:** Dr John Healy <br />
**GitHub:** https://github.com/EngineBuilderCorporation/ExpressionEngine <br />

# Introduction

### What is the application?

This is the final year project for the B.Sc.(Hons) in Software Development in GMIT. The project is an expression engine which has a custom built expression builder. It allows users to build any expression with the use of the AND, OR, >, < and Equals operators. The data used in the expressions can be pulled from any data source provided they are implemented. Currently, the values that are evaluated are positive and negative decimal and integer values. Once the expression is built, the user can select to run a command. The user can choose whether they want to the command to run if the expression evaluates as either True or False.

### What does the appication run on?

This application is built using Java and will run on any machine or OS once it has Java installed. It is recommended that your computer has Java 8 installed.

### What is the application built with?
The project is in an IntelliJ IDEA project solution. For more on IntelliJ IDEA, see [here](https://www.jetbrains.com/idea/). The project has been developed with Java 8 and JavaFX. 

### How can the application be deployed?

The application can be downloaded from GitHub using the above link. You will be need to run it from the command line. It is recommended that you navigate to the root directory of the project. To compile the source code, the Java 8 JDK is required. JavaFX comes bundled with the Java 8 JDK. For Oracle's Java 8 JDK, see [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). 

The source code can be run by compiling and running Main.java in the package ie.gmit.sw. This will start the application.

To compile the source code from the terminal or command line, navigate to the src folder and run:
```
javac ie/gmit/sw/*.java
```
To start the application, run:
```
java ie.gmit.sw.Main
```

# Technologies used

**Operating System:** Ubuntu 16.04 LTS, Linux Mint 17.3 “Rosa” <br />
**IDE:** IntelliJ IDEA 2017.1.1 <br />
**Software Version Control:** Git 1.9.1 <br />
**Hosting Site:** GitHub <br />
**Programming Language:** Java 8 <br />
**UML Generator:** Violet UML Editor

# Package Contents

```
Project
|-----------------------------------|
|----ie.gmit.sw                     |
|    |-- Command.java               |
|    |-- Controller.java            |
|    |-- DataFactory.java           |
|    |-- DataSourceable.java        |
|    |-- DataSourceController.java  |
|    |-- dataSourceWindow.fxml      |
|    |-- Email.java                 |
|    |-- EmailCommand.java          |
|    |-- Expression.java            |
|    |-- Expressionable.java        |
|    |-- Main.java                  |
|    |-- Main.fxml                  |
|    |-- MobileMessage.java         |
|    |-- MobileMessageCommand       |
|    |-- NotificationController     |
|    |-- Parameterable.java         |
|    |-- ParameterImpl.java         |
|    |-- ParameterType.java         |
|    |-- Ruleable.java              |
|    |-- RuleImpl.java              |
|    |-- SQLProductionImp.java      |
|    |-- TestDataSourceImp.java     |
|    |-- UIExpressionTree.java      |
|    |-- WebMessage.java            |
|    |-- WebMessageCommand.java     |
|-----------------------------------|

```

# Features

## Design
The design of the application comes down to three main sections. 

* Expression binary tree  
* UI binary tree
* Command objects.

## The Expression Binary Tree

To make the design as extensible as possible, a binary tree was created that allows for any operator or value to be added. For the scope of the project, the operators AND, OR, >, < and equals were implemented. The values that are evaluated with these operators are positive and negative decimals and integers. With the current design, other operators such as addition, subtraction, multiplication and division could also be implemented very easily. With the current implementation, any decimal or integer values can be pulled from a database and used in an expression. Once the expression tree is built, it recursively evaluates itself.

```Java
public interface Parameterable {

    public Object getParameter();

    public boolean setParameter(Object parameter, ParameterType type);

    public ParameterType getParameterType();

} // interface
```

A UML diagram of the expression binary tree that creates and evaluates expressions can be seen below:

![Expression UML Diagram](imgs/Expressionable-Parameterable.png)

## The UI Binary Tree

For dynamically creating UI elements, as a user creates an expression, a tree structure was created. This binary tree keeps track of the expression the user is building and generates the required UI elements. The UML diagram for this class can be seen below. This class, called UIExpressionTree, keeps track of the expression as it is built, to generate the correct UI elements. It also uses a factory that gets data from a data source. This data source can obtain data from anywhere, it just needs to be implemented using the DataSourceabe interface and then added to the DataFactory. This allows for a wide variety of possible data sources to be implemented. Once the expression is built and ready to be evaluated, the UIExpressionTree can recursively build an expression tree, using the values selected in the UI. The expression can then be evaluated.

![UIExpression & DataFactory UML Diagram](imgs/UIExpressionTree-DataFactory.png)

## The Command Objects

Running a command based on the result of the expression evaluation is optional but still a desireable feature. Once an expression is created, the user can select a command that they want to run. The user can then select if they want the command to run, depending on if the expression evaluates as either true or false. As the commands are implemented with the Command pattern, additional commands can be added with ease. This is important because the commands that are required could change or the scope of the application could be increased. The commands that are implemented in the project are currently just placeholders. Later, the application can be extended and commands with the required functionality can be implemented.



 
