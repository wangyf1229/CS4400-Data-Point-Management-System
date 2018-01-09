package phaseIII;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by cao on 4/11/17.
 */
public class DB_test {

    static Connection conn;
    static Statement stmt;
    
    public static void conn_and_test(){
        conn = null;
        stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String username = "root";
            String password = "liang2012";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql" +
                    "?autoReconnect=true&useSSL=false", username, password);
            //now we have connected to mysql DB system.
            System.out.println("Creating Database..");
            stmt = conn.createStatement();
            //stmt.executeUpdate("DROP DATABASE IF EXISTS DB_G77"); //to be deleted, just for test.
            //check and create a database if this database exist or not.
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS DB_G77");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/DB_G77" +
                    "?autoReconnect=true&useSSL=false", username, password);
            stmt = conn.createStatement();
            //CREATE ALL TABLES FIRST:
            //stmt.executeUpdate("DELETE FROM CityState"); //to be deleted, just for test.
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS CityState" +
                    "(City VARCHAR(100) NOT NULL," +
                    "State VARCHAR(100) NOT NULL," +
                    "PRIMARY KEY(City,State)) ENGINE=InnoDB");
            //stmt.executeUpdate("DELETE FROM User"); //to be deleted, just for test.
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS User" +
                    "(Username VARCHAR(100) NOT NULL," +
                    "Email VARCHAR(100) NOT NULL," +
                    "Password VARCHAR(100) NOT NULL," +
                    "UserType ENUM('Administrator','City Scientist','City Official') NOT NULL," +
                    "PRIMARY KEY(Username)," +
                    "UNIQUE(Email)," +
                    "CHECK(Password>8)) ENGINE=InnoDB");
            //stmt.executeUpdate("DELETE FROM CityOfficial"); //to be deleted, just for test.
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS CityOfficial" +
                    "(Username VARCHAR(100) NOT NULL," +
                    "Title VARCHAR(30)," +
                    "Approved BOOLEAN DEFAULT NULL," +
                    "City VARCHAR(100)," +
                    "State VARCHAR(100)," +
                    "FOREIGN KEY(Username) REFERENCES User(Username)" +
                    "ON DELETE CASCADE ON UPDATE CASCADE," +
                    "FOREIGN KEY (City,State) REFERENCES CityState (City,State)" +
                    "ON DELETE SET NULL ON UPDATE CASCADE) ENGINE=InnoDB");
            //stmt.executeUpdate("DELETE FROM POI"); //to be deleted, just for test.
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS POI" +
                    "(LocName VARCHAR(100) NOT NULL," +
                    "Flag BOOLEAN DEFAULT NULL," +
                    "DateFlagged DATE," +
                    "ZipCode VARCHAR(5) NOT NULL," +
                    "City VARCHAR(100) NOT NULL," +
                    "State VARCHAR(100) NOT NULL," +
                    "PRIMARY KEY (LocName)," +
                    "FOREIGN KEY (City,State) REFERENCES CityState(City,State)" +
                    "ON DELETE CASCADE  ON UPDATE CASCADE," +
                    "CHECK (ZipCode=5)) ENGINE=InnoDB");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS DataType" +
                    "(DataType ENUM('Mold','Air Quality') NOT NULL," +
                    "PRIMARY KEY (DataType)) ENGINE=InnoDB");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS DataPoint" +
                    "(LocationName VARCHAR(100) NOT NULL," +
                    "DateRecorded TIMESTAMP NOT NULL," +
                    "Accepted BOOLEAN DEFAULT NULL," +
                    "DataValue INT NOT NULL," +
                    "DataType ENUM('Mold','Air Quality') NOT NULL," +
                    "PRIMARY KEY (LocationName,DateRecorded)," +
                    "FOREIGN KEY (LocationName) REFERENCES POI(LocName)" +
                    "ON DELETE CASCADE  ON UPDATE CASCADE," +
                    "FOREIGN KEY (DataType) REFERENCES DataType(DataType)" +
                    "ON DELETE CASCADE  ON UPDATE CASCADE) ENGINE=InnoDB");
              }catch(SQLException se){
            se.printStackTrace();
            System.out.println("Error 01");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error 02");
        }
        //finally{
            //try {
                //if(stmt!= null)
                    //stmt.close();
            //}catch(SQLException se2){
                //System.out.println("Error 03");
            //}
            //try{
                //if(conn!=null)
                    //conn.close();
            //}catch(SQLException se){
                //se.printStackTrace();
                //System.out.println("Error 05");
            //}
        //}
        System.out.println("Completed!");
    }
    

    public static int login(String Username_input,String Password_input){
        conn_and_test();
        int flag = 99;
        try{
            System.out.println("test01"+Username_input+","+Password_input);
        	PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM User WHERE Username = ?;");
        	pstmt.setString(1,Username_input);
        	//pstmt.setString(2,Password_input);
        	ResultSet rs_ck = pstmt.executeQuery();
        	//return 50;  // Not Admin, maybe other two types of Users.
        	if(rs_ck.next()) {
                    String Username_ck = rs_ck.getString("Username");
                    String Password_ck = rs_ck.getString("Password");
                    String UserType_ck = rs_ck.getString("UserType");
                     System.out.println(UserType_ck);
                    if(Username_ck.equals(Username_input)){
                        System.out.println("test03");
                        if(Password_ck.equals(Password_input)){
                            System.out.println("test04");
                            if(UserType_ck.equals("City Scientist")){
                                    flag = 1; //Allow CityScientist login
	            		}else if(UserType_ck.equals("City Official")){
                                System.out.println("test05");
                                ResultSet rs_ck_CO = stmt.executeQuery("SELECT * FROM CityOfficial " +
                                        "WHERE Username = '" + Username_ck +"' AND " +
                                        "Approved IS TRUE;");
                                if(rs_ck_CO.next()){
                                    flag = 2; //Allow CityOfficial login
                                    System.out.println("CityOfficial Approved!");
                                }else{
                                    flag = 20; //Decline Unapproved CityOfficial
                                    System.out.println("CityOfficial Decline!!");
                                }
                                //System.out.println(rs_ck_CO);
                                //Boolean CO_Approved = rs_ck_CO.getBoolean("Approved");
                                //System.out.println(CO_Approved);
                                //if(CO_Approved){
                                    //flag = 2; //Allow CityOfficial login
                                //}else{
                                //    flag = 20; //Decline Unapproved CityOfficial
                                //    System.out.println("CityOfficial Decline!!");
                                //}
	            		}else{
                                        flag = 3; //Allow Admin login
	            		}
	 			}else{
                                  flag = 10;//Wrong Password           		
	            	}
        		}else{
                                flag = 11;  //Wrong Username
        		}         		
         	}
        
        }catch(SQLException se){
             throw se;
        }finally{
            return flag;
        }
    }
    
    public static int Register_User(User input){
        conn_and_test();
        int flag = 99;
        try{
        String Username_input = input.getUsername();
        String Email_input = input.getEmail();
        String Password_input = input.getPassword();
        String UserType_input = input.getUsertype();
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM User " + "WHERE Username= ? OR Email=?;");
        pstmt.setString(1,Username_input);
        pstmt.setString(2,Email_input);
        ResultSet rs_ck = pstmt.executeQuery();
        if(rs_ck.next()) {      
                if(rs_ck.getString("Username").equals(Username_input)){
                System.out.println("Username existed!");
                flag = 0;}
                else{
                System.out.println("Email existed!");
                flag = 1;            
                } 
         }else {
            //stmt.executeUpdate("INSERT INTO User values(Username_input,Email_input,Password_input,UserType_input)");
            pstmt = conn.prepareStatement("INSERT INTO User values(?,?,?,?)");
            pstmt.setString(1,Username_input);
            pstmt.setString(2,Email_input);
            pstmt.setString(3,Password_input);
            pstmt.setString(4,UserType_input);
            pstmt.executeUpdate();
            //stmt.executeUpdate("INSERT INTO User values('Xin','xin@gmail.com','666666666','City Scientist')");
            System.out.println("Register Successful!");
            flag = 2 ;            
         }
        }catch(SQLException se){
            throw se;
        }finally{
            return flag;
        }    
    }
    
      public static int Register_CityOfficial(CityOfficial input){
        conn_and_test();
        int flag = -1;
        try{
            String Username_input = input.getUsername();
            String Title_input = input.getTitle();
            String Email_input = input.getEmail();
            String Password_input = input.getPassword();
            String UserType_input = input.getUsertype();
            Boolean Approved_input=input.getApproved();
            String City_input = input.getCity();
            String State_input = input.getState();
            
            PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM User WHERE Username = ? OR Email = ? ");
            pstmt1.setString(1,Username_input);
            pstmt1.setString(2,Email_input);
            System.out.println("test2.1 ");
            //ResultSet rs_ck1 = pstmt1.executeQuery();
            ResultSet rs_ck2 = pstmt1.executeQuery();

            
            if(rs_ck2.next()){
                if(rs_ck2.getString("Username").equals(Username_input)){
                System.out.println("test2.2 ");
                System.out.println("Username existed!");
                flag = 0;}
                else{
                System.out.println("test2.3 ");
                System.out.println("Email existed!");
                flag = 1;            
                } 
                //Need output hint to frontend!
            }
            
            else{
                //stmt.executeUpdate("INSERT INTO User values(Username_input,Email_input,Password_input,UserType_input)");
                System.out.println("test2.5 ");
                PreparedStatement pstmt4 = conn.prepareStatement("INSERT INTO User values(?,?,?,?)");
                pstmt4.setString(1,Username_input);
                pstmt4.setString(2,Email_input);
                pstmt4.setString(3,Password_input);
                pstmt4.setString(4,UserType_input);
                pstmt4.executeUpdate();
                System.out.println("miaomiao");
                PreparedStatement pstmt3 = conn.prepareStatement("INSERT INTO CityOfficial(Username,Title,City,State) values(?,?,?,?)");       
                pstmt3.setString(1,Username_input);
                pstmt3.setString(2,Title_input);
                pstmt3.setString(3,City_input);
                pstmt3.setString(4,State_input);
                pstmt3.executeUpdate();
                System.out.println("CityOfficial Register Successful!");
                flag = 2;
            }
        }catch(SQLException se){
            System.out.println("Registration failed!");
            flag = 3;
            throw se;
        }finally{
            return flag;
        }
    }
    //-------------------------------//----------------------------------------//  
        //genereating POI report.
    //Starting function for Admin Function:
    public static Object[][] Pending_CityOfficial_Graph() {
        //String [] Usernames_selected is which CityOfficials the Admin chooses to approve or reject.
        conn_and_test();
        Object [][] graph = new Object [1][5];
        try {
         ResultSet rs_ck_rows = stmt.executeQuery("SELECT count(*) as ct FROM CityOfficial WHERE Approved IS NULL;");
            rs_ck_rows.next();
            int rows = rs_ck_rows.getInt("ct");
            graph = new Object[rows][6];
            //ResultSet rs_ck_rows = stmt.executeQuery("SELECT count(*) FROM CityOfficial;");
            //int rows = rs_ck_rows.getInt(1);
            //System.out.println("the number of rows of the table: " + rows);
           if(rows>0){       
             ResultSet rs_ck = stmt.executeQuery("SELECT * FROM (CityOfficial as CO join User as UR) where CO.Username = UR.Username AND Approved IS NULL;");
             int ir = 0;
            while(rs_ck.next()){
            graph[ir][0] = false;
            graph[ir][1] =rs_ck.getString("Username");
            graph[ir][2] =rs_ck.getString("Email");
            graph[ir][3] =rs_ck.getString("City");
            graph[ir][4] =rs_ck.getString("State");
            graph[ir][5] =rs_ck.getString("Title"); 
             ir = ir+1;
         }
            return graph;
         }else{
         System.out.println("Empty CityOfficial table!!");
         }
        } catch (SQLException se) {
            System.out.println("Error in extract non-apporved city official");
        }finally {
            return graph;
        }
    }
    
   public static void accept_CityOfficial(String[] usernames){
       conn_and_test();
       for(int i = 0; i<usernames.length;i++){
           try {
               String temp_name = usernames[i];          
               System.out.println(temp_name);      
               PreparedStatement pstmt = conn.prepareStatement("UPDATE CityOfficial SET Approved=TRUE"
                       + " WHERE Username = ?");           
               pstmt.setString(1,temp_name);
               pstmt.executeUpdate();
               //ResultSet rs_ck = stmt.executeQuery("UPDATE DataPoint SET Accepted=TRUE"
                       //+ " WHERE LocationName = "+locname+" AND DataRecorded = "+date_recorded);         
           } catch (SQLException ex) {
              System.out.println("Error in accept city official");
           }
    }
   }
   public static void  reject_CityOfficial(String[] usernames){
       conn_and_test();
       for(int i = 0; i<usernames.length;i++){
           try {
               String temp_name = usernames[i];          
               System.out.println(temp_name);      
               PreparedStatement pstmt = conn.prepareStatement("UPDATE CityOfficial SET Approved=FALSE"
                       + " WHERE Username = ?");           
               pstmt.setString(1,temp_name);
               pstmt.executeUpdate();
               //ResultSet rs_ck = stmt.executeQuery("UPDATE DataPoint SET Accepted=TRUE"
                       //+ " WHERE LocationName = "+locname+" AND DataRecorded = "+date_recorded);         
           } catch (SQLException ex) {
              System.out.println("Error in accept city official");
           }
    }
   }
   public static void accept_Datapoint(DataPoint[] accepted){
       conn_and_test();
       for(int i = 0; i<accepted.length;i++){
           try {
               String locname = accepted[i].getD_name();
               String date_recorded = accepted[i].getD_DateRecorded();
               System.out.println(date_recorded+locname);      
               PreparedStatement pstmt = conn.prepareStatement("UPDATE DataPoint SET Accepted=TRUE"
                       + " WHERE LocationName = ? AND DateRecorded = ?");           
               pstmt.setString(1,locname);
               pstmt.setString(2,date_recorded);
               pstmt.executeUpdate();
               //ResultSet rs_ck = stmt.executeQuery("UPDATE DataPoint SET Accepted=TRUE"
                       //+ " WHERE LocationName = "+locname+" AND DataRecorded = "+date_recorded);         
           } catch (SQLException ex) {
              System.out.println("Error in accept dp");
           }
    }
   }
   
    public static void reject_Datapoint(DataPoint[] accepted){
       conn_and_test();
       for(int i = 0; i<accepted.length;i++){
           try {
               String locname = accepted[i].getD_name();
               String date_recorded = accepted[i].getD_DateRecorded();
               System.out.println(date_recorded);
               PreparedStatement pstmt = conn.prepareStatement("UPDATE DataPoint SET Accepted=FALSE"
                       + " WHERE LocationName = ? AND DateRecorded = ?");
               pstmt.setString(1,locname);
               pstmt.setString(2,date_recorded);
               pstmt.executeUpdate();
               //ResultSet rs_ck = stmt.executeQuery("UPDATE DataPoint SET Accepted=TRUE"
                       //+ " WHERE LocationName = "+locname+" AND DataRecorded = "+date_recorded);         
           } catch (SQLException ex) {
              System.out.println("Error in reject dp");
           }
    }
   }
   
  public static Object[][] Pending_DataPoint_Graph() {
        //String [] Usernames_selected is which CityOfficials the Admin chooses to approve or reject.
        conn_and_test();
        Object[][] graph = new Object[1][5];
        try {
         ResultSet rs_ck_rows = stmt.executeQuery("SELECT count(*) as ct FROM DataPoint WHERE Accepted IS NULL;");
            rs_ck_rows.next();
            int rows = rs_ck_rows.getInt("ct");
            System.out.println(rows);
            graph = new Object[rows][5];
            
            //ResultSet rs_ck_rows = stmt.executeQuery("SELECT count(*) FROM CityOfficial;");
            //int rows = rs_ck_rows.getInt(1);
            //System.out.println("the number of rows of the table: " + rows);
        if(rows>0){     
            ResultSet rs_ck = stmt.executeQuery("SELECT * FROM DataPoint where Accepted IS NULL;");
            int ir = 0;
            System.out.println("ir:"+ir); 
            while(rs_ck.next()){
                System.out.println("ir:"+ir);
            graph[ir][0] = false;
            //if(rs_ck.getString("Accepted")==null){
              System.out.println("miaomiao");
                //graph[ir][0] = false;
            //}else{
                //graph[ir][0] = true;
            //}
            graph[ir][1] =rs_ck.getString("LocationName");
            graph[ir][2] =rs_ck.getString("DataType");
            graph[ir][3] =rs_ck.getInt("DataValue");
            String temp = rs_ck.getString("DateRecorded") ;           
            graph[ir][4] = temp.substring(0, temp.indexOf('.'));
            ir = ir+1;
            }
            //return graph;
         }else{
            System.out.println("Empty DataPoint table!");
         }
        } catch (SQLException se) {
            System.out.println("Error in extract dp");
        }finally{
            //return graph;
        }
        return graph;
  }
     //filtering and returning filted POI list.
      public static int Add_POI(POI input){
        conn_and_test();
        try{
            String LocName_input = input.getName();
            //Boolean Flag_input = input.getFlag();
            //String date_input = input.getDateFlagged();
            //String DateFlagged_input = date_input.toString();
            String ZipCode_input = input.getZipcode();
            String City_input = input.getCity();
            String State_input = input.getState();
            //check if a Locname exists or not:
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM POI " + "WHERE LocName= ?;");
            pstmt.setString(1,LocName_input);
            System.out.println(LocName_input);
            ResultSet rs_ck = pstmt.executeQuery();
            if(rs_ck.next()) {
                System.out.println("POI existed!");
                return 0;
                //Need output hint to frontend!
            } else {
               PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO POI (LocName,ZipCode,City,State) values(?,?,?,?)");
                pstmt2.setString(1,LocName_input);
                pstmt2.setString(2,ZipCode_input);
                pstmt2.setString(3,City_input);
                pstmt2.setString(4,State_input);
                pstmt2.executeUpdate();
                System.out.println("Adding POI Successfully!");
                return 1 ;
            }
        }catch(SQLException se){
            System.out.println("SQLException");
            return 3;
        }
    }
      
      public static ResultSet Get_POI(){
    	conn_and_test();
        ResultSet rs_ck=null;
        try{
    	rs_ck = stmt.executeQuery("SELECT * FROM POI;");
        }catch(SQLException se){
             System.out.println("SQLException in Get_POI");
        }
          return rs_ck;
      }
      
      public static int Add_DataPoint(DataPoint input){
           conn_and_test();
        try{
            String LocationName_input = input.getD_name();
            String DateRecorded_input = input.getD_DateRecorded();
            int DataValue_input = input.getD_DataValue();
            String DataType_input = input.getD_DateType();
            //check if a username exists or not:
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM DataPoint WHERE LocationName= ? AND DateRecorded=?;");
            pstmt.setString(1,LocationName_input);
            pstmt.setString(2,DateRecorded_input);
            ResultSet rs_ck = pstmt.executeQuery();
            if(rs_ck.next()) {
                System.out.println("DataPoint existed!");
                return 0;
                //Need output hint to frontend!
            } else {
                PreparedStatement pstmt2 = conn.prepareStatement("INSERT INTO DataPoint" +
                        "(LocationName, DateRecorded, DataValue, DataType) values(?,?,?,?)");
                pstmt2.setString(1,LocationName_input);
                pstmt2.setString(2,DateRecorded_input);
                pstmt2.setInt(3,DataValue_input);
                pstmt2.setString(4,DataType_input);
                pstmt2.executeUpdate();
                System.out.println("Adding DataPoint Successfully!");
                return 1 ;
            }
        }catch(SQLException se){
            System.out.println("SQLEXception in Add_DataPoint");
            return 3;
        }
      }
      
      public static Object[][] View_POIs (POI input,String endDate,boolean isEmpty_tmp) throws SQLException{
          conn_and_test();
          ResultSet rs_filter=null;
           String Locname_input = input.getName();
              String City_input = input.getCity();
              String State_input = input.getState();
              String Zipcode_input = input.getZipcode();
              Boolean Flag_input = input.getFlag();
              String Datestart_input = input.getDateFlagged();
              String Dateend_input = endDate;
              boolean isEmpty = isEmpty_tmp;
              if(!isEmpty){
                  if(Flag_input){
          try{
              PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM POI WHERE " +
                      "(LocName = ? OR ? IS NULL) AND "+
                      "(City = ? OR ? IS NULL) AND "+
                      "(State = ? OR ? IS NULL) AND "+
                      "(ZipCode = ? OR ? IS NULL) AND "+
                      "(DateFlagged BETWEEN ? AND ?) AND"+
                      "(Flag = ?)");
              pstmt1.setString(1,Locname_input);
              pstmt1.setString(2,Locname_input);
              pstmt1.setString(3,City_input);
              pstmt1.setString(4,City_input);
              pstmt1.setString(5,State_input);
              pstmt1.setString(6,State_input);
              pstmt1.setString(7,Zipcode_input);
              pstmt1.setString(8,Zipcode_input);
              pstmt1.setString(9,Datestart_input);
              pstmt1.setString(10,Dateend_input);
              pstmt1.setString(10,Dateend_input);
              pstmt1.setBoolean(11,Flag_input);
             rs_filter = pstmt1.executeQuery();
              if(!rs_filter.next()) {
                  System.out.println("No such records!");
              } else {
                  System.out.println("Search completed when date exists!");
          }}catch(SQLException se){
             System.out.println("SQLException when date exists");
           }}
                  else{try{
              PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM POI WHERE " +
                      "(LocName = ? OR ? IS NULL) AND "+
                      "(City = ? OR ? IS NULL) AND "+
                      "(State = ? OR ? IS NULL) AND "+
                      "(ZipCode = ? OR ? IS NULL) AND "+
                      "(DateFlagged BETWEEN ? AND ?)");
              pstmt1.setString(1,Locname_input);
              pstmt1.setString(2,Locname_input);
              pstmt1.setString(3,City_input);
              pstmt1.setString(4,City_input);
              pstmt1.setString(5,State_input);
              pstmt1.setString(6,State_input);
              pstmt1.setString(7,Zipcode_input);
              pstmt1.setString(8,Zipcode_input);
              pstmt1.setString(9,Datestart_input);
              pstmt1.setString(10,Dateend_input);
              pstmt1.setString(10,Dateend_input);
             rs_filter = pstmt1.executeQuery();
              if(!rs_filter.next()) {
                  System.out.println("No such records!");
              } else {
                  System.out.println("Search completed when date exists!");
          }}catch(SQLException se){
             System.out.println("SQLException when date exists");
           }
                      
                  }
          }else{   if(Flag_input){
               try{
              PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM POI WHERE "+
                        "(LocName = ? OR ? IS NULL) AND"+
                        "(City = ? OR ? IS NULL) AND "+
                        "(State = ? OR ? IS NULL) AND "+
                        "(ZipCode = ? OR ? IS NULL) AND"+
                         "(Flag = ?)");
            pstmt2.setString(1,Locname_input);
             pstmt2.setString(2,Locname_input);
            pstmt2.setString(3,City_input);
             pstmt2.setString(4,City_input);
             pstmt2.setString(5,State_input);
              pstmt2.setString(6,State_input);
              pstmt2.setString(7,Zipcode_input);
              pstmt2.setString(8,Zipcode_input);
              pstmt2.setBoolean(9,Flag_input);
             rs_filter = pstmt2.executeQuery();
              if(!rs_filter.next()) {
                  System.out.println("No such records!");
              } else {
                  System.out.println("Search completed when date not exists!");
          }}catch(SQLException se){
             System.out.println("SQLException when date not exists");
           }} else{
              try{
              PreparedStatement pstmt2 = conn.prepareStatement("SELECT * FROM POI WHERE "+
                        "(LocName = ? OR ? IS NULL) AND"+
                        "(City = ? OR ? IS NULL) AND "+
                        "(State = ? OR ? IS NULL) AND "+
                        "(ZipCode = ? OR ? IS NULL)");
            pstmt2.setString(1,Locname_input);
             pstmt2.setString(2,Locname_input);
            pstmt2.setString(3,City_input);
             pstmt2.setString(4,City_input);
             pstmt2.setString(5,State_input);
              pstmt2.setString(6,State_input);
              pstmt2.setString(7,Zipcode_input);
              pstmt2.setString(8,Zipcode_input);
             rs_filter = pstmt2.executeQuery();
              if(!rs_filter.next()) {
                  System.out.println("No such records!");
              } else {
                  System.out.println("Search completed when date not exists!");
          }}catch(SQLException se){
             System.out.println("SQLException when date not exists");
           }
          }
                  
              }
          rs_filter.beforeFirst();
         int num_rows=0;
         while (rs_filter.next()){num_rows++;}
         rs_filter.beforeFirst();
         Object[][] graph = new Object[1][6];
             try {
            graph = new Object[num_rows][6];
            System.out.println("rows are "+num_rows);
        if(num_rows>0){     
            int ir = 0;
            //System.out.println("ir:"+ir); 
            while(rs_filter.next()){
            //System.out.println("ir:"+ir);
            graph[ir][0] = rs_filter.getString("LocName");
            // System.out.println(graph[ir][0]);
            graph[ir][1] =rs_filter.getString("City");
            graph[ir][2] =rs_filter.getString("State");
            graph[ir][3] =rs_filter.getInt("ZipCode");       
            Boolean flagged =rs_filter.getBoolean("Flag");
            if(flagged == null){
            graph[ir][4] = "null";
            }else if(flagged == true){
                graph[ir][4]="yes";
            }else if(flagged == false){
                graph[ir][4] = "no";
            }
            Date temp =rs_filter.getDate("DateFlagged");
            if(temp==null){
               graph[ir][5]="null";
            }if(temp!=null){
                graph[ir][5]=temp.toString();
            }
             ir = ir+1;
            }
            //return graph;
         }else{
            System.out.println("Empty POI table!");
         }
        } catch (SQLException se) {
            System.out.println("Error in extract dp");
        }finally{
            //return graph;
        }
        return graph;
      }
      
  public static void FlagToggle(String POI_Loc){
        try{
            String sql;
            String sql2;
            ResultSet rs;
               PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM POI WHERE LocName = ?");
                pstmt.setString(1,POI_Loc);
                rs = pstmt.executeQuery();
                System.out.println(POI_Loc);
            
                if(rs.next()){
                    //rs.beforeFirst();
                boolean Flag = rs.getBoolean("Flag");
                System.out.println(Flag);
                    if (Flag) {
                    //unFlag the data
                    sql = "UPDATE POI SET Flag = false WHERE " +
                            "LocName = '"+ POI_Loc +"'";
                    stmt.executeUpdate(sql);
                    sql2 = "UPDATE POI SET DateFlagged = NULL WHERE " +
                            "LocName = '"+ POI_Loc +"'";
                    stmt.executeUpdate(sql2);

                } else {
                    //unFlag the data
                    sql = "UPDATE POI SET Flag = true WHERE " +
                            "LocName = '"+ POI_Loc +"'";
                    stmt.executeUpdate(sql);
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date dateobj_tmp = new Date();
                    String dateobj=df.format(dateobj_tmp);
                    System.out.println(dateobj);
                    String dateflagged =dateobj.toString();
                    sql2 = "UPDATE POI SET DateFlagged = '"+dateflagged+"' WHERE LocName = '"+ POI_Loc +"'";
                    stmt.executeUpdate(sql2);
                }
                }
            
            System.out.println("FlagToggle Done!");
        }catch (SQLException se){
            se.printStackTrace();
            System.out.println("Error 01");
        }
        
  }
  
   public static Object[][] View_POI_datapoint (DataPoint input,String endTime, int endValue, boolean isTimeEmpty_tmp, boolean isDVEmpty_tmp) throws SQLException{
          conn_and_test();
          ResultSet rs_filter=null;
          String Locname_input=input.getD_name();
           String datatype_input =input.getD_DateType();
           int start_datavalue_input = input.getD_DataValue();
           String start_time=input.getD_DateRecorded();
           String end_time=endTime;
           int end_datavalue_input=endValue;
           boolean isTimeEmpty = isTimeEmpty_tmp;
           boolean isDVEmpty =isDVEmpty_tmp;
              if(!isTimeEmpty){
                  if(!isDVEmpty){
              // DV range and time range existed
          try{
              PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM DataPoint WHERE " +
                      "(Accepted = true) AND"+
                      "(LocationName = ?) AND "+
                      "(DataType =?) AND"+
                      "(DataValue BETWEEN ? AND ?) AND "+
                      "(DateRecorded BETWEEN ? AND ?)");
              pstmt1.setString(1,Locname_input);
              pstmt1.setString(2,datatype_input);
              pstmt1.setInt(3,start_datavalue_input);
              pstmt1.setInt(4,end_datavalue_input);
              pstmt1.setString(5,start_time);
              pstmt1.setString(6,end_time);
             rs_filter = pstmt1.executeQuery();
              if(!rs_filter.next()) {
                  System.out.println("No such records when dv and time exists!");
              } else {
                  System.out.println("Search completed when dv and time exists!");
          }}catch(SQLException se){
             System.out.println("SQLException when dv and time exists");
           }
          }else{ 
               //Time range existed and DV range not existed
                      try{
              PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM DataPoint WHERE " +
                      "(Accepted = true) AND"+
                      "(LocationName = ?) AND "+
                      "(DataType =?) AND"+
                      "(DateRecorded BETWEEN ? AND ?)");
              pstmt1.setString(1,Locname_input);
              pstmt1.setString(2,datatype_input);
              pstmt1.setString(3,start_time);
              pstmt1.setString(4,end_time);
             rs_filter = pstmt1.executeQuery();
              if(!rs_filter.next()) {
                  System.out.println("No such records when time exists but not the value!");
              } else {
                  System.out.println("Search completed when time exists but not the value!");
          }}catch(SQLException se){
             System.out.println("SQLException when time exists but not the value");
           }  
                  }}
              else{
          if(!isDVEmpty){
              // DV range existed but not the time
          try{
              PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM DataPoint WHERE " +
                      "(Accepted = true) AND"+
                      "(LocationName = ?) AND "+
                      "(DataType =?) AND"+
                      "(DataValue BETWEEN ? AND ?)");
              pstmt1.setString(1,Locname_input);
              pstmt1.setString(2,datatype_input);
              pstmt1.setInt(3,start_datavalue_input);
              pstmt1.setInt(4,end_datavalue_input);
             rs_filter = pstmt1.executeQuery();
              if(!rs_filter.next()) {
                  System.out.println("No such records when dv exists but not the time!");
              } else {
                  System.out.println("Search completed when dv exists but not the time!");
          }}catch(SQLException se){
             System.out.println("SQLException when dv exists but not the time");
           }
          }else{ 
               //Time and DV range not existed
                      try{
              PreparedStatement pstmt1 = conn.prepareStatement("SELECT * FROM DataPoint WHERE " +
                      "(Accepted = true) AND"+
                      "(LocationName = ?) AND "+
                      "(DataType =?)");
              pstmt1.setString(1,Locname_input);
              pstmt1.setString(2,datatype_input);
              System.out.println(Locname_input);
              System.out.println(datatype_input);
             rs_filter = pstmt1.executeQuery();
              if(!rs_filter.next()) {
                  System.out.println("No such records when time and value not existed!");
              } else {
                  System.out.println("Search completed when time and value not existed!");
          }}catch(SQLException se){
             System.out.println("SQLException when time and value not existed");
           }               
              }}
         rs_filter.beforeFirst();
         int num_rows=0;
         while (rs_filter.next()){num_rows++;}
         rs_filter.beforeFirst();
         Object[][] graph = new Object[1][3];
             try {
            graph = new Object[num_rows][3];
            System.out.println("rows are "+num_rows);
        if(num_rows>0){     
            int ir = 0;
            System.out.println("ir:"+ir); 
            while(rs_filter.next()){
            System.out.println("ir:"+ir);
            graph[ir][0] = rs_filter.getString("DataType");
            System.out.println(graph[ir][0]);
            graph[ir][1] =rs_filter.getInt("DataValue");
            graph[ir][2] =rs_filter.getString("DateRecorded");
             ir = ir+1;
            }
            //return graph;
         }else{
            System.out.println("Empty DataPoint table!");
         }
        } catch (SQLException se) {
            System.out.println("Error in extract dp");
        }finally{
            //return graph;
        }
        return graph;
      }
   
    ///-------------------------------//--///-///////-------------------------------//
     public static Object[][] POI_Report_View(){
        Object[][] POI_Report = null;
        try{      
            ResultSet rs_ck01 = stmt.executeQuery("SELECT AA.LocName, AA.City, AA.State, " +
                    "AA.MIN_Mold, AA.AVG_Mold, AA.MAX_Mold, BB.MIN_AQ, BB.AVG_AQ, BB.MAX_AQ, " +
                    "AA.NO_DataPoints_Mold+BB.No_DataPoints_AQ, AA.Flag from (SELECT * from ((SELECT LocName, City, State, " +
                    "MIN(DataValue) as MIN_Mold, AVG(DataValue) as AVG_Mold, MAX(DataValue) as MAX_Mold, " +
                    "count(*) as NO_DataPoints_Mold, Flag, DataType from (POI as POI01 join DataPoint) " +
                    "WHERE LocName = LocationName AND DataType = 'Mold' AND Accepted = true Group by LocName, DataType) as AA0) WHERE DataType = 'Mold') as AA join " +
                    "(SELECT * from ((SELECT LocName as LocName_AQ," +
                    "MIN(DataValue) as MIN_AQ, AVG(DataValue) as AVG_AQ, MAX(DataValue) as MAX_AQ, " +
                    "count(*) as NO_DataPoints_AQ, DataType from (POI as POI02 join DataPoint) " +
                    "WHERE LocName = LocationName AND DataType = 'Air Quality' AND Accepted = true Group by LocName, DataType) as BB0) where DataType = 'Air Quality') as BB " +
                    "WHERE AA.LocName = BB.LocName_AQ;");
            
            rs_ck01.last();
            int Rows01 = rs_ck01.getRow();
            rs_ck01.beforeFirst();
            POI_Report = new Object[Rows01][11];
            //rs_ck02.beforeFirst();
            //Object POI_Report[][] = new Object[Rows01][11];
            int irow = 0;
            while(rs_ck01.next()){
                POI_Report[irow][0] = rs_ck01.getString(1);
                System.out.println(POI_Report[irow][0]);
                POI_Report[irow][1] = rs_ck01.getString(2);
                System.out.println(POI_Report[irow][1]);
                POI_Report[irow][2] = rs_ck01.getString(3);
                System.out.println(POI_Report[irow][2]);
                POI_Report[irow][3] = rs_ck01.getInt(4);
                System.out.println(POI_Report[irow][3]);
                POI_Report[irow][4] = rs_ck01.getFloat(5);
                System.out.println(POI_Report[irow][4]);
                POI_Report[irow][5] = rs_ck01.getInt(6);
                System.out.println(POI_Report[irow][5]);
                POI_Report[irow][6] = rs_ck01.getInt(7);
                System.out.println(POI_Report[irow][6]);
                POI_Report[irow][7] = rs_ck01.getFloat(8);
                System.out.println(POI_Report[irow][7]);
                POI_Report[irow][8] = rs_ck01.getInt(9);
                System.out.println(POI_Report[irow][8]);
                POI_Report[irow][9] = rs_ck01.getInt(10);
                System.out.println(POI_Report[irow][9]);
                if(rs_ck01.getBoolean(11)==true){
                POI_Report[irow][10] = "yes";}
                else{
                 POI_Report[irow][10] = "no";
                }
                System.out.println(POI_Report[irow][10]);
                irow = irow + 1;
            }

            //return POI_Report;


        }catch(SQLException se){
            se.printStackTrace();
        }
        return POI_Report;
    }
}