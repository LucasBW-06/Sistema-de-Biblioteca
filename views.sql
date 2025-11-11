USE biblioteca;

CREATE VIEW v_livros_disponiveis AS
	SELECT * FROM livro l
		WHERE l.id NOT IN (
			SELECT e.livro_id FROM emprestimo e
				WHERE e.estado IN ("EMPRESTADO", "ATRASADO")
        );

CREATE VIEW v_usuarios_vencidos AS
	SELECT u.* FROM emprestimo e
        JOIN usuario u ON e.usuario_id = u.id
        WHERE e.estado = "ATRASADO";