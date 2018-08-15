/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosocketjavaesp8266;
import java.sql.*;
import java.sql.Connection;
//import java.sql.PreparedStatement;
//import com.mysql.jdbc.PreparedStatement;
//import java.sql.Statement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Municipio de Gye
 */
public class Login extends javax.swing.JFrame {

    basedata bd = new basedata(); 
    Connection cc = bd.conectar();
    
    
    /**
     * Creates new form Login
     */
    public Login() {
        
        initComponents();
        jPanelchange.setVisible(false);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

    }

    
    public boolean acceder (String user){
        boolean cond=false;
        String capt="";
        String nombre="";
        String sql = "SELECT * FROM pacientes WHERE cedula='"+user+"' ";
        try {
            Statement st = cc.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                capt= rs.getString("cedula");
                nombre = rs.getString("nombres");
            }
            if(user.equals(capt)){
                    JOptionPane.showMessageDialog(this,"BIENVENIDO "+nombre+" de cédula: "+user+"!!");
                    cond=true;
                } else{
                    JOptionPane.showMessageDialog(null,"INCORRECTO");
                }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
         return cond;
        
    }
    
    
    public void create_user (String nombres, String apellidos, String cedula, String cod_sexo, String fecha, String lugar){
        String mysql="INSERT INTO pacientes (nombres, apellidos, cedula, cod_sexo, fecha_nacimiento, lugar_nacimiento)"+ "values (?,?,?,?,?,?)";
        try {
            Statement st = cc.createStatement();
            PreparedStatement insertar = cc.prepareStatement(mysql);
            
           insertar.setString(1,nombres);
           insertar.setString(2,apellidos);
           insertar.setString(3,cedula);
           insertar.setString(4,cod_sexo);
           insertar.setString(5,fecha);
           insertar.setString(6,lugar);
           insertar.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*int ced=0;
        String mysql2="INSERT INTO datos_corazon (datos_x, datos_y, cod_paciente)"+ "values (?,?,?)";
        String my = "SELECT cod_paciente FROM pacientes WHERE cedula = '"+cedula+"'";
        try {
            Statement st2 = cc.createStatement();
            ResultSet registro = st2.executeQuery(my);
            
            if (registro.next()==true) {
  	        ced = registro.getInt("cod_paciente");
  			  	
  	    }else {
  		setTitle("No hay");
  	    }
            PreparedStatement insertar2 = cc.prepareStatement(mysql2);
            
           insertar2.setInt(1,0);
           insertar2.setInt(2,0);
           insertar2.setInt(3,ced);
           insertar2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }*/
         
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanellogin = new javax.swing.JPanel();
        jLabelicon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldingreso = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanelchange = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldnombres = new javax.swing.JTextField();
        jComboBoxsexo = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jTextFieldapellidos = new javax.swing.JTextField();
        jTextFieldcedula = new javax.swing.JTextField();
        jTextFieldfecha = new javax.swing.JTextField();
        jTextFieldlugar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanellogin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/unnameddef.png"))); // NOI18N
        jPanellogin.add(jLabelicon, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 190, 220));

        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel2.setText("CÉDULA PACIENTE: ");
        jPanellogin.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 170, 20));
        jPanellogin.add(jTextFieldingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 140, -1));

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel3.setText("CONTRASEÑA:");
        jPanellogin.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 120, 20));

        jButton1.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add-user222.png"))); // NOI18N
        jButton1.setText("REGISTRAR PACIENTE");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanellogin.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 200, 40));

        jButton2.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/user1.png"))); // NOI18N
        jButton2.setText("ANALIZAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanellogin.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 350, 150, 30));
        jPanellogin.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 120, -1));

        jButton4.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/exit1.png"))); // NOI18N
        jButton4.setText("SALIR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanellogin.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 100, -1));

        jButton6.setText("jButton6");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanellogin.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondologin2.jpg"))); // NOI18N
        jLabel1.setText("    ");
        jPanellogin.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 400));

        getContentPane().add(jPanellogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanelchange.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/add-user222.png"))); // NOI18N
        jButton3.setText("REGISTRAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanelchange.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 170, 40));

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel5.setText("REGISTRAR PACIENTE");
        jLabel5.setAlignmentX(0.5F);
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelchange.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 190, 30));
        jPanelchange.add(jTextFieldnombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 180, 30));

        jComboBoxsexo.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        jComboBoxsexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MASCULINO", "FEMENINO" }));
        jPanelchange.add(jComboBoxsexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 180, 30));

        jButton5.setFont(new java.awt.Font("Berlin Sans FB", 0, 12)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/atras1.png"))); // NOI18N
        jButton5.setText("CANCELAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanelchange.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, -1));
        jPanelchange.add(jTextFieldapellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 180, 30));
        jPanelchange.add(jTextFieldcedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 180, 30));
        jPanelchange.add(jTextFieldfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 180, 30));
        jPanelchange.add(jTextFieldlugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 180, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondologin2.jpg"))); // NOI18N
        jPanelchange.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 400));

        getContentPane().add(jPanelchange, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String paciente = jTextFieldingreso.getText();
        //String b = new String(jPasswordField1.getPassword());
        if(acceder(paciente) == true){
           //Interfaz i = new Interfaz();
           //i.setVisible(true);
           //this.setVisible(false);
           //new Login().setVisible(false);
          //this.setVisible(false);
          prueba p = new prueba();
          p.setVisible(true);
          this.setVisible(false);
          p.jLabelhide.setText(paciente);
          
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jPanellogin.setVisible(false);
        jPanelchange.setVisible(true);
        
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        /* String us = jTextFieldnombres.getText();
        String pw = new String(jPasswordField2.getPassword());
        String pw2 = new String(jPasswordField3.getPassword());
        String comb = jComboBoxsexo.getSelectedItem().toString();  
        if(String.valueOf(jPasswordField2.getPassword()).equals(String.valueOf(jPasswordField3.getPassword()))){
        create_user(us,pw,comb);
        jPanelchange.setVisible(false);
        jPanellogin.setVisible(true);
        }
        else{
            jPasswordField2.setText("");
            jPasswordField3.setText("");
            JOptionPane.showMessageDialog(null,"VALIDACIÓN INCORRECTA DE LA CONTRASEÑA");
        }*/
        
        String nom = jTextFieldnombres.getText();
        String ape = jTextFieldapellidos.getText();
        String ced = jTextFieldcedula.getText();
        String sexo = jComboBoxsexo.getSelectedItem().toString(); 
        String fecha = jTextFieldfecha.getText();
        String lugar = jTextFieldlugar.getText();
        
        if(sexo.equals("MASCULINO")){
            sexo = "M";
        } else{
            sexo = "F";
        }
        
        create_user(nom, ape, ced, sexo, fecha, lugar);
        jTextFieldnombres.setText("");
        jTextFieldapellidos.setText("");
        jTextFieldcedula.setText("");
        jTextFieldfecha.setText("");
        jTextFieldlugar.setText("");
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        jPanelchange.setVisible(false);
        jPanellogin.setVisible(true);
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

prueba p = new prueba();
p.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
                //new Login().setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBoxsexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelicon;
    private javax.swing.JPanel jPanelchange;
    private javax.swing.JPanel jPanellogin;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextFieldapellidos;
    private javax.swing.JTextField jTextFieldcedula;
    private javax.swing.JTextField jTextFieldfecha;
    private javax.swing.JTextField jTextFieldingreso;
    private javax.swing.JTextField jTextFieldlugar;
    private javax.swing.JTextField jTextFieldnombres;
    // End of variables declaration//GEN-END:variables
}
