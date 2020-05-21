# CVSConsume

PURPOSE
This repo includes the source code for a project that takes in a CSV file and adds it to the SQLite database of the 
application.

GET THE APP RUNNING.
After cloning the applicaton, it can be ran on a java IDE including IntelliJ IDEA and Eclipse. Once the run app button is 
pressed on the IDE, the application will automatically start adding the contacts in the ms3Interview.csv file, which is in the
solution of the project, to the SQLite database.

APPROACH, DESIGN, and ASSUMPTIONS
My overall approach was to keep things simple to start with since there is no telling what the application would be used for.
I simply wanted to create an application that reads the CSV file and adds it to the databse. In my design, I made sure to keep 
things modular so that it can be tested more easily and to make it easier to read. Each funtion has a specific purpose and I
created the Connect SQLite class to do all of the database work. My assumption about this app is that it should be close to
bare bones as of now. Extra features can be added such as taking in user input of a csv file in the future.
