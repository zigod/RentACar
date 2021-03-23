import javax.swing.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.util.ArrayList;

public class Mainpage {
    private JPanel main;
    private JTabbedPane tabbedPane1;
    private JScrollPane mainscroll;
    private JList izpispodatkov;
    private JScrollPane scrollmain;
    private JTable tabela;

    public Mainpage(int id)
{

    JFrame frame = new JFrame("RentACar");
    frame.setContentPane(main);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setSize(1050, 700);
    frame.setVisible(true);
    ArrayList<Oglasi> oglas =  baza.IzpisOglasi();

    DefaultListModel<String> ListOglasov = new DefaultListModel<>();

    for (Oglasi ogl : oglas)
    {
        String prikaznioglas = ogl.toString();


        ListOglasov.addElement(prikaznioglas);
    }
    izpispodatkov.setModel(ListOglasov);


}


}
