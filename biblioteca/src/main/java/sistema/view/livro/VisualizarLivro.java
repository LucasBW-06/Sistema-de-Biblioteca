package sistema.view.livro;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sistema.model.Funcionario;
import sistema.model.Livro;

public class VisualizarLivro extends JFrame {
    
    private Livro livro;
    private Funcionario funcionario;
    
    public VisualizarLivro(Livro l, Funcionario f) throws SQLException, ParseException {
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
        JTextField campoTitulo = new JTextField(livro.getTitulo());
        campoTitulo.setEditable(false);
        campoTitulo.setFont(fonteCampos);

        JLabel labelAutor = new JLabel("Autor:");
        labelAutor.setFont(fonteLabels);
        JTextField campoAutor = new JTextField(livro.getAutor());
        campoAutor.setEditable(false);
        campoAutor.setFont(fonteCampos);

        JLabel labelEditora = new JLabel("Editora:");
        labelEditora.setFont(fonteLabels);
        JTextField campoEditora = new JTextField(livro.getEditora());
        campoEditora.setEditable(false);
        campoEditora.setFont(fonteCampos);

        JLabel labelGenero = new JLabel("Gênero:");
        labelGenero.setFont(fonteLabels);
        JTextField campoGenero = new JTextField(livro.getGenero());
        campoGenero.setEditable(false);
        campoGenero.setFont(fonteCampos);

        JLabel labelIsbn = new JLabel("ISBN:");
        labelIsbn.setFont(fonteLabels);
        JTextField campoIsbn = new JTextField(livro.getIsbn());
        campoIsbn.setEditable(false);
        campoIsbn.setFont(fonteCampos);

        JLabel labelCodigo = new JLabel("Código:");
        labelCodigo.setFont(fonteLabels);
        JTextField campoCodigo = new JTextField(livro.getCodigo());
        campoCodigo.setEditable(false);
        campoCodigo.setFont(fonteCampos);

        JButton btnEditar = new JButton("Editar");
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
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnVoltar);
        painel.add(painelBotoes, c);

        add(painel);

        btnEditar.addActionListener(e -> { try {
            editarLivro();
        } catch (ParseException e1) {
            e1.printStackTrace();
        } });

        btnVoltar.addActionListener(e -> { voltar(); });
    }

    public void editarLivro() throws ParseException {
        try {
            new EditarLivro(livro, funcionario).setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dispose();
    }

    public void voltar() {
        try {
            new TelaLivro(funcionario).setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dispose();
    }
}
