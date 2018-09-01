		package com.scp.app.util;
		
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * driver class list - -- https://www.tutorialspoint.com/jdbc/jdbc-db-connections.htm
 * jar -- driver class name
 */

		public class DataBaseUtil {
			
			static private Connection getConnection(){
				Connection connection = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seleniumdb","root","admin123");//ack/handshaking
					
					if(connection!=null)
						System.out.println("Connection created Successfully");
					
					return connection;
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
					System.out.println("There is some problem with DB Connection");
					return null;
				}
				  
		
			}
			
			public static void createTable(){
				Connection connection = null;
				Statement statement =null;
					try {
						connection =  getConnection();
								if(connection!=null){
												statement = connection.createStatement(); ////communication channel -- you can commiucate/fire query on db
												String createTableQuery = "CREATE TABLE EMPLOYEE("
														+ "EMP_ID INT NOT NULL AUTO_INCREMENT,"
														+ "EMP_NAME VARCHAR(100) NOT NULL,"
														+ "EMP_ADDRESS VARCHAR(40) NOT NULL,"
														+"PRIMARY KEY (emp_id ))";
												statement.executeUpdate(createTableQuery);
								}
								System.out.println("Database table created Succefully....!");
					} catch (SQLException e) {
						e.printStackTrace();
					}finally{
							closeConnectionNStmt(statement,connection);
					}
					
			}
			
			
			private static void closeConnectionNStmt(Statement statement,
					Connection connection) {
				if(statement!=null)
					try {
						statement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				if(connection!=null)
					try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	

				
			}

			public static void insertDummyRecords(int no){
				Connection connection = getConnection();
				Statement statement =null;
				try {
					statement = connection.createStatement();
					for(int i=1;i<=no;i++){
							statement.execute("INSERT INTO EMPLOYEE VALUES("+i+",'YOGESH"+i+"','LATUR"+i+"')");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					closeConnectionNStmt(statement, connection);
				}
				
			}

			public static List<Emp> fetchAllDataFromDB() {
				Connection connection = getConnection();
				Statement stmt =null;
				ResultSet resultSet = null;
				try {
					stmt = connection.createStatement();
					resultSet = stmt.executeQuery("SELECT * FROM EMPLOYEE");
					Emp emp = null;
					List<Emp> listOfemps = new ArrayList<Emp>();
					while(resultSet.next()){
						emp = new Emp();
						emp.setEmpId(resultSet.getInt("EMP_ID"));
						emp.setEmpAddress(resultSet.getString("EMP_ADDRESS"));
						emp.setEmpName(resultSet.getString("EMP_NAME"));
						listOfemps.add(emp);
					}
					return listOfemps;
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
				
				
				
			}
			
			

		}
		
				/**
				 * Steps - JDBC --- Java database connectivity
				 * Step1 -- load the drivers -- copy driver class name from net
				 * Step2 -- create connection
				 * 			1st -- url  -- DBA      :   "jdbc:mysql://localhost:3306/seleniumdb"
				 * 						--1.1 protocol -- set of rules for communication
				 * 						--1.2 DBName -- Mysql
				 * 				*		--1.3 MachineName/IpAddress/hostname -- localhost...127.0.0.1
				 * 				*		--1.4 PortNo
				 * 				*		--1.5 -- db schema name
				 * 			
				 * 		2nd -- username  admin
				 * 		3rd -- password  admin123
				 * 
				 * 
				 * 
				 * 
				 * Step3 -- Create Statement/PreparedStmt
				 * Step4 -- Execute Query
				 * Step5 - Close the connection/stmt/pstmt/rs
				 * 
				 * 
				 * *Optional -- 
				 * ALTER USER 'admin'@'localhost' IDENTIFIED WITH mysql_native_password BY 'admin123';
				 */
			