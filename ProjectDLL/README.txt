****************
* Project1 Double Linked List
* CS221-1
* 04/12/2020
* Cesar Raymundo
**************** 

OVERVIEW:

 This program tests my own implementation of a double linked list with test scenarios that were given 
to us to implement in our driver class(ListTester).  


INCLUDED FILES:
 * ListTester.java - test file, used for testing implemented double linked list.
 * IUDoubleLinkedList.java - source file, implements IndexedUnsortedList Interface and is the child class.
 * LinearNode.java - source file, contains methods for using nodes when implementing double linked list.
 * IndexedUnsortedList.java - Interface for the methods implemented in IUDoubleLinkedList
 * README - this file


COMPILING AND RUNNING:

 From the directory containing all source files, compile the
 driver class (and all dependencies) with the command:
 $ javac ListTester.java

 Run the compiled class file with the command:
 $ java ListTester

 The console output will be the results of the tests ran in ListTester. This will show scenarios
 being tested, the results of which case passed or fail, and how many scenarios ran in the ListTester.
 With my ListTester there should be a total of 9900 tests being run.


PROGRAM DESIGN AND IMPORTANT CONCEPTS:
 
 This program uses an implementation of IUDoubleLinkedList that implements IndexedUnsortedList methods. 
 It implements the list using forward and rear linking nodes with LinearNode. With this it uses ListTester 
 to test all different test scenarios implementations.

	ListTester.java:
		This class is the tester class. With all 82 scenarios implemented, it builds all the scenarios
        After it builds all 82 scenarios, ListTester will call one of four tester methods that are used
	for these scenarios. They are based by list element size. Once these are called it will make sure
	that each method is running correctly in IUDoubleLinkedList.

	IUDoubleLinkedList.java:
		This class is the implementation of the IndexedUnsortedList's interface, and uses LinearNode to help
	keep track of every element within the list. This class is used to be tested in ListTester

	LinearNode.java:
		A class that contains methods related to node usage such as getting the next or previous node,
	or setting the element of a node.
		
	IndexedUnsortedList.java:
		The interface for IUDoubleLinkedList.java. It contains all unimplemented methods with their javadocs for IUDoubleLinkedList.
	
	I was responsible for the IUDoubleLinked class and for some of the ListTester class. 
        I implemented the IndexedUnsortedList Interface and used the unimplemented methods. And for the ListTester I was 
	responsible for making sure all scenarios were being implemented and all four test methods. As for now, there is nothing 
	I would do to change either class. 

TESTING:

For testing, this program contains a driver class which is the ListTester. Within this program, it has all the tests scenarios
I need to make sure that my IUDoubleLinkedList is working properly and is not causing any errors. To make sure that all the methods
are passing, the ListTester will state which methods pass and which fail. It will also show you the expected output in case your are
having a hard time debugging what is wrong with your program. My program can handle bad input, if a test scenario or method is not implemented
right it will through different types of exception depending on what it is. Any program can be idiot-proof, but if the user has no idea 
of the context within the project, it can cause them to have a bad time. 

DISCUSSION:

I did not have too many issues while doing this project but I did have two issues that were notable during this process. My first issues were when
I was testing my double linked list. A lot of test scenarios were failing and I was getting a lot of NullPointerExceptions. At first, I was not sure on 
what was wrong with the program because since I implemented the rest of the test scenarios for this project. I first started with my IUDoubleLinked list and 
after further analysis and the help of resources as the textbook and class notes I realized that some of my methods were not correct and causing these issues. 
Some of the mistakes were little, such as order, but others required a few more statements within them. With a little of trail and error, I was eventually able
to fix this issue. The other issue I had was getting lost with all of testing methods. ListTester was a lot of copying and pasting but if you do not put the right
enum for one of them it can cause issues when testing, which happened to me. I eventually had to go through each one individually to make sure they were right, but
after I found what results were wrong and what other cases I needed, I was able to run my ListTester just fine. Of course I did have other issues with this project, 
like format, using a method wrong, adding an element at the wrong, not implementing a scenario right, but all of these were easy to fix and did not require a lot of 
analysis to figure what was wrong with them.