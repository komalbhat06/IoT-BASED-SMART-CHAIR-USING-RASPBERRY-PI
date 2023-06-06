/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sumit
 */
public class get_sensor_data extends HttpServlet {

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
//            String s1=request.getParameter("s1");
//            String s2=request.getParameter("s2");
//            String s3=request.getParameter("s3");
//            String t=request.getParameter("t");
//            String h=request.getParameter("h");
            String data=request.getParameter("data");
            String d[]=data.split("##");
            System.out.println("s1="+d[2]);
            System.out.println("s2="+d[3]);
            System.out.println("s3="+d[4]);
            System.out.println("t="+d[0]);
            System.out.println("h="+d[1]);
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String tdate = simpleDateFormat.format(new Date());
            System.out.println(tdate);

            String pattern1 = "hh-mm-ss";
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(pattern1);

            String tdate1 = simpleDateFormat1.format(new Date());
            tdate1=tdate1.replace(":", "-");
            System.out.println(tdate1);
            String path = "D:/chair_data.txt";
            String text = d[2]+"#"+d[3]+"#"+d[4]+"#"+d[0]+"#"+d[1]+"#"+tdate+"#"+tdate1+"\n";

            try {
                FileWriter fw = new FileWriter(path, true);
                fw.write(text);
                fw.close();
            }
            catch(IOException e) {
            }
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
