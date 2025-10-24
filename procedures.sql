USE biblioteca;

DELIMITER $$
CREATE PROCEDURE p_registrar_emprestimo(IN p_livro_id INT, IN p_usuario_id INT)
	BEGIN
		INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_devolucao) VALUES
        (p_livro_id, p_usuario_id, CURRENT_DATE, DATE_ADD(CURRENT_DATE, INTERVAL 10 DAY));
	END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE p_registrar_devolucao(IN p_livro_id INT)
	BEGIN
		UPDATE livro SET estado = "Disponível"
        WHERE id = p_livro_id;
	END $$
DELIMITER ;