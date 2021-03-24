import javax.swing.*;

public class Oglasi {
    private int id_o;
    private String cena;
    private String pot_slika;
    public String uporabnik_o;
    private String opis_avta;
    private String kraj;

    Oglasi(int id,String cenao, String pot,String uporabnik, String opis, String kraja)
    {
        id_o = id;
        cena = cenao;
        pot_slika = pot;
        uporabnik_o = uporabnik;
        opis_avta = opis;
        kraj = kraja;
    }


    Oglasi(String xd,String pot)
    {
        uporabnik_o = xd;
        pot_slika = pot;
    }

    @Override
    public String toString() {
        return  opis_avta + "|" + cena + "|" +  uporabnik_o + "|" + kraj;
    }

    public Icon getImg()
    {
        ImageIcon slika = new ImageIcon(getClass().getResource(pot_slika));
        return slika;
    }
}
