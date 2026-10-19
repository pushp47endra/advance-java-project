package com.shopsphere.service;
import com.shopsphere.util.DB;import java.sql.*;import java.math.*;
public class CheckoutService {
 public int place(int uid,String line,String city,String state,String pin)throws SQLException {
 if(line.isBlank()||city.isBlank()||state.isBlank()||!pin.matches("[0-9]{6}"))throw new SQLException("Valid address and 6-digit PIN required");
 try(Connection c=DB.connect()) {c.setAutoCommit(false);try {
 int address;try(PreparedStatement s=c.prepareStatement("INSERT INTO addresses(user_id,address_line,city,state,pincode) VALUES(?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS)){s.setInt(1,uid);s.setString(2,line);s.setString(3,city);s.setString(4,state);s.setString(5,pin);s.executeUpdate();try(ResultSet r=s.getGeneratedKeys()){r.next();address=r.getInt(1);}}
 // Lock product rows in deterministic order, then validate stock and compute price within the same transaction.
 record Item(int id,int qty,BigDecimal price){}java.util.List<Item> items=new java.util.ArrayList<>();BigDecimal total=BigDecimal.ZERO;
 try(PreparedStatement s=c.prepareStatement("SELECT c.product_id,c.quantity,p.stock,p.status,ROUND(p.price*(100-p.discount)/100,2) price FROM cart c JOIN products p ON p.product_id=c.product_id WHERE c.user_id=? ORDER BY p.product_id FOR UPDATE")){s.setInt(1,uid);try(ResultSet r=s.executeQuery()){while(r.next()){int qty=r.getInt("quantity");if(!r.getBoolean("status")||qty<1||qty>r.getInt("stock"))throw new SQLException("Some products are out of stock");BigDecimal price=r.getBigDecimal("price");total=total.add(price.multiply(BigDecimal.valueOf(qty)));items.add(new Item(r.getInt("product_id"),qty,price));}}}
 if(items.isEmpty())throw new SQLException("Your cart is empty");int order;
 try(PreparedStatement s=c.prepareStatement("INSERT INTO orders(user_id,address_id,total_amount,payment_method,payment_status,order_status) VALUES(?,?,?,'COD','PENDING','PLACED')",Statement.RETURN_GENERATED_KEYS)){s.setInt(1,uid);s.setInt(2,address);s.setBigDecimal(3,total);s.executeUpdate();try(ResultSet r=s.getGeneratedKeys()){r.next();order=r.getInt(1);}}
 try(PreparedStatement lineItem=c.prepareStatement("INSERT INTO order_items(order_id,product_id,quantity,price) VALUES(?,?,?,?)");PreparedStatement stock=c.prepareStatement("UPDATE products SET stock=stock-? WHERE product_id=? AND stock>=?")){for(Item i:items){lineItem.setInt(1,order);lineItem.setInt(2,i.id());lineItem.setInt(3,i.qty());lineItem.setBigDecimal(4,i.price());lineItem.executeUpdate();stock.setInt(1,i.qty());stock.setInt(2,i.id());stock.setInt(3,i.qty());if(stock.executeUpdate()!=1)throw new SQLException("Stock changed: retry checkout");}}
 try(PreparedStatement s=c.prepareStatement("INSERT INTO payments(order_id,payment_method,amount,payment_status) VALUES(?,'COD',?,'PENDING')")){s.setInt(1,order);s.setBigDecimal(2,total);s.executeUpdate();}
 try(PreparedStatement s=c.prepareStatement("DELETE FROM cart WHERE user_id=?")){s.setInt(1,uid);s.executeUpdate();}c.commit();return order;
 }catch(Exception e){c.rollback();if(e instanceof SQLException)throw (SQLException)e;throw new SQLException(e);}finally{c.setAutoCommit(true);}}
 }
}
