# Setting up IntelliJ IDEA Coverage

*This is an example from previous class, and just for reference of how to set up the coverage test*

Here are the basic steps for setting up and creating a report of your code coverage with IntelliJ;

1. __Update your test runner.__ All teams started with an AllTests class in their test directory, so it just needs to be 
   modified to include all test classes in the project. Every test class should be included in the AllTests 
   @Suite.SuiteClasses block. Make sure all the packages you've defined are included as imports as well. Here's a sample 
   test runner from last semester:
   ![Image of example1](https://github.com/GoldenaArcher/Chess/blob/master/extra%20files/pg1.png)

2. __Configure__ how IntelliJ manages coverage information. First, navigate to File->Settings and go to the Build, Execution, 
   Deployment tab. Then select Coverage. Select "Replace active suites with the new one" and apply the change. Check 
   "Activate Coverage View" as well if it's not already checked. This will overwrite old coverage information with the 
   new instead of accumulating coverage results from multiple run configurations.
   ![Image of example2](https://github.com/GoldenaArcher/Chess/blob/master/extra%20files/pg2.png)

3. __Set up a JUnit run configuration.__ Go to Run->Edit Configurations. Click the + in the top left corner of the window and 
   select JUnit. Name the configuration whatever you'd like. Click the "..." next to the "Class:" box and select your 
   AllTests class. Switch to the Code Coverage tab in the configurations window. Make sure IntelliJ IDEA is selected as 
   the coverage runner and select "Tracing." Make sure "Track per test coverage" is checked as well. In the "Classes and 
   packages to record coverage data" box, click the second "+" symbol down. Select the top package of your project:
   ![images of example3](https://github.com/GoldenaArcher/Chess/blob/master/extra%20files/pg3.png)
   

It should be the "T##" package, i.e. edu.csu2017fa314.T## for your projects. You should end up with a configuration 
looking something like this:
![images of example4](https://github.com/GoldenaArcher/Chess/blob/master/extra%20files/pg4.png)
![images of example5](https://github.com/GoldenaArcher/Chess/blob/master/extra%20files/pg5.png)

Make sure you hit OK or Apply to save the configuration.
4. __Run the configuration__ (Run -> Run... -> [configuration name]). If everything goes well, IntelliJ will run all of 
   your tests. If this doesn't happen, either there's something wrong with your configuration or your project is failing 
   to build
   . 
5. __Calculate code coverage.__ After running the configuration, you should now see a "Run [configuration name] with Coverage"
   option under the Run menu. Running this will calculate the code coverage of your tests, breaking it down by class, method,
   and line coverage. You can double click a package in the coverage window to view the details about it. The file tree on 
   the left should also show coverage information as well:
   ![images of example6](https://github.com/GoldenaArcher/Chess/blob/master/extra%20files/pg6.png)

6. __Inspecting coverage.__ Now that you've run the code coverage tool, the gutter on the left side of any open Java code will 
   be highlighted. Green means the line is fully covered, yellow means it's partially covered, and red means it's never 
   touched by tests. Clicking in the gutter for yellow or green lines will give details of how many times that line is 
   touched by tests. Clicking it for a yellow or green "if" statement will ever show the number of times the "if" evaluated 
   to true and false. 
   ![images of example7](https://github.com/GoldenaArcher/Chess/blob/master/extra%20files/pg7.png)
   ![images of example8](https://github.com/GoldenaArcher/Chess/blob/master/extra%20files/pg8.png)

7. Creating a coverage report. In the coverage window that appeared, you can generate an HTML web page of coverage 
   information.To do so, click the fifth button down on the left side of the window (the one that looks like a rectangle 
   with a green arrow pointing upwards). You'll be prompted to choose a folder where the report is saved. I recommend 
   creating a new subdirectory, because quite a few files will be generated. Open the index.html page generated to go through
   the results:
   ![images of example9](https://github.com/GoldenaArcher/Chess/blob/master/extra%20files/pg9.png)