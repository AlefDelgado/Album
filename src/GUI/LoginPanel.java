package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginPanel extends JPanel {
    private final GUI ventana;
    
    public LoginPanel(GUI ventana) {
        this.ventana = ventana;
        setLayout(new GridBagLayout());
        setBackground(GUI.GRIS_CLARO);
        
        // Panel central con sombra
        JPanel panelLogin = crearPanelLogin();
        add(panelLogin);
    }
    
    private JPanel crearPanelLogin() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(40, 50, 40, 50)
        ));
        panel.setPreferredSize(new Dimension(450, 500));
        
        // Icono de fútbol grande
        JLabel iconoFutbol = new JLabel("⚽", SwingConstants.CENTER);
        iconoFutbol.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 80));
        iconoFutbol.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Título
        JLabel lblTitulo = new JLabel("Bienvenido", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        lblTitulo.setForeground(GUI.AZUL_PRINCIPAL);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Subtítulo
        JLabel lblSubtitulo = new JLabel("Accede a tu álbum digital", SwingConstants.CENTER);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSubtitulo.setForeground(GUI.GRIS_TEXTO);
        lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Espaciador
        panel.add(iconoFutbol);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(lblTitulo);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(lblSubtitulo);
        panel.add(Box.createRigidArea(new Dimension(0, 35)));
        
        // Campo Usuario
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblUsuario.setForeground(GUI.GRIS_TEXTO);
        lblUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JTextField txtUsuario = crearCampoTexto();
        txtUsuario.setMaximumSize(new Dimension(350, 40));
        
        panel.add(lblUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        panel.add(txtUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Campo Contraseña
        JLabel lblPassword = new JLabel("Contraseña");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblPassword.setForeground(GUI.GRIS_TEXTO);
        lblPassword.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JPasswordField txtPassword = crearCampoPassword();
        txtPassword.setMaximumSize(new Dimension(350, 40));
        
        panel.add(lblPassword);
        panel.add(Box.createRigidArea(new Dimension(0, 8)));
        panel.add(txtPassword);
        panel.add(Box.createRigidArea(new Dimension(0, 25)));
        
        // Hint de credenciales
        JLabel lblHint = new JLabel("admin/admin o fan/fan", SwingConstants.CENTER);
        lblHint.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        lblHint.setForeground(new Color(120, 120, 120));
        lblHint.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(lblHint);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Botón de ingreso
        JButton btnEntrar = GUI.crearBotonPrimario("Iniciar Sesión");
        btnEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEntrar.setMaximumSize(new Dimension(350, 45));
        
        panel.add(btnEntrar);
        
        // Acción del botón
        btnEntrar.addActionListener(e -> {
            String usuario = txtUsuario.getText().trim();
            String password = new String(txtPassword.getPassword());
            
            if (usuario.isEmpty() || password.isEmpty()) {
                mostrarError("Por favor, complete todos los campos");
                return;
            }
            
            if (usuario.equals("admin") && password.equals("admin")) {
                mostrarExito("Bienvenido Administrador");
                ventana.mostrar("admin");
                limpiarCampos(txtUsuario, txtPassword);
            } else if (usuario.equals("fan") && password.equals("fan")) {
                mostrarExito("¡Bienvenido Fanático! ⚽");
                ventana.mostrar("album");
                limpiarCampos(txtUsuario, txtPassword);
            } else {
                mostrarError("Credenciales incorrectas. Intente nuevamente.");
                txtPassword.setText("");
                txtUsuario.setText("");
            }
        });
        
        // Permitir Enter para iniciar sesión
        txtPassword.addActionListener(e -> btnEntrar.doClick());
        
        return panel;
    }
    
    private JTextField crearCampoTexto() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        // Efecto focus
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campo.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(GUI.AZUL_SECUNDARIO, 2),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campo.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
        });
        
        return campo;
    }
    
    private JPasswordField crearCampoPassword() {
        JPasswordField campo = new JPasswordField();
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        // Efecto focus
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campo.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(GUI.AZUL_SECUNDARIO, 2),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campo.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
            }
        });
        
        return campo;
    }
    
    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            " Error de autenticación",
            JOptionPane.ERROR_MESSAGE
        );
    }
    
    private void mostrarExito(String mensaje) {
        JOptionPane.showMessageDialog(
            this,
            mensaje,
            "Acceso concedido",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private void limpiarCampos(JTextField txtUsuario, JPasswordField txtPassword) {
        txtUsuario.setText("");
        txtPassword.setText("");
    }
}