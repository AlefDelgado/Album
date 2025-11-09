package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame {
    private JLabel titulo;
    private JPanel contenedor;
    private CardLayout cards;
    
    public GUI() {
        super("Álbum de Estampillas");
        setLayout(new BorderLayout());
        
        // Menu 
        JMenu menuArchivo = new JMenu("Menu");
        menuArchivo.setMnemonic('A');
        
        JMenuItem aboutThis = new JMenuItem("About");
        aboutThis.setMnemonic('c');
        menuArchivo.add(aboutThis);
        aboutThis.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                JOptionPane.showMessageDialog(
                    GUI.this,
                    "                       Album Futbol\n" +
                    "                       UAEMex 2025\n" +
                    "Centro Universitario UAEMex Tianguistenco",
                    "Acerca de",
                    JOptionPane.PLAIN_MESSAGE
                );
            }
        });
        
        JMenuItem elementoSalir = new JMenuItem("Exit");
        elementoSalir.setMnemonic('S');
        menuArchivo.add(elementoSalir);
        elementoSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evento) {
                System.exit(0);
            }
        });
        
        JMenuBar barra = new JMenuBar();
        setJMenuBar(barra);
        barra.add(menuArchivo);
        
        // Encabezado
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.BLACK);
        titulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        header.add(titulo, BorderLayout.CENTER);
        add(header, BorderLayout.NORTH);
        
        // Contenedor con CardLayout
        cards = new CardLayout();
        contenedor = new JPanel(cards);
        
        contenedor.add(new LoginPanel(this), "login");
        contenedor.add(new AdminPanel(this), "admin");
        contenedor.add(new AlbumPanel(this), "album");
        
        add(contenedor, BorderLayout.CENTER);
        
        revalidate();
        repaint();
    }
    
    public void mostrar(String nombrePanel) {
        cards.show(contenedor, nombrePanel);
        if ("login".equals(nombrePanel)) {
            titulo.setText("Iniciar Sesión");
        } else if ("admin".equals(nombrePanel)) {
            titulo.setText("Panel Administrador");
        } else if ("album".equals(nombrePanel)) {
            titulo.setText("Álbum de Estampas");
        }
    }
}