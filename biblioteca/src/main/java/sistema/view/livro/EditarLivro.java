package sistema.view.livro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import sistema.dao.LivroDao;
import sistema.model.Funcionario;
import sistema.model.Livro;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

public class EditarLivro extends JFrame {
    
    private JTextField campoTitulo;
    private JTextField campoAutor;
    private JTextField campoEditora;
    private JTextField campoGenero;
    private JTextField campoIsbn;
    private JTextField campoCodigo;
    
    private Livro livro;
    private Funcionario funcionario;
    
    public EditarLivro(Livro l, Funcionario f) throws SQLException, ParseException {
        livro = l;
        funcionario = f;
        setTitle("Sistema de Biblioteca - " + livro.getTitulo());
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
        campoTitulo = new JTextField(livro.getTitulo());
        campoTitulo.setFont(fonteCampos);

        JLabel labelAutor = new JLabel("Autor:");
        labelAutor.setFont(fonteLabels);
        campoAutor = new JTextField(livro.getAutor());
        campoAutor.setFont(fonteCampos);

        JLabel labelEditora = new JLabel("Editora:");
        labelEditora.setFont(fonteLabels);
        campoEditora = new JTextField(livro.getEditora());
        campoEditora.setFont(fonteCampos);

        JLabel labelGenero = new JLabel("Gênero:");
        labelGenero.setFont(fonteLabels);
        campoGenero = new JTextField(livro.getGenero());
        campoGenero.setFont(fonteCampos);

        JLabel labelIsbn = new JLabel("ISBN:");
        labelIsbn.setFont(fonteLabels);
        campoIsbn = new JTextField(livro.getIsbn());
        campoIsbn.setFont(fonteCampos);

        JLabel labelCodigo = new JLabel("Código:");
        labelCodigo.setFont(fonteLabels);
        campoCodigo = new JTextField(livro.getCodigo());
        campoCodigo.setFont(fonteCampos);

        JButton btnSalvar = new JButton("Salvar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnCancelar = new JButton("Cancelar");

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

        // Linha 2: Autor
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelAutor, c);

        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoAutor, c);

        // Linha 3: Editora
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelEditora, c);

        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoEditora, c);

        // Linha 4: Gênero
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelGenero, c);

        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoGenero, c);

        // Linha 5: ISBN
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelIsbn, c);

        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoIsbn, c);

        // Linha 6: Código
        c.gridx = 0;
        c.gridy = 5;
        c.weightx = 0;
        c.anchor = GridBagConstraints.EAST;
        painel.add(labelCodigo, c);

        c.gridx = 1;
        c.gridy = 5;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        painel.add(campoCodigo, c);

        // Linha 7: Botões
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnCancelar);
        painel.add(painelBotoes, c);

        add(painel);

        btnSalvar.addActionListener(e -> { salvarEdicao(); });

        btnExcluir.addActionListener(e -> { excluirLivro(); });

        btnCancelar.addActionListener(e -> { try {
            cancelar();
        } catch (ParseException e1) {
            e1.printStackTrace();
        } });
    }

    public void salvarEdicao() {
        try {
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
            daoL.modificarLivro(livro);

            JOptionPane.showMessageDialog(this, "Edição realizada com sucesso!");
            new TelaLivro(funcionario).setVisible(true);
            dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de validação", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao registrar: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void excluirLivro() {
        try {
            LivroDao daoL = new LivroDao();
            daoL.softdeleteLivro(livro);

            JOptionPane.showMessageDialog(this, "Livro excluido com sucesso!");
            new TelaLivro(funcionario).setVisible(true);
            dispose();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public void cancelar() throws ParseException {
        try {
            new VisualizarLivro(livro, funcionario).setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }
}
