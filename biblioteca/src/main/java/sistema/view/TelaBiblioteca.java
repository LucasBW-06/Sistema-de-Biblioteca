package sistema.view;

import javax.swing.*;

import sistema.model.Funcionario;
import sistema.view.emprestimo.TelaEmprestimo;
import sistema.view.livro.TelaLivro;
import sistema.view.usuario.TelaUsuario;
import sistema.view.funcionario.TelaFuncionario;

import java.awt.*;
import java.sql.SQLException;

public class TelaBiblioteca extends JFrame {

    private Funcionario funcionario;

    public TelaBiblioteca(Funcionario f) throws SQLException {
        funcionario = f;
        setTitle("Sistema de Biblioteca - Menu Principal");
        setSize(350, 300); // Tela mais estreita
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout());
        JPanel painelBotoes = new JPanel();
        
        // Coluna única
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));

        // Botões
        JButton btnTelaUsuario = new JButton("Usuários");
        JButton btnTelaFuncionario = new JButton("Funcionários");
        JButton btnTelaLivro = new JButton("Acervo");
        JButton btnTelaEmprestimo = new JButton("Empréstimos");

        // Centralizar botões individualmente
        btnTelaUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTelaFuncionario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTelaLivro.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnTelaEmprestimo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Listeners
        btnTelaUsuario.addActionListener(e -> telaUsuario());
        btnTelaFuncionario.addActionListener(e -> telaFuncionario());
        btnTelaLivro.addActionListener(e -> telaLivro());
        btnTelaEmprestimo.addActionListener(e -> telaEmprestimo());

        // Espaçamento entre botões
        painelBotoes.add(Box.createVerticalStrut(15));
        painelBotoes.add(btnTelaUsuario);
        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.add(btnTelaFuncionario);
        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.add(btnTelaLivro);
        painelBotoes.add(Box.createVerticalStrut(10));
        painelBotoes.add(btnTelaEmprestimo);

        painel.add(painelBotoes, BorderLayout.CENTER);
        add(painel);
    }

    public void telaUsuario() {
        try {
            new TelaUsuario(funcionario).setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }

    public void telaFuncionario() {
        try {
            new TelaFuncionario(funcionario).setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }

    public void telaLivro() {
        try {
            new TelaLivro(funcionario).setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }

    public void telaEmprestimo() {
        try {
            new TelaEmprestimo(funcionario).setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }
}
