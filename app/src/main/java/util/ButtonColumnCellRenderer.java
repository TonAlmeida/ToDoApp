
package util;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ton618
 */
public class ButtonColumnCellRenderer extends DefaultTableCellRenderer {
    
    private String buttonType;
    
    public ButtonColumnCellRenderer (String buttonType) {
        this.buttonType = buttonType;
    }

    public String getButtonType() {
        return buttonType;
    }

    public void setButtonType(String buttonType) {
        this.buttonType = buttonType;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel label = (JLabel) super.getTableCellRendererComponent(table, 
                value, isSelected, hasFocus, row, column);

        label.setHorizontalAlignment(CENTER);
        
        if(!"edit".equals(buttonType)) {
            String deleteLocationIcon = "/home/ton618/Documents/Study/Programming"
                    + "/StartCourse-JAVA/TodoApp/app/build/resources/delete/close (5).png";
            label.setIcon(new javax.swing.ImageIcon(deleteLocationIcon));
        } else {
            String editLocationIcon = "/home/ton618/Documents/Study/Programming"
                    + "/StartCourse-JAVA/TodoApp/app/build/resources/edit/pencil (5).png";
            label.setIcon(new javax.swing.ImageIcon(editLocationIcon));
        }
        
        return label;
    }
}
