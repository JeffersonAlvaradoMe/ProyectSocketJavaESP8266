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
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
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
    
    basedata bd = new basedata(); 
    Connection cc = bd.conectar();
    int l=0;
    Boolean iniciar=false;
    Boolean Finalizar= false;
    
    /**
     * Creates new form prueba
     */
    File fichero = new File("E:\\N6.2\\DisenoRedes\\Feria\\datoslectura.txt");
    
    PrintWriter escribir = null;
    FileWriter newline = null;
    
    public void inicio(){
        //fichero.delete();
        
        
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
    
    public void consulta(String cedula){
        String sql = "SELECT nombres FROM pacientes WHERE cedula='"+cedula+"' ";
        String nom="";
        try {
            Statement st = cc.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                nom = rs.getString("nombres");
            }
            System.out.println(nom);
            jLabelnombres.setText("BIENVENIDO: "+nom);
           
        } catch (SQLException ex) {
            System.out.println("malo");
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
        jLabelhide.setVisible(false);
        inicio();
        
                pane.removeAll();
		pane.add(new ChartPanel(chart), BorderLayout.CENTER);
                pane.validate();
                //Hilo.start();
                
                BotonParar.setVisible(true);
                botonComenzar.setVisible(true);
                
                
         
            
                //
    }
    /*
    
    wdewdwe
    */
   
public void mostrar(){
     String c = jTextFieldbuscar.getText();
     int cod=0;
    String sql = "SELECT cod_paciente FROM pacientes WHERE nombres='"+c+"' ";
    try {
            Statement st = cc.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                cod = rs.getInt("cod_paciente");
            }
            
        } catch (SQLException ex) {
            System.out.println("malo");
        }
    int x=0, y=0, pri=0, codi=0; 
    String sql1 = "SELECT * FROM datos_corazon WHERE cod_paciente='"+cod+"' ";
    DefaultTableModel modelo = new DefaultTableModel();
     try {
            Statement st2 = cc.createStatement();
            ResultSet rs2 = st2.executeQuery(sql1);
            modelo.setColumnIdentifiers(new Object[]{"Código","Datos X","Datos Y","Código Paciente"});
            while(rs2.next()){
                pri = rs2.getInt("cod_corazon");
                x = rs2.getInt("datos_x");
                y = rs2.getInt("datos_y");
                codi = rs2.getInt("cod_paciente");
                modelo.addRow(new Object[]{pri,x,y,codi});
                tabla.setModel(modelo);
            }
            
        } catch (SQLException ex) {
            System.out.println("malo");
        }
}
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
    
    //richi
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        pane = new javax.swing.JPanel();
        jLabelhide = new javax.swing.JLabel();
        BotonParar = new javax.swing.JButton();
        botonComenzar = new javax.swing.JButton();
        jLabelnombres = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabelbuscar = new javax.swing.JLabel();
        jTextFieldbuscar = new javax.swing.JTextField();
        jButtonaceptar = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 255, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pane.setBackground(new java.awt.Color(204, 255, 255));
        pane.setLayout(new java.awt.BorderLayout());
        jPanel1.add(pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 96, 770, 490));

        jLabelhide.setText("jLabel2");
        jPanel1.add(jLabelhide, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, -1, -1));

        BotonParar.setText("Parar");
        BotonParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonPararActionPerformed(evt);
            }
        });
        jPanel1.add(BotonParar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 604, 103, -1));

        botonComenzar.setText("Comenzar");
        botonComenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonComenzarActionPerformed(evt);
            }
        });
        jPanel1.add(botonComenzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 604, 99, -1));

        jLabelnombres.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel1.add(jLabelnombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 350, 40));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 600, -1, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/proyectosocketjavaesp8266/azul.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 650));

        jTabbedPane1.addTab("Recepción de datos", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, -1, -1));

        jLabelbuscar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabelbuscar.setText("Buscar:");
        jPanel2.add(jLabelbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 70, 30));
        jPanel2.add(jTextFieldbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 190, 30));

        jButtonaceptar.setText("BUSCAR");
        jButtonaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonaceptarActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonaceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 90, 30));

        jTabbedPane1.addTab("Datos obtenidos", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonComenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonComenzarActionPerformed
        // TODO add your handling code here:
        
    //datos();
    String campo = "";
    campo =jLabelhide.getText();
    consulta(campo);
    
    //Al disparar el el evento del boton comenzar, se comienza la lecrua de datos desde el socket...
    Hilo.start();
   // botonComenzar.disable();
        
      
        
    }//GEN-LAST:event_botonComenzarActionPerformed

    private void BotonPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonPararActionPerformed
        
        //como evento para q en el metodo q se ejecuta en el RUN, salga del ciclo while
        Finalizar=true;
        
        //series.clear();
        
        ///rr
        ///rrr
        
        
    }//GEN-LAST:event_BotonPararActionPerformed

    private void jButtonaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaceptarActionPerformed
        // TODO add your handling code here:
        mostrar();
    }//GEN-LAST:event_jButtonaceptarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        long tiempoinicio=0;
        long total,finali;
        if(l==0){
        tiempoinicio=System.currentTimeMillis();
        l++;
        }
        datos(); 
        finali=System.currentTimeMillis();
       total=finali-tiempoinicio;
       System.out.println(total/1000);

// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    
    
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonaceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelbuscar;
    public javax.swing.JLabel jLabelhide;
    private javax.swing.JLabel jLabelnombres;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldbuscar;
    private javax.swing.JPanel pane;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
//Lights On
    @Override
    public void run() {
        //llamamos al metodo q se quiera, hacen lo mismo, solo q en por ahora tcp usaremos para no perder datos...
        tcp();

        
    }
    
    public void udp(){
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
       
                
        //GUARDAR EN BASE DE DATOS
                int ced=0;
                 String cedu = jLabelhide.getText();
        String mysql2="INSERT INTO datos_corazon (datos_x, datos_y, cod_paciente)"+ "values (?,?,?)";
        String my = "SELECT cod_paciente FROM pacientes WHERE cedula = '"+cedu+"'";
        try {
            Statement st2 = cc.createStatement();
            ResultSet registro = st2.executeQuery(my);
            
            if (registro.next()==true) {
  	        ced = registro.getInt("cod_paciente");
  			  	
  	    }else {
  		setTitle("No hay");
  	    }
            PreparedStatement insertar2 = cc.prepareStatement(mysql2);
            
           insertar2.setInt(1,i);
           insertar2.setInt(2,a);
           insertar2.setInt(3,ced);
           insertar2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
               /* if(i>10){
                    series.clear(); 
                    i=0;
                    System.out.println("LLEGUE HASTA EL 10");
                }*/
                
                newline = new FileWriter(fichero,true);
                escribir = new PrintWriter(newline);
                escribir.println(i+"\t"+a);
                escribir.close();
                newline.close();
                //misocket.close();
                }
                 
            } catch (IOException ex) {
                //Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } 
    
    }

    public void tcp(){
    try{
                System.out.println("Estoy a la escucha");
                ServerSocket servidor = new ServerSocket(5000);
                
                //DatagramSocket servidor = new DatagramSocket(5000);
                //byte [] buffer = new byte[1024];
                String mensaje="";
               //Hilo
              // int a1 =0;
               
               
               
                while(true && Finalizar==false){
                Socket misocket = servidor.accept();    
               DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());
               
                PrintStream salida;
                salida = new PrintStream(misocket.getOutputStream());
                    //DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                    System.out.println("Esperando conexion");
                    //servidor.receive(peticion);
                    
                    System.out.println("Conexion aceptada");
                    //System.out.println(peticion);
                //misocket.getLocalPort();
                 mensaje = flujo_entrada.readLine();
                
                
                
                //mensaje = flujo_entrada.readUTF();
               int a = Integer.parseInt(mensaje);
                    //String sms = new String(peticion.getData(),0, peticion.getLength());
                    //int a = Integer.parseInt(sms);
                    
                    //salida.println("ok"  );
                System.out.println(a);
                i=i+1;
                series.add(i,a);
                
                 int ced=0;
                 String cedu = jLabelhide.getText();
        String mysql2="INSERT INTO datos_corazon (datos_x, datos_y, cod_paciente)"+ "values (?,?,?)";
        String my = "SELECT cod_paciente FROM pacientes WHERE cedula = '"+cedu+"'";
        try {
            Statement st2 = cc.createStatement();
            ResultSet registro = st2.executeQuery(my);
            
            if (registro.next()==true) {
  	        ced = registro.getInt("cod_paciente");
  			  	
  	    }else {
  		setTitle("No hay");
  	    }
            PreparedStatement insertar2 = cc.prepareStatement(mysql2);
            
           insertar2.setInt(1,i);
           insertar2.setInt(2,a);
           insertar2.setInt(3,ced);
           insertar2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
               /* if(i>10){
                    series.clear(); 
                    i=0;
                    System.out.println("LLEGUE HASTA EL 10");
                }*/
                
                newline = new FileWriter(fichero,true);
                escribir = new PrintWriter(newline);
                escribir.println(i+"\t"+a);
                escribir.close();
                newline.close();
                misocket.close();
                }
                 
                System.out.println("salio del socket");
            } catch (IOException ex) {
                //Logger.getLogger(MarcoServidor.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            } 
}
}


//Falta poner imagnes bonitas