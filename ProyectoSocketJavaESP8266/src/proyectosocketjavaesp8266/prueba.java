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
import java.io.DataOutputStream;
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
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
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
    
    int[] ValoresRecibidos= new int[4];
    int ChipId, Temperatura, Humedad, NivelAgua;
    

    File fichero = new File("E:\\N6.2\\DisenoRedes\\Feria\\datoslectura.txt");
    
    PrintWriter escribir = null;
    FileWriter newline = null;
    
    public void inicio(){
        //navegador  doonde esta jlabelhide?
        //fichero.delete();
        // prende el cell
       this.tempe.setText("Bienvenido ");
     
   
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
    //27,28.100
    public void prender(int tempe,int humed,int nivela){
        System.out.println(tempe);
        System.out.println(humed);
        System.out.println(nivela);
        if(tempe>=32){
        envio("a"); 
        }else if(tempe<32){
            System.out.println("entre para b");
        envio("b");
        }
            
            
            
        if(humed>50){
        envio("c");
        }else if(humed<67){
        envio("d");
        }
            
        if(nivela>450){
        envio("e");
        }else if(nivela<450){
        envio("f");
        }
        
        
    
    }
    
    public void envio(String l){
        try {
            Socket misocket = new Socket("192.168.10.120",5001);
                //DatagramSocket misocket = new DatagramSocket();
                String a="";
                a=Integer.toString(misocket.getLocalPort());
                char b;
                b= l.charAt(0);
                
               System.out.println("\nServidor a aceptado petición");
                DataOutputStream flujo_salida = new DataOutputStream(misocket.getOutputStream());
              //  flujo_salida.writeUTF(b);
               // flujo_salida.writeChar(b);
                flujo_salida.writeBytes(l);
                //flujo_salida.writeUTF(nick2.getText());
                //flujo_salida.writeUTF(nick3.getText());
                flujo_salida.close();
        } catch (Exception e) {
        }
    }
    
      public void nivel(String temp)
    {
        ImageIcon image = null;
        int x=0;
        String estado = null;
        x=Integer.parseInt(temp);
       if(x>450){
      image= new ImageIcon(getClass().getResource("/imagenes/lleno.png"));
       estado="lleno";}
       else if(x>=20 || x<=200){
     image = new ImageIcon(getClass().getResource("/imagenes/medio.png"));
       estado="medio";}
       else if(x<20){
       image= new ImageIcon(getClass().getResource("/imagenes/vacio.png"));
               estado="vacio";
       }
    this.nivel.setIcon(image);
    this.nivel.setText(estado+":"+x);
    //ImageIcon icono = image.getImage (); 
   
    }
     public void humedad (String temp)
    {
        ImageIcon image = null;
        int x=0;
        x=Integer.parseInt(temp);
       if(x>=30 || x<=50){
      image= new ImageIcon(getClass().getResource("/imagenes/op.png"));}
       else if(x>50){
     image = new ImageIcon(getClass().getResource("/imagenes/on.png"));}
       else if(x<30){
       image= new ImageIcon(getClass().getResource("/imagenes/off.png"));
       }
    this.hume.setIcon(image);
    this.hume.setText(x+"%");
    //ImageIcon icono = image.getImage (); 
   
    }
    public void cambio (String temp)
    {
        ImageIcon image;
        int x=0;
        x=Integer.parseInt(temp);
       if(x>=32){
      image= new ImageIcon(getClass().getResource("/imagenes/calor.png"));}
       else{
     image = new ImageIcon(getClass().getResource("/imagenes/frio.png"));}
    this.tempe.setIcon(image);
    this.tempe.setText(x+"°C");
    //ImageIcon icono = image.getImage (); 
   
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
            tempe.setText("BIENVENIDO: "+nom);
           
        } catch (SQLException ex) {
            System.out.println("malo");
        }
    }
    
    
                
    XYSeries series = new XYSeries("Sensor");
		XYSeriesCollection dataset = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory.createXYLineChart("SENTITY", "", "DATOS", dataset, PlotOrientation.VERTICAL,true,true,false);
                Thread Hilo = new Thread(this);
                 int x=0;
                 //hola
                 
    public prueba() {
       initComponents();
//   Hilo.start();
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
       int i=0, u=0, i2=0;
       int ss=0;
    XYSeries temperatura = new XYSeries("Temparatura");
        XYSeries humedad = new XYSeries("Humedad");  
         XYSeries nivel_agua = new XYSeries("Nivel Agua");
     
       public void datos(){
         i++;
        u++;
        i2++;
        double numero,numero1, numero2;
        numero =  (Math.random()*1024)+1;
         numero1 =  (Math.random()*1024)+1;
         numero2 =  (Math.random()*1024)+1;
        
        //serie #1
        temperatura.add( i, numero);
        
        //serie #2
        humedad.add( u, numero1);
        
        nivel_agua.add(i2, numero2);
      

        
        dataset.addSeries( temperatura );        
        dataset.addSeries( humedad ); 
        dataset.addSeries( nivel_agua ); 
       }
    
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
        botonComenzar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tempe = new javax.swing.JLabel();
        nombre1 = new javax.swing.JLabel();
        BotonParar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        hume = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        nivel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabelbuscar = new javax.swing.JLabel();
        jTextFieldbuscar = new javax.swing.JTextField();
        jButtonaceptar = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 255, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setForeground(new java.awt.Color(0, 102, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pane.setBackground(new java.awt.Color(204, 255, 255));
        pane.setLayout(new java.awt.BorderLayout());
        jPanel1.add(pane, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 1040, 490));
        jPanel1.add(jLabelhide, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, -1, -1));

        botonComenzar.setText("Comenzar");
        botonComenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonComenzarActionPerformed(evt);
            }
        });
        jPanel1.add(botonComenzar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 560, 140, 60));

        tempe.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tempe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/frio.png"))); // NOI18N
        tempe.setText("0 *C");
        jScrollPane2.setViewportView(tempe);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 220, 230, 130));

        nombre1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nombre1.setText("Bienvenido:");
        jPanel1.add(nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 350, 40));

        BotonParar.setText("Parar");
        BotonParar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonPararActionPerformed(evt);
            }
        });
        jPanel1.add(BotonParar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 580, 130, 40));

        hume.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        hume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/off.png"))); // NOI18N
        hume.setText("0%");
        jScrollPane3.setViewportView(hume);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 370, 230, 130));

        nivel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nivel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vacio.png"))); // NOI18N
        nivel.setText("Vacio");
        jScrollPane4.setViewportView(nivel);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 70, 230, 130));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/wi.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 650));

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

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1970, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonaceptarActionPerformed
        // TODO add your handling code here:
        mostrar();
    }//GEN-LAST:event_jButtonaceptarActionPerformed

    private void botonComenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonComenzarActionPerformed
