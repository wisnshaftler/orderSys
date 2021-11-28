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
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author wisnshaftler
 */
public class getOrderItems {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sql = "";
    int counter =0;
    ArrayList<String> list= new ArrayList<>();
    
    public getOrderItems(){
        this.conn = conn;
        this.pst = pst;
        this.rs = rs;
        this.sql = sql;
        this.counter = counter;
        this.list = list;
        
        
    }
    public String LoadItems(String id){
        list.removeAll(list);
        conn =  connector.connectDB();
        sql = "select items.id, items.description, items.price, order_items.qty,"
                + " order_items.qty * items.price from items, orders, order_items "
                + "WHERE order_items.orderID = orders.id and order_items.itemID = items.id and orders.id = ?";
        try{
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            while(rs.next()){
                list.add(rs.getString("id"));
                list.add(rs.getString("description"));
                list.add(rs.getString("price"));
                list.add(rs.getString("qty")); 
                list.add(rs.getString("order_items.qty * items.price")); 
                counter ++;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Communicating with table have error" +e);
            return null;
        }
        return String.valueOf(String.valueOf(counter));
    }
    public String returnCustomer(String id){
        conn =  connector.connectDB();
        sql = "select customer.id, customer.name, customer.address, customer.phone, orders.totalAmount, orders.advanceAmount, "
                + "orders.balanceAmount, orders.date FROM orders, customer WHERE customer.id = orders.customerID and orders.id=?";
        try{
            list.removeAll(list);
            pst = conn.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            counter =0;
            while(rs.next()){
                list.add(rs.getString("id"));
                list.add(rs.getString("name"));
                list.add(rs.getString("address"));
                list.add(rs.getString("phone")); 
                list.add(rs.getString("totalAmount"));
                list.add(rs.getString("advanceAmount"));
                list.add(rs.getString("balanceAmount"));
                list.add(rs.getString("date"));
                counter ++;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Communicating with table have error" +e);
            return null;
        }
        return (String.valueOf(counter));
    }
    public String getOrdersByPhone(String phone){
        conn =  connector.connectDB();
        sql = "SELECT orders.id, customer.name, orders.totalAmount, orders.advanceAmount, orders.balanceAmount, orders.date "
                + "FROM orders, customer WHERE orders.customerID = customer.id and customer.phone LIKE ?";
        try{
            list.removeAll(list);
            pst = conn.prepareStatement(sql);
            pst.setString(1, "%"+phone+"%");
            rs = pst.executeQuery();
            counter =0;
            while(rs.next()){
                list.add(rs.getString("id"));
                list.add(rs.getString("name"));
                list.add(rs.getString("totalAmount"));
                list.add(rs.getString("advanceAmount"));
                list.add(rs.getString("balanceAmount"));
                list.add(rs.getString("date"));
                counter ++;
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Communicating with table have error" +e);
            return null;
        }
        return (String.valueOf(counter));
    }
    
    public ArrayList<String> returnData(){
       return list;
    }
}
