/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectosocketjavaesp8266;

import com.sun.java.swing.plaf.windows.resources.windows;
import java.awt.BorderLayout;
import static java.awt.SystemColor.window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
/**
 *
 * @author jefferson.alvarado
 */
public class GraficaPantalla {
    
     JFrame graf = new JFrame();
      JButton botonComenzar = new JButton("COMENZAR");
      JButton botonTerminar = new JButton("TERMINAR");
      JPanel topPanel = new JPanel();
    public GraficaPantalla(){
       
        	graf.setTitle("Electrocardiograma WIFI");
		graf.setSize(600, 400);
		graf.setLayout(new BorderLayout());
		graf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                graf.setLocation(700, 0);
                
                graf.add(topPanel, BorderLayout.NORTH);
                topPanel.setLayout(new BorderLayout());
		topPanel.add(botonComenzar, BorderLayout.WEST);
		topPanel.add(botonTerminar, BorderLayout.EAST);
		graf.add(topPanel, BorderLayout.NORTH);
                
                XYSeries series = new XYSeries("Sensor Electrocardiograma");
		XYSeriesCollection dataset = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory.createXYLineChart("Sensor", "Tiempo (Segundos)", "Lectura", dataset);
		graf.add(new ChartPanel(chart), BorderLayout.CENTER);
                
    
        graf.setVisible(true);
    }
	
     private void botonComenzarActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
       System.out.println("Prueba action boton");
                                
         }           
    
}
