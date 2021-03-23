import javax.swing.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Mainpage {
    private JPanel main;
    private JScrollPane scrollmain;
    private JTable tabela;

    public Mainpage()
{
    JFrame frame = new JFrame("RentACar");
    String[][] data = {
            { "test", "test", "test","test" },
            { "test", "test", "test","test" }
    };
    String[] columnNames = { "Cena", "Kraj", "Slika","Oglej"};
    tabela = new JTable(data, columnNames);
    tabela.setBounds(30, 40, 200, 300);

    JScrollPane sp = new JScrollPane(tabela);
    frame.add(sp);
    frame.setSize(500, 500);


    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();

    frame.setVisible(true);

}


}
