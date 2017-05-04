
package factory;

import java.sql.Connection;
import java.sql.DriverManager;


public class connfac {
    
    public static Connection getCon()
    {
        Connection con=null;
    try{
    Class.forName("com.mysql.jdbc.Driver");
       con= DriverManager.getConnection("jdbc:mysql://localhost:3306/complaint management?zeroDateTimeBehavior=convertToNull","root","nanu");
    
    }
    catch(Exception e)
    {
        System.out.println("Error:"+e);
    }
    return con;
    }
    
}
