CREATE TABLE korisnik (
	id int identity primary key,
	ime varchar(50) not null,
	prezime varchar(50) not null,
	godine int,
	mjesto varchar(50),
	profilna_slika_url varchar(100),
	opis varchar(500),
	spol varchar(20),
	trazeni_spol varchar(20),
	korisnicko_ime varchar(50) not null,
	lozinka varchar(120) not null,
	aktivan tinyint not null default 1
);

CREATE TABLE korisnik_uloga (
	id int identity primary key,
	korisnik varchar(50) not null,
	uloga varchar(50) not null,
	foreign key (korisnik) references korisnik(korisnicko_ime)
);

CREATE TABLE slike (
	korisnik_id int not null,
	slika_url varchar(100) not null,
	foreign key (korisnik_id) references korisnik(id)
);

CREATE TABLE korisnik_like (
	korisnik_id int not null,
	korisnik_id_liked int not null,
	foreign key (korisnik_id) references korisnik(id),
	foreign key (korisnik_id_liked) references korisnik(id)
);

CREATE TABLE korisnik_dislike (
	korisnik_id int not null,
	korisnik_id_disliked int not null,
	foreign key (korisnik_id) references korisnik(id),
	foreign key (korisnik_id_disliked) references korisnik(id)
);

CREATE TABLE dogadaj (
	id int identity primary key,
	naziv varchar(50) not null,
	opis varchar(200) not null,
	datum date not null,
	mjesto varchar(50) not null
);

CREATE TABLE dogadaj_korisnik (
	korisnik_id int not null,
	dogadaj_id int not null,
	foreign key (korisnik_id) references korisnik(id),
	foreign key (dogadaj_id) references dogadaj(id)
);

--korisnici
insert into korisnik(ime, prezime, korisnicko_ime, lozinka, aktivan, godine, mjesto, opis, spol, trazeni_spol) 
	values('Ime1', 'Prezime1', 'korisnik1', '$2a$04$XLmCwvymbuo6A.C9PHWF8emU.1v6hS88S7ddY6g0F3i3.WiN.toFC', 1, 25, 'Zagreb, Hrvatska', 'Lorem ipsum dolor sit amet. Praesent in aliquam enim, quis varius orci.', 'Muško', 'Žensko');
insert into korisnik(ime, prezime, korisnicko_ime, lozinka, aktivan, godine, mjesto, opis, spol, trazeni_spol) 
	values('Ime2', 'Prezime2', 'korisnik2', '$2a$04$XLmCwvymbuo6A.C9PHWF8emU.1v6hS88S7ddY6g0F3i3.WiN.toFC', 1, 34, 'Zagreb, Hrvatska', 'Lorem ipsum dolor sit amet. Praesent in aliquam enim, quis varius orci.', 'Muško', 'Žensko');
insert into korisnik(ime, prezime, korisnicko_ime, lozinka, aktivan, godine, mjesto, opis, spol, trazeni_spol) 
	values('Ime3', 'Prezime3', 'korisnik3', '$2a$04$XLmCwvymbuo6A.C9PHWF8emU.1v6hS88S7ddY6g0F3i3.WiN.toFC', 1, 53, 'Split, Hrvatska', 'Lorem ipsum dolor sit amet. Praesent in aliquam enim, quis varius orci.', 'Žensko', 'Muško');
insert into korisnik(ime, prezime, korisnicko_ime, lozinka, aktivan, godine, mjesto, opis, spol, trazeni_spol) 
	values('Ime4', 'Prezime4', 'korisnik4', '$2a$04$XLmCwvymbuo6A.C9PHWF8emU.1v6hS88S7ddY6g0F3i3.WiN.toFC', 1, 27, 'Pula, Hrvatska', 'Lorem ipsum dolor sit amet. Praesent in aliquam enim, quis varius orci.', 'Žensko', 'Muško');
insert into korisnik(ime, prezime, korisnicko_ime, lozinka, aktivan, godine, mjesto, opis, spol, trazeni_spol) 
	values('Ime5', 'Prezime5', 'korisnik5', '$2a$04$XLmCwvymbuo6A.C9PHWF8emU.1v6hS88S7ddY6g0F3i3.WiN.toFC', 1, 20, 'Zagreb, Hrvatska', 'Lorem ipsum dolor sit amet. Praesent in aliquam enim, quis varius orci.', 'Žensko', 'Muško');
