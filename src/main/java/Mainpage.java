import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.Renderer;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.io.File;

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
    private JTextField searchField;
    private JButton searchButton;
    private JScrollPane scrollmain;
    private JTable tabela;

    DefaultListModel dm = new DefaultListModel();
    private Integer idu = 0;


    public JFrame frame = new JFrame("RentACar");

    public Mainpage(Integer id_) {
        idu = id_;
        polnjenje();

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();



        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, screensize.height);
        centreWindow(frame);
        frame.setVisible(true);
        System.out.print(uporabnik.id_prijave);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                onExit();
                frame.dispose();
            }
        });




        setActionListeners();


        ArrayList<String> kraji = new ArrayList<>();
        kraji = baza.SelectKraji();
        kraji.forEach((s) -> krajBox.addItem(s));

        ArrayList<String> znamke = new ArrayList<String>();
        znamke = baza.SelectZnamke();
        znamke.forEach((s) -> znamkaBox.addItem(s));

        ArrayList<String> avti = new ArrayList<>();
        avti = baza.SelectAvti(id);
        avti.forEach((s) -> avtoBox.addItem(s));
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public void onExit() {
        if (fileIfDelete == true)
        {
            File file = new File("src\\main\\img\\" + fileName);
            //System.out.print(file);
            file.delete();
        }

    }
    int id_a;
    int id = login.id_;
    public static String fileName;
    public boolean fileIfDelete = true;
    String kraj;

    private void setActionListeners() {
        izpispodatkov.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String pod = ((Oglasi)izpispodatkov.getSelectedValue()).prikaz;

                String[] replacestrings = {"<html>","</html>","<br>","Znamka:", "Model:" , "Letnik:",  "Cena:", "Ime oglasevalca:", "Priimek oglasevalca:", "Kraj oglasa:" };
                for(int i = 0;i < replacestrings.length;i++)
                {
                    pod = pod.replace(replacestrings[i],"");
                }
                String[] spl = pod.split(" ");
                Oglasi izbOglas = new Oglasi(spl[1],spl[2],Integer.parseInt(spl[3]),Double.parseDouble(spl[4]),spl[5],spl[6],spl[7]);
                Integer ido = baza.OglasId(izbOglas);
                System.out.print(ido);
                frame.dispose();
                new prikazoglas(ido,idu);

            }
        });

        searchButton.addActionListener(e -> {
            String search = searchField.getText();
            searchPolnjenje(search);
        });

        krajBox.addActionListener(e -> {
            String krajStr = krajBox.getSelectedItem().toString();
            String[] arrOfStr = krajStr.split(Pattern.quote(" | "), 2);
            kraj = arrOfStr[0];
            System.out.println(kraj);
            System.out.println(Arrays.toString(arrOfStr));
        });
        dodajOglasButton.addActionListener(e -> {
            int cena = Integer.parseInt(cenaTextField.getText());
            String naslov = naslovTextField.getText();

            System.out.println(cena + " " + kraj + " " + naslov + " " + id_a + " " + id);
            boolean preveritevOglasInsertMain = baza.InsertOglas(cena, kraj, naslov, id_a, id);
            if (preveritevOglasInsertMain == false)
            {
                JOptionPane.showMessageDialog(main,
                        "Oglas ni bil dodan! Mogoče že obstaja ali pa ste se zatipkali.",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
            polnjenje();
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
            fileIfDelete = false;

            String potSlike = fileName;
            //System.out.print(potSlike);
            baza.InsertAvto(letnik, kw, ccm, km, opis, modelId, potSlike,id);

            letnikTextField.setText(null);
            kwTextField.setText(null);
            ccmTextField.setText(null);
            KMTextField.setText(null);
            opisTextField.setText(null);

            AWS(potSlike);

            avtoBox.removeAllItems();
            ArrayList<String> avti = new ArrayList<>();
            avti = baza.SelectAvti(id);
            avti.forEach((s) -> avtoBox.addItem(s));

        });

        znamkaBox.addActionListener(e -> {
            modeliBox.removeAllItems();
            String znamkaIme = znamkaBox.getSelectedItem().toString();

            //System.out.print(znamkaIme);

            ArrayList<String> modeli = new ArrayList<String>();
            modeli = baza.SelectModeli(znamkaIme);

            //System.out.print(modeli);

            modeli.forEach((s) -> modeliBox.addItem(s));


        });


        avtoBox.addActionListener(e -> {
            avtoBox.removeAllItems();
            ArrayList<String> avti = new ArrayList<>();
            avti = baza.SelectAvti(id);
            avti.forEach((s) -> avtoBox.addItem(s));

            String str = avtoBox.getSelectedItem().toString();
            String[] arrOfStr = str.split(" | ", 2);
            //System.out.println(Arrays.toString(arrOfStr));
            //System.out.println(arrOfStr[0]);
            id_a = Integer.parseInt(arrOfStr[0]);
        });

        dodajZnamkoModelButton.addActionListener(e -> {
            new dodajanjeModelaZnamke();
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

    public void searchPolnjenje(String search)
    {
        ArrayList<Oglasi> oglas = baza.SearchOglasi(search);

        System.out.println(oglas);

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

    public void AWS(String path)
    {
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAZE5W2FP77YGXVEWB",
                "nmW6v2YwYDx6PFXyhQYREZVrClR7cgZlZqgNvHBp"
        );




        String bucket_name = "rentcar-upb";
        String key_name = path;
        String filepath ="src\\main\\img\\" + path;

        System.out.format("Uploading %s to S3 bucket %s...\n", filepath, bucket_name);
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).build();
        try {
            s3.putObject(bucket_name, key_name, new File(filepath));
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }



}
