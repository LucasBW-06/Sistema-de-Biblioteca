package sistema.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import sistema.dao.LivroDao;
import sistema.model.Livro;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TelaBiblioteca extends JFrame {
    public TelaBiblioteca() throws SQLException {
        setTitle("Sistema de Biblioteca - Menu Principal");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnCadastrarUsuario = new JButton("Cadastrar Usuário");
        JButton btnRegistrarLivro = new JButton("Registrar Livro");
        JButton btnEmprestarLivro = new JButton("Emprestar Livro");

        btnCadastrarUsuario.addActionListener(e -> {
            try {
                new CadastrarUsuario().setVisible(true);
            } catch (SQLException | ParseException e1) {
                e1.printStackTrace();
            }
            dispose();
        });

        btnRegistrarLivro.addActionListener(e -> {
            try {
                new RegistrarLivro().setVisible(true);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            dispose();
        });

        painelBotoes.add(btnCadastrarUsuario);
        painelBotoes.add(btnRegistrarLivro);
        painelBotoes.add(btnEmprestarLivro);

        LivroDao daoL = new LivroDao();
        List<Livro> listaLivros = new ArrayList<>();
        listaLivros = daoL.getListaLivro();

        String[] colunas = {"Título do Livro", "Autor", "Editora"};
        Object[][] dados = new Object[listaLivros.size()][3];
        for (int i = 0; i < listaLivros.size(); i++) {
            dados[i][0] = listaLivros.get(i).getTitulo();
            dados[i][1] = listaLivros.get(i).getAutor();
            dados[i][2] = listaLivros.get(i).getEditora();
        }

        JTable tabela = new JTable(new DefaultTableModel(dados, colunas));
        JScrollPane scroll = new JScrollPane(tabela);

        painel.add(painelBotoes, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);

        add(painel);
    }
}
