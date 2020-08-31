****************
* Project0 Grid Monitor
* CS221-1
* 01/26/2020
* Cesar Raymundo
**************** 

OVERVIEW:

 The GridMonitor's purpose is to read in a file to make it into a 2D array object. Within the array object the 
GridMonitor uses different methods with the class to find the surrounding sum, average, delta, and determining 
which cells within the array are in danger of exploding.  


INCLUDED FILES:
 * GridMonitorInterface.java - source file, methods that are unimplemented for GridMonitor.java
 * GridMonitor.java - source file, implements GridMonitorInterface and is the child class.
 * GridMonitorTest.java - test file, used for testing GridMonitor.java
 * README - this file


COMPILING AND RUNNING:

 Give the command for compiling the program, the command
 for running the program, and any usage instructions the
 user needs.
 
 These are command-line instructions for a system like onyx.
 They have nothing to do with Eclipse or any other IDE. They
 must be specific - assume the user has Java installed, but
 has no idea how to compile or run a Java program from the
 command-line.
 
 e.g.
 From the directory containing all source files, compile the
 driver class (and all dependencies) with the command:
 $ javac Class1.java

 Run the compiled class file with the command:
 $ java Class1

 Console output will give the results after the program finishes.


PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 The main concept of this project is working with 2D array objects that are passed in through text file and using methods 
 to find various things using the array. For context, the project was about creating a system monitor to keep track of cells
 that are in danger of exploding. We are given an interface with unimplemented methods and we use those methods to help make
 the system monitor find what cells are at risk of exploding and which ones are not.

 My program has 3 different classes, the GridMonitor, GridMonitorInterface, and GridMonitorTest. To make the grid 
 monitor, we must use the parent class, GridMonitorInterface. The reason being is because the class has the unimplemented methods
 that we use for the child class, GridMonitor. The child class uses the methods to make the grid monitor. The GridMonitorTest
 class is a class we use to make sure every method is working properly and to help find issues within the GridMonitor class.
 
 I was responsible for the GridMonitor class. I implemented the GridMonitorInterface and used their program design. There is nothing 
 I would do to change it. 

TESTING:

I made sure my program ran by using the unit tester that was given to us for GridMonitor class.
I made sure each method passed their given tests before continuing with the next one. My program can handle bad input
if a person tries to enter a file with an invalid name or it cannot be read. It throws a FileNotFoundException. Any program can be idiot-proof, 
but if the user has no idea of the context within the project, it can cause them to have a bad time. 

DISCUSSION:
 
 An issue that I was having with my program was making the constructor for the GridMonitor class. My constructor was not reading in 
 text file right to make the 2D array object. At first, I wasn't sure what I was doing wrong. Everything seemed to be right
 and should be running properly. After looking at Java Foundations book and looking within the text files we were given. I found that
 the first line of each text file gives the dimensions of the array. After finding this, I changed my code to read the first line as int
 and use them for the dimensions to create the 2D array. Once I fixed this issue, it was a smooth process. I did have a few issues with the surrounding sum method, 
 but with the help of a tutor, I found what was missing with my code and finished the other methods.