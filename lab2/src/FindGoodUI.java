import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindGoodUI extends OutputUI implements ActionListener {
    Storage storage;
    JTextField textField;
    JButton enter;
     public FindGoodUI() {
         super();
         storage = Storage.getInstance();
         setInputUI();
     }

     private void setInputUI() {
         textField = new JTextField();
         textField.setPreferredSize(new Dimension(220, 60));
         textField.setFont(new Font("Default", Font.PLAIN, 17));
         lowerPanel.add(textField);

         enter = new JButton();
         createButtonWithAndAddToPanel(enter, "Знайти", lowerPanel);
     }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(back)) {
            this.setVisible(false);
            menu.returned();
        } else {
            StringBuilder text = new StringBuilder();
            for (Good good: storage.findGood(textField.getText())) {
                text.append(good + "\n");
            }
            if (text.isEmpty()) {
                text.append("No goods found");
            }
            output.setText("Found:\n" + text.toString());
        }
    }
}
