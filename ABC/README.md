Integration Test Framework

To do an Integration tests of web application we can make use of this Java-JSON Integration Test  Framework

Setup Steps:

Checkout the fresh code on the physical machine using git clone, perform the following on the "src" folder of the project exists

Now, we have to let the Java know where the libraries are available

$ java -cp /Users/umajyothi/Downloads/ABC/ABC/src:/Users/umajyothi/Downloads/ABC/ABC/lib/commons-collections4-4.1.jar:/Users/umajyothi/Downloads/ABC/ABC/lib/json-20140107.jar:/Users/umajyothi/Downloads/ABC/ABC/lib/junit-4.12.jar:/Users/umajyothi/Downloads/ABC/ABC/lib/json-simple-1.1.1.jar:/Users/umajyothi/Downloads/ABC/ABC/lib/ooxml-schemas-1.3.jar:/Users/umajyothi/Downloads/ABC/ABC/lib/poi-3.15.jar:/Users/umajyothi/Downloads/ABC/ABC/lib/poi-ooxml-3.16-beta1.jar:/Users/umajyothi/Downloads/ABC/ABC/lib/stax-api-1.0.1.jar:/Users/umajyothi/Downloads/ABC/ABC/lib/xmlbeans-2.6.0.jar JSONValidator 

replace "/Users/umajyothi/Downloads" with your git repo folder 

This will do the code and gives the result in the console as "Build success" along with JSON data

Changing the Environment:

If you want to change the Environment and check it out, please perform the following steps:
1) Go to  environments.properties
2) Change the ExecutionEnvironment=StagingEnvironment
3) Save the File
4) Run the JSONValidator

Note: I have added all libraries directly instead we can use maven and create a pom.xml file to list all the dependencies of the framework.