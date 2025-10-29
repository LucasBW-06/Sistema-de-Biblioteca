package sistema;

import java.sql.SQLException;
import java.util.List;

import sistema.dao.UsuarioDao;
import sistema.modelo.Usuario;

public class Main {
    public static void main(String[] args) {
            try {
            UsuarioDao dao = new UsuarioDao();

            // Inserir um usuário
            Usuario novo = new Usuario();
            novo.setNome("Lucas");
            novo.setEmail("lucas@email.com");
            novo.setCpf("12345678900");
            novo.setTelefone("66999999999");
            dao.inserirUsuario(novo);
            System.out.println("Usuário inserido com sucesso!");

            // Listar todos
            List<Usuario> usuarios = dao.getListaUsuario();
            for (Usuario u : usuarios) {
                System.out.println(u.getId() + " - " + u.getNome());
            }

            // Buscar um usuário pelo ID
            if (!usuarios.isEmpty()) {
                Usuario buscado = dao.getUsuario(usuarios.get(0).getId());
                System.out.println("Encontrado: " + buscado.getNome());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}