insert into korisnik(ime, prezime, korisnicko_ime, lozinka, aktivan, godine, mjesto, opis, spol, trazeni_spol) 
	values('Ime6', 'Prezime6', 'korisnik6', '$2a$04$XLmCwvymbuo6A.C9PHWF8emU.1v6hS88S7ddY6g0F3i3.WiN.toFC', 1, 31, 'Dubrovnik, Hrvatska', 'Lorem ipsum dolor sit amet. Praesent in aliquam enim, quis varius orci.', 'Muško', 'Žensko');
	
--uloge
insert into korisnik_uloga(korisnik, uloga)
	values('korisnik1', 'ROLE_ADMIN');
insert into korisnik_uloga(korisnik, uloga)
	values('korisnik2', 'ROLE_KORISNIK');
insert into korisnik_uloga(korisnik, uloga)
	values('korisnik3', 'ROLE_KORISNIK');
insert into korisnik_uloga(korisnik, uloga)
	values('korisnik4', 'ROLE_KORISNIK');
insert into korisnik_uloga(korisnik, uloga)
	values('korisnik5', 'ROLE_KORISNIK');
insert into korisnik_uloga(korisnik, uloga)
	values('korisnik6', 'ROLE_KORISNIK');
insert into korisnik_uloga(korisnik, uloga)
	values('korisnik1', 'ROLE_KORISNIK');

--korisnik like
insert into korisnik_like(korisnik_id, korisnik_id_liked)
	values(1, 2);
insert into korisnik_like(korisnik_id, korisnik_id_liked)
	values(2, 3);
insert into korisnik_like(korisnik_id, korisnik_id_liked)
	values(3, 2);
insert into korisnik_like(korisnik_id, korisnik_id_liked)
	values(1, 3);
insert into korisnik_like(korisnik_id, korisnik_id_liked)
	values(4, 5);

--korisnik dislike
insert into korisnik_dislike(korisnik_id, korisnik_id_disliked)
	values(4, 5);
insert into korisnik_dislike(korisnik_id, korisnik_id_disliked)
	values(1, 5);
insert into korisnik_dislike(korisnik_id, korisnik_id_disliked)
	values(1, 4);

--dogadaji
insert into dogadaj(naziv, opis, mjesto, datum)
	values('Dogadaj1', 'Lorem ipsum dolor sit amet. Praesent in aliquam enim, quis varius orci. Etiam in ipsum sit amet purus tempor bibendum.', 'Zagreb, Hrvatska', parsedatetime('2018-06-17 18:47:22', 'yyyy-MM-dd hh:mm:ss'));
insert into dogadaj(naziv, opis, mjesto, datum)
	values('Dogadaj2', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent in aliquam enim, quis varius orci. Etiam in ipsum sit amet purus tempor bibendum.', 'Dubrovnik, Hrvatska', parsedatetime('2018-04-25 18:47:22', 'yyyy-MM-dd hh:mm:ss'));
insert into dogadaj(naziv, opis, mjesto, datum)
	values('Dogadaj3', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent in aliquam enim. Etiam in ipsum sit amet purus tempor bibendum.', 'Zadar, Hrvatska', parsedatetime('2018-07-02 18:47:22', 'yyyy-MM-dd hh:mm:ss'));
insert into dogadaj(naziv, opis, mjesto, datum)
	values('Dogadaj4', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent in aliquam enim, quis varius orci. Etiam in ipsum sit amet purus tempor bibendum.', 'Zagreb, Hrvatska', parsedatetime('2018-09-12 18:47:22', 'yyyy-MM-dd hh:mm:ss'));
insert into dogadaj(naziv, opis, mjesto, datum)
	values('Dogadaj5', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent in aliquam enim, quis varius orci.', 'Split, Hrvatska', parsedatetime('2018-10-22 18:47:22', 'yyyy-MM-dd hh:mm:ss'));

--korisnici koji dolaze na dogadaje
insert into dogadaj_korisnik(korisnik_id, dogadaj_id)
	values(1, 1);
insert into dogadaj_korisnik(korisnik_id, dogadaj_id)
	values(2, 1);
insert into dogadaj_korisnik(korisnik_id, dogadaj_id)
	values(3, 1);
insert into dogadaj_korisnik(korisnik_id, dogadaj_id)
	values(1, 2);
insert into dogadaj_korisnik(korisnik_id, dogadaj_id)
	values(3, 2);
insert into dogadaj_korisnik(korisnik_id, dogadaj_id)
	values(5, 3);
insert into dogadaj_korisnik(korisnik_id, dogadaj_id)
	values(4, 3);