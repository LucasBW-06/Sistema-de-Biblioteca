package sistema.view.funcionario;

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

import sistema.dao.FuncionarioDao;
import sistema.model.Funcionario;
import sistema.view.TelaBiblioteca;;

public class TelaFuncionario extends JFrame{

    private JTable tabela;
    private List<Funcionario> lista;
    private Funcionario funcionario;

    public TelaFuncionario(Funcionario f) throws SQLException {
        funcionario = f;
        setTitle("Sistema de Biblioteca - Menu de Funcionários");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnCadastrar = new JButton("Cadastrar Funcionário");
        JButton btnRetornar = new JButton("Retornar");
        JButton btnExcluir = new JButton("Excluir Selecionado");

        btnCadastrar.addActionListener(e -> cadastrarFuncionario());
        btnRetornar.addActionListener(e -> retornar());
        btnExcluir.addActionListener(e -> {
            try {
                selecionarFuncionario();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        });

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnRetornar);
        painelBotoes.add(btnExcluir);

        lista = new FuncionarioDao().getListaFuncionarioAtivo();

        String[] colunas = {"Nome", "Login", "Cargo"};
        Object[][] dados = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            dados[i][0] = lista.get(i).getNome();
            dados[i][1] = lista.get(i).getLogin();
            dados[i][2] = lista.get(i).getCargo();
        }

        tabela = new JTable(new DefaultTableModel(dados, colunas));
        JScrollPane scroll = new JScrollPane(tabela);

        painel.add(painelBotoes, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);

        add(painel);
    }

    public void cadastrarFuncionario() {
        try {
            new CadastrarFuncionario(funcionario).setVisible(true);
        } catch (SQLException | ParseException e1) {
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

    public void selecionarFuncionario() throws ParseException {
        if (tabela.getSelectedRow() != -1) {
            int linha = tabela.getSelectedRow();
            Funcionario funcionarioSelecionado = lista.get(linha);
            excluirFuncionario(funcionarioSelecionado);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um funcionário na tabela.");
        }
    }

    public void excluirFuncionario(Funcionario f) throws IllegalArgumentException, ParseException {
        try {
            if (funcionario.getId() == f.getId()) {
                throw new IllegalArgumentException("Operação negada!");
            }

            FuncionarioDao daoF = new FuncionarioDao();
            daoF.softdeleteFuncionario(f);

            JOptionPane.showMessageDialog(this, "Funcionário excluido com sucesso!");
            new TelaFuncionario(funcionario).setVisible(true);
            dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro: ", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar: " + e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
