# symbol-counter

## How to run the app using JAR file

If you have JRE on the PC, you can run it with that single command:<br>
`java -jar target/symbol-counter-0.0.1-SNAPSHOT.jar`

## How to run the app using Docker

This method require Docker.<br>

1. Create a Docker image:<br>
`docker build -t symbol-counter .` <br><br>
2. Run a Docker container:<br>
`docker run -dp 8080:8080 symbol-counter`

## How to use the app
1. After you run the program (see the instructions above), you will be able to access it via http://localhost:8080/count <br><br>
2. To send a string in which characters need to be counted, you need to use parameters 
(the parameter is called 'text'). Example:<br><br>
`http://localhost:8080/count?text=aaaabccc`
<br><br>The program accepts only Latin letters and string's length should be less than 1000001, 
otherwise the input data is considered invalid, it will return a message with an error description. 
If the string is missing, it will return an empty character-count table.<br><br>
3. In the case of valid input data, 
the results will be a JSON containing a table of the form 
_"symbol": count_. In descending order of counts. Example:<br><br>
`{"symbols":{"a":4,"c":3,"b":1}}` <br><br>

