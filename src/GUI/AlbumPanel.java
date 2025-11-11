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
        setBackground(GUI.GRIS_CLARO);
        
        // Panel superior con filtros
        JPanel panelSuperior = crearPanelSuperior();
        add(panelSuperior, BorderLayout.NORTH);
        
        // Panel de estampas con scroll
        panelEstampas = new JPanel();
        panelEstampas.setLayout(new GridLayout(0, 3, 20, 20));
        panelEstampas.setBackground(GUI.GRIS_CLARO);
        panelEstampas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JScrollPane scroll = new JScrollPane(panelEstampas);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setBorder(null);
        
        add(scroll, BorderLayout.CENTER);
        
        actualizarEstampas();
    }
    
    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 2, 0, GUI.AZUL_CLARO),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        
        // Panel de filtros a la izquierda
        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        panelFiltro.setBackground(Color.WHITE);
        
        JLabel lblFiltro = new JLabel("🔍 Filtrar:");
        lblFiltro.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblFiltro.setForeground(GUI.GRIS_TEXTO);
        
        cbEquipos = new JComboBox<>();
        cbEquipos.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cbEquipos.setPreferredSize(new Dimension(250, 35));
        cbEquipos.setBackground(Color.WHITE);
        cbEquipos.addItem("📋 Todos los equipos");
        
        for (String equipo : gestor.obtenerEquipos()) {
            cbEquipos.addItem("⚽ " + equipo);
        }
        cbEquipos.addActionListener(e -> actualizarEstampas());
        
        panelFiltro.add(lblFiltro);
        panelFiltro.add(cbEquipos);
        
        // Botón cerrar sesión a la derecha
        JButton btnCerrarSesion = GUI.crearBotonSecundario("🚪 Cerrar Sesión");
        btnCerrarSesion.addActionListener(e -> ventana.mostrar("login"));
        
        panel.add(panelFiltro, BorderLayout.WEST);
        panel.add(btnCerrarSesion, BorderLayout.EAST);
        
        return panel;
    }
    
    private void actualizarEstampas() {
        panelEstampas.removeAll();
        
        String equipoSeleccionado = (String) cbEquipos.getSelectedItem();
        ArrayList<Jugador> jugadores;
        
        if (equipoSeleccionado == null || equipoSeleccionado.startsWith("📋")) {
            jugadores = gestor.obtenerTodosJugadores();
        } else {
            // Remover el emoji "⚽ " del nombre del equipo
            String equipoLimpio = equipoSeleccionado.substring(2);
            jugadores = gestor.obtenerJugadoresPorEquipo(equipoLimpio);
        }
        
        for (Jugador j : jugadores) {
            panelEstampas.add(crearEstampa(j));
        }
        
        if (jugadores.isEmpty()) {
            JPanel panelVacio = crearPanelVacio();
            panelEstampas.add(panelVacio);
        }
        
        panelEstampas.revalidate();
        panelEstampas.repaint();
    }
    
    private JPanel crearPanelVacio() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        JLabel lblIcono = new JLabel("📭", SwingConstants.CENTER);
        lblIcono.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 60));
        lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel lblMensaje = new JLabel("No hay jugadores registrados", SwingConstants.CENTER);
        lblMensaje.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblMensaje.setForeground(GUI.GRIS_TEXTO);
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(lblIcono);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(lblMensaje);
        
        return panel;
    }
    
    private JPanel crearEstampa(Jugador j) {
        JPanel estampa = new JPanel();
        estampa.setLayout(new BoxLayout(estampa, BoxLayout.Y_AXIS));
        estampa.setBackground(Color.WHITE);
        estampa.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        estampa.setPreferredSize(new Dimension(240, 420));
        estampa.setMaximumSize(new Dimension(240, 420));
        estampa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Header con equipo y escudo
        JPanel headerEstampa = new JPanel(new BorderLayout(5, 0));
        headerEstampa.setBackground(GUI.AZUL_MUY_CLARO);
        headerEstampa.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        headerEstampa.setMaximumSize(new Dimension(240, 50));
        
        JLabel lblEscudo = cargarImagen(j.getRutaEscudo(), 30, 30);
        
        JLabel lblEquipo = new JLabel(j.getNombreEquipo());
        lblEquipo.setFont(new Font("Segoe UI", Font.BOLD, 11));
        lblEquipo.setForeground(GUI.AZUL_PRINCIPAL);
        
        headerEstampa.add(lblEscudo, BorderLayout.WEST);
        headerEstampa.add(lblEquipo, BorderLayout.CENTER);
        
        // Imagen del jugador
        JLabel lblImagen = cargarImagen(j.getRutaImagen(), 160, 160);
        lblImagen.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblImagen.setBorder(BorderFactory.createLineBorder(GUI.AZUL_CLARO, 2));
        
        // Número destacado
        JLabel lblNumero = new JLabel("#" + j.getNumero());
        lblNumero.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblNumero.setForeground(GUI.AZUL_SECUNDARIO);
        lblNumero.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Nombre del jugador
        JLabel lblNombre = new JLabel("<html><center>" + j.getNombreJugador() + "</center></html>");
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNombre.setForeground(GUI.GRIS_TEXTO);
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Posición
        JLabel lblPosicion = new JLabel(j.getPosicion());
        lblPosicion.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblPosicion.setForeground(new Color(100, 100, 100));
        lblPosicion.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Info adicional (nacionalidad y edad)
        JPanel panelInfo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setMaximumSize(new Dimension(240, 30));
        
        JLabel lblNacionalidad = new JLabel("🌍 " + j.getNacionalidad());
        lblNacionalidad.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblNacionalidad.setForeground(new Color(120, 120, 120));
        
        JLabel lblEdad = new JLabel("📅 " + j.getEdad() + " años");
        lblEdad.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        lblEdad.setForeground(new Color(120, 120, 120));
        
        panelInfo.add(lblNacionalidad);
        panelInfo.add(new JLabel("|"));
        panelInfo.add(lblEdad);
        
        // Ensamblar estampa
        estampa.add(headerEstampa);
        estampa.add(Box.createRigidArea(new Dimension(0, 10)));
        estampa.add(lblImagen);
        estampa.add(Box.createRigidArea(new Dimension(0, 10)));
        estampa.add(lblNumero);
        estampa.add(Box.createRigidArea(new Dimension(0, 5)));
        estampa.add(lblNombre);
        estampa.add(Box.createRigidArea(new Dimension(0, 3)));
        estampa.add(lblPosicion);
        estampa.add(Box.createRigidArea(new Dimension(0, 8)));
        estampa.add(panelInfo);
        
        // Efectos hover y click
        estampa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                estampa.setBackground(GUI.AZUL_MUY_CLARO);
                estampa.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(GUI.AZUL_SECUNDARIO, 2),
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));
            }
            
            public void mouseExited(java.awt.event.MouseEvent evt) {
                estampa.setBackground(Color.WHITE);
                estampa.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                    BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));
            }
            
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mostrarDetalleJugador(j);
            }
        });
        
        return estampa;
    }
    
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
                // Placeholder moderno
                label.setOpaque(true);
                label.setBackground(GUI.GRIS_CLARO);
                label.setText("📷");
                label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, ancho/3));
                label.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
            }
        } catch (Exception e) {
            label.setOpaque(true);
            label.setBackground(new Color(255, 220, 220));
            label.setText("❌");
            label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, ancho/4));
            label.setBorder(BorderFactory.createLineBorder(GUI.ROJO_PELIGRO));
        }
        
        return label;
    }
    
    private void mostrarDetalleJugador(Jugador j) {
        JPanel panelDetalle = new JPanel();
        panelDetalle.setLayout(new BoxLayout(panelDetalle, BoxLayout.Y_AXIS));
        panelDetalle.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        String[] datos = {
            "⚽ EQUIPO: " + j.getNombreEquipo(),
            "👤 NOMBRE: " + j.getNombreJugador(),
            "🎯 POSICIÓN: " + j.getPosicion(),
            "🔢 NÚMERO: " + j.getNumero(),
            "🌍 NACIONALIDAD: " + j.getNacionalidad(),
            "📅 EDAD: " + j.getEdad() + " años",
            "🏆 DIVISIÓN: " + j.getDivision(),
            "👔 TIPO: " + j.getTipo()
        };
        
        for (String dato : datos) {
            JLabel lbl = new JLabel(dato);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
            panelDetalle.add(lbl);
            panelDetalle.add(Box.createRigidArea(new Dimension(0, 8)));
        }
        
        JOptionPane.showMessageDialog(
            this,
            panelDetalle,
            "📋 Detalle de " + j.getNombreJugador(),
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}