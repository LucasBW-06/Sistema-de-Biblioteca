package sistema.view.usuario;

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

import sistema.dao.UsuarioDao;
import sistema.model.Funcionario;
import sistema.model.Usuario;
import sistema.view.TelaBiblioteca;

public class TelaUsuario extends JFrame{

    private Funcionario funcionario;

    private JTable tabela;
    private List<Usuario> lista;

    public TelaUsuario(Funcionario f) throws SQLException {
        funcionario = f;
        setTitle("Sistema de Biblioteca - Menu de Usuários");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnRetornar = new JButton("Retornar");
        JButton btnSelecionar = new JButton("Selecionar");

        btnCadastrar.addActionListener(e -> cadastrarUsuario());
        btnRetornar.addActionListener(e -> retornar());
        btnSelecionar.addActionListener(e -> {
            try {
                selecionarUsuario();
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        });

        painelBotoes.add(btnCadastrar);
        painelBotoes.add(btnRetornar);
        painelBotoes.add(btnSelecionar);

        lista = new UsuarioDao().getListaUsuarioAtivo();

        String[] colunas = {"Nome", "Email", "CPF"};
        Object[][] dados = new Object[lista.size()][4];
        for (int i = 0; i < lista.size(); i++) {
            dados[i][0] = lista.get(i).getNome();
            dados[i][1] = lista.get(i).getEmail();
            dados[i][2] = lista.get(i).getCpf();
        }

        tabela = new JTable(new DefaultTableModel(dados, colunas));
        JScrollPane scroll = new JScrollPane(tabela);

        painel.add(painelBotoes, BorderLayout.NORTH);
        painel.add(scroll, BorderLayout.CENTER);

        add(painel);
    }

    public void cadastrarUsuario() {
        try {
            new CadastrarUsuario(funcionario).setVisible(true);
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

    public void selecionarUsuario() throws ParseException {
        if (tabela.getSelectedRow() != -1) {
            int linha = tabela.getSelectedRow();
            Usuario usuarioSelecionado = lista.get(linha);
            visualizarUsuario(usuarioSelecionado);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário na tabela.");
        }
    }

    public void visualizarUsuario(Usuario u) throws ParseException {
        try {
            new VisualizarUsuario(u, funcionario).setVisible(true);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        dispose();
    }
}
