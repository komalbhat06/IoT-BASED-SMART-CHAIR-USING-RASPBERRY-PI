/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import Database.DBQuery;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * pulse and DHT
 */
public class Listener {
     public static void main(String[] args) throws IOException {
    	
        ServerSocket listener = new ServerSocket(8000);
        try{
            while(true){
                Socket socket = listener.accept();
                socket.setKeepAlive(true);
                
                
                System.out.println("Client Connected");
              
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String data=in.readLine();
                    if(!data.equals(""))
                    {
                     //  String a[]=data.split("#");
                    System.out.println("Client response: " + data);
                    
                  
                     System.out.println("Client response: " + data);
                   String path="D:/iot_pulse_temp_humi.txt"; 
                     
                    String text = data+"\n";

                    try {
                        FileWriter fw = new FileWriter(path, true);
                        fw.write(text);
                        fw.close();
                    }
                    catch(IOException e) {
                    }
                    
                    
                    }

            }
        }catch (Exception ex) {
                        Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                finally {
            
          }
     }
}
