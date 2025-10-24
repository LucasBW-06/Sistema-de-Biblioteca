USE biblioteca;

DELIMITER $$
CREATE TRIGGER trg_atualiza_estado
	AFTER INSERT ON emprestimo
    FOR EACH ROW
	BEGIN
		UPDATE livro l
        SET l.estado = "Emprestado"
        WHERE l.id = NEW.livro_id;
	END$$
DELIMITER ;