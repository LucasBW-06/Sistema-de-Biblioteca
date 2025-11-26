package sistema.view.emprestimo;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sistema.dao.EmprestimoDao;
import sistema.model.Emprestimo;
import sistema.model.Funcionario;

public class VisualizarEmprestimo extends JFrame {
    
    private Emprestimo emprestimo;
    private Funcionario funcionario;
    
    public VisualizarEmprestimo(Emprestimo em, Funcionario f) throws SQLException, ParseException {
        emprestimo = em;
        funcionario = f;
        setTitle("Sistema de Biblioteca - " + emprestimo.getId());
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

        JLabel labelTitulo = new JLabel("Título:");
        labelTitulo.setFont(fonteLabels);
        JTextField campoTitulo = new JTextField(emprestimo.getLivro().getTitulo());
        campoTitulo.setEditable(false);
        campoTitulo.setFont(fonteCampos);

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setFont(fonteLabels);
        JTextField campoUsuario = new JTextField(emprestimo.getUsuario().getNome());
        campoUsuario.setEditable(false);
        campoUsuario.setFont(fonteCampos);

        JLabel labelDataEmprestimo = new JLabel("Data do Empréstimo:");
        labelDataEmprestimo.setFont(fonteLabels);
        String dataEmprestimo = emprestimo.getDataEmprestimo().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        JTextField campoDataEmprestimo = new JTextField(dataEmprestimo);
        campoDataEmprestimo.setEditable(false);
        campoDataEmprestimo.setFont(fonteCampos);

        JLabel labelDataDevolucao = new JLabel("Prazo:");
        labelDataDevolucao.setFont(fonteLabels);
        String dataDevolucao = emprestimo.getDataDevolucao().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        JTextField campoDataDevolucao = new JTextField(dataDevolucao);
        campoDataDevolucao.setEditable(false);
        campoDataDevolucao.setFont(fonteCampos);


        JLabel labelEstado = new JLabel("Estado:");
        labelEstado.setFont(fonteLabels);
        JTextField campoEstado = new JTextField(emprestimo.getEstado());
        campoEstado.setEditable(false);
        campoEstado.setFont(fonteCampos);

        JButton btnExcluir = new JButton("Excluir Registro");
        JButton btnVoltar = new JButton("Voltar");

        // Linha 1: Titulo
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelTitulo, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoTitulo, c);

        // Linha 2: Usuario
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelUsuario, c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoUsuario, c);

        // Linha 3: Data emprestimo
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelDataEmprestimo, c);

        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoDataEmprestimo, c);

        // Linha 4: Data devolução
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelDataDevolucao, c);

        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoDataDevolucao, c);

        // Linha 5: Estado
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelEstado, c);

        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoEstado, c);

        // Linha 6: Data devolvido

        if (emprestimo.getEstado().equals("DEVOLVIDO")) {
            JLabel labelDataDevolvido = new JLabel("Data da Devolução:");
            labelDataDevolvido.setFont(fonteLabels);
            String dataDevolvido = emprestimo.getDataDevolvido().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            JTextField campoDataDevolvido = new JTextField(dataDevolvido);
            campoDataDevolvido.setEditable(false);
            campoDataDevolvido.setFont(fonteCampos);

            c.gridx = 0;
            c.gridy = 5;
            c.weightx = 0;
            c.anchor = GridBagConstraints.EAST;
            painel.add(labelDataDevolvido, c);

            c.gridx = 1;
            c.gridy = 5;
            c.weightx = 1.0;
            c.anchor = GridBagConstraints.WEST;
            painel.add(campoDataDevolvido, c);
        }

        // Linha 7: Botões
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnVoltar);
        painel.add(painelBotoes, c);

        add(painel);

        btnExcluir.addActionListener(e -> { excluir(); });

        btnVoltar.addActionListener(e -> { voltar(); });
    }

    public void excluir() {
        try {
            EmprestimoDao dao = new EmprestimoDao();
            dao.softdeleteEmprestimo(emprestimo);

            JOptionPane.showMessageDialog(this, "Empréstimo excluído com sucesso!");
            new TelaEmprestimo(funcionario).setVisible(true);
            dispose();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void voltar() {
        try {
            new TelaEmprestimo(funcionario).setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dispose();
    }
}
