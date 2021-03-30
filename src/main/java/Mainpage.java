import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.Renderer;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Mainpage extends javax.swing.JFrame{
    private JPanel main;
    private JTabbedPane tabbedPane1;
    private JScrollPane mainscroll;
    private JList izpispodatkov;
    private JFormattedTextField cenaTextField;
    private JComboBox krajBox;
    private JFormattedTextField naslovTextField;
    private JComboBox avtoBox;
    private JButton dodajOglasButton;
    private JFormattedTextField letnikTextField;
    private JFormattedTextField kwTextField;
    private JFormattedTextField ccmTextField;
    private JFormattedTextField KMTextField;
    private JComboBox znamkaBox;
    private JComboBox modeliBox;
    private JFormattedTextField opisTextField;
    private JButton dodajanjeSlikeButton;
    private JButton dodajAvtoButton;
    private JButton dodajZnamkoModelButton;
    private JScrollPane scrollmain;
    private JTable tabela;

    DefaultListModel dm = new DefaultListModel();


    public Mainpage(int id_) {
        polnjenje();


        JFrame frame = new JFrame("RentACar");
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 320);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit();
                frame.dispose();
            }
        });




        setActionListeners();

        ArrayList<String> znamke = new ArrayList<String>();
        znamke = baza.SelectZnamke();
        znamke.forEach((s) -> znamkaBox.addItem(s));







    }
    public void onExit() {
        if (fileIfDelete == true)
        {
            File file = new File("src\\main\\img\\" + fileName);
            System.out.print(file);
            file.delete();
        }

    }
    public static String fileName;
    public boolean fileIfDelete = true;

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
        dodajOglasButton.addActionListener(e -> {


        });

        dodajanjeSlikeButton.addActionListener(e -> {
            //Shranjevanje slike
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG and PNG Images", "jpg", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(main);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                System.out.println("You chose to open this file: "
                        + file.getName());
                BufferedImage image;
                try {
                    image = ImageIO.read(file);
                    ImageIO.write(image, "jpg",new File("src\\main\\img\\" + file.getName()));
                    ImageIO.write(image, "png",new File("src\\main\\img\\" + file.getName()));
                    fileName = file.getName();
                    fileIfDelete = true;
                } catch (IOException ex) {
                    Logger.getLogger(Mainpage.class.getName()).log(Level.SEVERE, null, ex);

                    //izpis errorja ce ne zberes slike
                    JOptionPane.showMessageDialog(main,
                            "Niste izbrali slike!",
                            "Error!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        //ko kliknes ta zadn button kjer pise DODAJ AVTO
        dodajAvtoButton.addActionListener(e -> {
            int letnik = Integer.parseInt(letnikTextField.getText());
            int kw = Integer.parseInt(kwTextField.getText());
            int ccm = Integer.parseInt(ccmTextField.getText());
            int km = Integer.parseInt(KMTextField.getText());

            String model = modeliBox.getSelectedItem().toString();
            int modelId = baza.SelectIdModel(model);
            String opis = opisTextField.getText();
            int id = login.id_;
            fileIfDelete = false;

            String potSlike = "src\\main\\img\\" + fileName;
            System.out.print(potSlike);
            baza.InsertAvto(letnik, kw, ccm, km, opis, modelId, potSlike,id);
        });

        znamkaBox.addActionListener(e -> {
            modeliBox.removeAllItems();
            String znamkaIme = znamkaBox.getSelectedItem().toString();

            System.out.print(znamkaIme);

            ArrayList<String> modeli = new ArrayList<String>();
            modeli = baza.SelectModeli(znamkaIme);

            System.out.print(modeli);

            modeli.forEach((s) -> modeliBox.addItem(s));


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
