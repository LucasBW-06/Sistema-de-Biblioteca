package sistema;

import java.sql.SQLException;
import javax.swing.SwingUtilities;
import sistema.view.Inicio;

public class Main {
    public static void main(String[] args) {
            
        SwingUtilities.invokeLater(() -> {
            try {
                new Inicio().setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}