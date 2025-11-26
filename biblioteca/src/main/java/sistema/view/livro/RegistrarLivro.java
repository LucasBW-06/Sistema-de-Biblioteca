package sistema.view.livro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


import sistema.dao.LivroDao;
import sistema.model.Funcionario;
import sistema.model.Livro;

import java.awt.*;
import java.sql.SQLException;

public class RegistrarLivro extends JFrame {
    
    private JTextField campoTitulo;
    private JTextField campoAutor;
    private JTextField campoEditora;
    private JTextField campoGenero;
    private JTextField campoIsbn;
    private JTextField campoCodigo;

    private Livro livro;
    private Funcionario funcionario;

    public RegistrarLivro(Funcionario f) throws SQLException {
        funcionario = f;
        setTitle("Sistema de Biblioteca - Registrar Livro");
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
        JLabel labelTitulo = new JLabel("Título:");
        labelTitulo.setFont(fonteLabels);
        campoTitulo = new JTextField();
        campoTitulo.setFont(fonteCampos);

        JLabel labelAutor = new JLabel("Autor:");
        labelAutor.setFont(fonteLabels);
        campoAutor = new JTextField();
        campoAutor.setFont(fonteCampos);

        JLabel labelEditora = new JLabel("Editora:");
        labelEditora.setFont(fonteLabels);
        campoEditora = new JTextField();
        campoEditora.setFont(fonteCampos);

        JLabel labelGenero = new JLabel("Gênero:");
        labelGenero.setFont(fonteLabels);
        campoGenero = new JTextField();
        campoGenero.setFont(fonteCampos);

        JLabel labelIsbn = new JLabel("ISBN:");
        labelIsbn.setFont(fonteLabels);
        campoIsbn = new JTextField();
        campoIsbn.setFont(fonteCampos);

        JLabel labelCodigo = new JLabel("Código:");
        labelCodigo.setFont(fonteLabels);
        campoCodigo = new JTextField();
        campoCodigo.setFont(fonteCampos);

        // Botões
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnRetornar = new JButton("Retornar");

        // Linha 1: Título
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelTitulo, c);

        c.gridx = 1;
        c.gridy = 0;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoTitulo, c);

        // Linha 2: Autor
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelAutor, c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoAutor, c);

        // Linha 3: Editora
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelEditora, c);

        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoEditora, c);

        // Linha 4: Gênero
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelGenero, c);

        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoGenero, c);

        // Linha 5: ISBN
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelIsbn, c);

        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoIsbn, c);

        // Linha 6: Código
        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        formPainel.add(labelCodigo, c);

        c.gridx = 1;
        c.gridy = 5;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        formPainel.add(campoCodigo, c);

        // Linha 7: Botões
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.add(btnRegistrar);
        painelBotoes.add(btnRetornar);
        formPainel.add(painelBotoes, c);

        add(formPainel);

         // Ações dos botões
        btnRegistrar.addActionListener(e -> registrarLivro());

        btnRetornar.addActionListener(e -> retornar());
    }

    public void registrarLivro() {
        try {
            livro = new Livro();

            String titulo = campoTitulo.getText();
            String autor = campoAutor.getText();
            String editora = campoEditora.getText();
            String genero = campoGenero.getText();
            String isbn = campoIsbn.getText();
            String codigo = campoCodigo.getText();

            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setEditora(editora);
            livro.setGenero(genero);
            livro.setIsbn(isbn);
            livro.setCodigo(codigo);

            livro.validar();

            LivroDao daoL = new LivroDao();
            daoL.inserirLivro(livro);

            JOptionPane.showMessageDialog(this, "Livro registrado com sucesso!");
            new TelaLivro(funcionario).setVisible(true);
            dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de validação", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void retornar() {
        try {
            new TelaLivro(funcionario).setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dispose();
    }
}
