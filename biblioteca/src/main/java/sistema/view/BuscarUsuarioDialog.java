package sistema.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import sistema.model.Usuario;
import sistema.dao.UsuarioDao;

public class BuscarUsuarioDialog extends Dialog {
    
    private JTable tabela;
    private JTextField campoNome;
    private JFormattedTextField campoCpf;
    private Usuario usuarioSelecionado;

    public BuscarUsuarioDialog(Frame parent) throws SQLException, ParseException {
        super(parent, "Buscar Usuário", true);
        setSize(500, 400);
        setLocationRelativeTo(parent);

        JPanel painel = new JPanel(new BorderLayout(10, 10));

        campoNome = new JTextField();
        MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
        cpfMask.setPlaceholderCharacter('_');
        campoCpf = new JFormattedTextField(cpfMask);
        JButton btnBuscar = new JButton("Buscar");

        JPanel topo = new JPanel(new GridLayout(2, 3, 5, 5));
        topo.add(new JLabel("Nome:"));
        topo.add(campoNome);
        topo.add(new JLabel());

        topo.add(new JLabel("CPF:"));
        topo.add(campoCpf);
        topo.add(btnBuscar);

        painel.add(topo, BorderLayout.NORTH);

        tabela = new JTable(new DefaultTableModel(
            new Object[]{"ID", "Nome", "CPF"}, 0
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
                carregarUsuarios();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        btnSelecionar.addActionListener(e -> {
            try {
                selecionarUsuario();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        btnCancelar.addActionListener(e -> dispose());

        carregarUsuarios();
    }

    private void carregarUsuarios() throws SQLException {
        try {
            String nome = campoNome.getText();
            String cpf = campoCpf.getText().replaceAll("[\\.\\-]", "");
            List<Usuario> usuarios = new UsuarioDao().getListaUsuarioVencido(nome, cpf);

            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
            model.setRowCount(0);

            for (Usuario u : usuarios) {
                model.addRow(new Object[]{
                    u.getId(),
                    u.getNome(),
                    u.getCpf()
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void selecionarUsuario() throws SQLException {
        int linha = tabela.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário na tabela.");
        } else {
            long id = (long) tabela.getValueAt(linha, 0);
            usuarioSelecionado = new UsuarioDao().getUsuario(id);

            dispose();
        }
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }
}
