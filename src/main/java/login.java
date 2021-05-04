import javax.swing.*;
import java.awt.Dimension;

public class login {
    private JPanel login;
    private JFormattedTextField emailField;
    private JPasswordField passwordField;
    private JButton prijavaButton;
    private JButton regbutton;

    public static Integer id_;

    JFrame frame = new JFrame("Login");

    public login()
    {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setSize(500, 300);
        frame.setMinimumSize(new Dimension(400, 250));
        frame.setVisible(true);

        setActionListeners();
    }
    public boolean ifClose = false;

    private void setActionListeners()
    {
        //login button on click
        prijavaButton.addActionListener(e -> {

            String email = emailField.getText();
            char[] password = passwordField.getPassword()       ;

            String pass = new String(password);
            //System.out.print(username);

            try {
                pass = encrypt_decrypt.encrypt_(pass);
            } catch (Exception exception) {
                exception.printStackTrace();
            }


            boolean b = baza.SelectLogin(email, pass);
            if(b == true)
            {
                Integer id = baza.idUporabnik(email,pass);
                uporabnik.id_prijave = id;

                id_ = id;
                ifClose = true;

                new Mainpage(id);



            }
            else{
                JOptionPane.showMessageDialog(login,
                        "Narobno geslo!",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        regbutton.addActionListener(e -> {
        new registracija();
        });

    }

}
