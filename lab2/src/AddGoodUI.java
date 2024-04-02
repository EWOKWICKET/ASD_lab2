/*
UI for adding a good
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddGoodUI extends JFrame implements ActionListener {
    Storage storage;
    WorkWithProductUI workWithProductUI;
    JTextField productName;
    JTextArea description;
    JTextField manufacturer;
    JSpinner amount;
    JSpinner price;
    JComboBox groups;
    ArrayList<Group> groupsList;
    JButton createProduct;
    JButton back;
    JFrame imageFrame;

    public AddGoodUI() {
        super("Додати товар");
        this.setSize(700, 500);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("lab2/Images/wareHouseIcon.png");
        this.setIconImage(icon.getImage());

        storage = Storage.getInstance();
        groupsList = storage.getGroups();
        setWindow();


        this.setVisible(true);
    }

    private void setWindow() {
        //create upper part label with picture
        addUpperPart();
        //create central part with buttons to deal with product
        addCentralPart();
        //create  lower part with back button
        addLowerPart();

    }

    private void addUpperPart() {
        JPanel upperPart = new JPanel(new BorderLayout());
        upperPart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        ImageIcon productPicture = new ImageIcon("lab2/Images/addProduct.png");//add picture
        JLabel productLabel = new JLabel(); // Create a JLabel to display the image
        productLabel.setIcon(productPicture);
        productLabel.setHorizontalAlignment(JLabel.CENTER);
        productLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        upperPart.add(productLabel, "West");

        JLabel workWithProductLabel = new JLabel("Додавання товару"); // Create a JLabel to display the name
        workWithProductLabel.setHorizontalAlignment(JLabel.CENTER);
        workWithProductLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        workWithProductLabel.setFont(new Font("Default", Font.BOLD, 17));
        upperPart.add(workWithProductLabel, "Center");

        this.add(upperPart, "North");
    }

    private void addCentralPart() {
        JPanel centralPart = new JPanel(new GridLayout(0, 2));
        centralPart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JPanel centralLeft = new JPanel(new GridLayout(2, 0));
        JPanel centralLeftUp = new JPanel(new GridLayout(2, 1));
        productName = new JTextField();
        createTextFieldWithLabelWithAndAddToPanel(productName, "Назва товару", centralLeftUp);
        manufacturer = new JTextField();
        createTextFieldWithLabelWithAndAddToPanel(manufacturer, "Виробник", centralLeftUp);
        centralLeft.add(centralLeftUp);
        description = new JTextArea();
        createAreaFieldWithLabelWithAndAddToPanel(description, "Опис", centralLeft);


        JPanel centralRight = new JPanel(new GridLayout(3, 0));

        groups = new JComboBox();
        createComboBoxWithLabelWithAndAddToPanel(groups, "Група", centralRight);

        amount = new JSpinner();
        createSpinnerWithLabelWithAndAddToPanel(amount, "Кількість", centralRight);

        price = new JSpinner();
        createSpinnerWithLabelWithAndAddToPanel(price, "Ціна", centralRight);
        centralPart.add(centralLeft);
        centralPart.add(centralRight);
        this.add(centralPart, "Center");
    }

    private void createComboBoxWithLabelWithAndAddToPanel(JComboBox comboBox, String labelText, JPanel originPanel) {
        JPanel labelAndTextPanel = new JPanel(new BorderLayout());
        labelAndTextPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        labelAndTextPanel.setBackground(Color.LIGHT_GRAY);
        JLabel label = new JLabel(labelText);

        labelAndTextPanel.add(label, "North");

        for (int i = 0; i < groupsList.size(); i++) {
            comboBox.addItem(groupsList.get(i).getName());
        }
        labelAndTextPanel.add(comboBox, "Center");

        originPanel.add(labelAndTextPanel);
    }

    private void createSpinnerWithLabelWithAndAddToPanel(JSpinner spinner, String labelText, JPanel originPanel) {
        JPanel labelAndTextPanel = new JPanel(new BorderLayout());
        labelAndTextPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        labelAndTextPanel.setBackground(Color.LIGHT_GRAY);
        JLabel label = new JLabel(labelText);

        labelAndTextPanel.add(label, "North");
        if (spinner.equals(amount)) {
            spinner.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        } else {
            spinner.setModel(new SpinnerNumberModel(0.1, 0.1, Integer.MAX_VALUE, 0.1));
        }
        labelAndTextPanel.add(spinner, "Center");

        originPanel.add(labelAndTextPanel);
    }

    private void createAreaFieldWithLabelWithAndAddToPanel(JTextArea textArea, String labelText, JPanel originPanel) {
        JPanel labelAndTextPanel = new JPanel(new BorderLayout());
        labelAndTextPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        labelAndTextPanel.setBackground(Color.LIGHT_GRAY);
        JLabel label = new JLabel(labelText);

        labelAndTextPanel.add(label, "North");
        JScrollPane textAreaScroll = new JScrollPane(textArea);
        labelAndTextPanel.add(textAreaScroll, "Center");

        originPanel.add(labelAndTextPanel);
    }

    private void createTextFieldWithLabelWithAndAddToPanel(JTextField textField, String labelText, JPanel originPanel) {
        JPanel labelAndTextPanel = new JPanel(new BorderLayout());
        labelAndTextPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        labelAndTextPanel.setBackground(Color.LIGHT_GRAY);
        JLabel label = new JLabel(labelText);

        labelAndTextPanel.add(label, "North");
        labelAndTextPanel.add(textField, "Center");

        originPanel.add(labelAndTextPanel);
    }

    private void addLowerPart() {
        JPanel lowerPanel = new JPanel(new FlowLayout());
        lowerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        back = new JButton();
        createButtonWithAndAddToPanel(back, "Повернутися назад", lowerPanel);
        lowerPanel.setBackground(Color.GRAY);
        createProduct = new JButton();
        createButtonWithAndAddToPanel(createProduct, "Додати товар", lowerPanel);
        this.add(lowerPanel, "South");
    }

    public void setWorkWithProductUI(WorkWithProductUI workWithProductUI) {
        this.workWithProductUI = workWithProductUI;
    }


    private void createButtonWithAndAddToPanel(JButton button, String buttonLabel, JPanel originPanel) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.GRAY);
        button.setText(buttonLabel);
        button.setHorizontalAlignment(JButton.CENTER);
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(220, 70));
        button.setFont(new Font("Default", Font.BOLD, 17));
        buttonPanel.add(button);
        originPanel.add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.setVisible(false);
        if (e.getSource().equals(createProduct)) {

            if (storage.findGood(productName.getText()).isEmpty() ||
                    !storage.findGood(productName.getText()).getFirst().getName().equals(productName.getText())) {
                String text = productName.getText();
                if (!text.isBlank()) {
                    if (!text.equals("Окуляри")) {
                        int groupNumb = groups.getSelectedIndex();
                        Group tempGr = groupsList.get(groupNumb);
                        tempGr.addGood(new Good(tempGr.getName(), productName.getText(), description.getText(), manufacturer.getText(), (Integer) amount.getValue(), Float.parseFloat(Double.toString((Double) price.getValue()))));
                        JOptionPane.showMessageDialog(null, "Товар додано", "Успіх", JOptionPane.INFORMATION_MESSAGE);
                        this.setVisible(true);
                    } else {
                        ImageIcon imageIcon = new ImageIcon("lab2/Images/estrEgg.gif");

                        imageFrame = new JFrame("Easter Egg");
                        imageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        imageFrame.setSize(700, 500);
                        imageFrame.setLayout(new BorderLayout());
                        imageFrame.setLocationRelativeTo(null);
                        imageFrame.setVisible(true);

                        JLabel imageLabel = new JLabel(imageIcon);
                        imageLabel.setHorizontalAlignment(JLabel.CENTER);
                        imageLabel.setVerticalAlignment(JLabel.CENTER);

                        imageFrame.add(imageLabel, "Center");
                        JPanel backButton = new JPanel(new FlowLayout());
                        backButton.setBackground(Color.GRAY);
                        back = new JButton();
                        createButtonWithAndAddToPanel(back, "Повернутися назад", backButton);

                        imageFrame.add(backButton, "South");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Не можна створити товар з порожнім ім'ям", "Помилка", JOptionPane.ERROR_MESSAGE);
                    this.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Така назва товару вже існує", "Помилка", JOptionPane.ERROR_MESSAGE);
                this.setVisible(true);
            }
        } else {
            if (imageFrame != null) {
                imageFrame.setVisible(false);
            }
            workWithProductUI.returned();
        }

    }
}