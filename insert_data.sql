USE cooperative_db;

INSERT INTO VOITURE (idvoit, Design, type, nbrplace, frais) VALUES ('V001', 'Mercedes Classe C', 'premium', 20, 300);
INSERT INTO VOITURE (idvoit, Design, type, nbrplace, frais) VALUES ('V002', 'Toyota Corolla', 'simple', 15, 180);
INSERT INTO VOITURE (idvoit, Design, type, nbrplace, frais) VALUES ('V003', 'BMW Série 5', 'VIP', 12, 500);

-- générer 15-20 places par voiture
INSERT INTO PLACE (idvoit, place, occupation)
VALUES
('V001', 1, 'non'),('V001', 2, 'non'),('V001', 3, 'non'),('V001', 4, 'non'),('V001', 5, 'non'),
('V001', 6, 'non'),('V001', 7, 'non'),('V001', 8, 'non'),('V001', 9, 'non'),('V001', 10, 'non'),
('V001', 11, 'non'),('V001', 12, 'non'),('V001', 13, 'non'),('V001', 14, 'non'),('V001', 15, 'non'),
('V002', 1, 'non'),('V002', 2, 'non'),('V002', 3, 'non'),('V002', 4, 'non'),('V002', 5, 'non'),
('V002', 6, 'non'),('V002', 7, 'non'),('V002', 8, 'non'),('V002', 9, 'non'),('V002', 10, 'non'),
('V002', 11, 'non'),('V002', 12, 'non'),('V002', 13, 'non'),('V002', 14, 'non'),('V002', 15, 'non'),
('V003', 1, 'non'),('V003', 2, 'non'),('V003', 3, 'non'),('V003', 4, 'non'),('V003', 5, 'non'),
('V003', 6, 'non'),('V003', 7, 'non'),('V003', 8, 'non'),('V003', 9, 'non'),('V003', 10, 'non'),
('V003', 11, 'non'),('V003', 12, 'non');
