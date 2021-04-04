import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class prikazoglas {
    private JPanel main;
    private JTextArea avtoarea;
    private JLabel Imagelabel;
    private JLabel cenatext;
    private JLabel naslovtext;
    private JLabel uporabniktext;
    private JButton rezuredi_button;
    private JLabel imagelabel;

    private Integer ajdi;
    private Integer ajdiupo;
    private Boolean tipoglas;
    public prikazoglas(Integer ido,Integer idu)
    {
        JFrame frame = new JFrame("RentACar");
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 320);
        frame.setVisible(true);
        ajdi = ido;
        ajdiupo = idu;
        Polnjenje();
        setActionListeners();
        tipoglas = baza.preverioglas(ido,idu);
        if(tipoglas == true)
        {
            System.out.println("To je vaš oglas");
            rezuredi_button.setText("Uredi Avtomobil");
        }
        else
        {
            System.out.println("Ni vaš oglas");
            rezuredi_button.setText("Rezerviraj Avtomobil");
        }


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
    private void setActionListeners()
    {
        rezuredi_button.addActionListener(e -> {
            if(tipoglas == true)
            {
                new urejanjeavtomobila();
            }
            else
            {
                new rezervacija();
            }
        });
    }





}
