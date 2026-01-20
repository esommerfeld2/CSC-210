# Assignment 0

In this assignment, you will develop an abstract data type for arrays.
Think about the exercise we did in class where we described the functionality of a variable.
You will apply the same line of thinking to arrays for this week's assignment.

## Interface 1: IndexAccess

### Writing the Interface
Begin by thinking through the methods available for Java's built-in array type (e.g., int[], char[], etc.)
Your goal is to create a Java interface called `IndexAccessADT`.
This interface will describe the operations supported directly by the built-in array.
You will also write Javadoc to accompany the interface.
Your interface should compile cleanly from the command line and the Javadoc should build cleanly.

Your interface should include each of the following:
* Javadoc describing the purpose of the interface. 
Since an interface in Java doesn't include any constructors, this Javadoc should specify your assumptions about how a hypothetical IndexAccess object would be created (arguments required, etc).
* Call signatures for all the necessary methods
* Javadoc for those methods describing what they do and any restrictions on their use.
If a method might throw an exception under certain circumstances, the Javadoc should say so.

### Writing Unit Tests

Next, write, in plain English, a set of tests that to confirm that an object conforming to this interface works correctly.
Remember that tests typically make some sort of assertion (AssertTrue, AssertFalse, AssertEqual, etc.) about what your method will return.
The testing framework then evaluates whether the assertion holds to determine whether the test passes or fails.

You can put this text into a (non-Javadoc) block comment below the interface itself.
Your tests should use the following palette of operations:
* Create an object with some set of properties (i.e., length)
* Method call with expected return value
* Method call with expected exception thrown

Since some method calls change the state of the object, your tests may require a sequence of operations to set the object up as desired.

### Kudos

You may wish to write code to see if your tests work as intended.
Writing code is not required for the assignment this week, but it will give you a head start on next week's assignment.
To do this part of the assignment, you'll need to write:
* an `IndexAccess` class that implements your interface, and 
* a `TestIndexAccess` class
This class can either include a main method that runs your tests or use a testing platform such as `JUnit`.
We will start working with `Junit` next week.

## Part 2: Sequences

![alphabet.png](alphabet.png)

The second interface you will write should be called `SequenceADT.`
For this part of the assignment, think about how you keep track of sequences of elements.
You will want to think about operations you could do with a sequence alone (ABC), with a sequence and an index (ABC, 1), or with two sequences (ABC and DEF).
For this interface, specify the operations you would need to achieve this.
You should specify the same information in this inferface as you did for `IndexAccessADT`.
However, this portion of the assignment will be evaluated on completion only, rather than correctness.
The goal is to start thinking about sequences prior to next week's class.
