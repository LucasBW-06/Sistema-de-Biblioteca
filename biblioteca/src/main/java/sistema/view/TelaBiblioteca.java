package sistema.view;

import javax.swing.*;

import sistema.view.livro.TelaLivro;
import sistema.view.usuario.TelaUsuario;

import java.awt.*;
import java.sql.SQLException;

public class TelaBiblioteca extends JFrame {
    public TelaBiblioteca() throws SQLException {
        setTitle("Sistema de Biblioteca - Menu Principal");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnTelaUsuario = new JButton("Usuários");
        JButton btnTelaLivro = new JButton("Acervo");
        JButton btnEmprestarLivro = new JButton("Emprestar Livro");

        btnTelaUsuario.addActionListener(e -> telaUsuario());
        btnTelaLivro.addActionListener(e -> telaLivro());
        btnEmprestarLivro.addActionListener(e -> registrarEmprestimo());

        painelBotoes.add(btnTelaUsuario);
        painelBotoes.add(btnTelaLivro);
        painelBotoes.add(btnEmprestarLivro);

        painel.add(painelBotoes, BorderLayout.NORTH);

        add(painel);
    }

    public void telaUsuario() {
        try {
            new TelaUsuario().setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }

    public void telaLivro() {
        try {
            new TelaLivro().setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }

    public void registrarEmprestimo() {
        try {
            new RegistrarEmprestimo().setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }
}