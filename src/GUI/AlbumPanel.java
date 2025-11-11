package GUI;

import CONTROL.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

public class AlbumPanel extends JPanel {
    private final GUI ventana;
    private GestorAlbum gestor;
    private JPanel panelEstampas;
    private JComboBox<String> cbEquipos;
    
    public AlbumPanel(GUI ventana) {
        this.ventana = ventana;
        this.gestor = new GestorAlbum();
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240));
        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBackground(Color.WHITE);
        
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltro.setBackground(Color.WHITE);
        panelFiltro.add(new JLabel("Filtrar por equipo:"));
        
        cbEquipos = new JComboBox<>();
        cbEquipos.addItem("Todos los equipos");
        for (String equipo : gestor.obtenerEquipos()) {
            cbEquipos.addItem(equipo);
        }
        cbEquipos.addActionListener(e -> actualizarEstampas());
        panelFiltro.add(cbEquipos);
        
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(e -> ventana.mostrar("login"));
        
        panelSuperior.add(panelFiltro, BorderLayout.WEST);
        panelSuperior.add(btnCerrarSesion, BorderLayout.EAST);
        
        add(panelSuperior, BorderLayout.NORTH);
        
        panelEstampas = new JPanel();
        panelEstampas.setLayout(new GridLayout(0, 3, 15, 15));
        panelEstampas.setBackground(new Color(240, 240, 240));
        
        JScrollPane scroll = new JScrollPane(panelEstampas);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scroll, BorderLayout.CENTER);
        
        actualizarEstampas();
    }
    
    private void actualizarEstampas() {
        panelEstampas.removeAll();
        
        String equipoSeleccionado = (String) cbEquipos.getSelectedItem();
        ArrayList<Jugador> jugadores;
        
        if ("Todos los equipos".equals(equipoSeleccionado)) {
            jugadores = gestor.obtenerTodosJugadores();
        } else {
            jugadores = gestor.obtenerJugadoresPorEquipo(equipoSeleccionado);
        }
        
        for (Jugador j : jugadores) {
            panelEstampas.add(crearEstampa(j));
        }
        
        if (jugadores.isEmpty()) {
            JLabel lblVacio = new JLabel("No hay jugadores registrados", SwingConstants.CENTER);
            lblVacio.setFont(new Font("Arial", Font.ITALIC, 16));
            panelEstampas.add(lblVacio);
        }
        
        panelEstampas.revalidate();
        panelEstampas.repaint();
    }
    
    private JPanel crearEstampa(Jugador j) {
        JPanel estampa = new JPanel();
        estampa.setLayout(new BoxLayout(estampa, BoxLayout.Y_AXIS));
        estampa.setBackground(Color.WHITE);
        estampa.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        estampa.setPreferredSize(new Dimension(220, 380));
        estampa.setMaximumSize(new Dimension(220, 380));
        
        JLabel lblEquipo = new JLabel(j.getNombreEquipo(), SwingConstants.CENTER);
        lblEquipo.setFont(new Font("Arial", Font.BOLD, 12));
        lblEquipo.setForeground(new Color(0, 102, 204));
        lblEquipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // CARGAR IMAGEN DEL JUGADOR
        JLabel lblImagen = cargarImagen(j.getRutaImagen(), 140, 140);
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // CARGAR ESCUDO (pequeño)
        JLabel lblEscudo = cargarImagen(j.getRutaEscudo(), 30, 30);
        lblEscudo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblNombre = new JLabel(j.getNombreJugador(), SwingConstants.CENTER);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblNumero = new JLabel("#" + j.getNumero(), SwingConstants.CENTER);
        lblNumero.setFont(new Font("Arial", Font.BOLD, 20));
        lblNumero.setForeground(new Color(220, 53, 69));
        lblNumero.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblPosicion = new JLabel(j.getPosicion(), SwingConstants.CENTER);
        lblPosicion.setFont(new Font("Arial", Font.PLAIN, 11));
        lblPosicion.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblNacionalidad = new JLabel("🌍 " + j.getNacionalidad(), SwingConstants.CENTER);
        lblNacionalidad.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNacionalidad.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblEdad = new JLabel(j.getEdad() + " años", SwingConstants.CENTER);
        lblEdad.setFont(new Font("Arial", Font.PLAIN, 10));
        lblEdad.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        estampa.add(lblEquipo);
        estampa.add(Box.createRigidArea(new Dimension(0, 3)));
        estampa.add(lblEscudo);
        estampa.add(Box.createRigidArea(new Dimension(0, 5)));
        estampa.add(lblImagen);
        estampa.add(Box.createRigidArea(new Dimension(0, 8)));
        estampa.add(lblNombre);
        estampa.add(lblNumero);
        estampa.add(lblPosicion);
        estampa.add(Box.createRigidArea(new Dimension(0, 5)));
        estampa.add(lblNacionalidad);
        estampa.add(lblEdad);
        
        estampa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                estampa.setBackground(new Color(245, 245, 245));
                estampa.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0, 102, 204), 2),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                estampa.setBackground(Color.WHITE);
                estampa.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 2),
                    BorderFactory.createEmptyBorder(10, 10, 10, 10)
                ));
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarDetalleJugador(j);
            }
        });
        
        return estampa;
    }
    
    // MÉTODO PARA CARGAR IMÁGENES
    private JLabel cargarImagen(String ruta, int ancho, int alto) {
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(ancho, alto));
        label.setMaximumSize(new Dimension(ancho, alto));
        label.setMinimumSize(new Dimension(ancho, alto));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        
        try {
            File archivoImagen = new File(ruta);
            
            if (archivoImagen.exists()) {
                Image img = ImageIO.read(archivoImagen);
                Image imgEscalada = img.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(imgEscalada));
            } else {
                // Si no existe la imagen, mostrar placeholder
                label.setOpaque(true);
                label.setBackground(new Color(230, 230, 230));
                label.setText("Sin imagen");
                label.setFont(new Font("Arial", Font.ITALIC, 10));
                label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            }
        } catch (Exception e) {
            // Error al cargar, mostrar placeholder
            label.setOpaque(true);
            label.setBackground(new Color(230, 230, 230));
            label.setText("Error");
            label.setFont(new Font("Arial", Font.ITALIC, 10));
            label.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        
        return label;
    }
    
    private void mostrarDetalleJugador(Jugador j) {
        String mensaje = String.format(
            "EQUIPO: %s\n\n" +
            "NOMBRE: %s\n" +
            "POSICIÓN: %s\n" +
            "NÚMERO: %s\n" +
            "NACIONALIDAD: %s\n" +
            "EDAD: %d años\n" +
            "DIVISIÓN: %s\n" +
            "TIPO: %s",
            j.getNombreEquipo(),
            j.getNombreJugador(),
            j.getPosicion(),
            j.getNumero(),
            j.getNacionalidad(),
            j.getEdad(),
            j.getDivision(),
            j.getTipo()
        );
        
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "Detalle de " + j.getNombreJugador(),
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}