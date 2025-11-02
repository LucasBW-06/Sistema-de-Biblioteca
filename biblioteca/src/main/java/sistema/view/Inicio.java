package sistema.view;

import javax.swing.*;
import java.awt.*;

import java.sql.SQLException;

public class Inicio extends JFrame {
    
    public Inicio() throws SQLException {
        setTitle("Sistema de Biblioteca");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("Sistema de Biblioteca", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Arial", Font.PLAIN, 16));

        btnEntrar.addActionListener(e -> {
            try {
                new TelaBiblioteca().setVisible(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            dispose();
        });

        painel.add(titulo, BorderLayout.CENTER);
        painel.add(btnEntrar, BorderLayout.SOUTH);

        add(painel);
    }
}
