# RentACar

Aplikacija omogoča pregled oglasov avtomobilov ter v teh oglasih avtomobil rezervirati. Uporabniki ter lastniki oglasov lahko vidijo čase ko so avtomobili rezervirani, ter takrat nemorejo narediti rezervacije.
Preko aplikacije je mozno dodajo svojih avtomobilov ter, kateri so shranjeni na profilu in jih je možno potem vključiti v oglase.
Na webapp je mozen pregled vse statisicnih podatkov aplikacije.


Functions
------------------------------------------------------
// Funkcija preveri če oglas že obstaja, če ne ga doda
-- FUNCTION: public.dodajavto(integer, integer, integer, integer, character varying, integer, character varying, integer)

-- DROP FUNCTION public.dodajavto(integer, integer, integer, integer, character varying, integer, character varying, integer);

CREATE OR REPLACE FUNCTION public.dodajavto(
	letnik_ integer,
	kw_ integer,
	ccm_ integer,
	km_ integer,
	opis_ character varying,
	id_m_ integer,
	potslike character varying,
	id_l_ integer)
    RETURNS void
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    
AS $BODY$
DECLARE
BEGIN
	INSERT INTO avtomobili(letnik, kw, ccm, km, opis, id_modela, pot_slike, id_lastnika)
	VALUES (letnik_, kw_, ccm_, km_, opis_, id_m_, potSlike, id_l_);
END;
$BODY$;

ALTER FUNCTION public.dodajavto(integer, integer, integer, integer, character varying, integer, character varying, integer)
    OWNER TO ooenbkgvbwqtco;



------------------------------------------------------

// Funkcija preveri lastnika oglasa
-- FUNCTION: public.preverioglas(integer, integer)

-- DROP FUNCTION public.preverioglas(integer, integer);

CREATE OR REPLACE FUNCTION public.preverioglas(
	idu integer,
	ido integer)
    RETURNS boolean
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    
AS $BODY$
DECLARE ok BOOL;
idupo INT;
BEGIN
ok := FALSE;
SELECT INTO idupo id_uporabnika FROM oglasi WHERE id_o = ido;
IF idu = idupo THEN
ok := TRUE;
END IF;
RETURN ok;
END;
$BODY$;

ALTER FUNCTION public.preverioglas(integer, integer)
    OWNER TO ooenbkgvbwqtco;

------------------------------------------------------
//Registracija

-- FUNCTION: public.registracija(character varying, character varying, character varying, character varying, timestamp without time zone, character varying, character varying)

-- DROP FUNCTION public.registracija(character varying, character varying, character varying, character varying, timestamp without time zone, character varying, character varying);

CREATE OR REPLACE FUNCTION public.registracija(
	ime character varying,
	enaslov character varying,
	geslo character varying,
	tel character varying,
	datum timestamp without time zone,
	pri character varying,
	kraj character varying)
    RETURNS boolean
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    
AS $BODY$
DECLARE kid INT;
mejl VARCHAR;
ok BOOL;
BEGIN
ok := true;
SELECT INTO kid id_k FROM kraji WHERE ime_k = kraj;

FOR mejl IN SELECT email FROM uporabniki
LOOP
IF mejl = enaslov THEN 
ok := true;
END IF;
END LOOP;

IF(ok = true)THEN
INSERT INTO uporabniki(email,pass,ime_u,priimek_u,telefon,datum_roj,id_kraja) VALUES(enaslov,geslo,ime,pri,tel,datum,kid); 
END IF;

RETURN ok;
END;
$BODY$;

ALTER FUNCTION public.registracija(character varying, character varying, character varying, character varying, timestamp without time zone, character varying, character varying)
    OWNER TO ooenbkgvbwqtco;


------------------------------------------------------
// Funkcija preveri če je možna rezervacija, če je ga rezervira
-- FUNCTION: public.rezervacija(timestamp without time zone, timestamp without time zone, integer, integer)

-- DROP FUNCTION public.rezervacija(timestamp without time zone, timestamp without time zone, integer, integer);

CREATE OR REPLACE FUNCTION public.rezervacija(
	zacd timestamp without time zone,
	koncd timestamp without time zone,
	id_oglas integer,
	id_uporabnik integer)
    RETURNS boolean
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    
AS $BODY$
DECLARE
previd INT;
ok BOOL;
BEGIN
ok:=FALSE;
SELECT INTO previd id_zas FROM zaseden_cas WHERE (zac_datum BETWEEN zacd AND koncd) OR (zac_datum = zacd) 
OR (kon_datum = koncd) OR (kon_datum BETWEEN zacd AND koncd) OR (kon_datum = zacd) OR (kon_datum = koncd);
IF(previd IS NULL) THEN
INSERT INTO zaseden_cas(zac_datum,kon_datum,id_oglasa,id_uporabnika) VALUES(zacd,koncd,id_oglas,id_uporabnik);
ok := TRUE;
END IF;
RETURN ok;
END;
$BODY$;

ALTER FUNCTION public.rezervacija(timestamp without time zone, timestamp without time zone, integer, integer)
    OWNER TO ooenbkgvbwqtco;


------------------------------------------------------
// Test oglasa

-- FUNCTION: public.testoglas(real, character varying, integer, integer, integer)

-- DROP FUNCTION public.testoglas(real, character varying, integer, integer, integer);

CREATE OR REPLACE FUNCTION public.testoglas(
	cena_ real,
	naslov_prevzema_ character varying,
	id_kr_ integer,
	id_avta_ integer,
	id_upor_ integer)
    RETURNS integer
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    
AS $BODY$
DECLARE
	lol int;
