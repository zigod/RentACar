import javax.swing.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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





        setActionListeners();







    }

    private void setActionListeners() {
        izpispodatkov.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String pod = ((Oglasi)izpispodatkov.getSelectedValue()).prikaz;
                pod = pod.replace("<html>","");
                pod = pod.replace("</html>","");
                pod = pod.replace("<br>","");

                System.out.print(pod);
            }
        });
    }

    public void polnjenje()
    {
        ArrayList<Oglasi> oglas = baza.IzpisOglasi();
        dm.clear();

        for (Oglasi ogl : oglas)
        {
            String podatki = ogl.toString();
            String pot = ogl.pot_slika;
            dm.addElement(new Oglasi(podatki,pot));


        }
        izpispodatkov.setCellRenderer(new RenderPls());
        izpispodatkov.setModel(dm);

    }



}
