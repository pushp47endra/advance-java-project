package com.shopsphere.controller;
import jakarta.servlet.*;import jakarta.servlet.annotation.WebServlet;import jakarta.servlet.http.*;import java.io.*;import java.sql.*;import java.util.*;import com.shopsphere.dao.*;import com.shopsphere.model.*;import com.shopsphere.util.DB;
@WebServlet(urlPatterns={"/products","/product","/login","/register","/logout","/cart","/checkout","/orders","/wishlist","/review"})
public class ShopServlet extends HttpServlet {
 private final UserDAO users=new UserDAO();private final ProductDAO products=new ProductDAO();private final CartDAO carts=new CartDAO();
 private String path(HttpServletRequest r){return r.getServletPath();}
 private void view(HttpServletRequest r,HttpServletResponse s,String page)throws ServletException,IOException{r.getRequestDispatcher("/WEB-INF/views/"+page+".jsp").forward(r,s);}
 private int number(String s,int def){try{return Integer.parseInt(s);}catch(Exception e){return def;}}
 private User user(HttpServletRequest r){HttpSession s=r.getSession(false);return s==null?null:(User)s.getAttribute("user");}
 private void err(HttpServletRequest r,HttpServletResponse s,String message,String view)throws ServletException,IOException{r.setAttribute("error",message);view(r,s,view);}
 @Override protected void doGet(HttpServletRequest r,HttpServletResponse s)throws ServletException,IOException {try {
 switch(path(r)){
 case "/login" -> view(r,s,"login");case "/register"->view(r,s,"register");
 case "/logout" -> {HttpSession session=r.getSession(false);if(session!=null)session.invalidate();s.sendRedirect(r.getContextPath()+"/products");}
 case "/product" -> {r.setAttribute("product",products.find(number(r.getParameter("id"),-1)));view(r,s,"product");}
 case "/cart" -> {r.setAttribute("items",carts.items(user(r).getUserId()));view(r,s,"cart");}
 case "/checkout" -> {r.setAttribute("items",carts.items(user(r).getUserId()));view(r,s,"checkout");}
 case "/orders" -> {List<Map<String,Object>> out=new ArrayList<>();try(Connection c=DB.connect();PreparedStatement q=c.prepareStatement("SELECT order_id,total_amount,order_status,payment_status,created_at FROM orders WHERE user_id=? ORDER BY order_id DESC")){q.setInt(1,user(r).getUserId());try(ResultSet rs=q.executeQuery()){while(rs.next()){Map<String,Object> m=new HashMap<>();for(String col:List.of("order_id","total_amount","order_status","payment_status","created_at"))m.put(col,rs.getObject(col));out.add(m);}}}r.setAttribute("orders",out);view(r,s,"orders");}
 case "/wishlist"->{List<Product> out=new ArrayList<>();try(Connection c=DB.connect();PreparedStatement q=c.prepareStatement("SELECT p.* FROM wishlist w JOIN products p ON p.product_id=w.product_id WHERE w.user_id=?")){q.setInt(1,user(r).getUserId());try(ResultSet rs=q.executeQuery()){while(rs.next())out.add(ProductDAO.map(rs));}}r.setAttribute("products",out);view(r,s,"wishlist");}
 default -> {r.setAttribute("products",products.list(r.getParameter("q")));view(r,s,"products");}
 }
 }catch(SQLException e){getServletContext().log("Database operation failed",e);s.sendError(500,"Database error. Check MySQL settings.");}}
 @Override protected void doPost(HttpServletRequest r,HttpServletResponse s)throws ServletException,IOException {r.setCharacterEncoding("UTF-8");String base=r.getContextPath();try{
 switch(path(r)){
 case "/register" -> {String name=r.getParameter("name"),email=r.getParameter("email"),password=r.getParameter("password");if(name==null||name.isBlank()||email==null||!email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")||password==null||password.length()<8){err(r,s,"Enter a name, valid email and password of 8+ characters","register");return;}users.register(name.trim(),email.trim(),password);s.sendRedirect(base+"/login?registered=1");}
 case "/login" -> {User u=users.login(r.getParameter("email"),r.getParameter("password"));if(u==null){err(r,s,"Incorrect email or password","login");return;}HttpSession old=r.getSession(false);if(old!=null)old.invalidate();HttpSession session=r.getSession(true);session.setAttribute("user",u);session.setMaxInactiveInterval(1800);s.sendRedirect(base+("ADMIN".equals(u.getRole())?"/admin":"/products"));}
 case "/cart" -> {int pid=number(r.getParameter("productId"),-1),qty=number(r.getParameter("quantity"),1);if(pid<1||qty<0||qty>99)throw new SQLException("Invalid quantity");carts.change(user(r).getUserId(),pid,qty);s.sendRedirect(base+"/cart");}
 case "/checkout" -> {int order=new com.shopsphere.service.CheckoutService().place(user(r).getUserId(),r.getParameter("line"),r.getParameter("city"),r.getParameter("state"),r.getParameter("pin"));s.sendRedirect(base+"/orders?placed="+order);}
 case "/wishlist" -> {try(Connection c=DB.connect();PreparedStatement ps=c.prepareStatement("INSERT IGNORE INTO wishlist(user_id,product_id) SELECT ?,product_id FROM products WHERE product_id=? AND status=TRUE")){ps.setInt(1,user(r).getUserId());ps.setInt(2,number(r.getParameter("productId"),-1));ps.executeUpdate();}s.sendRedirect(base+"/wishlist");}
 case "/review" -> {int rating=number(r.getParameter("rating"),0),pid=number(r.getParameter("productId"),-1);if(rating<1||rating>5||pid<1)throw new SQLException("Invalid rating");try(Connection c=DB.connect();PreparedStatement ps=c.prepareStatement("INSERT INTO reviews(user_id,product_id,rating,review_text) VALUES(?,?,?,?)")){ps.setInt(1,user(r).getUserId());ps.setInt(2,pid);ps.setInt(3,rating);ps.setString(4,r.getParameter("text"));ps.executeUpdate();}s.sendRedirect(base+"/product?id="+pid);}
 default->s.sendError(405);
 }
 }catch(SQLException|IllegalArgumentException e){getServletContext().log("Action failed",e);r.setAttribute("error",e.getMessage());String pg=path(r).equals("/checkout")?"checkout":path(r).equals("/register")?"register":path(r).equals("/login")?"login":"cart";if(pg.equals("cart")||pg.equals("checkout")){try{r.setAttribute("items",carts.items(user(r).getUserId()));}catch(Exception ignored){}}view(r,s,pg);}}
}
