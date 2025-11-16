USE biblioteca;

DELIMITER $$
CREATE PROCEDURE p_registrar_emprestimo(
		IN p_livro_id INT, 
        IN p_usuario_id INT,
        IN p_data_emprestimo DATE, 
        IN p_data_devolucao DATE
	)
	BEGIN
        DECLARE v_existe_emprestimo INT;
        
		IF p_data_emprestimo IS NULL THEN
			SET p_data_emprestimo = CURRENT_DATE;
		END IF;
        IF p_data_devolucao IS NULL THEN
			SET p_data_devolucao = DATE_ADD(p_data_emprestimo, INTERVAL 10 DAY);
		END IF;
        
		IF p_data_devolucao < p_data_emprestimo OR p_data_devolucao = p_data_emprestimo THEN
			SIGNAL SQLSTATE "45000"
            SET MESSAGE_TEXT = "ERRO: data de emprestimo e data de devolução com valores não permitidos";
		END IF;
        
        SELECT COUNT(*) INTO v_existe_emprestimo FROM emprestimo
			WHERE livro_id = p_livro_id AND estado = "EMPRESTADO";
		
        IF v_existe_emprestimo > 0 THEN
			SIGNAL SQLSTATE "45000"
			SET MESSAGE_TEXT = "ERRO: Livro já está emprestado";
		END IF;
		
		INSERT INTO emprestimo (livro_id, usuario_id, data_emprestimo, data_devolucao) VALUES
        (p_livro_id, p_usuario_id, p_data_emprestimo, p_data_devolucao);
	END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE p_registrar_devolucao(
		IN p_emprestimo_id INT,
        IN p_data_devolvido DATE
	)
	BEGIN
		IF p_data_devolvido IS NULL THEN
			SET p_data_devolvido = CURRENT_DATE;
		END IF;
    
		UPDATE emprestimo SET estado = "DEVOLVIDO", data_devolvido = p_data_devolvido
			WHERE id = p_emprestimo_id;
	END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE p_softdelete_livro(
		IN p_livro_id INT
	)
	BEGIN
		UPDATE livro SET ativo = FALSE
			WHERE id = p_livro_id;
	END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE p_softdelete_usuario(
		IN p_usuario_id INT
	)
	BEGIN
		UPDATE usuario SET ativo = FALSE
			WHERE id = p_usuario_id;
	END $$
DELIMITER ;

DELIMITER $$

CREATE PROCEDURE p_softdelete_emprestimo(
		IN p_emprestimo_id INT
	)
	BEGIN
		UPDATE emprestimo SET ativo = FALSE
			WHERE id = p_emprestimo_id;
	END $$
DELIMITER ;

DELIMITER $$

CREATE PROCEDURE p_softdelete_funcionario(
		IN p_funcionario_id INT
	)
	BEGIN
		UPDATE funcionario SET ativo = FALSE
			WHERE id = p_funcionario_id;
	END $$
DELIMITER ;