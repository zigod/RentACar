import javax.swing.*;
import javax.swing.ImageIcon;

public class prikazoglas {
    private JPanel main;
    private JTextField njfjgfjTextField;
    private JTextField lulTextField;
    private JTextField khkTextField;
    private JTextArea luluTextArea;
    private JTextField lulTextField1;
    private JLabel Imagelabel;
    private JLabel imagelabel;

    public prikazoglas(int id)
    {
        JFrame frame = new JFrame("RentACar");
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 320);
        frame.setVisible(true);
        ImageIcon slika = new ImageIcon("src\\main\\img\\tvojamat.jpg");
        Imagelabel.setIcon(slika);
        Imagelabel.setText("");

    }


}
