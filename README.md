# udemy-springboot-rest-apis-todos-v2-jwt
### MySQL database
* Create a MySQL database by running a Docker container: <br>
`docker run -d -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=tododb --name mysqldb -p 3307:3306 mysql:8.0` <br>
* Download DBeaver, and view the database<br>
Click the new Connection, Port: 3307, Database: mysql, Username: root, Password: password<br>
Click Test Connection <br>
Click Finish <br>
Right-click the database and select View Database or View Diagram <br>
Right-click the tables and select View Diagram or View Data