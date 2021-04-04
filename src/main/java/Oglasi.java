import javax.swing.*;

public class Oglasi {
    private int id_o;

    public String pot_slika;


    public String Znamka;
    public String Model;
    public Integer Letnik;
    public Integer kw;
    public Integer ccm;
    public String Opis;
    public Integer km;

    public String imeuporabnika;
    public String priimek;

    public String kraj;

    public String prikaz;

    public Double cena;
    public String Naslov;

    Oglasi()
    {

    }
    Oglasi(Double cen,String nasl,String imekr, Integer let, Integer kww, Integer cccm,Integer kmh,String op, String mod, String zna, String uime, String uppas,String slika)
    {
        cena = cen;
        Naslov = nasl;
        kraj = imekr;
        Letnik = let;
        kw = kww;
        ccm = cccm;
        km = kmh;
        Opis = op;
        Model = mod;
        Znamka = zna;
        imeuporabnika = uime;
        priimek = uppas;
        pot_slika = slika;
    }
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

    public String OpisAvtaDolgo()
    {
        String opisavta = "Znamka: " + Znamka + "\n" + "Model: " + Model + "\n" + "Letnik: " + Letnik.toString() + "\n"  +  "KW: " + kw.toString()  + "\n" + "CCM: " + ccm.toString()  + "\n" + "KM: " + km.toString() + "\n" + "Opis: "  + Opis + "\n" ;
        return opisavta;
    }
}
