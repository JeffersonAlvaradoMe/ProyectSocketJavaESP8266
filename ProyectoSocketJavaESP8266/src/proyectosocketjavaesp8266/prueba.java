/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosocketjavaesp8266;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import proyectosocketjavaesp8266.Misocket;

/**
 *
 * @author Cmaster
 */
public class prueba extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form prueba
     */
    File fichero = new File("C:/Users/Municipio de Gye/Desktop/holi.txt");
    
    PrintWriter escribir = null;
    FileWriter newline = null;
    
    public void inicio(){
        fichero.delete();
        if(!fichero.exists()){
            try{
                fichero.createNewFile();
                //escribir = new PrintWriter(fichero);
                //escribir.print(".");
                //escribir.close();
            } catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    
    
                
    XYSeries series = new XYSeries("Sensor Electrocardiograma");
		XYSeriesCollection dataset = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory.createXYLineChart("Sensor", "Tiempo (Segundos)", "Lectura", dataset, PlotOrientation.VERTICAL,true,true,false);
                Thread Hilo = new Thread(this);
                 int x=0;
                 //hola
                 
    public prueba() {
        initComponents();
        inicio();
        
                pane.removeAll();
		pane.add(new ChartPanel(chart), BorderLayout.CENTER);
                pane.validate();
                Hilo.start();
                
                BotonParar.setVisible(true);
                botonComenzar.setVisible(true);
                
                
         
            
                //
    }
    /*
    
    wdewdwe
    */
    

    //public
       int i=0;
       int ss=0;
    public void datos(){
        double numero;
        i++;
        numero =  (Math.random()*1024)+1;
        series.add(i,1023-numero);
       /* if(i>10){
            series.clear();
            i=0;
            System.out.println("Llgue al 10");
        }*/
    } //No sirvehujj
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pane = new javax.swing.JPanel();
        botonComenzar = new javax.swing.JButton();
        BotonParar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 255, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pane.setBackground(new java.awt.Color(204, 255, 255));
        pane.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 11, 770, 575));

        botonComenzar.setText("Comenzar");
        botonComenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonComenzarActionPerformed(evt);
            }
        });
        getContentPane().add(botonComenzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 604, 99, -1));

        BotonParar.setText("Parar");
        BotonParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonPararActionPerformed(evt);
            }
        });
        getContentPane().add(BotonParar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 604, 103, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectosocketjavaesp8266/azul.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonComenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonComenzarActionPerformed
        // TODO add your handling code here:
        
    //datos();
    
        
      
        
    }//GEN-LAST:event_botonComenzarActionPerformed

    private void BotonPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonPararActionPerformed
        // TODO add your handling code here:
        this.hide();
        //series.clear();
        
        ///rr
        ///rrr
        
        
    }//GEN-LAST:event_BotonPararActionPerformed

    
    
    public void datasetActionPerformed(java.awt.event.ActionEvent e) {
      
      //if(e.getClickCount()==2){
         BotonParar.setVisible(true);
         //botonComenzar.setVisible(true);
       //}
    }

    
    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonParar;
    private javax.swing.JButton botonComenzar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pane;
    // End of variables declaration//GEN-END:variables
//Lights On
    @Override
    public void run() {
        
           try{
                //System.out.println("Estoy a la escucha");
                //ServerSocket misocket = new ServerSocket(5000);
                DatagramSocket servidor = new DatagramSocket(5000);
                byte [] buffer = new byte[1024];
                //String mensaje="";
               //Hilo
               int a1 =0;
                while(true){
                    DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                    System.out.println("Esperando conexion");
                    servidor.receive(peticion);
                //Socket misocket = servidor.accept();
                    System.out.println("Conexion aceptada");
                    System.out.println(peticion);
                //misocket.getLocalPort();
                //DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());
                //mensaje = flujo_entrada.readLine();
                //mensaje = flujo_entrada.readUTF();
               //int a = Integer.parseInt(mensaje);
                    String sms = new String(peticion.getData(),0, peticion.getLength());
                    int a = Integer.parseInt(sms);
                System.out.println(a);
                i=i+1;
                series.add(i,a);
               /* if(i>10){
                    series.clear(); 
                    i=0;
                    System.out.println("LLEGUE HASTA EL 10");
                }*/
                
                newline = new FileWriter(fichero,true);
                escribir = new PrintWriter(newline);
                escribir.println(a);
                escribir.close();
                newline.close();
                //misocket.close();
                }
                 
            } catch (IOException ex) {
                //Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } 
        
    }

}

//hola
//hi
//
//
//hola
