# ATS_ResumeScanner
This is a resume scanner which scans against the keyword given by user  using PDFBox Library.

The code is a Java program that takes a resume file in PDF format as input and performs several operations on it. These operations include finding keywords in the resume, calculating the match percentage of the keywords found, and extracting the email and LinkedIn URL from the resume. Below are the steps to understand and set up the code:


Steps:

1.Install Java: You need to have Java installed on your system to run the program. If you do not have Java installed, you can download and install it from the Oracle website.

2.Install PDFBox library: The program uses the Apache PDFBox library to extract text from the PDF file. You need to download the PDFBox library and add it to the classpath of your Java project.

3.Create a Java/Maven project: You can create a new Java/Maven project in your favorite Java IDE or use the command line to create a project.

4.Copy the code: Copy the code and paste it into a Java file in your project.

5.Set up the keyword.properties file: The program reads a file called keyword.properties that contains a list of keywords to search for in the resume. You need to create this file and add it to the project. The file should be located in the directory /src/main/java/properties/ and should contain the keywords separated by commas. For example: keywords=java, python, c++

6.Run the program: To run the program, you can either run it from the command line or use the Run command in your Java IDE. When the program runs, it will prompt you to enter the path to the resume file. Enter the full path to the PDF file, including the file name and extension.

View the results: The program will output the matching results, the match percentage, the email address, and the LinkedIn URL found in the resume.

Output :
![Screenshot_3](https://user-images.githubusercontent.com/40470805/230779151-8643fcfb-52a1-4dbf-afe5-ffe6ff754052.jpg)

Steps to create Maven Project : 
To create a Maven project, you can follow the following steps:

1.Open your preferred IDE (Eclipse, IntelliJ IDEA, NetBeans, etc.).

2.Click on the "File" menu and select "New" -> "Project".

3.In the "New Project" dialog box, select "Maven" from the list of project types.Click on "Next".

4.Select the archetype for your project. Archetype is a template or a blueprint for your project structure. You can choose a simple project or select from the predefined archetypes that match your project requirements.Click on "Next".

5.Enter the group ID, artifact ID, and version of your project. Group ID is the unique identifier for your project, artifact ID is the name of your project, and version is the version of your project.Click on "Finish".

6.Your Maven project is now created. You can see the project structure in your IDE's "Project Explorer" or "Package Explorer" view.

7.To add dependencies to your project, open the pom.xml file and add the dependencies you require.

8.To build your project, you can use the Maven build lifecycle phases. The common build lifecycle phases are "compile", "test", "package", "install", and "deploy".
To run your project, you can use the Maven command "mvn exec:java".
