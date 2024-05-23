ALTER TABLE videos
    ADD COLUMN categoria_id INT,
ADD CONSTRAINT fk_categoria_id FOREIGN KEY (categoria_id) REFERENCES categorias(id);
