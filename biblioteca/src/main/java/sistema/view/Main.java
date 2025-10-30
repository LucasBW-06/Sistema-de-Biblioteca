package sistema.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Sistema de Biblioteca");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Bem-vindo ao Sistema de Biblioteca", JLabel.CENTER);
        JButton btnEntrar = new JButton("Entrar");
        
        
        btnEntrar.addActionListener(e ->
            JOptionPane.showMessageDialog(this, "Você clicou em Entrar!")
        );

        painel.add(titulo, BorderLayout.CENTER);
        painel.add(btnEntrar, BorderLayout.SOUTH);

        add(painel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