BEGIN	
	lol := null;
	SELECT INTO lol id_o FROM oglasi WHERE (cena_ura=cena_) AND (naslov_prevzema=naslov_prevzema_) AND (id_kraja=id_kr_) AND (id_avtomobila=id_avta_) AND (id_uporabnika=id_upor_);
	
	RETURN lol;
END;
$BODY$;

ALTER FUNCTION public.testoglas(real, character varying, integer, integer, integer)
    OWNER TO ooenbkgvbwqtco;



------------------------------------------------------
// Funkcija doda avto
-- FUNCTION: public.dodajavto(integer, integer, integer, integer, character varying, integer, character varying, integer)

-- DROP FUNCTION public.dodajavto(integer, integer, integer, integer, character varying, integer, character varying, integer);

CREATE OR REPLACE FUNCTION public.dodajavto(
	letnik_ integer,
	kw_ integer,
	ccm_ integer,
	km_ integer,
	opis_ character varying,
	id_m_ integer,
	potslike character varying,
	id_l_ integer)
    RETURNS void
    LANGUAGE 'plpgsql'

    COST 100
    VOLATILE 
    
AS $BODY$
DECLARE
BEGIN
	INSERT INTO avtomobili(letnik, kw, ccm, km, opis, id_modela, pot_slike, id_lastnika)
	VALUES (letnik_, kw_, ccm_, km_, opis_, id_m_, potSlike, id_l_);
END;
$BODY$;

ALTER FUNCTION public.dodajavto(integer, integer, integer, integer, character varying, integer, character varying, integer)
    OWNER TO ooenbkgvbwqtco;
    
    
    ------------------------------------------------------
   
   
   
   
   
   
   
   
   
   
   TRIGGER FUNKCIJE
   
   
   //Arhiv na tabelo zasedenicasi_arhiv
    
    -- FUNCTION: public.arhivirajcase()

-- DROP FUNCTION public.arhivirajcase();

CREATE FUNCTION public.arhivirajcase()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
DECLARE 
oglaslol oglasi%ROWTYPE;
idarhiva INTEGER;
BEGIN
SELECT * INTO oglaslol FROM oglasi WHERE id_o = NEW.id_oglasa;
SELECT id_a INTO idarhiva FROM arhiv WHERE  cena_nauro = oglaslol.cena_ura AND naslov = oglaslol.naslov_prevzema AND 
id_kraja = oglaslol.id_kraja AND id_avtomobila = oglaslol.id_avtomobila AND id_uporabnika = oglaslol.id_uporabnika;
		
        INSERT INTO zasedenicasi_arhiv(zac_datum_a, kon_datum_a, id_ogl, id_upo)
        VALUES(NEW.zac_datum, NEW.kon_datum,idarhiva,NEW.id_uporabnika );

    RETURN NULL;
    END;
$BODY$;

ALTER FUNCTION public.arhivirajcase()
    OWNER TO ooenbkgvbwqtco;


    ------------------------------------------------------
    
    
    //Arhiv nad tabelo Oglasi
    -- FUNCTION: public.arhivirajoglas()

-- DROP FUNCTION public.arhivirajoglas();

CREATE FUNCTION public.arhivirajoglas()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
DECLARE
BEGIN
INSERT INTO arhiv(cena_nauro,naslov,id_kraja,id_avtomobila,id_uporabnika) 
VALUES(NEW.cena_ura,NEW.naslov_prevzema,NEW.id_kraja,NEW.id_avtomobila,NEW.id_uporabnika);
RETURN NULL;
END;
$BODY$;

ALTER FUNCTION public.arhivirajoglas()
    OWNER TO ooenbkgvbwqtco;

    
     ------------------------------------------------------
     //Shranjuje stevilo oglasov glede na kraj
    -- FUNCTION: public.st_oglasov()

-- DROP FUNCTION public.st_oglasov();

CREATE FUNCTION public.st_oglasov()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
DECLARE 
    stoglasov INT;
	kim VARCHAR;
	BEGIN
	FOR kim IN SELECT ime_k FROM kraji
	LOOP 
	SELECT INTO stoglasov COUNT(*) FROM kraji k INNER JOIN oglasi o ON o.id_kraja = k.id_k WHERE k.ime_k = kim;
	UPDATE kraji SET st_oglasov = stoglasov WHERE ime_k = kim;
	END LOOP;
    RETURN NULL;
    END;
$BODY$;

ALTER FUNCTION public.st_oglasov()
    OWNER TO ooenbkgvbwqtco;
    
    
     ------------------------------------------------------
     //Shranjuje kdo je zadnji rezerviral oglas
     
     -- FUNCTION: public.zadnjarezervacija()

-- DROP FUNCTION public.zadnjarezervacija();

CREATE FUNCTION public.zadnjarezervacija()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE NOT LEAKPROOF
AS $BODY$
DECLARE
uporab_i VARCHAR;
uporab_p VARCHAR;
upor VARCHAR;
BEGIN
	SELECT ime_u INTO uporab_i FROM uporabniki WHERE id_u = NEW.id_uporabnika;
	SELECT priimek_u INTO uporab_p FROM uporabniki WHERE id_u = NEW.id_uporabnika;
	upor := CONCAT(uporab_i,' ',uporab_p);
	
	UPDATE oglasi SET zadnjarezervacija = upor WHERE id_o = NEW.id_oglasa;

    RETURN NULL;
    END;
$BODY$;

ALTER FUNCTION public.zadnjarezervacija()
    OWNER TO ooenbkgvbwqtco;
    
    
    
  
    
    
