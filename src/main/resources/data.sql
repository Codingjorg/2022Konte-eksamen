INSERT INTO LAGER(lagerid, lagernavn, adresse) VALUES
(1,'OSLO SENTRALLAGER', 'KARL JOHAN GATE 64'),
(2,'NORDSTRAND LAGER','HOLTVEIEN 3');
INSERT INTO BRUKER(brukerid,fornavn,etternavn,epost,passord)VALUES
    (1,'OLA','NORDMANN','Ola.nordmann@live.no','JAVIELSKER'),
    (2,'Per','Tuvalini','Per.Øymann@live.tv','Kronglebisk'),
    (10,'Legolas','Alv','L.Alv@live.tv','pilogbue');
INSERT INTO PAKKE(pakkeid,lagerid,eierid,vekt,volum)VALUES
(1,1,1,30.5,10.5),
(2,2,1,15.5,4.5);
// SKal være tre feil ift pakkenavn i denne filen
