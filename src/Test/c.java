package Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class c {
   public static void main(String[] args){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/xsl", "root", "xsl203457");
           PreparedStatement preparedStatement = con.prepareStatement("create table ? (id int ,name varchar(20),position varchar(25),salary double)") ;
           preparedStatement.setString(1,"bbb");
           //preparedStatement.executeUpdate();
           preparedStatement.execute();
       }catch(Exception ex){
           ex.getStackTrace();
       }

   }
}
