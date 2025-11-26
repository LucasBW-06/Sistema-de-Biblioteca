package sistema.view.emprestimo;

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

import sistema.dao.EmprestimoDao;
import sistema.model.Emprestimo;
import sistema.model.Funcionario;
import sistema.view.TelaBiblioteca;

public class TelaEmprestimo extends JFrame{

    private Funcionario funcionario;

    private JTable tabela;
    private List<Emprestimo> lista;

    public TelaEmprestimo(Funcionario f) throws SQLException {
        funcionario = f;
        setTitle("Sistema de Biblioteca - Empréstimos");
        setSize(700, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnEmprestar = new JButton("Registrar Emprestimo");
        JButton btnDevolver = new JButton("Registrar Devolução");
        JButton btnRetornar = new JButton("Retornar");
        JButton btnSelecionar = new JButton("Selecionar");

        btnEmprestar.addActionListener(e -> emprestar());
        btnDevolver.addActionListener(e -> devolver());
        btnRetornar.addActionListener(e -> retornar());
        btnSelecionar.addActionListener(e -> {
            try {
                selecionarRegistro();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        });

        painelBotoes.add(btnEmprestar);
        painelBotoes.add(btnDevolver);
        painelBotoes.add(btnRetornar);
        painelBotoes.add(btnSelecionar);

        lista = new EmprestimoDao().getListaEmprestimo();

        String[] colunas = {"Usuário", "Livro", "Data de Empréstimo", "Estado"};
        Object[][] dados = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            dados[i][0] = lista.get(i).getUsuario().getNome();
            dados[i][1] = lista.get(i).getLivro().getTitulo();
            dados[i][2] = lista.get(i).getDataEmprestimo();
            dados[i][3] = lista.get(i).getEstado();
        }

        tabela = new JTable(new DefaultTableModel(dados, colunas));
        JScrollPane scroll = new JScrollPane(tabela);

        painel.add(painelBotoes, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);

        add(painel);
    }

    public void emprestar() {
        try {
            new RegistrarEmprestimo(funcionario).setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }

    public void devolver() {
        try {
            new RegistrarDevolucao(funcionario).setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }

    public void retornar() {
        try {
            new TelaBiblioteca(funcionario).setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        dispose();
    }

    public void selecionarRegistro() throws ParseException {
        if (tabela.getSelectedRow() != -1) {
            int linha = tabela.getSelectedRow();
            Emprestimo emprestimoSelecionado = lista.get(linha);
            visualizarEmprestimo(emprestimoSelecionado);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um registro na tabela.");
        }
    }

    public void visualizarEmprestimo(Emprestimo e) throws ParseException {
        try {
            new VisualizarEmprestimo(e, funcionario).setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }
}
