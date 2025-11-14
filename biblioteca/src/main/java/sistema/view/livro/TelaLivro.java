package sistema.view.livro;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import sistema.dao.LivroDao;
import sistema.model.Livro;
import sistema.view.TelaBiblioteca;

public class TelaLivro extends JFrame{

    private JTable tabela;
    private List<Livro> lista;

    public TelaLivro() throws SQLException {
        setTitle("Sistema de Biblioteca - Acervo");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnRetornar = new JButton("Retornar");
        JButton btnSelecionar = new JButton("Selecionar");

        btnRegistrar.addActionListener(e -> registrarLivro());
        btnRetornar.addActionListener(e -> retornar());
        btnSelecionar.addActionListener(e -> {
            try {
                selecionarLivro();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        });

        painelBotoes.add(btnRegistrar);
        painelBotoes.add(btnRetornar);
        painelBotoes.add(btnSelecionar);

        lista = new LivroDao().getListaLivroAtivo();

        String[] colunas = {"Título", "Autor", "Código"};
        Object[][] dados = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            dados[i][0] = lista.get(i).getTitulo();
            dados[i][1] = lista.get(i).getAutor();
            dados[i][2] = lista.get(i).getCodigo();
        }

        tabela = new JTable(new DefaultTableModel(dados, colunas));
        JScrollPane scroll = new JScrollPane(tabela);

        painel.add(painelBotoes, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);

        add(painel);
    }

    public void registrarLivro() {
        try {
            new RegistrarLivro().setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }

    public void retornar() {
        try {
            new TelaBiblioteca().setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dispose();
    }

    public void selecionarLivro() throws ParseException {
        if (tabela.getSelectedRow() != -1) {
            int linha = tabela.getSelectedRow();
            Livro livroSelecionado = lista.get(linha);
            visualizarLivro(livroSelecionado);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um livro na tabela.");
        }
    }

    public void visualizarLivro(Livro l) throws ParseException {
        try {
            new VisualizarLivro(l).setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }
}
