/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordersys;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author wisnshaftler
 */
public class getItems {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql = "";
    int counter =0;
    ArrayList<String> list= new ArrayList<>();
    
    public getItems(){
        this.conn = conn;
        this.pst = pst;
        this.rs = rs;
        this.sql = sql;
        conn =  connector.connectDB();
        sql = "Select * from items";
        this.counter = counter;
        this.list = list;
        
        try{
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                list.add(rs.getString("id"));
                list.add(rs.getString("description"));
                list.add(rs.getString("price"));
                list.add(rs.getString("imagename")); 
                list.add(rs.getString("available")); 
            }
            
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Communicating with table have error");
        }
    }
    
    public ArrayList<String> returnData(){
       return list;
    }
}
