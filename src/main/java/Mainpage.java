import javax.swing.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.Renderer;
import javax.swing.SwingUtilities;

public class Mainpage extends javax.swing.JFrame{
    private JPanel main;
    private JTabbedPane tabbedPane1;
    private JScrollPane mainscroll;
    private JList izpispodatkov;
    private JScrollPane scrollmain;
    private JTable tabela;

    DefaultListModel dm = new DefaultListModel();


    public Mainpage(int id) {
        polnjenje();


        JFrame frame = new JFrame("RentACar");
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1050, 700);
        frame.setVisible(true);


        // ArrayList<Oglasi> oglas = baza.IzpisOglasi();


        izpispodatkov.setCellRenderer(new RenderPls());
        izpispodatkov.setModel(dm);



    /*
    DefaultListModel<String> ListOglasov = new DefaultListModel<>();

    for (Oglasi ogl : oglas)
    {
        String prikaznioglas = ogl.toString();


        ListOglasov.addElement(prikaznioglas);
    }
    izpispodatkov.setModel(ListOglasov);
    */

    }


    public void polnjenje()
    {
        String x = "PLSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS";
        dm.clear();
        dm.addElement(new Oglasi(x,"bruh.jpg"));


    }



}
