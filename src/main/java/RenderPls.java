import javax.swing.*;
import javax.swing.DefaultListCellRenderer;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.Icon;

public class RenderPls  extends DefaultListCellRenderer implements ListCellRenderer<Object> {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {


        Oglasi o = (Oglasi) value;



        setText(o.uporabnik_o);
        setIcon(o.getImg());

        if(isSelected)
        {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        }
        else
        {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        setEnabled(true);
        setFont(list.getFont());

        return this;
    }
}

