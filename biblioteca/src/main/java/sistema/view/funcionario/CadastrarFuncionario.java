package sistema.view.funcionario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import sistema.dao.FuncionarioDao;
import sistema.model.Funcionario;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

public class CadastrarFuncionario extends JFrame {

    private JTextField campoNome;
    private JTextField campoCargo;
    private JTextField campoLogin;
    private JTextField campoSenha;

    private Funcionario funcionario;
    private Funcionario func;

    public CadastrarFuncionario(Funcionario f) throws SQLException, ParseException {
        func = f;
        setTitle("Sistema de Biblioteca - Cadastro de Funcionário");
        setSize(500, 350);
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
        JLabel labelNome = new JLabel("Nome:");
        labelNome.setFont(fonteLabels);
        campoNome = new JTextField();
        campoNome.setFont(fonteCampos);

        JLabel labelCargo = new JLabel("Cargo:");
        labelCargo.setFont(fonteLabels);
        campoCargo = new JTextField();
        campoCargo.setFont(fonteCampos);

        JLabel labelLogin = new JLabel("Login:");
        labelLogin.setFont(fonteLabels);
        campoLogin = new JTextField();
        campoLogin.setFont(fonteCampos);

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setFont(fonteLabels);
        campoSenha = new JTextField();
        campoSenha.setFont(fonteCampos);

        // Botões
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnRetornar = new JButton("Retornar");

        // Linha 1: Nome
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelNome, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoNome, c);

        // Linha 2: Cargo
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelCargo, c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoCargo, c);

        // Linha 3: Login
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelLogin, c);

        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoLogin, c);

        // Linha 4: Senha
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelSenha, c);

        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoSenha, c);

        // Linha 5: Botões
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnRetornar);
        formPainel.add(painelBotoes, c);

        add(formPainel);

        // Ações dos botões
        btnCadastrar.addActionListener(e -> cadastrarFuncionario());

        btnRetornar.addActionListener(e -> retornar());
    }

    public void cadastrarFuncionario() {
        try {
            funcionario = new Funcionario();

            String nome = campoNome.getText();
            String cargo = campoCargo.getText();
            String login = campoLogin.getText();
            String senha= campoSenha.getText();

            funcionario.setNome(nome);
            funcionario.setCargo(cargo);
            funcionario.setLogin(login);
            funcionario.setSenha(senha);

            funcionario.validar();

            FuncionarioDao daoF = new FuncionarioDao();
            daoF.inserirFuncionario(funcionario);

            JOptionPane.showMessageDialog(this, "Funcionário cadastrado com sucesso!");
            new TelaFuncionario(func).setVisible(true);
            dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de validação", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void retornar() {
        try {
            new TelaFuncionario(func).setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dispose();
    }
}
