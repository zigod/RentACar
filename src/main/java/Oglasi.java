import javax.swing.*;

public class Oglasi {
    private int id_o;
    private String cena;
    public String pot_slika;


    private String Znamka;
    private String Model;
    private String Letnik;

    private String imeuporabnika;
    private String priimek;

    private String kraj;

    public String prikaz;

    Oglasi(int id,String cenao, String pot,String imeu,String pri, String letn,String mod,String zna, String kraja)
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


   //prikazni podatki
   Oglasi(String prikazpodatki,String pot)
    {
        prikaz = prikazpodatki;
        pot_slika = pot;
    }


    @Override
    public String toString() {
        return  "<html>"+ OpisAvtaKratko() + "Cena: " + cena + "<br>" +  Uporabnikpod() + "Kraj oglasa: " + kraj + "</html>";
    }

    public String OpisAvtaKratko()
    {
        String opis = "Znamka: " + Znamka + "<br>" + "Model: " + Model + "<br>" + "Letnik: " + Letnik + "<br>";
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
