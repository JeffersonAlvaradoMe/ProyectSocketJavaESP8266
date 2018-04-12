/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosocketjavaesp8266;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;

/**
 *
 * @author Cmaster
 * 
 * 
 */
public   class Misocket implements Runnable{
    private String DireccionIP;
    private int Puerto;
    
    BufferedReader Entradabuf;
    
    
    public String getDatos() {
        return Datos;
    }

    public void setDatos(String Datos) {
        this.Datos = Datos;
    }
    private String Datos;
    
    Thread HiloEscucha= new Thread(this);
  
    

    public String getDireccionIP() {
        return DireccionIP;
    }

    public void setDireccionIP(String DireccionIP) {
        this.DireccionIP = DireccionIP;
    }

    public int getPuerto() {
        return Puerto;
    }

    public void setPuerto(int Puerto) {
        this.Puerto = Puerto;
    }

    
    
     public Misocket(){
DireccionIP = "";
Puerto =5001;
Datos="";
  HiloEscucha.start();
}

    @Override
    public void run() {
        
        try {
            System.out.println("Prueba");
          //  System.err.println(getPuerto());
            ServerSocket servidor= new ServerSocket(getPuerto());
            System.out.println("Esperando una conexión:");
            Socket socketServidor= servidor.accept();
           // while (true) {                
            InputStreamReader flujoentrada= new InputStreamReader(socketServidor.getInputStream());
            Entradabuf= new BufferedReader(flujoentrada);
            System.out.println("Un cliente se ha conectado.");
            Datos= Entradabuf.readLine();
            System.out.println("El mensaje es:"+ Datos);
           // }
            socketServidor.close();
            servidor.close();
            //System.out.println();
        } catch (IOException ex) {
            Logger.getLogger(Misocket.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
    }

    
    
}
