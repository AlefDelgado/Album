package GUI;

import CONTROL.*;
import java.awt.*;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AdminPanel extends JPanel {
    private final GUI ventana;
    private GestorAlbum gestor;
    private JTabbedPane pestanas;
    private String rutaImagenSeleccionada = "";
    private String rutaEscudoSeleccionado = "";
    
    public AdminPanel(GUI ventana) {
        this.ventana = ventana;
        this.gestor = new GestorAlbum();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(e -> ventana.mostrar("login"));
        panelSuperior.add(btnCerrarSesion);
        add(panelSuperior, BorderLayout.NORTH);
        
        pestanas = new JTabbedPane();
        pestanas.addTab("Agregar", crearPanelAgregar());
        pestanas.addTab("Consultar", crearPanelConsultar());
        pestanas.addTab("Modificar", crearPanelModificar());
        pestanas.addTab("Eliminar", crearPanelEliminar());
        
        add(pestanas, BorderLayout.CENTER);
    }
    
    private JPanel crearPanelAgregar() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JTextField txtEquipo = new JTextField(20);
        JTextField txtNombre = new JTextField(20);
        JTextField txtPosicion = new JTextField(20);
        JTextField txtNumero = new JTextField(20);
        JTextField txtNacionalidad = new JTextField(20);
        JTextField txtEdad = new JTextField(20);
        JTextField txtDivision = new JTextField(20);
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Jugador", "Director Técnico"});
        
        // NUEVOS: Campos para imágenes
        JLabel lblImagenSeleccionada = new JLabel("No seleccionada");
        JLabel lblEscudoSeleccionado = new JLabel("No seleccionado");
        JButton btnSeleccionarImagen = new JButton("Seleccionar Imagen Jugador");
        JButton btnSeleccionarEscudo = new JButton("Seleccionar Escudo");
        JCheckBox chkRutaAuto = new JCheckBox("Generar rutas automáticas", true);
        
        JButton btnGuardar = new JButton("Guardar Jugador");
        JButton btnLimpiar = new JButton("Limpiar Campos");
        
        int fila = 0;
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        panel.add(cbTipo, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(new JLabel("Nombre del Equipo:"), gbc);
        gbc.gridx = 1;
        panel.add(txtEquipo, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panel.add(txtNombre, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(new JLabel("Posición:"), gbc);
        gbc.gridx = 1;
        panel.add(txtPosicion, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(new JLabel("Número:"), gbc);
        gbc.gridx = 1;
        panel.add(txtNumero, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(new JLabel("Nacionalidad:"), gbc);
        gbc.gridx = 1;
        panel.add(txtNacionalidad, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        panel.add(txtEdad, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(new JLabel("División:"), gbc);
        gbc.gridx = 1;
        panel.add(txtDivision, gbc);
        
        // SECCIÓN DE IMÁGENES
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        gbc.gridwidth = 2;
        panel.add(new JSeparator(), gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        gbc.gridwidth = 2;
        panel.add(chkRutaAuto, gbc);
        
        fila++;
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(btnSeleccionarImagen, gbc);
        gbc.gridx = 1;
        panel.add(lblImagenSeleccionada, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panel.add(btnSeleccionarEscudo, gbc);
        gbc.gridx = 1;
        panel.add(lblEscudoSeleccionado, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        gbc.gridwidth = 2;
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnGuardar);
        panelBotones.add(btnLimpiar);
        panel.add(panelBotones, gbc);
        
        // Habilitar/deshabilitar selectores según checkbox
        chkRutaAuto.addActionListener(e -> {
            boolean auto = chkRutaAuto.isSelected();
            btnSeleccionarImagen.setEnabled(!auto);
            btnSeleccionarEscudo.setEnabled(!auto);
            if (auto) {
                lblImagenSeleccionada.setText("Automática");
                lblEscudoSeleccionado.setText("Automático");
                rutaImagenSeleccionada = "";
                rutaEscudoSeleccionado = "";
            } else {
                lblImagenSeleccionada.setText("No seleccionada");
                lblEscudoSeleccionado.setText("No seleccionado");
            }
        });
        
        // Selector de imagen de jugador
        btnSeleccionarImagen.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("imagenes/jugadores"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Imágenes (*.jpg, *.jpeg, *.png)", "jpg", "jpeg", "png");
            fileChooser.setFileFilter(filter);
            
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                rutaImagenSeleccionada = selectedFile.getPath();
                lblImagenSeleccionada.setText(selectedFile.getName());
            }
        });
        
        // Selector de escudo
        btnSeleccionarEscudo.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("imagenes/escudos"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Imágenes (*.jpg, *.jpeg, *.png)", "jpg", "jpeg", "png");
            fileChooser.setFileFilter(filter);
            
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                rutaEscudoSeleccionado = selectedFile.getPath();
                lblEscudoSeleccionado.setText(selectedFile.getName());
            }
        });
        
        // Acción Guardar
        btnGuardar.addActionListener(e -> {
            try {
                String tipo = (String) cbTipo.getSelectedItem();
                Jugador j = new Jugador();
                j.setNombreEquipo(txtEquipo.getText().trim());
                j.setNombreJugador(txtNombre.getText().trim());
                j.setPosicion(txtPosicion.getText().trim());
                j.setNumero(txtNumero.getText().trim());
                j.setNacionalidad(txtNacionalidad.getText().trim());
                j.setEdad(Integer.parseInt(txtEdad.getText().trim()));
                j.setDivision(txtDivision.getText().trim());
                j.setTipo(tipo);
                
                // Asignar rutas de imágenes
                if (chkRutaAuto.isSelected()) {
                    j.setRutaImagen(j.generarRutaImagenAuto());
                    j.setRutaEscudo(j.generarRutaEscudoAuto());
                } else {
                    j.setRutaImagen(rutaImagenSeleccionada.isEmpty() ? "" : rutaImagenSeleccionada);
                    j.setRutaEscudo(rutaEscudoSeleccionado.isEmpty() ? "" : rutaEscudoSeleccionado);
                }
                
                if (!GestorAlbum.validarCampos(j)) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (gestor.agregarJugador(j)) {
                    JOptionPane.showMessageDialog(this, "Jugador agregado exitosamente");
                    limpiarCampos(txtEquipo, txtNombre, txtPosicion, txtNumero, txtNacionalidad, txtEdad, txtDivision);
                    lblImagenSeleccionada.setText(chkRutaAuto.isSelected() ? "Automática" : "No seleccionada");
                    lblEscudoSeleccionado.setText(chkRutaAuto.isSelected() ? "Automático" : "No seleccionado");
                    rutaImagenSeleccionada = "";
                    rutaEscudoSeleccionado = "";
                } else {
                    JOptionPane.showMessageDialog(this, "Ya existe un jugador con ese equipo y número", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La edad debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        // Acción Limpiar
        btnLimpiar.addActionListener(e -> {
            limpiarCampos(txtEquipo, txtNombre, txtPosicion, txtNumero, txtNacionalidad, txtEdad, txtDivision);
            lblImagenSeleccionada.setText(chkRutaAuto.isSelected() ? "Automática" : "No seleccionada");
            lblEscudoSeleccionado.setText(chkRutaAuto.isSelected() ? "Automático" : "No seleccionado");
            rutaImagenSeleccionada = "";
            rutaEscudoSeleccionado = "";
        });
        
        return panel;
    }
    
    private JPanel crearPanelConsultar() {
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBackground(Color.WHITE);
        
        JPanel panelBusqueda = new JPanel(new FlowLayout());
        JTextField txtEquipo = new JTextField(15);
        JTextField txtNumero = new JTextField(8);
        JButton btnBuscar = new JButton("Buscar");
        
        panelBusqueda.add(new JLabel("Equipo:"));
        panelBusqueda.add(txtEquipo);
        panelBusqueda.add(new JLabel("Número:"));
        panelBusqueda.add(txtNumero);
        panelBusqueda.add(btnBuscar);
        
        JTextArea areaResultado = new JTextArea(15, 40);
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaResultado);
        
        panel.add(panelBusqueda, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        
        btnBuscar.addActionListener(e -> {
            String equipo = txtEquipo.getText().trim();
            String numero = txtNumero.getText().trim();
            
            if (equipo.isEmpty() || numero.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese equipo y número", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Jugador j = gestor.buscarJugador(equipo, numero);
            if (j != null) {
                areaResultado.setText(formatearTarjeta(j));
            } else {
                areaResultado.setText("No se encontró el jugador");
            }
        });
        
        return panel;
    }
    
    private JPanel crearPanelModificar() {
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBackground(Color.WHITE);
        
        JPanel panelBusqueda = new JPanel(new FlowLayout());
        JTextField txtEquipoBuscar = new JTextField(15);
        JTextField txtNumeroBuscar = new JTextField(8);
        JButton btnBuscar = new JButton("Buscar");
        
        panelBusqueda.add(new JLabel("Equipo:"));
        panelBusqueda.add(txtEquipoBuscar);
        panelBusqueda.add(new JLabel("Número:"));
        panelBusqueda.add(txtNumeroBuscar);
        panelBusqueda.add(btnBuscar);
        
        JPanel panelEdicion = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JTextField txtEquipo = new JTextField(20);
        JTextField txtNombre = new JTextField(20);
        JTextField txtPosicion = new JTextField(20);
        JTextField txtNumero = new JTextField(20);
        JTextField txtNacionalidad = new JTextField(20);
        JTextField txtEdad = new JTextField(20);
        JTextField txtDivision = new JTextField(20);
        JButton btnModificar = new JButton("Guardar Cambios");
        
        int fila = 0;
        gbc.gridx = 0; gbc.gridy = fila;
        panelEdicion.add(new JLabel("Equipo:"), gbc);
        gbc.gridx = 1;
        panelEdicion.add(txtEquipo, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panelEdicion.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panelEdicion.add(txtNombre, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panelEdicion.add(new JLabel("Posición:"), gbc);
        gbc.gridx = 1;
        panelEdicion.add(txtPosicion, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panelEdicion.add(new JLabel("Número:"), gbc);
        gbc.gridx = 1;
        panelEdicion.add(txtNumero, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panelEdicion.add(new JLabel("Nacionalidad:"), gbc);
        gbc.gridx = 1;
        panelEdicion.add(txtNacionalidad, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panelEdicion.add(new JLabel("Edad:"), gbc);
        gbc.gridx = 1;
        panelEdicion.add(txtEdad, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        panelEdicion.add(new JLabel("División:"), gbc);
        gbc.gridx = 1;
        panelEdicion.add(txtDivision, gbc);
        
        fila++;
        gbc.gridx = 0; gbc.gridy = fila;
        gbc.gridwidth = 2;
        panelEdicion.add(btnModificar, gbc);
        
        panel.add(panelBusqueda, BorderLayout.NORTH);
        panel.add(panelEdicion, BorderLayout.CENTER);
        
        habilitarCampos(false, txtEquipo, txtNombre, txtPosicion, txtNumero, txtNacionalidad, txtEdad, txtDivision);
        btnModificar.setEnabled(false);
        
        btnBuscar.addActionListener(e -> {
            String equipo = txtEquipoBuscar.getText().trim();
            String numero = txtNumeroBuscar.getText().trim();
            
            Jugador j = gestor.buscarJugador(equipo, numero);
            if (j != null) {
                txtEquipo.setText(j.getNombreEquipo());
                txtNombre.setText(j.getNombreJugador());
                txtPosicion.setText(j.getPosicion());
                txtNumero.setText(j.getNumero());
                txtNacionalidad.setText(j.getNacionalidad());
                txtEdad.setText(String.valueOf(j.getEdad()));
                txtDivision.setText(j.getDivision());
                
                habilitarCampos(true, txtEquipo, txtNombre, txtPosicion, txtNumero, txtNacionalidad, txtEdad, txtDivision);
                btnModificar.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el jugador", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnModificar.addActionListener(e -> {
            try {
                Jugador j = new Jugador();
                j.setNombreEquipo(txtEquipo.getText().trim());
                j.setNombreJugador(txtNombre.getText().trim());
                j.setPosicion(txtPosicion.getText().trim());
                j.setNumero(txtNumero.getText().trim());
                j.setNacionalidad(txtNacionalidad.getText().trim());
                j.setEdad(Integer.parseInt(txtEdad.getText().trim()));
                j.setDivision(txtDivision.getText().trim());
                j.setRutaImagen(j.generarRutaImagenAuto());
                j.setRutaEscudo(j.generarRutaEscudoAuto());
                j.setTipo("Jugador");
                
                if (gestor.modificarJugador(txtEquipoBuscar.getText().trim(), txtNumeroBuscar.getText().trim(), j)) {
                    JOptionPane.showMessageDialog(this, "Jugador modificado exitosamente");
                    limpiarCampos(txtEquipoBuscar, txtNumeroBuscar, txtEquipo, txtNombre, txtPosicion, txtNumero, txtNacionalidad, txtEdad, txtDivision);
                    habilitarCampos(false, txtEquipo, txtNombre, txtPosicion, txtNumero, txtNacionalidad, txtEdad, txtDivision);
                    btnModificar.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La edad debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        return panel;
    }
    
    private JPanel crearPanelEliminar() {
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBackground(Color.WHITE);
        
        JPanel panelBusqueda = new JPanel(new FlowLayout());
        JTextField txtEquipo = new JTextField(15);
        JTextField txtNumero = new JTextField(8);
        JButton btnBuscar = new JButton("Buscar");
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setEnabled(false);
        btnEliminar.setBackground(new Color(220, 53, 69));
        btnEliminar.setForeground(Color.WHITE);
        
        panelBusqueda.add(new JLabel("Equipo:"));
        panelBusqueda.add(txtEquipo);
        panelBusqueda.add(new JLabel("Número:"));
        panelBusqueda.add(txtNumero);
        panelBusqueda.add(btnBuscar);
        panelBusqueda.add(btnEliminar);
        
        JTextArea areaResultado = new JTextArea(15, 40);
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(areaResultado);
        
        panel.add(panelBusqueda, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        
        final Jugador[] jugadorActual = {null};
        
        btnBuscar.addActionListener(e -> {
            String equipo = txtEquipo.getText().trim();
            String numero = txtNumero.getText().trim();
            
            Jugador j = gestor.buscarJugador(equipo, numero);
            if (j != null) {
                jugadorActual[0] = j;
                areaResultado.setText(formatearTarjeta(j));
                btnEliminar.setEnabled(true);
            } else {
                areaResultado.setText("No se encontró el jugador");
                btnEliminar.setEnabled(false);
            }
        });
        
        btnEliminar.addActionListener(e -> {
            if (jugadorActual[0] != null) {
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "¿Está seguro de eliminar este jugador?", 
                    "Confirmar eliminación", 
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    if (gestor.eliminarJugador(jugadorActual[0].getNombreEquipo(), jugadorActual[0].getNumero())) {
                        JOptionPane.showMessageDialog(this, "Jugador eliminado exitosamente");
                        areaResultado.setText("");
                        txtEquipo.setText("");
                        txtNumero.setText ("");
                        btnEliminar.setEnabled(false);
                        jugadorActual[0] = null;
                    }
                }
            }
        });
        
        return panel;
    }
    
    private void limpiarCampos(JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setText("");
        }
    }
    
    private void habilitarCampos(boolean habilitar, JTextField... campos) {
        for (JTextField campo : campos) {
            campo.setEnabled(habilitar);
        }
    }
    
    private String formatearTarjeta(Jugador j) {
        StringBuilder sb = new StringBuilder();
        sb.append("╔══════════════════════════════════════════╗\n");
        sb.append("║         TARJETA DE JUGADOR               ║\n");
        sb.append("╠══════════════════════════════════════════╣\n");
        sb.append(String.format("║ Equipo:        %-25s ║\n", j.getNombreEquipo()));
        sb.append(String.format("║ Nombre:        %-25s ║\n", j.getNombreJugador()));
        sb.append(String.format("║ Posición:      %-25s ║\n", j.getPosicion()));
        sb.append(String.format("║ Número:        %-25s ║\n", j.getNumero()));
        sb.append(String.format("║ Nacionalidad:  %-25s ║\n", j.getNacionalidad()));
        sb.append(String.format("║ Edad:          %-25s ║\n", j.getEdad() + " años"));
        sb.append(String.format("║ División:      %-25s ║\n", j.getDivision()));
        sb.append(String.format("║ Tipo:          %-25s ║\n", j.getTipo()));
        sb.append("╚══════════════════════════════════════════╝\n");
        return sb.toString();
    }
}