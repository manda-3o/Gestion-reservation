-- schema.sql
CREATE DATABASE IF NOT EXISTS cooperative_db;
USE cooperative_db;

CREATE TABLE IF NOT EXISTS VOITURE (
    idvoit VARCHAR(50) NOT NULL,
    Design VARCHAR(255) NOT NULL,
    type ENUM('simple', 'premium', 'VIP') NOT NULL,
    nbrplace INT NOT NULL,
    frais INT NOT NULL,
    PRIMARY KEY (idvoit)
);

CREATE TABLE IF NOT EXISTS CLIENT (
    idcli INT NOT NULL AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    numtel VARCHAR(50) NOT NULL,
    PRIMARY KEY (idcli)
);

CREATE TABLE IF NOT EXISTS PLACE (
    idvoit VARCHAR(50) NOT NULL,
    place INT NOT NULL,
    occupation ENUM('oui', 'non') NOT NULL DEFAULT 'non',
    PRIMARY KEY (idvoit, place),
    CONSTRAINT fk_place_voiture FOREIGN KEY (idvoit) REFERENCES VOITURE(idvoit) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS RESERVER (
    idreserv VARCHAR(50) NOT NULL,
    idvoit VARCHAR(50) NOT NULL,
    idcli INT NOT NULL,
    place INT NOT NULL,
    date_reserv DATETIME NOT NULL,
    date_voyage DATE NOT NULL,
    payment ENUM('sans avance', 'avec avance', 'tout payé') NOT NULL,
    montant_avance INT NOT NULL DEFAULT 0,
    PRIMARY KEY (idreserv),
    CONSTRAINT fk_reserver_voiture FOREIGN KEY (idvoit) REFERENCES VOITURE(idvoit) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_reserver_client FOREIGN KEY (idcli) REFERENCES CLIENT(idcli) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT fk_reserver_place FOREIGN KEY (idvoit, place) REFERENCES PLACE(idvoit, place) ON DELETE RESTRICT ON UPDATE CASCADE
);
