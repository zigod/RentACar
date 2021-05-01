import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class rezervacija {
    private JPanel main;
    private JTextField zacdat;
    private JTextField kondat;
    private JButton rezervirajButton;
    private Integer id_oglasa;

    public rezervacija(int ido)
    {
        JFrame frame = new JFrame("RentACar");
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 320);
        frame.setVisible(true);
        setActionListeners();
        id_oglasa = ido;

    }

    private void setActionListeners() {
        rezervirajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String zacdatum = zacdat.getText().toString();
            String kondatum = kondat.getText().toString();
            boolean ok = baza.RezervacijaOglasa(zacdatum,kondatum,id_oglasa);
            if(ok == true)
            {
                new prikazoglas(id_oglasa,uporabnik.id_prijave);

            }
            else
            {
                System.out.println("Napaka pri rezervaciji");
            }


            }
        });
    }
}
