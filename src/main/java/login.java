import javax.swing.*;

public class login {

    private JPanel login;
    private JFormattedTextField emailField;
    private JPasswordField passwordField;
    private JButton prijavaButton;

    public static int id_;

    public login()
    {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(300, 150);
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
            boolean b = baza.SelectLogin(email, pass);
            if(b == true)
            {
                int id = baza.idUporabnik(email,pass);
                id_ = id;
                ifClose = true;
                new Mainpage(id);
            }
        });

    }

}
