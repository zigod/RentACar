import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class urejanjeavtomobila {
    private JPanel main;
    private JTextField cenatext;
    private JTextField naslovtext;
    private JButton okbutton;
    private JComboBox kraj;

    public JFrame frame = new JFrame("RentACar");
    Integer id;
    public urejanjeavtomobila(Integer ido)
    {
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, 1000);
        frame.setVisible(true);

        ArrayList<String> kraji = new ArrayList<>();
        kraji = baza.SelectKraji();
        kraji.forEach((s) -> kraj.addItem(s));


        String lol = baza.Selectcenanaslov(ido);
        System.out.print(lol);
        String [] ok = lol.split(":");
        cenatext.setText(ok[0]);
        naslovtext.setText(ok[1]);

        id = ido;
        setActionListeners();

    }

    private void setActionListeners()
    {
        okbutton.addActionListener(e ->
                {
                    double cena = Double.parseDouble(cenatext.getText());
                    String naslov = naslovtext.getText();
                    String krajime = kraj.getSelectedItem().toString();
                    String [] spl = krajime.split(Pattern.quote(" | "), 2);
                    baza.updateOglas(id,cena,naslov,spl[0]);
                }
                );
    }


}
