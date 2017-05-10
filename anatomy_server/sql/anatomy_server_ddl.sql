-- DROP DATABASE anatomy_server;
CREATE DATABASE IF NOT EXISTS anatomy_server;
USE anatomy_server;

CREATE TABLE IF NOT EXISTS model (
  model_id INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (model_id)
);

CREATE TABLE IF NOT EXISTS anatom (
  anatom_id INT NOT NULL AUTO_INCREMENT,
  model_id INT NOT NULL,
  PRIMARY KEY (anatom_id),
  FOREIGN KEY (model_id) REFERENCES model(model_id)
);

CREATE TABLE IF NOT EXISTS relation (
	relation_id INT NOT NULL AUTO_INCREMENT,
    from_anatom INT NOT NULL,
    to_anatom INT NOT NULL,
    PRIMARY KEY (relation_id),
	FOREIGN KEY (from_anatom) REFERENCES anatom(anatom_id),
    FOREIGN KEY (to_anatom) REFERENCES anatom(anatom_id)
);

CREATE TABLE IF NOT EXISTS attribute (
	attribute_id INT NOT NULL AUTO_INCREMENT,
    attribute_value INT,
    anatom_id INT,
    relation_id INT,
    PRIMARY KEY (attribute_id),
	CONSTRAINT UNIQUE (anatom_id, relation_id),
	CONSTRAINT CHECK (anatom_id IS NOT NULL OR relation_id IS NOT NULL),
	FOREIGN KEY (anatom_id) REFERENCES anatom(anatom_id),
	FOREIGN KEY (relation_id) REFERENCES relation(relation_id)
);