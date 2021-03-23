import javax.swing.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Mainpage {
    private JPanel main;

    //Elementi za dodajanje oglasov
    private JTabbedPane dodajOglasPane;
    private JFormattedTextField cenaTextField;
    private JComboBox krajBox;
    private JFormattedTextField naslovTextField;
    private JComboBox avtoBox;

    //Elementi za dodajanje avta v Profilu
    private JFormattedTextField letnikTextField;
    private JFormattedTextField kwTextField;
    private JFormattedTextField ccmTextField;
    private JFormattedTextField KMTextField;
    private JComboBox znamkaBox;
    private JComboBox modelBox;
    private JFormattedTextField opisTextField;
    private JButton dodajanjeSlikeButton;
    private JButton dodajOglasButton;



    public Mainpage(int id)
    {
        JFrame frame = new JFrame("RentACar");
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(300, 300);
        frame.setVisible(true);

        //System.out.print(id);

        setActionListeners();
    }

    private void setActionListeners() {
        //login button on click
        dodajOglasButton.addActionListener(e -> {


        });

        dodajanjeSlikeButton.addActionListener(e -> {
            // Using this process to invoke the contructor,
            // JFileChooser points to user's default directory
            JFileChooser j = new JFileChooser();

            // Open the save dialog
            j.showSaveDialog(null);


        });
    }


}
