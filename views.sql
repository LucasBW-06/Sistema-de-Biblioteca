USE biblioteca;

CREATE VIEW v_livros_disponiveis AS
	SELECT * FROM livro l
		WHERE l.id NOT IN (
			SELECT e.livro_id FROM emprestimo e
				WHERE e.estado IN ("EMPRESTADO", "ATRASADO")
        ) AND l.ativo = TRUE;

CREATE VIEW v_usuarios_permitido AS
	SELECT * FROM usuario u
        WHERE u.id NOT IN (
			SELECT e.usuario_id FROM emprestimo e
				WHERE e.estado = "ATRASADO"
        ) AND u.ativo = TRUE;
        
CREATE VIEW v_usuarios_vencido AS
	SELECT u.* FROM emprestimo e
        JOIN usuario u ON e.usuario_id = u.id
        WHERE e.estado = 'ATRASADO' AND u.ativo = TRUE;

CREATE VIEW v_livro_ativo AS
	SELECT * FROM livro l
		WHERE l.ativo = TRUE;

CREATE VIEW v_usuario_ativo AS
	SELECT * FROM usuario u
		WHERE u.ativo = TRUE;

CREATE VIEW v_emprestimo_ativo AS
	SELECT * FROM emprestimo e
		WHERE e.ativo = TRUE;