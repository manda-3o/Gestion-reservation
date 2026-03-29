USE cooperative_db;

TRUNCATE TABLE RESERVER;
TRUNCATE TABLE PLACE;
TRUNCATE TABLE VOITURE;
TRUNCATE TABLE CLIENT;

INSERT INTO VOITURE (idvoit, Design, type, nbrplace, frais) VALUES ('V1', 'Renault Clio', 'simple', 15, 150);
INSERT INTO VOITURE (idvoit, Design, type, nbrplace, frais) VALUES ('V2', 'Peugeot 308', 'premium', 18, 250);
INSERT INTO VOITURE (idvoit, Design, type, nbrplace, frais) VALUES ('V3', 'Audi A6', 'VIP', 12, 450);

INSERT INTO PLACE (idvoit, place, occupation) VALUES
('V1', 1, 'non'),('V1', 2, 'non'),('V1', 3, 'non'),('V1', 4, 'non'),('V1', 5, 'non'),
('V1', 6, 'non'),('V1', 7, 'non'),('V1', 8, 'non'),('V1', 9, 'non'),('V1', 10, 'non'),
('V1', 11, 'non'),('V1', 12, 'non'),('V1', 13, 'non'),('V1', 14, 'non'),('V1', 15, 'non');

INSERT INTO PLACE (idvoit, place, occupation) VALUES
('V2', 1, 'non'),('V2', 2, 'non'),('V2', 3, 'non'),('V2', 4, 'non'),('V2', 5, 'non'),
('V2', 6, 'non'),('V2', 7, 'non'),('V2', 8, 'non'),('V2', 9, 'non'),('V2', 10, 'non'),
('V2', 11, 'non'),('V2', 12, 'non'),('V2', 13, 'non'),('V2', 14, 'non'),('V2', 15, 'non');

INSERT INTO PLACE (idvoit, place, occupation) VALUES
('V3', 1, 'non'),('V3', 2, 'non'),('V3', 3, 'non'),('V3', 4, 'non'),('V3', 5, 'non'),
('V3', 6, 'non'),('V3', 7, 'non'),('V3', 8, 'non'),('V3', 9, 'non'),('V3', 10, 'non'),
('V3', 11, 'non'),('V3', 12, 'non');
