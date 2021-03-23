import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class baza {

    private static Connection connect() {
        Connection con = null;
        try
        {
            con = DriverManager.getConnection("jdbc:postgresql://ec2-52-19-170-215.eu-west-1.compute.amazonaws.com:5432/dal6j2j2bi233e", "ooenbkgvbwqtco", "b26c71535815417b340efb10b2d2a4914b6bdb03a2dc611b9381e76b794bf457");
        }
        catch (SQLException e)
        {
            System.out.println("povezava z bazo ni uspela");
        }
        return con;
    }

    public static int idUporabnik(String mail, String geslo)
    {
        String com = "SELECT id_u FROM uporabniki WHERE email ='" + mail + "' AND pass = '" + geslo + "';";
        int idu = 0;
        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com))
        {

            while (rez.next()) {
                idu = rez.getInt(1);
            }

        }
        catch (SQLException e) {

            System.out.println("idUporabnik() napaka " + e );
        }
       return idu;
    }
    // Izpiše vse kraje
    public static ArrayList<String> SelectKraji()
    {
        String com = "SELECT ime_k,posta FROM kraji";
        ArrayList<String> kraji = new ArrayList<String>();

        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com))
        {

            while (rez.next()) {
                String k = rez.getString(1);
                k += "|";
                k += rez.getString(2);
                kraji.add(k);
            }

        }
        catch (SQLException e) {

            System.out.println("SelectKraji() napaka " + e );
        }
        return kraji;

    }
    // registracija vrne bool
    public static boolean registracija(String ime,String email,String pass,String telefon, String datum, String kraj,String priimek)
    {
        String com = "SELECT registracija('" + ime + "','" + email + "','" + pass + "','" + telefon + "','" + datum + "','" + priimek + "','" + kraj + "')";
        boolean ok = true;
        try (Connection con = connect();
             Statement stat = con.createStatement();
             ResultSet rez = stat.executeQuery(com))
        {

            while (rez.next()) {
                ok = rez.getBoolean(1);
            }

        }
        catch (SQLException e) {

            System.out.println("registracija() napaka " + e );
        }
        return ok;

    }

    //preveri pri login
    public static boolean SelectLogin(String email_, String pass_)
    {
        String com = "SELECT email, pass FROM uporabniki WHERE (email='" + email_ + "') AND (pass='" + pass_ + "');";
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

            System.out.println("Login napaka " + e );
        }
        return potrditev;
    }

}