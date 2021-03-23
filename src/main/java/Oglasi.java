public class Oglasi {
    int id_o;
    String cena;
    String pot_slika;
    String uporabnik_o;
    String opis_avta;
    String kraj;

    Oglasi(int id,String cenao, String pot,String uporabnik, String opis, String kraja)
    {
        id_o = id;
        cena = cenao;
        pot_slika = pot;
        uporabnik_o = uporabnik;
        opis_avta = opis;
        kraj = kraja;
    }

    @Override
    public String toString() {
        return pot_slika + "|" + opis_avta + "|" + cena + "|" +  uporabnik_o + "|" + kraj;
    }
}
