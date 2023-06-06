/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author sumit
 */
public class monitor_sitting_position extends Thread{
    double max=200;
    public static int count=0,c=0;  
    public monitor_sitting_position(){
    start();
    }
    public void run(){
    while (true){
        try{
            File file1 = new File("D:/chair_data.txt"); 
            String data="";
            BufferedReader br = new BufferedReader(new FileReader(file1)); 

            String st; 
            while ((st = br.readLine()) != null) {
            System.out.println(st); 
            data+=st;
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
            if(s1>max)
            {
                System.out.println("Someone sitting on the chair");
                c++;
                if(c>300)
                {
                    System.out.println("Send Alert to get up and walk for sometime");
                    c=c-50;

                }
                double s2=Double.parseDouble(b[1]);
                double s3=Double.parseDouble(b[2]);
                if(s2>max && s3>max)
                {
                    System.out.println("Sitting Properly");
                }
                else if(s3>max && s2<100){
                    System.out.println("Improper Sitting");
                    count=0;
                } 
                else if(s2>max && s3<100){
                    System.out.println("Monitor Sitting Position");
                    count++;
                    if(count>60)
                    {
                        System.out.println("Send Alert");
                    }
                }

            }
            else{
                System.out.println("Nobody sitting on the chair");
                count=0;
                c=0;
            }
            }
            Thread.sleep(1000);
        }catch(Exception e)
        {
        e.printStackTrace();
        }
    
    }
        
        
        
    }
    
}
