import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;


public class baza {

    private static Connection connect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:postgresql://ec2-52-19-170-215.eu-west-1.compute.amazonaws.com:5432/dal6j2j2bi233e", "ooenbkgvbwqtco", "b26c71535815417b340efb10b2d2a4914b6bdb03a2dc611b9381e76b794bf457");
        } catch (SQLException e) {
            System.out.println("povezava z bazo ni uspela");
        }
        return con;
    }

    //id uporabnika
    public static int idUporabnik(String mail, String geslo) {
        String com = "SELECT id_u FROM uporabniki WHERE email ='" + mail + "' AND pass = '" + geslo + "';";
        int idu = 0;
        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com)) {

            while (rez.next()) {
                idu = rez.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println("idUporabnik() napaka " + e);
        }
        return idu;
    }

    //front page izpis
    public static ArrayList<Oglasi> IzpisOglasi() {
        ArrayList<Oglasi> k_oglasi = new ArrayList<Oglasi>();
        String com = " SELECT a.pot_slike,a.letnik,m.ime_m,z.ime_z, u.ime_u,u.priimek_u,o.cena_ura,k.ime_k,o.id_o FROM kraji k INNER JOIN oglasi o ON o.id_kraja = k.id_k INNER JOIN uporabniki u ON u.id_u = o.id_uporabnika INNER JOIN avtomobili a ON a.id_a = o.id_avtomobila INNER JOIN modeli m ON m.id_m = a.id_modela INNER JOIN znamke z ON z.id_z = m.id_znamke";


        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com)) {

            while (rez.next()) {
                String pot = rez.getString(1);
                Integer letnik = rez.getInt(2);
                String imem = rez.getString(3);
                String imez = rez.getString(4);
                String imeu = rez.getString(5);
                String priimeku = rez.getString(6);
                Double cena = rez.getDouble(7);
                String imek = rez.getString(8);
                int id_o = rez.getInt(9);


                Oglasi o = new Oglasi(id_o, cena, pot, imeu, priimeku, letnik, imem, imez, imek);
                k_oglasi.add(o);
            }

        } catch (SQLException e) {

            System.out.println("idUporabnik() napaka " + e);
        }
        return k_oglasi;
    }

    public static ArrayList<Oglasi> SearchOglasi(String search) {

        search = search.toLowerCase();
        ArrayList<Oglasi> k_oglasi = new ArrayList<Oglasi>();
        String com ="SELECT a.pot_slike,a.letnik,m.ime_m,z.ime_z, u.ime_u,u.priimek_u,o.cena_ura,k.ime_k,o.id_o\n" +
                "FROM kraji k INNER JOIN oglasi o ON o.id_kraja = k.id_k INNER JOIN uporabniki u ON u.id_u = o.id_uporabnika INNER JOIN avtomobili a ON a.id_a = o.id_avtomobila INNER JOIN modeli m ON m.id_m = a.id_modela INNER JOIN znamke z ON z.id_z = m.id_znamke\n" +
                "WHERE (CAST(a.letnik AS TEXT) LIKE '%" + search + "%') OR (LOWER(m.ime_m) LIKE '%" + search + "%') OR (LOWER(z.ime_z) LIKE '%" + search + "%') OR (LOWER(u.ime_u) LIKE '%" + search + "%') OR (LOWER(u.priimek_u) LIKE '%" + search + "%') OR (CAST(o.cena_ura AS TEXT) LIKE '%" + search + "%') OR (LOWER(k.ime_k) LIKE '%" + search + "%');";


        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com)) {

            while (rez.next()) {
                String pot = rez.getString(1);
                Integer letnik = rez.getInt(2);
                String imem = rez.getString(3);
                String imez = rez.getString(4);
                String imeu = rez.getString(5);
                String priimeku = rez.getString(6);
                Double cena = rez.getDouble(7);
                String imek = rez.getString(8);
                int id_o = rez.getInt(9);


                Oglasi o = new Oglasi(id_o, cena, pot, imeu, priimeku, letnik, imem, imez, imek);
                k_oglasi.add(o);
            }

        } catch (SQLException e) {

            System.out.println("searchOglasi napaka " + e);
        }
        return k_oglasi;
    }


    // Izpiše vse kraje
    public static ArrayList<String> SelectKraji() {
        String com = "SELECT ime_k,posta FROM kraji";
        ArrayList<String> kraji = new ArrayList<String>();

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com)) {

            while (rez.next()) {
                String k = rez.getString(1);
                k += " | ";
                k += rez.getString(2);
                kraji.add(k);
            }

        } catch (SQLException e) {

            System.out.println("SelectKraji() napaka " + e);
        }
        return kraji;

    }

    // registracija vrne bool
    public static boolean registracija(String ime, String email, String pass, String telefon, String datum, String kraj, String priimek) {
        String com = "SELECT registracija('" + ime + "','" + email + "','" + pass + "','" + telefon + "','" + datum + "','" + priimek + "','" + kraj + "')";
        boolean ok = true;
        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com)) {

            while (rez.next()) {
                ok = rez.getBoolean(1);
            }

        } catch (SQLException e) {

            System.out.println("registracija() napaka " + e);
        }
        return ok;

    }

    //preveri pri login
    public static boolean SelectLogin(String email_, String pass_) {
        String com = "SELECT email, pass FROM uporabniki WHERE (email='" + email_ + "') AND (pass='" + pass_ + "');";
        boolean potrditev = false;

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com)) {

            while (rez.next()) {
                String l = rez.getString(1);
                if (l != null) {
                    potrditev = true;
                }
            }

        } catch (SQLException e) {

            System.out.println("Login napaka " + e);
        }
        return potrditev;
    }

    public static ArrayList<String> SelectZnamke() {
        String com = "SELECT ime_z FROM znamke";
        ArrayList<String> znamke = new ArrayList<String>();

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com)) {

            while (rez.next()) {
                String z = rez.getString(1);
                znamke.add(z);
            }

        } catch (SQLException e) {

            System.out.println("SelectZnamke() napaka " + e);
        }
        return znamke;

    }

    public static ArrayList<String> SelectModeli(String znamkaIme) {

        String com = "SELECT ime_m FROM modeli WHERE (id_znamke IN (SELECT id_z FROM znamke WHERE ime_z='" + znamkaIme + "'));";
        ArrayList<String> modeli = new ArrayList<String>();

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com)) {

            while (rez.next()) {
                String m = rez.getString(1);
                modeli.add(m);
            }

        } catch (SQLException e) {

            System.out.println("SelectModeli() napaka " + e);
        }
        return modeli;

    }

    //pridobi id oglasa
    public static Integer OglasId(Oglasi ogid) {
        String com = " SELECT o.id_o FROM kraji k INNER JOIN oglasi o ON o.id_kraja = k.id_k INNER JOIN uporabniki u " +
                "ON u.id_u = o.id_uporabnika INNER JOIN avtomobili a ON a.id_a = o.id_avtomobila INNER JOIN modeli m ON m.id_m = a.id_modela " +
                "INNER JOIN znamke z ON z.id_z = m.id_znamke WHERE z.ime_z = '" + ogid.Znamka + "' AND m.ime_m = '" + ogid.Model + "' AND a.letnik = " + ogid.Letnik + " AND o.cena_ura = " + ogid.cena + "" +
                " AND u.ime_u  = '" + ogid.imeuporabnika + "' AND u.priimek_u = '" + ogid.priimek + "' AND k.ime_k = '" + ogid.kraj + "';";
        Integer id = 0;

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com)) {

            while (rez.next()) {
                id = rez.getInt(1);

            }

        } catch (SQLException e) {

            System.out.println("Napaka pri pridobitvi oglasa " + e);
        }
        return id;
    }

    public static int SelectIdModel(String modelIme) {

        String com = "SELECT id_m FROM modeli WHERE ime_m='" + modelIme + "';";

        int modelId = 0;

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com)) {

            while (rez.next()) {
                modelId = rez.getInt(1);
            }


        } catch (SQLException e) {

            System.out.println("SelectIdModel() napaka " + e);
        }
        return modelId;

    }

    // Izpise vse podatke o oglasu
    public static Oglasi IzpisOglasa(Integer id)
    {
        String com = "SELECT o.cena_ura,o.naslov_prevzema,k.ime_k,a.letnik,a.kw,a.ccm,a.km,a.opis,m.ime_m,z.ime_z,u.ime_u,u.priimek_u,a.pot_slike FROM kraji k INNER JOIN oglasi o ON o.id_kraja = k.id_k INNER JOIN uporabniki u " +
                "ON u.id_u = o.id_uporabnika INNER JOIN avtomobili a ON a.id_a = o.id_avtomobila INNER JOIN modeli m ON m.id_m = a.id_modela INNER JOIN znamke z ON z.id_z = m.id_znamke WHERE o.id_o = " + id +";";

        Oglasi poglas = new Oglasi();
        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com)) {

            while (rez.next()) {
            Double cena = rez.getDouble(1);
            String naslov = rez.getString(2);
            String imek = rez.getString(3);
            Integer letnik = rez.getInt(4);
            Integer kw = rez.getInt(5);
            Integer ccm = rez.getInt(6);
            Integer km = rez.getInt(7);
            String opis = rez.getString(8);
            String model = rez.getString(9);
            String znamka = rez.getString(10);
            String upoime = rez.getString(11);
            String upopass = rez.getString(12);
            String slikapot = rez.getString(13);

            poglas = new Oglasi(cena,naslov,imek,letnik,kw,ccm,km,opis,model,znamka,upoime,upopass,slikapot);

            }

        } catch (SQLException e) {

            System.out.println("Napaka pri pridobitvi podatvov o oglasu " + e);
        }
        return poglas;
    }

    public static boolean preverioglas(Integer ido, Integer idu)
    {
        String com = "SELECT preverioglas(" + idu + "," + ido + ")";
        boolean ok = false;

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com))
        {
            rez.next();
            ok = rez.getBoolean(1);
        }
        catch (SQLException e) {

            System.out.println("preveriOglas napaka " + e );
        }
       return ok;
    }

    public static boolean InsertAvto(int letnik_, int kw_, int ccm_, int km_, String opis_, int id_modela, String potSlike, int id_lastnika)
    {
        //dodajAvto(letnik_ int, kw_ int, ccm_ int, km_ int, opis_ varchar, id_m_ int, potSlike varchar, id_l_ int)
        String com = "SELECT dodajAvto("+ letnik_ + "," + kw_ + "," + ccm_ + "," + km_ + ",'" + opis_ + "'," + id_modela + ",'" + potSlike + "'," + id_lastnika + ");";
        boolean potrditev = false;

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com))
        {

            while (rez.next()) {
                String l = rez.getString(1);
                if(l != null)
                {
                    potrditev = true;
                }
            }

        }
        catch (SQLException e) {

            System.out.println("InsertAvto napaka " + e );
        }
        return potrditev;
    }

    public static ArrayList<String> SelectAvti(int id)
    {
        String com = "SELECT id_a, letnik, kw, ccm, km, opis  FROM avtomobili WHERE (id_lastnika=" + id + ");";
        ArrayList<String> avti = new ArrayList<String>();
        String a = "";

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com))
        {

            while (rez.next()) {
                a += Integer.toString(rez.getInt(1));
                a += " | ";
                a += Integer.toString(rez.getInt(2));
                a += " | ";
                a += Integer.toString(rez.getInt(3));
                a += " | ";
                a += Integer.toString(rez.getInt(4));
                a += " | ";
                a += Integer.toString(rez.getInt(5));
                a += " | ";
                a += rez.getString(6);
                avti.add(a);
                a = "";
            }

        }
        catch (SQLException e) {

            System.out.println("SelectAvti1() napaka " + e );
        }

        String comm = "SELECT m.ime_m  FROM modeli m INNER JOIN avtomobili a ON m.id_m=a.id_modela WHERE (id_lastnika=" + id + ");";
        String modelIme = "";

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(comm))
        {

            while (rez.next()) {
                modelIme = rez.getString(1);
                a += " | ";
                a += modelIme;
            }

        }
        catch (SQLException e) {

            System.out.println("SelectAvti2() napaka " + e );
        }
        return avti;
    }

    public static boolean RezervacijaOglasa(String zacd,String koncd, Integer ido)
    {
        String com = "SELECT rezervacija('" + zacd + "','" + koncd + "'," + ido + "," + uporabnik.id_prijave + ");";
        boolean potrditev = false;

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com))
        {
            rez.next();
            potrditev = rez.getBoolean(1);
        }
        catch (SQLException e) {

            System.out.println("Rezervacija napaka + e ");
        }
        return potrditev;

    }

    public static ArrayList<String> Zasedeni_casi(Integer id_oglas)
    {
        ArrayList<String> list = new ArrayList<String>();
        String time = java.time.LocalDate.now() + " " + java.time.LocalTime.now();
        String com = "SELECT zac_datum,kon_datum FROM zaseden_cas WHERE kon_datum > '" + time + "' AND  id_oglasa = " + id_oglas + ";";
        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com))
        {
            while (rez.next())
            {
                list.add("Od:" +  rez.getString(1 ) + " Do: " + rez.getString(2));
            }

        }
        catch (SQLException e) {

            System.out.println("izpis datumov napaka + e ");
        }
        return list;

    }

    public static boolean InsertOglas(double cena, String kraj, String naslov, int id_avto, int id_uporabnika)
    {
        String comm = "SELECT id_k FROM kraji WHERE (ime_k='" + kraj + "') ;";
        int id_kraja = 0;
        boolean preveritevOglasInsert = false;

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(comm))
        {
            while (rez.next()) {
                id_kraja = rez.getInt(1);
            }
        }
        catch (SQLException e) {
            System.out.println("InsertAvto-SelectKrajId napaka " + e );
        }

        String com = "SELECT dodajOglas(" + cena + ", '" + naslov + "', " + id_kraja + ", " + id_avto + ", " + id_uporabnika + ");";
        System.out.println("SELECT dodajOglas(" + cena + ", '" + naslov + "', " + id_kraja + ", " + id_avto + ", " + id_uporabnika + ");");

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com))
        {
            while (rez.next()) {
                preveritevOglasInsert = rez.getBoolean(1);
            }
        }
        catch (SQLException e) {

            System.out.println("InsertOglas napaka " + e );
        }
        return preveritevOglasInsert;
    }

    public static void InsertZnamka(String ime, String opis)
    {
        try (Connection con = connect();
             Statement stat = con.createStatement())
        {
            stat.executeUpdate("INSERT INTO znamke(ime_z, opis_z) VALUES('" + ime + "','" +  opis + "');");
        }
        catch (SQLException e) {

            System.out.println("InsertZnamka napaka " + e );
        }
    }

    public static void InsertModel(String ime, String opis, String znamka)
    {
        String comm = "SELECT id_z FROM znamke WHERE ime_z ='" + znamka +"';";
        int id_znamke = 0;

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(comm))
        {
            while (rez.next()) {
                id_znamke = rez.getInt(1);
            }
        }
        catch (SQLException e) {
            System.out.println("InsertModel-SelectId napaka " + e );
        }

        if (id_znamke == 0){
            System.out.println("HALO nek znamka bug je wtf");
        }

        //System.out.println("ime je: " + ime + " Opis je: " + " Znamka je: " +  id_znamke);

        try (Connection con = connect();
             Statement stat = con.createStatement())
        {
            stat.executeUpdate("INSERT INTO modeli(ime_m, opis_m, id_znamke) VALUES('" + ime + "', '" + opis + "', '" + id_znamke + "');");
        }
        catch (SQLException e) {

            System.out.println("InsertModel napaka " + e );
        }
    }

    public static void Rezervacija(Integer id_oglas, Integer id_uporabnik, String zac_datum, String kon_datum)
    {
        try (Connection con = connect();
             Statement stat = con.createStatement())
        {
            stat.executeUpdate("INSERT INTO zaseden_cas(id_oglasa, id_uporabnika, zac_datum, kon_datum) VALUES(" + id_oglas + ", " + id_uporabnik + ", '" + zac_datum + "', '" + kon_datum + "');");
        }
        catch (SQLException e) {

            System.out.println("Rezervacija napaka " + e );
        }
    }

    public static ArrayList<String> CasiVDnevu(String datum)
    {
        ArrayList<String> casi = new ArrayList<>();
        String comm = "SELECT zac_datum, kon_datum FROM zaseden_cas WHERE (CAST(zac_datum AS text) LIKE '" + datum + "%');";
        String cas;

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(comm))
        {
            while (rez.next()) {
                cas = rez.getString(1);
                cas += " | ";
                cas += rez.getString(2);
                casi.add(cas);
            }
        }
        catch (SQLException e) {
            System.out.println("InsertModel-SelectId napaka " + e );
        }

        return casi;
    }



}