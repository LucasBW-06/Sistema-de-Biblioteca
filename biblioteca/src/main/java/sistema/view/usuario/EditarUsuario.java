package sistema.view.usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import sistema.dao.UsuarioDao;
import sistema.model.Usuario;
import sistema.util.ValidarUsuario;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

public class EditarUsuario extends JFrame {
    
    private JTextField campoNome;
    private JFormattedTextField campoCpf;
    private JTextField campoEmail;
    private JFormattedTextField campoTelefone;
    
    private Usuario usuario;
    
    public EditarUsuario(Usuario u) throws SQLException, ParseException {
        usuario = u;
        setTitle("Sistema de Biblioteca - " + usuario.getNome());
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        Font fonteLabels = new Font("Arial", Font.PLAIN, 14);
        Font fonteCampos = new Font("Arial", Font.PLAIN, 14);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setFont(fonteLabels);
        campoNome = new JTextField(usuario.getNome());
        campoNome.setFont(fonteCampos);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(fonteLabels);
        campoEmail = new JTextField(usuario.getEmail());
        campoEmail.setFont(fonteCampos);

        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setFont(fonteLabels);
        MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
        cpfMask.setPlaceholderCharacter('_');
        campoCpf = new JFormattedTextField(cpfMask);
        String cpf = usuario.getCpf().substring(0, 3) + "." + usuario.getCpf().substring(3, 6) + "." + usuario.getCpf().substring(6, 9) + "-" + usuario.getCpf().substring(9, 11); 
        campoCpf.setValue(cpf);
        campoCpf.setFont(fonteCampos);

        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setFont(fonteLabels);
        MaskFormatter telefoneMask = new MaskFormatter("(##) #####-####");
        telefoneMask.setPlaceholderCharacter('_');
        campoTelefone = new JFormattedTextField(telefoneMask);
        String telefone = "(" + usuario.getTelefone().substring(0, 2) + ") " + usuario.getTelefone().substring(2,7) + "-" + usuario.getTelefone().substring(7,11);
        campoTelefone.setValue(telefone);
        campoTelefone.setFont(fonteCampos);


        JButton btnSalvar = new JButton("Salvar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnCancelar = new JButton("Cancelar");

        // Linha 1: Nome
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelNome, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoNome, c);

        // Linha 2: CPF
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelCpf, c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoCpf, c);

        // Linha 3: Email
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelEmail, c);

        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoEmail, c);

        // Linha 4: Telefone
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelTelefone, c);

        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoTelefone, c);

        // Linha 5: Botões
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnCancelar);
        painel.add(painelBotoes, c);

        add(painel);

        btnSalvar.addActionListener(e -> { salvarEdicao(); });

        btnExcluir.addActionListener(e -> { excluirUsuario(); });

        btnCancelar.addActionListener(e -> { try {
            cancelar();
        } catch (ParseException e1) {
            e1.printStackTrace();
        } });
    }

    public void salvarEdicao() {
        try {
            Usuario u = new Usuario();

            String nome = campoNome.getText();
            String cpf = campoCpf.getText().replaceAll("[\\.\\-]", "");
            String email = campoEmail.getText();
            String telefone = campoTelefone.getText().replaceAll("[\\(\\)\\-\\s+]", "");

            u.setId(usuario.getId());
            u.setNome(nome);
            u.setCpf(cpf);
            u.setEmail(email);
            u.setTelefone(telefone);

            ValidarUsuario.validar(u);

            UsuarioDao daoU = new UsuarioDao();
            daoU.modificarUsuario(u);

            JOptionPane.showMessageDialog(this, "Edição realizada com sucesso!");
            new TelaUsuario().setVisible(true);
            dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de validação", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void excluirUsuario() {
        try {
            UsuarioDao daoU = new UsuarioDao();
            daoU.softdeleteUsuario(usuario);

            JOptionPane.showMessageDialog(this, "Usuário excluido com sucesso!");
            new TelaUsuario().setVisible(true);
            dispose();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void cancelar() throws ParseException {
        try {
            new VisualizarUsuario(usuario).setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }
}
