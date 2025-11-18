USE biblioteca;

INSERT INTO livro (titulo, autor, editora, genero, ano, isbn, codigo) VALUES ("Calculo Aplicado", "Ron Larson", "CENGAGE LEARNING LV", "Matematica", "2016", "9788522125074", "LIV001");
INSERT INTO usuario (nome, email, cpf, telefone) VALUES ("Cleito", "cleitin@gmail.com", "15551625095", "55012345678");
INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_devolucao) VALUES (1, 1, "2025-11-18", "2025-11-20");
INSERT INTO funcionario (nome, cargo, login, senha) VALUES ("Lucas", "Biblioteca", "lucas", "senha");