USE biblioteca;

DELIMITER $$
CREATE TRIGGER trg_atualiza_estado
	AFTER INSERT ON emprestimo
    FOR EACH ROW
	BEGIN
		CALL p_atualiza_estado();
	END$$
DELIMITER ;