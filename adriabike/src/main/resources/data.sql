INSERT INTO korisnik(adresa, broj_telefona, email, ime, lozinka, prezime, roles, username) VALUES ('Temisvarski drum 51', '0653122','markovic@gmail.com', 'Janko','$2a$12$T6pecbhNSJMX8JKu/yJmcuThLjTQwiu8lEot0sNQulKiRHLEq2cCy', 'Markovic','KUPAC','janko');
INSERT INTO korisnik(adresa, broj_telefona, email, ime, lozinka, prezime, roles, username) VALUES ('Bulevar Republike 21', '063131','mitar@gmail.com', 'Mitar','$2a$12$T6pecbhNSJMX8JKu/yJmcuThLjTQwiu8lEot0sNQulKiRHLEq2cCy', 'Mitrovic','PRODAVAC',',mitar');



INSERT INTO preduzece(adresa, maticni_broj, naziv, poreski_broj) VALUES ('Bulevar Oslobodjenja 21', '432424113', 'AdriaStore 1', '4345435313');

INSERT INTO magacin(lokacija, naziv, preduzece_id) VALUES ('Glavni Trg, Titel', 'Magacin - Titel',1);

INSERT into poslovna_godina(godina, zakljucena, preduzece_id) VALUES (2022,false, 1);

INSERT INTO poreska_stopa(datum_stupanja, procenatpdv) values ('2012-08-25',25);
INSERT INTO poreska_kategorija(naziv, poreska_stopa_id) values ('PDV za prevozna sredstva', 1);

INSERT INTO artikal(naziv, opis, pakovanje, slika, poreska_kategorija_id) VALUES ('Adria Nomad', 'MTB Bike 2022', 1, 'url',1);
insert into magacinska_kartica(pocetno_stanje_kolicina, pocetno_stanje_vrednost, promet_izlaza_kolicina,promet_izlaza_vrednost,promet_ulaza_kolicina, promet_ulaza_vrednost, prosecna_cena, ukupna_vrednost, artikal_id, magacin_id, poslovna_godina_id) VALUES(0, 0, 0, 0, 1, 18000,18000, 18000, 1, 1, 1);

INSERT INTO cenovnik(start, preduzece_id) VALUES ('2022-05-06', 1);
INSERT INTO stavka_cenovnika(cena, artikal_id, cenovnik_id) VALUES (18000, 1, 1);