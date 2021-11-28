/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing .JOptionPane;
/**
 *
 * @author wisnshaftler
 */
public class connector {
    //create connection
    Connection conn = null;
    
    public static Connection connectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //edit this line with your database name and credentials
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ordersys","root","");
            //JOptionPane.showMessageDialog(null, "connected to database");
            return conn;
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
