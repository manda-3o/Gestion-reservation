-- SQL script to create the necessary tables for the transportation reservation application

CREATE TABLE VOITURE (
    idvoit INT AUTO_INCREMENT PRIMARY KEY,
    Design VARCHAR(255) NOT NULL,
    type ENUM('simple', 'premium', 'VIP') NOT NULL,
    nbrplace INT NOT NULL,
    frais DECIMAL(10, 2) NOT NULL
);

CREATE TABLE PLACE (
    idvoit INT,
    place INT NOT NULL,
    occupation ENUM('oui', 'non') NOT NULL,
    FOREIGN KEY (idvoit) REFERENCES VOITURE(idvoit)
);

CREATE TABLE CLIENT (
    idcli INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    numtel VARCHAR(15) NOT NULL
);

CREATE TABLE RESERVER (
    idreserv INT AUTO_INCREMENT PRIMARY KEY,
    idvoit INT,
    idcli INT,
    place INT NOT NULL,
    date_reserv TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_voyage DATE NOT NULL,
    payment ENUM('sans avance', 'avec avance', 'tout payé') NOT NULL,
    montant_avance DECIMAL(10, 2),
    FOREIGN KEY (idvoit) REFERENCES VOITURE(idvoit),
    FOREIGN KEY (idcli) REFERENCES CLIENT(idcli)
);