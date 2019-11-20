# fertileLandCalculator

case study - Barren Land Analysis

You have a farm of 400m by 600m where coordinates of the field are from (0, 0) to (399, 599). A portion of the farm is barren, and all the barren land is in the form of rectangles. Due to these rectangles of barren land, the remaining area of fertile land is in no particular shape. An area of fertile land is defined as the largest area of land that is not covered by any of the rectangles of barren land. Read input from STDIN. Print output to STDOUT Input You are given a set of rectangles that contain the barren land. These rectangles are defined in a string, which consists of four integers separated by single spaces, with no additional spaces in the string. The first two integers are the coordinates of the bottom left corner in the given rectangle, and the last two integers are the coordinates of the top right corner.

Output Output all the fertile land area in square meters, sorted from smallest area to greatest, separated by a space

Sample Input                                                                Sample Output

{“0 292 399 307”}                                                           116800  116800

{“48 192 351 207”,“48 392 351 407”,“120 52 135 547”,“260 52 275 547”}       22816 192608 

# Requirements to build and run the project
1) Java JDK/JRE 1.8 
2) Gradle 2.14.1 
3) JUnit-4.12
4) Eclipse or equivalent IDE

# Jars needed for JUnit testing
1) JUnit-4.12.jar 
2) hamcrest-core-1.3.jar

# Pointers to setup the project
1) The project can be set up in Eclipse IDE.
2) Use git to clone the project.
3) Download the Junit jars and copy it to fertileLandCalculator/lib folder
3) From Command prompt, Navigate to project root directory (example : <your workspace/fertileLandCalculator>).
4) Run command gradle build to build the project.
5) Run command gradle test to validate the project.

# To run the application 
The project can be run as Java Application from Eclipse IDE.

# Attachements : 

1) FertileLandCalculator_Flowchart.doc
2) FertileLandCalculator_Graph.doc
3) FertileLandCalculatorTestReport.doc
4) Console output screenshot