//datos();
    Hilo.start();
        // TODO add your handling code here:
    }//GEN-LAST:event_botonComenzarActionPerformed
int axc=-1;
    private void BotonPararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonPararActionPerformed
  axc++;
        if(axc==0){
        prender(30,90,455);}
  else{
       prender(27,28,100);
        axc=-1;
        }
  
 
 
        //cambio("34");
   // Finalizar=true;
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
    public javax.swing.JLabel hume;
    private javax.swing.JButton jButtonaceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelbuscar;
    public javax.swing.JLabel jLabelhide;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldbuscar;
    public javax.swing.JLabel nivel;
    public javax.swing.JLabel nombre1;
    private javax.swing.JPanel pane;
    private javax.swing.JTable tabla;
    public javax.swing.JLabel tempe;
    // End of variables declaration//GEN-END:variables
//Lights On
    int bienvenido=0;
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

    public void dibujar(int i, int temp, int hum, int Nivel){
        try {
            temperatura.add(i,temp);
                humedad.add(i,hum);
                nivel_agua.add(i,Nivel);
                
                dataset.addSeries( temperatura );        
                dataset.addSeries( humedad ); 
                dataset.addSeries( nivel_agua );
        } catch (Exception e) {
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
                   ValoresRecibidos= StringAArray(mensaje);
                   ChipId=ValoresRecibidos[0];
                   Temperatura=ValoresRecibidos[1];
                   Humedad=ValoresRecibidos[2];
                   NivelAgua=ValoresRecibidos[3];
                    
                    if(bienvenido==0){
                    this.nombre1.setText("Conectado con "+ChipId);
                    }
                 cambio(Integer.toString(Temperatura));
                 humedad(Integer.toString(Humedad));
                 nivel(Integer.toString(NivelAgua));
                //mensaje = flujo_entrada.readUTF();
               //int a = Integer.parseInt(mensaje);
                    //String sms = new String(peticion.getData(),0, peticion.getLength());
                    //int a = Integer.parseInt(sms);
                    
                    //salida.println("ok"  );
                System.out.println(mensaje);
                i=i+1;
                dibujar(i,Temperatura,Humedad,NivelAgua);
                prender(Temperatura,Humedad,NivelAgua);
                
                /*temperatura.add(i,Temperatura);
                humedad.add(i,Humedad);
                nivel_agua.add(i,NivelAgua);*/
                
             
                
                 int ced=0;
                 String cedu = jLabelhide.getText();
        String mysql2="INSERT INTO datos_corazon (chipId, humedad, temperatura, nivel_agua, datos_x)"+ "values (?,?,?,?,?)";
        
        try {
            Statement st2 = cc.createStatement();
            
            
            
            PreparedStatement insertar2 = cc.prepareStatement(mysql2);
            
            insertar2.setInt(1,ChipId);
           insertar2.setInt(2,Humedad);
           insertar2.setInt(3,Temperatura);
           insertar2.setInt(4,NivelAgua);
           insertar2.setInt(5,i);
           
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
                escribir.println(i+"\t"+Temperatura);
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
    public void disparadorTemp(int temp){
        Socket cliente = null;
		DataInputStream entrada = null;
		DataOutputStream salida = null;
		int Puerto2 =5001;//debe ser mayor que 1024	
		String ipServidor ="192.168.100.17";	
                
        try {	
 
			cliente = new Socket(ipServidor, Puerto2);  
			//asignamos este numero de puerto
			entrada = new DataInputStream(cliente.getInputStream());
			// será lo que enviaremos al servidor	
			salida = new DataOutputStream(cliente.getOutputStream());
			// será lo que nos devuelva el servidor	
 
		}
		catch (UnknownHostException excepcion) {
			System.err.println("El servidor no está levantado");
		}//Fin de excepciones especiales q arrojan esta clase socket
		catch (Exception e) {
			System.err.println("Error: " + e );
		}// fin de captura de excepciones de las clases q no son socket
 
		try {
			
			String linea_recibida;
			do{//búcle que sirve para enviar mensajes a servidor hasta que
				//usemos la palabra salir
				////System.out.println("Escriba un mensaje para Servidor:");
				System.out.print("-->");
				
				salida.writeBytes('a' + "\n");//metodo para enviar el mensaje
				linea_recibida = entrada.readLine();
				System.out.println("SERVIDOR DICE: " + linea_recibida);
			}while(temp>28);//fin bucle, sin este bucle la 
			//conexion cerraria apenas se envie un solo mensaje
			
			//Se deben cerrar los objetos de socket y de flujo
			salida.close();
			entrada.close();
			cliente.close();
			System.out.println("Fin conexion");
		}
		catch (UnknownHostException excepcion) {
			System.err.println("No encuentro el servidor en la dirección" + ipServidor);
		}//Fin de excepciones especiales q arrojan esta clase socket
		catch (IOException excepcion) {
			System.err.println("Error de entrada/salida");
		}
		catch (Exception e) {
			System.err.println("Error: " + e );
 
		}// fin de captura de excepciones de las clases q no son socket
    }
    
     public int [] StringAArray(String A){
        int [] arreglodatos={0,0,0,0};
       // String string = "123-654321-789";
            String[] parts = A.split(",", 6); 
            
            arreglodatos[0]= Integer.parseInt(parts[0]);
            arreglodatos[1]= Integer.parseInt(parts[1]);
            arreglodatos[2]= Integer.parseInt(parts[2]);
            arreglodatos[3]= Integer.parseInt(parts[3]);
            
            return arreglodatos;
        
    }
}


//Falta poner imagnes bonitas
