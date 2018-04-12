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
//Esta clase debe Implementar de la clase Runnable para heredar los hilos
public   class Misocket implements Runnable{
    //Atributos para el Socket, esta aplicacion por defecto tendar el puerto en 
    //5000, pero puede cambiarse
    private String DireccionIP;
    private int Puerto;
    private String Datos;
    
     Thread HiloEscucha= new Thread(this);//Hilo para la aplicacion
     
    BufferedReader Entradabuf;//Bufer para que no se pierdan datos a la hora de 
    //recibirlos por la red Wlan
    ServerSocket servidor;//Objeto del servidor
    Socket socketServidor;//Objeto Socket para la aplicacion que trabajara como
    //servidor
    InputStreamReader flujoentrada;//Objeto para leer la entrada de datos
    DataOutputStream flujosalida;//Objeto para enviar datos de respuesta al 
    //cliente ESP8266
    
    //Medtodos GET and SET 
    public String getDatos() {
        return Datos;
    }

    public void setDatos(String Datos) {
        this.Datos = Datos;
    }
   
    
  
  
    

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

    
    //Constructor para inicializar valores , en especial el del puerto hy el hilo
     public Misocket(){
    DireccionIP = "";
    Puerto =5000;
    Datos="";
    HiloEscucha.start();//inicializando el hilo

                       }

     //Metodo run para el hilo
    @Override
    public void run() {
                try {
           // System.out.println("Prueba");
          System.err.println("Esperando datos");
           while (true) {   
             servidor= new ServerSocket(getPuerto());
           // System.out.println("Esperando una conexión:");
             socketServidor= new Socket();
             socketServidor=servidor.accept();
                        
            flujoentrada= new InputStreamReader(socketServidor.getInputStream());
            Entradabuf= new BufferedReader(flujoentrada);
            //System.out.println("Un cliente se ha conectado.");
            Datos= Entradabuf.readLine();
            System.out.println("El mensaje es:"+ Datos);
           // }
            //socketServidor.close();
            servidor.close();
            //System.out.println();
        }
        } catch (IOException ex) {
            Logger.getLogger(Misocket.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
    }

    
    
}
