/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author sumit
 */
public class analyze extends HttpServlet {
double s1_max=7500;
double s2_max=7900;
double s3_max=7320;
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
            System.out.println("user="+user);
             File file1 = new File("D:/chair_data.txt"); 
            String data="";
            BufferedReader br = new BufferedReader(new FileReader(file1)); 

            String st; 
            while ((st = br.readLine()) != null) {
            System.out.println(st); 
            data+=st;
            }
            String a[]=data.split("\n");
            int corerct=0,incorrect=0;
            for (int i=0;i<a.length;i++)
            {
            String b[]=a[i].split("#");
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

            String tdate = simpleDateFormat.format(new Date());
                System.out.println("Checking");
            System.out.println(tdate);
            System.out.println(b[5]);
       //     tdate="29-04-2023";
            if(tdate.equals(b[5]))
            {
            double s1=Double.parseDouble(b[0]);
            double s2=Double.parseDouble(b[1]);
            double s3=Double.parseDouble(b[2]);
                System.out.println("s1="+s1);
                System.out.println("s2="+s2);
                System.out.println("s3="+s3);
                
                if(s1>=s1_max && s2>s2_max && s3>s3_max)
                {
                
                corerct++;
                }
                else{
                incorrect++;
                }
                
                
                
            }
            }
            System.out.println("Correct "+corerct);
            System.out.println("Incorrect "+incorrect);
            
    //        corerct=36;
     //       incorrect=12;
            final DefaultPieDataset data1 = new DefaultPieDataset();
// corerct=20;
//            incorrect=17;
            
          
                data1.setValue("Correct", new Double(corerct));
                data1.setValue("Incorrect", new Double(incorrect));
         

            JFreeChart chart = ChartFactory.createPieChart("Pie Chart ", data1, true, true, false);

            try {
                final ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
                final File file2 = new File(Logic.info.path+"piechart.png");
                
                ChartUtilities.saveChartAsPNG(file2, chart, 600, 400, info);

                final File file3 = new File(Logic.info.path1+"piechart.png");
                ChartUtilities.saveChartAsPNG(file3, chart, 600, 400, info);
            } catch (Exception e) {
                out.println(e);
            }
            System.out.println(">>>>>>>>>>piechart.png");
            
            
            
           
            out.print(corerct+"##"+incorrect);
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
