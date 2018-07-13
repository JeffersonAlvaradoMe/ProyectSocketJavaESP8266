package Client;

//import .*;
import javax.swing.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LaminaMarcoCliente mimarco=new LaminaMarcoCliente();
		
		mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}


class LaminaMarcoCliente extends JFrame{
        JTextField nick;
	JTextArea campochat;
	JButton miboton;
	
	public LaminaMarcoCliente(){
            
                getContentPane().setLayout(null);
                
                JLabel texto = new JLabel();
                getContentPane().add(texto);
                texto.setBounds(65,5, 150,30 );
                texto.setText("-RECEPCIÓN DE DATOS-");
                
		
                nick = new JTextField();
                getContentPane().add(nick);
                nick.setBounds(80, 40, 100, 30);
                
                campochat = new JTextArea(); //12,20
                getContentPane().add(campochat);
                campochat.setBounds(30, 90, 200, 100);
				
		miboton=new JButton();
                getContentPane().add(miboton);
                miboton.setText("ENVIAR");
                miboton.setBounds(80, 220,100, 30);
                EnviarTexto mievento = new EnviarTexto();
                miboton.addActionListener(mievento);
		
                
                
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setBounds(600,300,280,350);
		setVisible(true);
                setResizable(false);
	}
        
	
	private class EnviarTexto implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            
            try {
                Socket misocket = new Socket("127.0.0.1",9999);
                String a="";
                a=Integer.toString(misocket.getLocalPort());
                campochat.setText("");
                campochat.append("\nPuerto:"+a);
                campochat.append("\nServidor a aceptado petición");
                DataOutputStream flujo_salida = new DataOutputStream(misocket.getOutputStream());
                flujo_salida.writeUTF(nick.getText());
                flujo_salida.close();
            } catch(UnknownHostException e1){
                e1.printStackTrace();
            }catch (IOException ex) {
                ex.printStackTrace();
                System.out.println(ex.getMessage());
                Logger.getLogger(LaminaMarcoCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
            
        }
			
	
	
}


