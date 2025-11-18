USE biblioteca;

DELIMITER $$

CREATE TRIGGER trg_antes_emprestimo
BEFORE INSERT ON emprestimo
FOR EACH ROW
BEGIN
    DECLARE v_existe_emprestimo INT;

    SELECT COUNT(*) INTO v_existe_emprestimo FROM emprestimo
		WHERE livro_id = NEW.livro_id AND estado = 'EMPRESTADO' AND ativo = TRUE;

    IF v_existe_emprestimo > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'ERRO: O livro já está emprestado.';
    END IF;
END $$

DELIMITER ;
