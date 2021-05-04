import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Pattern;


public class registracija {
    private JPanel registracijaform;
    private JFormattedTextField ime_text;
    private JFormattedTextField pri_text;
    private JFormattedTextField email_text;
    private JPasswordField pass_text;
    private JFormattedTextField tel_text;
    private JComboBox krajbox;
    private JFormattedTextField darum_text;
    private JButton reg_button;
    private JFormattedTextField geslotext;


    public registracija()
    {

        JFrame frame = new JFrame("Registracija");
        frame.setContentPane(registracijaform);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(550, 400);

        frame.setVisible(true);
        Fill_krajcombo();
        setActionListeners();

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                frame.dispose();
            }
        });



    }

    private void Fill_krajcombo()
    {
        DefaultComboBoxModel mod = new DefaultComboBoxModel();
        mod.addAll(baza.SelectKraji());
        krajbox.setModel(mod);
    }

    private void setActionListeners() {
        reg_button.addActionListener(e -> {
            String ime = ime_text.getText();
            String email = email_text.getText();
            String telefon = tel_text.getText();
            String priimek = pri_text.getText();
            String datum = darum_text.getText();
            String kraj = krajbox.getSelectedItem().toString();
            String[] imek = kraj.split(Pattern.quote(" | "), 2);
            String pass = geslotext.getText();


            try {
                pass = encrypt_decrypt.encrypt_(pass);
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            System.out.println(pass);


            boolean ok = baza.registracija(ime,email,pass,telefon,datum,imek[0],priimek);
            System.out.print(ok);
            if(ok == true)
            {
                Integer id = baza.idUporabnik(email,pass);
                uporabnik.id_prijave = id;
                new Mainpage(id);
            }


        });

    }

}


