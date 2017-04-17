<div align="center"><img src ="https://github.com/EngineBuilderCorporation/ExpressionEngine/blob/master/gmit-logo.jpg"/></div>

# Expression Engine

**Student Names:** Ross Byrne, Christy Madden <br />
**Student IDs:** G00310619, G00214065 <br />
**Module:** Applied Project <br />
**Supervisor:** Dr John Healy <br />

# Introduction

### What is the application?

This is the final year project for the B.Sc.(Hons) in Software Development in GMIT. The project is an expression engine which has a custom built expression builder. It allows users to build any expression with the use of the AND, OR, >, < and Equals operators. The data used in the expressions can be pulled from any data source provided they are implemented. Currently, the values that are evaluated are positive and negative decimal and integer values. Once the expression is built, the user can select to run a command. The user can choose whether they want to the command to run if the expression evaluates as either True or False.

# Instructions
The project is in an IntelliJ IDEA project solution. For more on IntelliJ IDEA, see [here](https://www.jetbrains.com/idea/). The project has been developed with Java 8 and JavaFX. To compile the source code, the Java 8 JDK is required. JavaFX comes bundled with the Java 8 JDK. For Oracle's Java 8 JDK, see [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html). 

The source code can be run by compiling and running Main.java in the package ie.gmit.sw. This will start the application.

To compile the source code from the terminal or command line, navigate to the src folder and run:
```
javac ie/gmit/sw/*.java
```
To start the application, run:
```
java ie.gmit.sw.Main
```
# Design
The design of the application comes down to three main sections. The expression binary tree, the UI binary tree and the command objects.

## The Expression Binary Tree

To make the design as extensible as possible, a binary tree was created that allows for any operator or value to be added. For the scope of the project, the operators AND, OR, >, < and equals were implemented. The values that are evaluated with these operators are positive and negative decimals and integers. With the current design, other operators such as addition, subtraction, multiplication and division could also be implemented very easily. With the current implementation, any decimal or integer values can be pulled from a database and used in an expression. Once the expression tree is built, it recursively evaluates itself.

A UML diagram of the expression binary tree that creates and evaluates expressions can be seen below:

![Expression UML Diagram](imgs/Expressionable-Parameterable.png)

## The UI Binary Tree

For dynamically creating UI elements, as a user creates an expression, a tree structure was created. This binary tree keeps track of the expression the user is building and generates the required UI elements. The UML diagram for this class can be seen below. This class, called UIExpressionTree, keeps track of the expression as it is built, to generate the correct UI elements. It also uses a factory that gets data from a data source. This data source can obtain data from anywhere, it just needs to be implemented using the DataSourceabe interface and then added to the DataFactory. This allows for a wide variety of possible data sources to be implemented. Once the expression is built and ready to be evaluated, the UIExpressionTree can recursively build an expression tree, using the values selected in the UI. The expression can then be evaluated.

![UIExpression & DataFactory UML Diagram](imgs/UIExpressionTree-DataFactory.png)

## The Command Objects

Running a command based on the result of the expression evaluation is optional but still a desireable feature. Once an expression is created, the user can select a command that they want to run. The user can then select if they want the command to run, depending on if the expression evaluates as either true or false. As the commands are implemented with the Command pattern, additional commands can be added with ease. This is important because the commands that are required could change or the scope of the application could be increased. The commands that are implemented in the project are currently just placeholders. Later, the application can be extended and commands with the required functionality can be implemented.



 
