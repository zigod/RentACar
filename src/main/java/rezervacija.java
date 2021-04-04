import javax.swing.*;

public class rezervacija {
    private JPanel main;

    public rezervacija()
    {
        JFrame frame = new JFrame("RentACar");
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 320);
        frame.setVisible(true);

    }
}
