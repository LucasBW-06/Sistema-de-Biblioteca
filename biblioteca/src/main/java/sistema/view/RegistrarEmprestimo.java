package sistema.view;

import javax.swing.*;

import java.sql.SQLException;

public class RegistrarEmprestimo extends JFrame {
    
    public RegistrarEmprestimo() throws SQLException {
        setTitle("Sistema de Biblioteca - Registrar Livro");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
