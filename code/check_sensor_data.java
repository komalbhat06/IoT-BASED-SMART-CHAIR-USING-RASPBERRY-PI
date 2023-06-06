/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Database.DBQuery;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sumit
 */
public class check_sensor_data extends HttpServlet {
double s1_max=7229;
double s2_max=7746;
double s3_max=6991;
public static int count=0,c=0;
String msg="",data="";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String user=request.getParameter("user");
           
            DBQuery db=new DBQuery();
            String params="";
            try {
                params=db.load_params(user);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(check_sensor_data.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(check_sensor_data.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("params="+params);
            File file1 = new File("D:/chair_data.txt"); 
            String data="";
            BufferedReader br = new BufferedReader(new FileReader(file1)); 

            String st; 
            while ((st = br.readLine()) != null) {
            System.out.println(st); 
            data=st;
            }
            String a[]=data.split("\n");
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String tdate = simpleDateFormat.format(new Date());
            System.out.println(tdate);

            String pattern1 = "hh-mm-ss";
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);

            String tdate1 = simpleDateFormat1.format(new Date());
            tdate1=tdate1.replace(":", "-");
            for (int i=0;i<a.length;i++)
            {
            String b[]=a[i].split("#");
            double s1=Double.parseDouble(b[0]);
            double s2=Double.parseDouble(b[1]);
            double s3=Double.parseDouble(b[2]);
                System.out.println("s1="+s1);
                System.out.println("s2="+s2);
                System.out.println("s3="+s3);
                data=b[3]+"#"+b[4]+"#"+s1+"#"+s2+"#"+s3;
            if(s3>s3_max)
            {
                System.out.println("Someone sitting on the chair");
                msg="Someone sitting on the chair";
                c++;
                if(c>5)
                {
                    System.out.println("Send Alert to get up and walk for sometime");
                    msg="Send Alert to get up and walk for sometime";
                    c=c-50;

                }
                
                if(s1>s1_max && s2>s2_max && s3>s3_max)
                {
                    System.out.println("Sitting Properly");
                    msg="Sitting Properly";
                     count=0;
                }
                else if(s1<s1_max && s2>s2_max && s3>s3_max){
                    System.out.println("Improper Sitting");
                    msg="Improper Sitting";
              
                    count++;
                    if(count>20)
                    {
                        System.out.println("Send Alert");
                        msg="Alert-Improper Sitting";
                    }
                }

            }
            else{
                System.out.println("Nobody sitting on the chair");
                msg="Nobody sitting on the chair";
                count=0;
                c=0;
            }
            double t=Double.parseDouble(b[3]);
            double h=Double.parseDouble(b[4]);
            if(t>35)
            {
                System.out.println("Temerature Rising");
                msg="Temerature Rising";
            }
            if(h>70)
            {
                System.out.println("Humidity Rising");
                msg="Humidity Rising";
            }
            }
            System.out.println("sending "+msg+"@@"+data);
            out.print(msg+"@@"+data);   
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
