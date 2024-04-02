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
            String findName = textField.getText();
            StringBuilder text = new StringBuilder();
            if (findName.isEmpty()) {
                text.append("Не знайдено жодного товару");
            } else {
                for (Good good : storage.findGood(findName)) {
                    text.append(good + "\n");
                }
                if (text.isEmpty()) {
                    text.append("Не знайдено жодного товару");
                } else {
                    text.insert(0, "Знайдено:\n");
                }
            }

            output.setText(text.toString());
        }
    }
}
