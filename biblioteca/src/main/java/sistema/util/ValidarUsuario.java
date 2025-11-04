package sistema.util;

import sistema.model.Usuario;

public class ValidarUsuario {
    public static void validar(Usuario u) throws IllegalArgumentException {
        if (u.getNome() == null || u.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }
        if (u.getCpf() == null || u.getCpf().length() != 11 || !u.getCpf().matches("\\d+")) {
            throw new IllegalArgumentException("CPF inválido!");
        } else {
            String[] invalidos = {
                "00000000000", "11111111111", "22222222222", "33333333333",
                "44444444444", "55555555555", "66666666666", "77777777777",
                "88888888888", "99999999999"
            };

            String cpf = u.getCpf();

            Boolean validade = true;
            for (String inv : invalidos) {
                if (cpf.equals(inv)) {
                    validade = false;
                }
            }
            if (!validade) {
                throw new IllegalArgumentException("CPF inválido!");
            } else {
                int soma = 0;
                for (int i = 0; i < 9; i++) {
                    soma += ((10 - i) * Character.getNumericValue(cpf.charAt(i)));
                }
                int digito1 = soma % 11 < 2 ? 0 : 11 - (soma % 11);

                soma = 0;
                for (int i = 0; i < 10; i++) {
                    soma += ((11 - i) * Character.getNumericValue(cpf.charAt(i)));
                }
                int digito2 = 11 - (soma % 11);

                if (Character.getNumericValue(cpf.charAt(9)) != digito1 || Character.getNumericValue(cpf.charAt(10)) != digito2) {
                    throw new IllegalArgumentException("CPF inválido!");
                }
            }
        }
        if (u.getEmail() == null || !u.getEmail().contains("@") || u.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email inválido!");
        }
        if (u.getTelefone() == null || u.getTelefone().length() != 11 || !u.getTelefone().matches("\\d+")) {
            throw new IllegalArgumentException("Telefone inválido!");
        }
    }
}
