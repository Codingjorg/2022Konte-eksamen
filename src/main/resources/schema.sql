CREATE TABLE LAGER(
                      lagerid INTEGER AUTO_INCREMENT NOT NULL,
                      lagernavn VARCHAR(255),
                      adresse VARCHAR(255),
                      PRIMARY KEY(lagerid)

);

CREATE TABLE BRUKER(
    brukerid INTEGER AUTO_INCREMENT NOT NULL,
    epost VARCHAR(255),
    fornavn VARCHAR(255),
    etternavn VARCHAR(255),
    passord VARCHAR(255),
    PRIMARY KEY (BRUKERID)
);
CREATE TABLE PAKKE(
                      pakkeid INTEGER AUTO_INCREMENT NOT NULL,
                      lagerid INTEGER,
                      eierid INTEGER,
                      vekt DOUBLE,
                      volum DOUBLE ,
                      PRIMARY KEY (pakkeid),
                      FOREIGN KEY (lagerid) REFERENCES LAGER(lagerid),
                      FOREIGN KEY (eierid) REFERENCES BRUKER(brukerid)
);
