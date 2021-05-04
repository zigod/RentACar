import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class dodajanjeModelaZnamke {
    private JPanel dodajanje;
    private JTextField znamkaIme;
    private JTextField znamkaOpis;
    private JButton dodajZnamko;
    private JTextField modelIme;
    private JTextField modelOpis;
    private JButton dodajModel;
    private JComboBox znamkeBox;

    public dodajanjeModelaZnamke(Integer idu){

        JFrame frame = new JFrame("RentACar");
        frame.setContentPane(dodajanje);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 320);
        frame.setVisible(true);
        setActionListeners();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                frame.dispose();
                new Mainpage(idu);
            }
        });

        ArrayList<String> znamke = new ArrayList<String>();
        znamke = baza.SelectZnamke();
        znamke.forEach((s) -> znamkeBox.addItem(s));
    }

    private void setActionListeners(){
        dodajZnamko.addActionListener(e -> {
            String ime = znamkaIme.getText();
            String opis = znamkaOpis.getText();

            if(ime == null || opis == null)
            {
                JOptionPane.showMessageDialog(dodajanje,
                        "Izpolnite vsa polja!",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                baza.InsertZnamka(ime, opis);
                znamkeBox.removeAllItems();
                ArrayList<String> znamke = new ArrayList<String>();
                znamke = baza.SelectZnamke();
                znamke.forEach((s) -> znamkeBox.addItem(s));
                znamkaIme.setText(null);
                znamkaOpis.setText(null);
            }
        });

        dodajModel.addActionListener(e -> {
            String znamka = znamkeBox.getSelectedItem().toString();
            String ime = modelIme.getText();
            String opis = modelOpis.getText();

            if(ime == null || opis == null)
            {
                JOptionPane.showMessageDialog(dodajanje,
                        "Izpolnite vsa polja!",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                baza.InsertModel(ime, opis, znamka);
                modelIme.setText(null);
                modelOpis.setText(null);
            }
        });
    }
}
