package sistema.view.usuario;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import sistema.dao.EmprestimoDao;
import sistema.model.Emprestimo;
import sistema.model.Usuario;

public class VisualizarUsuario extends JFrame {
    
    private Usuario usuario;
    
    public VisualizarUsuario(Usuario u) throws SQLException, ParseException {
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
        JTextField campoNome = new JTextField(usuario.getNome());
        campoNome.setEditable(false);
        campoNome.setFont(fonteCampos);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(fonteLabels);
        JTextField campoEmail = new JTextField(usuario.getEmail());
        campoEmail.setEditable(false);
        campoEmail.setFont(fonteCampos);

        JLabel labelCpf = new JLabel("CPF:");
        labelCpf.setFont(fonteLabels);
        String cpf = usuario.getCpf().substring(0, 3) + "." + usuario.getCpf().substring(3, 6) + "." + usuario.getCpf().substring(6, 9) + "-" + usuario.getCpf().substring(9, 11); 
        JTextField campoCpf = new JTextField(cpf);
        campoCpf.setEditable(false);
        campoCpf.setFont(fonteCampos);

        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setFont(fonteLabels);
        String telefone = "(" + usuario.getTelefone().substring(0, 2) + ") " + usuario.getTelefone().substring(2,7) + "-" + usuario.getTelefone().substring(7,11);
        JTextField campoTelefone = new JTextField(telefone);
        campoTelefone.setEditable(false);
        campoTelefone.setFont(fonteCampos);

        JButton btnEditar = new JButton("Editar");
        JButton btnVoltar = new JButton("Voltar");

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
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnVoltar);
        painel.add(painelBotoes, c);

        List<Emprestimo> lista = new EmprestimoDao().getHistoricoUsuario(usuario);

        String[] colunas = {"Livro", "Data de Empréstimo", "Data de Devolução", "Situação"};
        Object[][] dados = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            dados[i][0] = lista.get(i).getLivro().getTitulo();
            dados[i][1] = lista.get(i).getDataEmprestimo();
            dados[i][2] = lista.get(i).getDataDevolucao();
            dados[i][3] = lista.get(i).getEstado();
        }

        JTable tabela = new JTable(new DefaultTableModel(dados, colunas));
        JScrollPane scroll = new JScrollPane(tabela);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        painel.add(scroll, c);

        add(painel);

        btnEditar.addActionListener(e -> { try {
            editarUsuario();
        } catch (ParseException e1) {
            e1.printStackTrace();
        } });

        btnVoltar.addActionListener(e -> { voltar(); });
    }

    public void editarUsuario() throws ParseException {
        try {
            new EditarUsuario(usuario).setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dispose();
    }

    public void voltar() {
        try {
            new TelaUsuario().setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dispose();
    }
}
