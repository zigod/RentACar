import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.WindowAdapter;


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
    private JComboBox modeliBox;
    private JFormattedTextField opisTextField;
    private JButton dodajanjeSlikeButton;
    private JButton dodajOglasButton;
    private JButton dodajAvtoButton;
    private JButton dodajZnamkoModelButton;


    public Mainpage(int id_)
    {


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

        //System.out.print(id);

        setActionListeners();

        ArrayList<String> znamke = new ArrayList<String>();
        znamke = baza.SelectZnamke();
        znamke.forEach((s) -> znamkaBox.addItem(s));

    }

    //delete slike ce user slucajno ne pritisne na USTVARI OGLAS in zapre okno
    public void onExit() {
        if (fileIfDelete == true)
        {
            File file = new File("C:\\Users\\Ziga\\IdeaProjects\\RentACar\\src\\img\\" + fileName);
            System.out.print(file);
            file.delete();
        }

    }
    public static String fileName;
    public boolean fileIfDelete = true;


    private void setActionListeners() {
        //login button on click
        dodajOglasButton.addActionListener(e -> {


        });


        dodajanjeSlikeButton.addActionListener(e -> {
            //Shranjevanje slike
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "JPG, GIF, and PNG Images", "jpg", "gif", "png");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(main);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                System.out.println("You chose to open this file: "
                        + file.getName());
                BufferedImage image;
                try {
                    image = ImageIO.read(file);
                    ImageIO.write(image, "jpg",new File("C:\\Users\\Ziga\\IdeaProjects\\RentACar\\src\\img\\" + file.getName()));
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

            String potSlike = "C:\\Users\\Ziga\\IdeaProjects\\RentACar\\src\\img\\" + fileName;
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
}
