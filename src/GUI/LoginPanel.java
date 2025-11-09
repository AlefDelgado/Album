package GUI;

import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel {
    private final GUI ventana;
    
    public LoginPanel(GUI ventana) {
        this.ventana = ventana;
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel titulo = new JLabel("Iniciar sesión", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        
        JTextField txtUsuario = new JTextField(15);
        JPasswordField txtPassword = new JPasswordField(15);
        JButton btnEntrar = new JButton("Entrar");
        
        JLabel hint = new JLabel("admin/admin o fan/fan", SwingConstants.CENTER);
        hint.setFont(new Font("SansSerif", Font.ITALIC, 11));
        
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(titulo, gbc);
        
        gbc.gridy++;
        add(hint, gbc);
        
        gbc.gridy++; gbc.gridwidth = 1;
        add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1;
        add(txtUsuario, gbc);
        
        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        add(txtPassword, gbc);
        
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        add(btnEntrar, gbc);
        
        btnEntrar.addActionListener(e -> {
            String u = txtUsuario.getText().trim();
            String p = new String(txtPassword.getPassword());
            
            if (u.equals("admin") && p.equals("admin")) {
                JOptionPane.showMessageDialog(this, "Bienvenido Administrador");
                ventana.mostrar("admin");
            } else if (u.equals("fan") && p.equals("fan")) {
                JOptionPane.showMessageDialog(this, "Bienvenido Usuario");
                ventana.mostrar("album");
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}