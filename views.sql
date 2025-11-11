USE biblioteca;

CREATE VIEW v_livros_disponiveis AS
	SELECT * FROM livro l
		WHERE l.id NOT IN (
			SELECT e.livro_id FROM emprestimo e
				WHERE e.estado IN ("EMPRESTADO", "ATRASADO")
        );
         
CREATE VIEW v_historico_usuario AS
	SELECT u.id, u.nome, l.titulo, e.data_emprestimo FROM emprestimo e
		JOIN livro l ON e.livro_id = l.id
        JOIN usuario u ON e.usuario_id = u.id;

CREATE VIEW v_emprestimos_vencidos AS
	SELECT u.* FROM emprestimo e
        JOIN usuario u ON e.usuario_id = u.id
        WHERE e.estado = "ATRASADO";