import javax.swing.*;

public class Oglasi {
    private int id_o;
    public Double cena;
    public String pot_slika;


    public String Znamka;
    public String Model;
    public Integer Letnik;

    public String imeuporabnika;
    public String priimek;

    public String kraj;

    public String prikaz;

    Oglasi(int id,Double cenao, String pot,String imeu,String pri, Integer letn,String mod,String zna, String kraja)
    {
        id_o = id;
        cena = cenao;
        pot_slika = pot;
        Znamka = zna;
        Letnik = letn;
        Model = mod;

        imeuporabnika  = imeu;
        priimek = pri;
        kraj = kraja;
    }

    Oglasi(String zn, String mod, Integer letn, Double cen, String imeo, String prio, String krajo)
    {
        Znamka = zn;
        Model = mod;
        Letnik = letn;
        cena = cen;
        imeuporabnika = imeo;
        priimek = prio;
        kraj = krajo;
    }


   //prikazni podatki
   Oglasi(String prikazpodatki,String pot)
    {
        prikaz = prikazpodatki;
        pot_slika = pot;
    }


    @Override
    public String toString() {
        return  "<html>"+ OpisAvtaKratko() + "Cena: " + cena.toString() + "<br>" +  Uporabnikpod() + "Kraj oglasa: " + kraj + "</html>";
    }

    public String OpisAvtaKratko()
    {
        String opis = "Znamka: " + Znamka + "<br>" + "Model: " + Model + "<br>" + "Letnik: " + Letnik.toString() + "<br>";
        return opis;
    }
    public String Uporabnikpod()
    {
        String pod = "Ime oglasevalca: " + imeuporabnika + "<br>" + "Priimek oglasevalca: " + priimek +  "<br>";
        return pod;
    }

    public ImageIcon getImg()
    {

        ImageIcon slika = new ImageIcon((new ImageIcon(getClass().getResource(pot_slika)).getImage().getScaledInstance(320, 240, java.awt.Image.SCALE_SMOOTH)));
        return slika;
    }
}
