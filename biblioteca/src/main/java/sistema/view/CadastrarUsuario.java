package sistema.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import sistema.dao.UsuarioDao;
import sistema.model.Usuario;
import sistema.util.ValidarUsuario;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

public class CadastrarUsuario extends JFrame {

    private JTextField campoNome;
    private JFormattedTextField campoCpf;
    private JTextField campoEmail;
    private JFormattedTextField campoTelefone;

    public CadastrarUsuario() throws SQLException, ParseException {
        setTitle("Sistema de Biblioteca - Cadastro de Usuário");
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

        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setFont(fonteLabels);
        MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
        cpfMask.setPlaceholderCharacter('_');
        campoCpf = new JFormattedTextField(cpfMask);
        campoCpf.setFont(fonteCampos);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(fonteLabels);
        campoEmail = new JTextField();
        campoEmail.setFont(fonteCampos);

        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setFont(fonteLabels);
        MaskFormatter telefoneMask = new MaskFormatter("(##) #####-####");
        telefoneMask.setPlaceholderCharacter('_');
        campoTelefone = new JFormattedTextField(telefoneMask);
        campoTelefone.setFont(fonteCampos);

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

        // Linha 2: CPF
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelCpf, c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoCpf, c);

        // Linha 3: Email
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelEmail, c);

        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoEmail, c);

        // Linha 4: Telefone
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelTelefone, c);

        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoTelefone, c);

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
        btnCadastrar.addActionListener(e -> {
            try {
                String nome = campoNome.getText();
                String cpf = campoCpf.getText();
                String email = campoEmail.getText();
                String telefone = campoTelefone.getText();

                Usuario u = new Usuario();
                u.setNome(nome);
                u.setCpf(cpf);
                u.setEmail(email);
                u.setTelefone(telefone);

                ValidarUsuario.validar(u);

                UsuarioDao daoU = new UsuarioDao();
                daoU.inserirUsuario(u);

                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
                new TelaBiblioteca().setVisible(true);
                dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de validação", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRetornar.addActionListener(e -> {
            try {
                new TelaBiblioteca().setVisible(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            dispose();
        });
    }
}
