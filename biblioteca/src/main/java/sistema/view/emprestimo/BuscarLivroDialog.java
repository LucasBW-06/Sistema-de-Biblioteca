package sistema.view.emprestimo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import sistema.model.Livro;
import sistema.dao.LivroDao;

public class BuscarLivroDialog extends JDialog {

    private JTable tabela;
    private JTextField campoTitulo;
    private JTextField campoCodigo;
    private Livro livroSelecionado;

    private List<Livro> lista;

    public BuscarLivroDialog(Frame parent) throws SQLException {
        super(parent, "Buscar Livro", true);
        setSize(500, 400);
        setLocationRelativeTo(parent);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        campoTitulo = new JTextField();
        campoCodigo = new JTextField();
        JButton btnBuscar = new JButton("Buscar");

        JPanel topo = new JPanel(new GridLayout(2, 3, 5, 5));
        topo.add(new JLabel("Título:"));
        topo.add(campoTitulo);
        topo.add(new JLabel());

        topo.add(new JLabel("Código:"));
        topo.add(campoCodigo);
        topo.add(btnBuscar);

        painel.add(topo, BorderLayout.NORTH);

        tabela = new JTable(new DefaultTableModel(
            new Object[]{"Título", "Autor", "Código"}, 0
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
                carregarLivros();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        btnSelecionar.addActionListener(e -> {
            selecionarLivro();
        });
        btnCancelar.addActionListener(e -> dispose());

        carregarLivros();
    }

    private void carregarLivros() throws SQLException {
        try {
            String titulo = campoTitulo.getText();
            String codigo = campoCodigo.getText();
            lista = new LivroDao().getListaLivroDisponiveis(titulo, codigo);

            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
            model.setRowCount(0);

            for (Livro l : lista) {
                model.addRow(new Object[]{
                    l.getTitulo(),
                    l.getAutor(),
                    l.getCodigo()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void selecionarLivro() {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um livro na tabela.");
        } else {
            livroSelecionado = lista.get(linha);
            dispose();
        }
    }

    public Livro getLivroSelecionado() {
        return livroSelecionado;
    }
}
