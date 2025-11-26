USE biblioteca;

DELIMITER $$
CREATE TRIGGER trg_antes_emprestimo
BEFORE INSERT ON emprestimo
FOR EACH ROW
BEGIN
    DECLARE v_existe_emprestimo INT;

    SELECT COUNT(*) INTO v_existe_emprestimo FROM emprestimo
		WHERE livro_id = NEW.livro_id AND estado != 'DEVOLVIDO' AND ativo = TRUE;

    IF v_existe_emprestimo > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'ERRO: O livro já está emprestado.';
    END IF;
END $$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER trg_auditoria_insercao_emprestimo
AFTER INSERT ON emprestimo
FOR EACH ROW
BEGIN
    INSERT INTO auditoria_emprestimos 
        (emprestimo_id, operacao, realizado_por)
    VALUES
        (NEW.id, 'INSERÇÃO', CURRENT_USER());
END $$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER trg_auditoria_update_emprestimo
AFTER UPDATE ON emprestimo
FOR EACH ROW
BEGIN
    INSERT INTO auditoria_emprestimos 
        (emprestimo_id, operacao, realizado_por)
    VALUES
        (NEW.id, 'ATUALIZAÇÃO', CURRENT_USER());
END $$
DELIMITER ;