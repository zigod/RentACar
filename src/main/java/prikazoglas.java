import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.CalendarPanel;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
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
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
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
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import com.github.lgooddatepicker.optionalusertools.CalendarListener;
import com.github.lgooddatepicker.zinternaltools.YearMonthChangeEvent;
import java.awt.Checkbox;
import java.time.YearMonth;


public class prikazoglas {
    private JPanel main;
    private JTextArea avtoarea;
    private JLabel Imagelabel;
    private JLabel cenatext;
    private JLabel naslovtext;
    private JLabel uporabniktext;
    private JButton rezuredi_button;
    private DateTimePicker picker;
    private CalendarPanel zasedeniCasi;
    private JList zasedendatum;
    private JLabel imagelabel;

    private Integer ajdi;
    private Integer ajdiupo;
    private Boolean tipoglas;


    public prikazoglas(Integer ido,Integer idu)
    {
        JFrame frame = new JFrame("RentACar");
        frame.setContentPane(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500, 320);
        frame.setVisible(true);
        ajdi = ido;
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

        DatePickerSettings dateSettings = new DatePickerSettings();
        DatePicker datePicker = new DatePicker(dateSettings);
        dateSettings.setHighlightPolicy(new SampleHighlightPolicy());
        dateSettings.setVetoPolicy(new SampleDateVetoPolicy());

        zasedeniCasi.setSettings(dateSettings);
    }

    private void Polnjenje()
    {
        Oglasi poglas = baza.IzpisOglasa(ajdi);
        cenatext.setText("Cena: " + poglas.cena.toString() + "€ (na uro)");
        naslovtext.setText("Naslov prevzema: " + poglas.Naslov + ", " + poglas.kraj);
        uporabniktext.setText("Lastnik avtomobila: " + poglas.imeuporabnika + " " + poglas.priimek);
        ImageIcon slika = new ImageIcon("src\\main\\img\\" + poglas.pot_slika);
        Imagelabel.setIcon(slika);
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

        picker.addDateTimeChangeListener(e -> {
            TimePicker time = picker.getTimePicker();
            System.out.println(time);

            DatePicker date = picker.getDatePicker();

        });
    }


    private static class SampleDateVetoPolicy implements DateVetoPolicy {

        /**
         * isDateAllowed, Return true if a date should be allowed, or false if a date should be
         * vetoed.
         */
        @Override
        public boolean isDateAllowed(LocalDate date) {
            // Disallow days 7 to 11.
            if ((date.getDayOfMonth() >= 7) && (date.getDayOfMonth() <= 11)) {
                return false;
            }
            // Disallow odd numbered saturdays.
            if ((date.getDayOfWeek() == DayOfWeek.SATURDAY) && ((date.getDayOfMonth() % 2) == 1)) {
                return false;
            }
            // Allow all other days.
            return true;
        }
    }

    private static class SampleHighlightPolicy implements DateHighlightPolicy {

        /**
         * getHighlightInformationOrNull, Implement this function to indicate if a date should be
         * highlighted, and what highlighting details should be used for the highlighted date.
         *
         * If a date should be highlighted, then return an instance of HighlightInformation. If the
         * date should not be highlighted, then return null.
         *
         * You may (optionally) fill out the fields in the HighlightInformation class to give any
         * particular highlighted day a unique foreground color, background color, or tooltip text.
         * If the color fields are null, then the default highlighting colors will be used. If the
         * tooltip field is null (or empty), then no tooltip will be displayed.
         *
         * Dates that are passed to this function will never be null.
         */
        @Override
        public HighlightInformation getHighlightInformationOrNull(LocalDate date) {
            // Highlight a chosen date, with a tooltip and a red background color.
            if (date.getDayOfMonth() == 25) {
                return new HighlightInformation(Color.red, null, "It's the 25th!");
            }
            // Highlight all Saturdays with a unique background and foreground color.
            if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
                return new HighlightInformation(Color.orange, Color.yellow, "It's Saturday!");
            }
            // Highlight all Sundays with default colors and a tooltip.
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
                return new HighlightInformation(null, null, "It's Sunday!");
            }
            // All other days should not be highlighted.
            return null;
        }
    }







}
