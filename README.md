# udemy-springboot-rest-apis-todos-v2-jwt
### MySQL database
* Create a MySQL database by running a Docker container: <br>
`docker run -d -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=tododb --name mysqldb -p 3307:3306 mysql:8.0` <br>
* Download DBeaver, and view the database<br>
Click the new Connection, Port: 3307, Database: mysql, Username: root, Password: password<br>
Click Test Connection <br>
Click Finish <br>
Right-click the database and select View Database or View Diagram <br>
Right-click the tables and select View Diagram or View Data <br>

### Swagger-ui REST API testing
* Open the Swagger UI through URL: http://localhost:8080/docs or http://localhost:8080/swagger-ui/index.html <br>
* Log in with username (default: "user") and password (generated in the project console)
* Test the Register a user POST method <br>
URL: http://localhost:8080/api/auth/register <br>
RequestBody:
  {
  "firstName": "Eric",
  "lastName": "Roby",
  "email": "eric@codingwithroby.com",
  "password": "test1234!"
  } <br>