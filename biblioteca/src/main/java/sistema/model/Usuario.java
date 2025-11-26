package sistema.model;

public class Usuario {
    protected long id;
    protected String nome;
    protected String email;
    protected String cpf;
    protected String telefone;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void validar() throws IllegalArgumentException {
        if (this.getNome() == null || this.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido!");
        }
        if (this.getCpf() == null || this.getCpf().length() != 11 || !this.getCpf().matches("\\d+")) {
            throw new IllegalArgumentException("CPF inválido!");
        } else {
            String[] invalidos = {
                "00000000000", "11111111111", "22222222222", "33333333333",
                "44444444444", "55555555555", "66666666666", "77777777777",
                "88888888888", "99999999999"
            };

            String cpf = this.getCpf();

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
        if (this.getEmail() == null || !this.getEmail().contains("@") || this.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email inválido!");
        }
        if (this.getTelefone() == null || this.getTelefone().length() != 11 || !this.getTelefone().matches("\\d+")) {
            throw new IllegalArgumentException("Telefone inválido!");
        }
    }
}
