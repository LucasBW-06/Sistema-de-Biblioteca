package sistema.view.emprestimo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import sistema.dao.EmprestimoDao;
import sistema.model.Emprestimo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.*;

public class RegistrarDevolucao extends JFrame {
    
    private JTextField campoLivro;
    private JTextField campoUsuario;
    private JSpinner campoDataDevolvido;

    private Emprestimo emprestimo;

    public RegistrarDevolucao() throws SQLException {
        setTitle("Sistema de Biblioteca - Registrar Devolução");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel formPainel = new JPanel(new GridBagLayout());
        formPainel.setBorder(new EmptyBorder(20, 20, 20, 20));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8, 8, 8, 8);
        c.fill = GridBagConstraints.HORIZONTAL;

        Font fonteLabels = new Font("Arial", Font.PLAIN, 14);
        Font fonteCampos = new Font("Arial", Font.PLAIN, 14);

        JLabel labelLivro = new JLabel("Livro:");
        labelLivro.setFont(fonteLabels);
        campoLivro = new JTextField();
        campoLivro.setFont(fonteCampos);
        campoLivro.setEditable(false);

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setFont(fonteLabels);
        campoUsuario = new JTextField();
        campoUsuario.setFont(fonteCampos);
        campoUsuario.setEditable(false);

        JButton btnBuscarEmprestimo = new JButton("Buscar Registro");
        btnBuscarEmprestimo.setFont(fonteLabels);

        btnBuscarEmprestimo.addActionListener(e -> {
            try {
                abrirDialogBuscarEmprestimo();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao buscar registro: " + ex.getMessage());
            }
        });

        SpinnerDateModel modeloDevolvido = new SpinnerDateModel();
        JLabel labelDevolvido = new JLabel("Data de Devolução:");
        labelDevolvido.setFont(fonteLabels);
        campoDataDevolvido = new JSpinner(modeloDevolvido);
        JSpinner.DateEditor editorEmprestimo = new JSpinner.DateEditor(campoDataDevolvido, "dd/MM/yyyy");
        campoDataDevolvido.setEditor(editorEmprestimo);

        JPanel painelDevolvido = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelDevolvido.add(labelDevolvido);
        painelDevolvido.add(campoDataDevolvido);

        JButton btnConfirmar = new JButton("Confirmar Devolução");
        JButton btnCancelar = new JButton("Cancelar");

        btnCancelar.addActionListener(e -> cancelar());

        btnConfirmar.addActionListener(e -> confirmarDevolucao());

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        c.gridx = 0; c.gridy = 0;
        formPainel.add(labelLivro, c);

        c.gridx = 1; c.gridy = 0;
        c.weightx = 1.0;
        formPainel.add(campoLivro, c);

        c.gridx = 0; c.gridy = 1;
        formPainel.add(labelUsuario, c);

        c.gridx = 1; c.gridy = 1;
        c.weightx = 1.0;
        formPainel.add(campoUsuario, c);

        c.gridx = 1; c.gridy = 2;
        c.weightx = 0;
        formPainel.add(btnBuscarEmprestimo, c);

        c.gridx = 0; c.gridy = 3;
        c.gridwidth = 3;
        formPainel.add(painelDevolvido, c);

        c.gridx = 0; c.gridy = 4;
        c.gridwidth = 3;
        formPainel.add(painelBotoes, c);

        add(formPainel);
    }

    public void abrirDialogBuscarEmprestimo() throws SQLException {
        BuscarEmprestimoDialog dialog = new BuscarEmprestimoDialog(this);
        dialog.setVisible(true);

        Emprestimo selecionado = dialog.getEmprestimoSelecionado();
        if (selecionado != null) {
            emprestimo = selecionado;
            campoLivro.setText(emprestimo.getLivro().getTitulo());
            campoUsuario.setText(emprestimo.getUsuario().getNome());
        }
    }

    private void confirmarDevolucao() {
        try {
            if (emprestimo == null) {
                throw new IllegalArgumentException("Nenhum registro foi selecionado.");
            }

            LocalDate dataDevolvido = ((Date) campoDataDevolvido.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            EmprestimoDao daoE = new EmprestimoDao();
            daoE.registrarDevolucao(emprestimo, dataDevolvido);

            JOptionPane.showMessageDialog(this, "Devolução registrado com sucesso!");
            new TelaEmprestimo().setVisible(true);
            dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de validação", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cancelar() {
        try {
            new TelaEmprestimo().setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dispose();
    }
}