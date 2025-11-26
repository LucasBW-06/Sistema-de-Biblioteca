package sistema.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import sistema.dao.EmprestimoDao;
import sistema.dao.FuncionarioDao;
import sistema.model.Funcionario;

import java.awt.*;

import java.sql.SQLException;

public class Inicio extends JFrame {
    
    private JTextField campoLogin;
    private JPasswordField campoSenha;
    private JButton btnLogin;

    public Inicio() {
        setTitle("Sistema de Biblioteca - Entrada");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel formPainel = new JPanel(new GridBagLayout());
        formPainel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8); // espaçamento entre elementos
        c.fill = GridBagConstraints.HORIZONTAL;

        Font fonteLabels = new Font("Arial", Font.PLAIN, 14);
        Font fonteCampos = new Font("Arial", Font.PLAIN, 14);

        // Campos de formulário
        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setFont(fonteLabels);
        campoLogin = new JTextField();
        campoLogin.setFont(fonteCampos);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(fonteLabels);
        campoSenha = new JPasswordField();
        campoSenha.setFont(fonteCampos);

        // Botões
        btnLogin = new JButton("Login");

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelLogin, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoLogin, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelSenha, c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoSenha, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.add(btnLogin);
        formPainel.add(painelBotoes, c);

        add(formPainel);

        // Ações dos botões
        btnLogin.addActionListener(e -> autenticar());
    }

    private void autenticar() {
        String login = campoLogin.getText();
        String senha = new String(campoSenha.getPassword());

        try {
            FuncionarioDao daoF = new FuncionarioDao();
            if (daoF.autenticar(login, senha)) {
                JOptionPane.showMessageDialog(this, "Login realizado!");
                Funcionario funcionario = new Funcionario();
                funcionario = daoF.getFuncionario(login, senha);
                EmprestimoDao daoE = new EmprestimoDao();
                daoE.autalizaEstado();
                new TelaBiblioteca(funcionario).setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.");
            }
        } catch (SQLException e1) {
            System.out.println(e1);
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
