Integration Test Framework

To do an Integration tests of web application we can make use of this Java-JSON Integration Test  Framework

Setup Steps

Checkout the fresh code on the physical machine using git clone, perform the following on the root folder of the project exists

$ javac JSONValidator.java `
$ java JSONValidator `
This will do the code and gives the result in the console

Changing the Environment:

If you want to change the Environment and check it out, please perform the following steps:
1) Go to  environments.properties
2) Change the ExecutionEnvironment=StagingEnvironment
3) Save the File
4) Run the JSONValidator `

Note: I have added all libraries directly instead we can use maven and create a pom.xml file to list all the dependencies of the framework.