USE biblioteca;

CREATE VIEW v_livros_disponiveis AS
	SELECT l.titulo, l.autor, l.editora FROM livro l
		 WHERE l.estado = "Disponível";
         
CREATE VIEW v_historico_usuario AS
	SELECT u.nome, l.titulo, e.data_emprestimo FROM emprestimo e
		JOIN livro l ON e.livro_id = l.id
        JOIN usuario u ON e.usuario_id = u.id
        WHERE u.id = 1;

CREATE VIEW v_emprestimos_vencidos AS
	SELECT u.nome, l.titulo, e.data_devolucao FROM emprestimos e
		JOIN livro l ON e.livro_id = l.id
        JOIN usuario u ON e.usuario_id = u.id
        WHERE e.data_devolucao < CURRENT_DATE;