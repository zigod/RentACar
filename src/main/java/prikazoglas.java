import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;

import java.awt.*;
import java.time.*;
import java.util.Locale;
import static org.junit.Assert.assertTrue;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.zinternaltools.DemoPanel;
import com.github.lgooddatepicker.components.DatePicker;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionEvent;
import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DatePickerSettings.DateArea;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.zinternaltools.InternalUtilities;
import com.github.lgooddatepicker.zinternaltools.WrapLayout;
import com.github.lgooddatepicker.zinternaltools.CalendarSelectionEvent;
import com.github.lgooddatepicker.zinternaltools.DateTimeChangeEvent;
import com.github.lgooddatepicker.zinternaltools.TimeChangeEvent;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;
import com.github.lgooddatepicker.optionalusertools.DateHighlightPolicy;
import com.github.lgooddatepicker.optionalusertools.TimeChangeListener;
import com.github.lgooddatepicker.optionalusertools.TimeVetoPolicy;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.optionalusertools.CalendarBorderProperties;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeArea;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;
import com.privatejgoodies.forms.factories.CC;
import javax.swing.border.LineBorder;
import com.github.lgooddatepicker.zinternaltools.HighlightInformation;

import java.awt.event.ActionListener;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;


public class prikazoglas {


    private JPanel main;
    private JTextArea avtoarea;
    private JLabel Imagelabel;
    private JLabel cenatext;
    private JLabel naslovtext;
    private JLabel uporabniktext;
    private JButton rezuredi_button;
    private JComboBox odCasiBox;
    private DatePicker picker;
    private JComboBox doCasiBox;
    private JButton rezervirajButton;
    private JList zasedendatum;
    private JLabel imagelabel;

    private Integer ajdi;
    private Integer ajdiupo;
    private Boolean tipoglas;

    String[] times = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
    String[] times1 = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};
    String[] times2 = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24"};


    public prikazoglas(Integer ido,Integer idu)
    {

        Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame frame = new JFrame("RentACar");
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(1000, screensize.height);
        centreWindow(frame);
        frame.setVisible(true);
        ajdi = ido;
        System.out.println("ajdi je " + ajdi);
        ajdiupo = idu;
        Polnjenje();
        setActionListeners();


        tipoglas = baza.preverioglas(ido,idu);
        if(tipoglas == true)
        {
            System.out.println("To je vaš oglas");
            rezuredi_button.setText("Uredi Avtomobil");
        }
        else
        {
            System.out.println("Ni vaš oglas");
            rezuredi_button.setText("Rezerviraj Avtomobil");
        }

    }

    private void Polnjenje()
    {
        Oglasi poglas = baza.IzpisOglasa(ajdi);
        //System.out.println("ajdi je se kr" + ajdi);
        cenatext.setText("Cena: " + poglas.cena.toString() + "€ (na uro)");
        naslovtext.setText("Naslov prevzema: " + poglas.Naslov + ", " + poglas.kraj);
        uporabniktext.setText("Lastnik avtomobila: " + poglas.imeuporabnika + " " + poglas.priimek);
        ImageIcon slika = new ImageIcon("src\\main\\img\\" + poglas.pot_slika);
        Image scaledImage = slika.getImage().getScaledInstance(500, 500, Image.SCALE_FAST);
        ImageIcon malaslika = new ImageIcon(scaledImage);
        Imagelabel.setIcon(malaslika);
        Imagelabel.setText("");
        avtoarea.setText(poglas.OpisAvtaDolgo());

        /* LIST ZASEDENIH CASOV
        ArrayList<String> casi = baza.Zasedeni_casi(ajdi);
        DefaultListModel dm = new DefaultListModel();
        for (String x : casi)
        {
            dm.addElement(x);
        }
        zasedendatum.setModel(dm);*/



    }

    LocalDate datum;

    private void setActionListeners()
    {
        rezuredi_button.addActionListener(e -> {
            if(tipoglas == true)
            {
                new urejanjeavtomobila();
            }
            else
            {
                new rezervacija(ajdi);
            }
        });

        picker.addDateChangeListener(e -> {
            datum = picker.getDate();

            ArrayList<String> damn = baza.CasiVDnevu(datum.toString());
            for (String s : damn) {
                //System.out.println(s);
                String[] cas = s.split(Pattern.quote(" | "), 2);

                /*for (String ca : cas) {
                    System.out.println("brale lej" + ca);
                }*/

                String[] ura1 = cas[0].split(" ", 2);
                String[] st1 = ura1[1].split(":", 3);
                //System.out.print(st1[0]);

                String najmanjsi = st1[0];

                String[] ura2 = cas[1].split(" ", 2);
                String[] st2 = ura2[1].split(":", 3);
                //System.out.print(st2[0]);

                String najvecji = st2[0];
                Integer najvecje = Integer.parseInt(najvecji);

                for (int i = 0; i <= 24; i++)
                {
                    System.out.println("eo ti i: " + i);
                    if (times[i].equals(najmanjsi))
                    {
                        times1[i] = "Rezervirano";
                        Integer envec = i + 1;
                        times2[envec] = "Rezervirano";
                        if(!envec.equals(najvecje))
                        {
                            int pos = findIndex(times, najvecji);
                            for (int o = i; o != pos; o++)
                            {
                                times1[o] = "Rezervirano";
                                Integer drugvec = o + 1 ;
                                times2[drugvec] = "Rezervirano";
                            }
                        }
                        else
                        {
                            break;
                        }
                    }

                }
            }
            System.out.println(" evo ti times1 :");
            for (String s : times1) {
                System.out.print(" " + s);
            }
            System.out.println(" eo ti zdj times2:");
            for (String s : times2) {
                System.out.print(" " + s);
            }


            urePoljenje();
        });

        rezervirajButton.addActionListener(e -> {
            String odUra = odCasiBox.getSelectedItem().toString();
            odUra += ":00:00";
            System.out.println("od ura je " + odUra);

            String doUra = doCasiBox.getSelectedItem().toString();
            doUra += ":00:00";
            System.out.println("do ura je " + doUra);

            String zac_datum = datum + " " + odUra;
            String kon_datum = datum + " " + doUra;

            System.out.println("od " + zac_datum + " do " + kon_datum);

            baza.Rezervacija(ajdi, ajdiupo, zac_datum, kon_datum);

        });
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }


    private void urePoljenje(){

        odCasiBox.removeAllItems();
        doCasiBox.removeAllItems();
        for (String x : times1)
        {
            odCasiBox.addItem(x);
        }
        for (String x : times2)
        {
            doCasiBox.addItem(x);
        }
    }

    // Linear-search function to find the index of an element
    public static int findIndex(String arr[], String t)
    {


        // if array is Null
        if (arr == null) {
            return -1;
        }

        // find length of array
        int len = arr.length;
        int i = 0;

        // traverse in the array
        while (i < len) {

            // if the i-th element is t
            // then return the index
            if (arr[i].equals(t)) {
                return i;
            }
            else {
                i = i + 1;
            }
        }
        return -1;
    }
}
