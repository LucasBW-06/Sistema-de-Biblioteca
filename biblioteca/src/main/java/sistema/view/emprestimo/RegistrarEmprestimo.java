package sistema.view.emprestimo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import sistema.dao.EmprestimoDao;
import sistema.model.Emprestimo;
import sistema.model.Livro;
import sistema.model.Usuario;

import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.awt.*;

public class RegistrarEmprestimo extends JFrame {
    
    private JTextField campoLivro;
    private JTextField campoUsuario;
    private JSpinner campoDataEmprestimo;
    private JSpinner campoDataDevolucao;
    private Livro livro;
    private Usuario usuario;

    private Emprestimo emprestimo;

    public RegistrarEmprestimo() throws SQLException {
        setTitle("Sistema de Biblioteca - Registrar Empréstimo");
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

        JButton btnBuscarLivro = new JButton("Buscar Livro");
        btnBuscarLivro.setFont(fonteLabels);

        btnBuscarLivro.addActionListener(e -> {
            try {
                abrirDialogBuscarLivro();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao buscar livro: " + ex.getMessage());
            }
        });

        JLabel labelUsuario = new JLabel("Usuário:");
        labelUsuario.setFont(fonteLabels);
        campoUsuario = new JTextField();
        campoUsuario.setFont(fonteCampos);
        campoUsuario.setEditable(false);

        JButton btnBuscarUsuario = new JButton("Buscar Usuário");
        btnBuscarUsuario.setFont(fonteLabels);

        btnBuscarUsuario.addActionListener(e -> {
            try {
                abrirDialogBuscarUsuario();
            } catch (SQLException | ParseException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao buscar usuário: " + ex.getMessage());
            }
        });

        SpinnerDateModel modeloEmprestimo = new SpinnerDateModel();
        JLabel labelEmprestimo = new JLabel("Data de Empréstimo:");
        labelEmprestimo.setFont(fonteLabels);
        campoDataEmprestimo = new JSpinner(modeloEmprestimo);
        JSpinner.DateEditor editorEmprestimo = new JSpinner.DateEditor(campoDataEmprestimo, "dd/MM/yyyy");
        campoDataEmprestimo.setEditor(editorEmprestimo);

        JPanel painelEmprestimo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelEmprestimo.add(labelEmprestimo);
        painelEmprestimo.add(campoDataEmprestimo);

        SpinnerDateModel modeloDevolucao = new SpinnerDateModel();
        JLabel labelDevolucao = new JLabel("Data de Devolução:");
        labelDevolucao.setFont(fonteLabels);
        campoDataDevolucao = new JSpinner(modeloDevolucao);
        JSpinner.DateEditor editorDevolucao = new JSpinner.DateEditor(campoDataDevolucao, "dd/MM/yyyy");
        campoDataDevolucao.setEditor(editorDevolucao);

        JPanel painelDevolucao = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelDevolucao.add(labelDevolucao);
        painelDevolucao.add(campoDataDevolucao);


        JButton btnConfirmar = new JButton("Confirmar Empréstimo");
        JButton btnCancelar = new JButton("Cancelar");

        btnCancelar.addActionListener(e -> cancelar());

        btnConfirmar.addActionListener(e -> confirmarEmprestimo());

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        c.gridx = 0; c.gridy = 0;
        formPainel.add(labelLivro, c);

        c.gridx = 1; c.gridy = 0;
        c.weightx = 1.0;
        formPainel.add(campoLivro, c);

        c.gridx = 2; c.gridy = 0;
        c.weightx = 0;
        formPainel.add(btnBuscarLivro, c);

        c.gridx = 0; c.gridy = 1;
        formPainel.add(labelUsuario, c);

        c.gridx = 1; c.gridy = 1;
        c.weightx = 1.0;
        formPainel.add(campoUsuario, c);

        c.gridx = 2; c.gridy = 1;
        c.weightx = 0;
        formPainel.add(btnBuscarUsuario, c);

        c.gridx = 0; c.gridy = 2;
        c.gridwidth = 3;
        formPainel.add(painelEmprestimo, c);

        c.gridx = 0; c.gridy = 3;
        c.gridwidth = 3;
        formPainel.add(painelDevolucao, c);

        c.gridx = 0; c.gridy = 4;
        c.gridwidth = 3;
        formPainel.add(painelBotoes, c);

        add(formPainel);
    }

    public void abrirDialogBuscarLivro() throws SQLException {
        BuscarLivroDialog dialog = new BuscarLivroDialog(this);
        dialog.setVisible(true);

        Livro selecionado = dialog.getLivroSelecionado();
        if (selecionado != null) {
            livro = selecionado;
            campoLivro.setText(livro.getTitulo());
        }
    }

    public void abrirDialogBuscarUsuario() throws SQLException, ParseException {
        BuscarUsuarioDialog dialog = new BuscarUsuarioDialog(this);
        dialog.setVisible(true);

        Usuario selecionado = dialog.getUsuarioSelecionado();
        if (selecionado != null) {
            usuario = selecionado;
            campoUsuario.setText(usuario.getNome());
        }
    }

    private void confirmarEmprestimo() {
        try {
            emprestimo = new Emprestimo();

            LocalDate dataEmprestimo = ((Date) campoDataEmprestimo.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dataDevolucao = ((Date) campoDataDevolucao.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            emprestimo.setLivro(livro);
            emprestimo.setUsuario(usuario);
            emprestimo.setDataEmprestimo(dataEmprestimo);
            emprestimo.setDataDevolucao(dataDevolucao);

            emprestimo.validar();

            EmprestimoDao daoE = new EmprestimoDao();
            daoE.registrarEmprestimo(emprestimo);

            JOptionPane.showMessageDialog(this, "Empréstimo registrado com sucesso!");
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