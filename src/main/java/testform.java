import javax.swing.*;
import java.util.ArrayList;

public class testform {


    private JPanel fieldtest;
    private JComboBox krajicombo;

    public testform()
    {

        JFrame frame = new JFrame("My First GUI");
        frame.setContentPane(fieldtest);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(300, 300);

        frame.setVisible(true);
        Fill_krajcombo();

    }

    private void Fill_krajcombo()
    {
        DefaultComboBoxModel mod = new DefaultComboBoxModel();
        mod.addAll(baza.SelectKraji());
        krajicombo.setModel(mod);
    }

}

