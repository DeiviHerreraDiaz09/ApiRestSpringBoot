-- Trigger decremental

DELIMITER $$

CREATE TRIGGER after_insert_detail_manga
AFTER INSERT ON detail_manga
FOR EACH ROW
BEGIN
   UPDATE manga
   SET amount = amount - 1
   WHERE id_manga = NEW.id_mangafk;

END;

$$

DELIMITER ; 


-- Trigger incremental

DELIMITER $$

CREATE TRIGGER after_update_detail_manga
AFTER UPDATE ON detail_manga
FOR EACH ROW
BEGIN
   IF NEW.fecha_devolucion IS NOT NULL THEN
      UPDATE manga
      SET amount = amount + 1
      WHERE id_manga = NEW.id_mangafk;
   END IF;
END;

$$

DELIMITER ; 

drop trigger after_update_detail_manga;

show triggers;









