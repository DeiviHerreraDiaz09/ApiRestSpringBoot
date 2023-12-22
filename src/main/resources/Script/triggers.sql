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
