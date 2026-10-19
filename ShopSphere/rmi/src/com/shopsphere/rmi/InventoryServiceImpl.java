package com.shopsphere.rmi;
import java.rmi.*;import java.rmi.server.*;import java.sql.*;
public class InventoryServiceImpl extends UnicastRemoteObject implements InventoryService {
 public InventoryServiceImpl()throws RemoteException{super();}
 private Connection connect()throws SQLException{return DriverManager.getConnection(System.getenv().getOrDefault("SHOPSPHERE_DB_URL","jdbc:mysql://localhost:3306/shopsphere"),System.getenv().getOrDefault("SHOPSPHERE_DB_USER","root"),System.getenv().getOrDefault("SHOPSPHERE_DB_PASSWORD",""));}
 public int getStock(int id)throws RemoteException {try(Connection c=connect();PreparedStatement q=c.prepareStatement("SELECT stock FROM products WHERE product_id=?")){q.setInt(1,id);try(ResultSet r=q.executeQuery()){return r.next()?r.getInt(1):-1;}}catch(SQLException e){throw new RemoteException("Database error",e);}}
 public boolean updateStock(int id,int qty)throws RemoteException {if(qty<0)throw new RemoteException("Negative stock");try(Connection c=connect();PreparedStatement q=c.prepareStatement("UPDATE products SET stock=? WHERE product_id=?")){q.setInt(1,qty);q.setInt(2,id);return q.executeUpdate()>0;}catch(SQLException e){throw new RemoteException("Database error",e);}}
}
