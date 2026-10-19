package com.shopsphere.dao;
import com.shopsphere.model.User;import com.shopsphere.util.DB;import java.sql.*;import org.mindrot.jbcrypt.BCrypt;
public class UserDAO {
 public void register(String name,String email,String password)throws SQLException {try(Connection c=DB.connect();PreparedStatement s=c.prepareStatement("INSERT INTO users(name,email,password,role) VALUES(?,?,?,'CUSTOMER')")){s.setString(1,name);s.setString(2,email.toLowerCase());s.setString(3,BCrypt.hashpw(password,BCrypt.gensalt(12)));s.executeUpdate();}}
 public User login(String email,String password)throws SQLException {try(Connection c=DB.connect();PreparedStatement s=c.prepareStatement("SELECT user_id,name,email,password,role FROM users WHERE email=? AND status=TRUE")){s.setString(1,email.toLowerCase());try(ResultSet r=s.executeQuery()){if(r.next()&&BCrypt.checkpw(password,r.getString("password")))return new User(r.getInt("user_id"),r.getString("name"),r.getString("email"),r.getString("role"));}}return null;}
}
