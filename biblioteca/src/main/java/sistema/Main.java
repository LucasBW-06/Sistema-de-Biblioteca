package sistema;

import javax.swing.SwingUtilities;
import sistema.view.Inicio;

public class Main {
    public static void main(String[] args) {
            
        SwingUtilities.invokeLater(() -> {
            new Inicio().setVisible(true);
        });
    }
}