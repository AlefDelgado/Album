package MAIN;
import GUI.GUI;
import java.awt.Color;
import javax.swing.JFrame;


public class Main {

    public static void main(String[] args) {
        GUI ventana = new GUI();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800,500);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
                
    }
    
}
 