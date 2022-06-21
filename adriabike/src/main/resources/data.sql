INSERT INTO korisnik(adresa, broj_telefona, email, ime, lozinka, prezime, roles, username) VALUES ('Temisvarski drum 51', '0653122','vladimirjokanovic00@gmail.com', 'Janko','$2a$12$T6pecbhNSJMX8JKu/yJmcuThLjTQwiu8lEot0sNQulKiRHLEq2cCy', 'Markovic','KUPAC','janko');
INSERT INTO korisnik(adresa, broj_telefona, email, ime, lozinka, prezime, roles, username) VALUES ('Bulevar Republike 21', '063131','mitar@gmail.com', 'Mitar','$2y$10$hot02.nCHwYwTBb6suq21uz1I/4P8EiSlEkWOzUxN5JHWgaNgF0gC', 'Mitrovic','PRODAVAC','mitar');



INSERT INTO preduzece(adresa, maticni_broj, naziv, poreski_broj) VALUES ('Bulevar Oslobodjenja 21', '432424113', 'AdriaStore 1', '4345435313');

INSERT INTO magacin(lokacija, naziv, preduzece_id) VALUES ('Glavni Trg, Titel', 'Magacin - Titel',1);

INSERT into poslovna_godina(godina, zakljucena, preduzece_id) VALUES (2022,false, 1);

INSERT INTO poreska_stopa(datum_stupanja, procenatpdv) values ('2012-08-25',25);
INSERT INTO poreska_kategorija(naziv, poreska_stopa_id) values ('PDV za prevozna sredstva', 1);
INSERT INTO poreska_kategorija(naziv, poreska_stopa_id) values ('PDV za periferije', 1);


INSERT INTO artikal(naziv, opis, pakovanje, slika, poreska_kategorija_id) VALUES ('Adria Nomad', 'MTB Bike 2022', 1, 'https://www.bcgroup-online.com/upload/b/38470-adria-nomad-26-crno-crveno.jpg',1);
INSERT INTO artikal(naziv, opis, pakovanje, slika, poreska_kategorija_id) VALUES ('Adria Hiperion 26', 'MTB Bike 2016', 1, 'https://www.capriolo.com/sites/default/files/styles/capriolo_app_960x660/public/bike/7_0.jpg?itok=ORiVJ7S9',1);
INSERT INTO artikal(naziv, opis, pakovanje, slika, poreska_kategorija_id) VALUES ('Trek King 1300x', 'Road bike 2021', 1, 'http://www.bikeland.rs/uploads/uploaded_pictures/_content_bicikle/558x355/chronos-sivo-narandzaste-boje-2017-2256-645009.jpg',1);
INSERT INTO artikal(naziv, opis, pakovanje, slika, poreska_kategorija_id) VALUES ('Scott Road', 'Roadbike 2016', 1, 'https://www.extremevital.com/catalog/images/scott/2020/scott-addict-rc-30-020-yel_l.jpg',1);
insert into magacinska_kartica(pocetno_stanje_kolicina, pocetno_stanje_vrednost, promet_izlaza_kolicina,promet_izlaza_vrednost,promet_ulaza_kolicina, promet_ulaza_vrednost, prosecna_cena, ukupna_vrednost, artikal_id, magacin_id, poslovna_godina_id) VALUES(0, 0, 0, 0, 10, 180000,18000, 180000, 1, 1, 1);
insert into magacinska_kartica(pocetno_stanje_kolicina, pocetno_stanje_vrednost, promet_izlaza_kolicina,promet_izlaza_vrednost,promet_ulaza_kolicina, promet_ulaza_vrednost, prosecna_cena, ukupna_vrednost, artikal_id, magacin_id, poslovna_godina_id) VALUES(0, 0, 0, 0, 10, 120000,18000, 120000, 2, 1, 1);
insert into magacinska_kartica(pocetno_stanje_kolicina, pocetno_stanje_vrednost, promet_izlaza_kolicina,promet_izlaza_vrednost,promet_ulaza_kolicina, promet_ulaza_vrednost, prosecna_cena, ukupna_vrednost, artikal_id, magacin_id, poslovna_godina_id) VALUES(0, 0, 0, 0, 10, 180000,18000, 180000, 3, 1, 1);
insert into magacinska_kartica(pocetno_stanje_kolicina, pocetno_stanje_vrednost, promet_izlaza_kolicina,promet_izlaza_vrednost,promet_ulaza_kolicina, promet_ulaza_vrednost, prosecna_cena, ukupna_vrednost, artikal_id, magacin_id, poslovna_godina_id) VALUES(0, 0, 0, 0, 10, 120000,18000, 120000, 4, 1, 1);

INSERT INTO cenovnik(start, preduzece_id) VALUES ('2022-03-06', 1);
INSERT INTO stavka_cenovnika(cena, artikal_id, cenovnik_id) VALUES (12000, 1, 1);
INSERT INTO cenovnik(start, preduzece_id) VALUES ('2022-05-06', 1);
INSERT INTO stavka_cenovnika(cena, artikal_id, cenovnik_id) VALUES (18000, 1, 2);
INSERT INTO stavka_cenovnika(cena, artikal_id, cenovnik_id) VALUES (9000, 2, 2);
INSERT INTO stavka_cenovnika(cena, artikal_id, cenovnik_id) VALUES (50000, 3, 2);
INSERT INTO stavka_cenovnika(cena, artikal_id, cenovnik_id) VALUES (80000, 4, 2);
