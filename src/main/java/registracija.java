import javax.swing.*;

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
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(750, 500);

        frame.setVisible(true);
        Fill_krajcombo();
        setActionListeners();



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
            String[] imek = kraj.split("\\|");
            String pass = geslotext.getText();


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


