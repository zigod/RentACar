import javax.swing.*;
import javax.swing.ImageIcon;

public class prikazoglas {
    private JPanel main;
    private JTextArea avtoarea;
    private JLabel Imagelabel;
    private JLabel cenatext;
    private JLabel naslovtext;
    private JLabel uporabniktext;
    private JLabel imagelabel;

    private int ajdi;
    public prikazoglas(int id)
    {
        JFrame frame = new JFrame("RentACar");
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 320);
        frame.setVisible(true);

        ajdi = id;
        Polnjenje();

    }
    private void Polnjenje()
    {
        Oglasi poglas = baza.IzpisOglasa(ajdi);
        cenatext.setText("Cena: " + poglas.cena.toString() + "€ (na uro)");
        naslovtext.setText("Naslov prevzema: " + poglas.Naslov + ", " + poglas.kraj);
        uporabniktext.setText("Lastnik avtomobila: " + poglas.imeuporabnika + " " + poglas.priimek);
        ImageIcon slika = new ImageIcon("src\\main\\img\\" + poglas.pot_slika);
        Imagelabel.setIcon(slika);
        Imagelabel.setText("");
        avtoarea.setText(poglas.OpisAvtaDolgo());



    }


}
