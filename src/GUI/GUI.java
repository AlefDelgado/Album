package GUI;

import java.awt.*;
import javax.swing.*;

public class GUI extends JFrame {
    private JLabel titulo;
    private JPanel contenedor;
    private CardLayout cards;
    
    // 🎨 PALETA DE COLORES MODERNA (Azul profesional)
    public static final Color AZUL_PRINCIPAL = new Color(13, 71, 161);     // Azul oscuro
    public static final Color AZUL_SECUNDARIO = new Color(25, 118, 210);   // Azul medio
    public static final Color AZUL_CLARO = new Color(66, 165, 245);        // Azul claro
    public static final Color AZUL_MUY_CLARO = new Color(227, 242, 253);   // Azul muy claro
    public static final Color VERDE_EXITO = new Color(46, 125, 50);        // Verde
    public static final Color ROJO_PELIGRO = new Color(198, 40, 40);       // Rojo
    public static final Color GRIS_TEXTO = new Color(66, 66, 66);          // Gris oscuro
    public static final Color GRIS_CLARO = new Color(245, 245, 245);       // Gris muy claro
    
    public GUI() {
        super("Album de Estampas de Futbol");
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);
        
        //  MENÚ SUPERIOR 
        JMenuBar barra = crearMenuBar();
        setJMenuBar(barra);
        
        //  ENCABEZADO CON DEGRADADO 
        JPanel header = crearEncabezado();
        add(header, BorderLayout.NORTH);
        
        //  CONTENEDOR CON CARDLAYOUT 
        cards = new CardLayout();
        contenedor = new JPanel(cards);
        contenedor.setBackground(GRIS_CLARO);
        
        contenedor.add(new LoginPanel(this), "login");
        contenedor.add(new AdminPanel(this), "admin");
        contenedor.add(new AlbumPanel(this), "album");
        
        add(contenedor, BorderLayout.CENTER);
        
        //  PIE DE PÁGINA 
        JPanel footer = crearFooter();
        add(footer, BorderLayout.SOUTH);
        
        revalidate();
        repaint();
    }
    
    private JMenuBar crearMenuBar() {
        JMenuBar barra = new JMenuBar();
        barra.setBackground(AZUL_PRINCIPAL);
        barra.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        JMenu menuArchivo = new JMenu("📋 Menu");
        menuArchivo.setForeground(Color.WHITE);
        menuArchivo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        
        JMenuItem aboutThis = new JMenuItem("ℹ️ Acerca de");
        aboutThis.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        aboutThis.addActionListener(e -> {
            mostrarDialogoPersonalizado(
                "Acerca del Sistema",
                "ALBUM DE ESTAMPAS DE FUTBOL\n\n" +
                "Sistema de album estampas de futbol\n" +
                "Centro Universitario UAEMex Tianguistenco\n" +
                "Universidad Autonoma del Estado de Mexico",
                JOptionPane.INFORMATION_MESSAGE
            );
        });
        
        JMenuItem elementoSalir = new JMenuItem("🚪 Salir");
        elementoSalir.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        elementoSalir.addActionListener(e -> {
            int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea salir?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            if (respuesta == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        
        menuArchivo.add(aboutThis);
        menuArchivo.addSeparator();
        menuArchivo.add(elementoSalir);
        
        barra.add(menuArchivo);
        return barra;
    }
    
    private JPanel crearEncabezado() {
        JPanel header = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                
                // Degradado de izquierda a derecha
                GradientPaint gradient = new GradientPaint(
                    0, 0, AZUL_PRINCIPAL,
                    getWidth(), 0, AZUL_SECUNDARIO
                );
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(0, 80));
        
        // Título con icono
        titulo = new JLabel("🔐 Iniciar Sesión", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        
        header.add(titulo, BorderLayout.CENTER);
        
        return header;
    }
    
    private JPanel crearFooter() {
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footer.setBackground(AZUL_PRINCIPAL);
        footer.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));
        
        JLabel lblFooter = new JLabel("© 2025 UAEMex - Sistema de Album Digital");
        lblFooter.setForeground(Color.WHITE);
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        
        footer.add(lblFooter);
        
        return footer;
    }
    
    public void mostrar(String nombrePanel) {
        cards.show(contenedor, nombrePanel);
        
        // Cambiar título según la vista
        switch (nombrePanel) {
            case "login":
                titulo.setText("🔐 Iniciar Sesion");
                break;
            case "admin":
                titulo.setText("Panel Administrador");
                break;
            case "album":
                titulo.setText("Album de Estampas");
                break;
        }
    }
    
    // Método auxiliar para diálogos personalizados
    public void mostrarDialogoPersonalizado(String titulo, String mensaje, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }
    
    // Método para crear botones estilizados
    public static JButton crearBotonPrimario(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(AZUL_SECUNDARIO);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(AZUL_CLARO);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(AZUL_SECUNDARIO);
            }
        });
        
        return boton;
    }
    
    public static JButton crearBotonSecundario(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(GRIS_CLARO);
        boton.setForeground(GRIS_TEXTO);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 18, 8, 18)
        ));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(230, 230, 230));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(GRIS_CLARO);
            }
        });
        
        return boton;
    }
    
    public static JButton crearBotonPeligro(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(ROJO_PELIGRO);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(220, 60, 60));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(ROJO_PELIGRO);
            }
        });
        
        return boton;
    }
    
    public static JButton crearBotonExito(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(VERDE_EXITO);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 13));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(56, 142, 60));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(VERDE_EXITO);
            }
        });
        
        return boton;
    }
}