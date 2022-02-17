Employee Resignation Portal :--- added swagger as well for easy access and testing

Employee Resignation project is based on React js and springboot, this project can be used by anyone who wants to develop a resignation application for learning purpose 
or for their organization, this project can provide a proper start and based on the requirement can be enhanced easily, it also has email functionality.
Based on the requirement email functionality can be utilized.


Project Requirements

JDK 1.11 or higher, Maven, spring boot 2.5 or higher, React js 17.0.2 or higher, H2 database

Project running instruction :- 

STEP 1 : start spring boot project server

STEP 2 : server will start on port 8080, open http://localhost:8080/h2
         this url will open h2 database, all the properties has mentioned in the application.property
         create a Table with all required attributes, if it doesnt create a table autometically.
         if name of the table is other than EMPLOYEE then update the table name in Employee model class
         
STEP 3 : save all your tables and data in local file so that when you re-run your server, data will be fetched from local file and your data will not get lost.
         To achieve this, you have to provide this line against JDBC url( it appears when you open http://localhost:8080/h2 ): jdbc:h2:file:C:/temp/test
         here C:/temp/test is the location in my system where data , schema, table stores.
         
STEP 4 : after doing this, open react project using VsCode, VsCode is best IDE for react based app, but you can open this project using any other IDE as well such as 
         intellij/eclipse etc.
         After opening the project, run following command
         npm install
         npm start
         
         above command will start server on port number 3000

STEP 5 : now open http://localhost:3000
         this will open first page of Employee Resignation Portal.
         
Since both server are running, frontend and backend will be in sync and whichever operation you perform on UI, will connect with backend code, to verify if data is updating 
properly , you can check in h2 database.


