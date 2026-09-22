import java.sql.*;
public class model {

    public void storeData(String name, String roll_number , String branch , String gender) throws SQLException  {
      

        try {
        // Explicitly load the driver
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3307/collage", "root", "root");
        System.out.println(con);
        System.out.println("connection created");

        PreparedStatement ps =con.prepareStatement( "INSERT INTO student(name, roll_number, branch, gender) VALUES(?,?,?,?)" );//id autoincrement , name , rollnuber, branch , gender
        ps.setString(1, name);
        ps.setString(2, roll_number);
        ps.setString(3, branch);
        ps.setString(4, gender);
        ps.executeUpdate();

        System.out.println("Record Inserted");
        // Statement st =con.createStatement();
        // ResultSet rs =st.executeQuery( "select * from student" );
        // while(rs.next())
        // {
        //     System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4)+" "+rs.getString(5));
        // }
       con.close();
    }
    

}
