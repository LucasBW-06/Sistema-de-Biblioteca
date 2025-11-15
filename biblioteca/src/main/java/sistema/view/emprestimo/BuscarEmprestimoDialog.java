package sistema.view.emprestimo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import sistema.model.Emprestimo;
import sistema.dao.EmprestimoDao;

public class BuscarEmprestimoDialog extends JDialog {

    private JTable tabela;
    private JTextField campoTitulo;
    private JTextField campoCodigo;
    private JTextField campoNome;
    private JTextField campoCpf;
    private Emprestimo emprestimoSelecionado;

    private List<Emprestimo> emprestimosCarregados;

    public BuscarEmprestimoDialog(Frame parent) throws SQLException {
        super(parent, "Buscar Emprestimo", true);
        setSize(500, 400);
        setLocationRelativeTo(parent);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        campoTitulo = new JTextField();
        campoCodigo = new JTextField();
        campoNome = new JTextField();
        campoCpf = new JTextField();
        JButton btnBuscar = new JButton("Buscar");

        JPanel topo = new JPanel(new GridLayout(2, 3, 5, 5));
        topo.add(new JLabel("Título do Livro:"));
        topo.add(campoTitulo);

        topo.add(new JLabel("Código do Livro:"));
        topo.add(campoCodigo);

        topo.add(new JLabel("Nome do Usuário:"));
        topo.add(campoNome);

        topo.add(new JLabel("CPF do Usuário:"));
        topo.add(campoCpf);
        topo.add(btnBuscar);

        painel.add(topo, BorderLayout.NORTH);

        tabela = new JTable(new DefaultTableModel(
            new Object[]{"Título do Livro", "Código do Livro", "Nome do Usuário", "CPF do Usuário"}, 0
        ));
        JScrollPane scroll = new JScrollPane(tabela);
        painel.add(scroll, BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        JButton btnSelecionar = new JButton("Selecionar");
        JButton btnCancelar = new JButton("Cancelar");
        botoes.add(btnSelecionar);
        botoes.add(btnCancelar);

        painel.add(botoes, BorderLayout.SOUTH);

        add(painel);

        btnBuscar.addActionListener(e -> {
            try {
                carregarEmprestimos();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });

        btnSelecionar.addActionListener(e -> selecionarRegistro());
        btnCancelar.addActionListener(e -> dispose());

        carregarEmprestimos();
    }

    private void carregarEmprestimos() throws SQLException {
        try {
            String titulo = campoTitulo.getText();
            String codigo = campoCodigo.getText();
            String nome = campoNome.getText();
            String cpf = campoCpf.getText();

            emprestimosCarregados = new EmprestimoDao().getListaEmprestimoPendente(titulo, codigo, nome, cpf);

            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
            model.setRowCount(0);

            for (Emprestimo e : emprestimosCarregados) {
                model.addRow(new Object[]{
                    e.getLivro().getTitulo(),
                    e.getLivro().getCodigo(),
                    e.getUsuario().getNome(),
                    e.getUsuario().getCpf()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void selecionarRegistro() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um empréstimo na tabela.");
            return;
        }

        emprestimoSelecionado = emprestimosCarregados.get(linha);

        dispose();
    }

    public Emprestimo getEmprestimoSelecionado() {
        return emprestimoSelecionado;
    }
}
