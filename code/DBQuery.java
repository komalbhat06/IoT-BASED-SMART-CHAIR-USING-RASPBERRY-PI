
package Database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBQuery {
    
      public int signup(String phone,String name,String email,String password)throws ClassNotFoundException,SQLException{
       
        String qry ="insert into user_details values('" + phone + "','" + name + "','" + email + "','"+password+"'  )";
        System.out.println(qry);
        Connection con =DBConnection.getcon();
        Statement stat = con.createStatement();
        
        int i=stat.executeUpdate(qry);
        
        return i;
        
        
    }
       public String  login_check(String mob,String pass)throws ClassNotFoundException,SQLException
       {
        String name="";
        String qry3 ="select * from user_details where mobile='"+mob+"' and password='"+pass+"'";
        System.out.println(qry3);
         
        
         Connection con =DBConnection.getcon();
        Statement stat = con.createStatement();
     
   
          ResultSet rs=stat.executeQuery(qry3);
         if(rs.next())
         {
            
           
          name=rs.getString("name");
         
           
       
             }
  return name;   
  }
       
        public int add_param(String phone,String t,String h,String s1,String s2,String s3)throws ClassNotFoundException,SQLException{
       String q1="select * from param_details where user='"+phone+"'";
        int i=0;
        String qry ="insert into param_details values('" + phone + "','" + t + "','" + h + "','"+s1+"' ,'"+s2+"' ,'"+s3+"'  )";
        String qry1 ="update  param_details set t='" + t + "',h='" + h + "',s1='"+s1+"' ,s2='"+s2+"' ,s3='"+s3+"' where user='"+phone+"'";
        
        System.out.println(qry);
        Connection con =DBConnection.getcon();
        Statement stat = con.createStatement();
        ResultSet rs=stat.executeQuery(q1);
        if(rs.next())
        {
        i=1;
        }
        if(i==0)
        {
        i=stat.executeUpdate(qry);
        }
        else{
        i=stat.executeUpdate(qry1);
        }
        return i;
        
        
    }
        
         public String  load_params(String mob)throws ClassNotFoundException,SQLException
       {
        String t="",h="",s1="",s2="",s3="";
        String qry3 ="select * from param_details where user='"+mob+"' ";
        System.out.println(qry3);
         
        
         Connection con =DBConnection.getcon();
        Statement stat = con.createStatement();
     
   
          ResultSet rs=stat.executeQuery(qry3);
         if(rs.next())
         {
            
           
          t=rs.getString("t");
          h=rs.getString("h");
          s1=rs.getString("s1");
          s2=rs.getString("s2");
          s3=rs.getString("s3");
         
           
       
         }
        return t+"#"+h+"#"+s1+"#"+s2+"#"+s3;   
        }
         
      
}
