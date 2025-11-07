package album;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 *
 * @author alefdelgado
 */

public class GUI extends JFrame
{
    private JLabel titulo;
    private JPanel panel;
    
    
    public GUI() {
        super("Log in");
        
        
        
        Componentes();
        
        JMenu menuArchivo = new JMenu("This App");
        menuArchivo.setMnemonic('A'); 
        
        panel.revalidate();
        panel.repaint();
        JMenuItem aboutThis = new JMenuItem("About");
        aboutThis.setMnemonic('c');
        menuArchivo.add(aboutThis); 
        aboutThis.addActionListener(
                new ActionListener()
        {

            
            public void actionPerformed(ActionEvent evento) {
                JOptionPane.showMessageDialog(GUI.this,"                       Album Futbol\n                       UAEMex 2025\nCentro Universitario UAEMex Tianguistenco","Acerca de", JOptionPane.PLAIN_MESSAGE);
            } 
        } 
        ); 
        
        JMenuItem elementoSalir = new JMenuItem("Exit");
        elementoSalir.setMnemonic('S'); 
        menuArchivo.add(elementoSalir); 
        elementoSalir.addActionListener(
                new ActionListener()
        {
            
            
            public void actionPerformed(ActionEvent evento) {
                System.exit(0); 
            } 
        }
        );
        
        JMenuBar barra = new JMenuBar();
        setJMenuBar(barra);
        barra.add(menuArchivo);
        
    }
    
    public void Componentes() {
        
        // Panel principal
        panel = new JPanel();
        panel.setBackground(Color.BLACK);
        add(panel, BorderLayout.CENTER);
        
        // Tiulo de la ventana
        titulo = new JLabel();
        titulo.setText("Iniciar Sesión");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.white);
        panel.add(titulo, BorderLayout.NORTH);
    }
    
}
