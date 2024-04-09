/*
UI for deleting a good
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteGoodUI extends JFrame implements ActionListener {
    Storage storage;
    WorkWithProductUI workWithProductUI;
    JTextField oldProductName;
    String rememOldProdName;
    ArrayList<Good> productsList;
    JComboBox products;
    JButton deleteProduct;
    JButton back;

    public DeleteGoodUI() {
        super("Видалити товар");
        this.setSize(700, 500);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("lab2/Images/wareHouseIcon.png");
        this.setIconImage(icon.getImage());

        storage = Storage.getInstance();
        productsList = storage.findGood("");
        setWindow();

        class listOfGoodsUpdateThread implements Runnable {
            public void run() {
                while (true) {
                    if (!oldProductName.getText().equals(rememOldProdName)) {
                        productsList = storage.findGood(oldProductName.getText());
                        products.removeAllItems();
                        for (int i = 0; i < productsList.size(); i++) {
                            products.addItem(productsList.get(i).getName());
                        }
                        rememOldProdName = oldProductName.getText();
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("Error");
                    }
                }
            }
        }
        Thread updateProdList = new Thread(new listOfGoodsUpdateThread());
        updateProdList.start();
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
        ImageIcon productPicture = new ImageIcon("lab2/Images/deleteProduct.png");//add picture
        JLabel productLabel = new JLabel(); // Create a JLabel to display the image
        productLabel.setIcon(productPicture);
        productLabel.setHorizontalAlignment(JLabel.CENTER);
        productLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        upperPart.add(productLabel, "West");

        JLabel workWithProductLabel = new JLabel("Видалення товару"); // Create a JLabel to display the name
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
        oldProductName = new JTextField();
        createTextFieldWithLabelWithAndAddToPanel(oldProductName, "Назва товару", centralLeft);

        products = new JComboBox();
        createComboBoxWithLabelWithAndAddToPanel(products, "Товари", centralLeft);


        JPanel centralRight = new JPanel(new GridLayout(1, 0));

        deleteProduct = new JButton();
        createButtonWithAndAddToPanel(deleteProduct, "Видалити товар", centralRight);

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

        for (int i = 0; i < productsList.size(); i++) {
            comboBox.addItem(productsList.get(i).getName());
        }
        labelAndTextPanel.add(comboBox, "Center");

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
        this.add(lowerPanel, "South");
    }

    public void setWorkWithProductUI(WorkWithProductUI workWithProductUI) {
        this.workWithProductUI = workWithProductUI;
    }


    private void createButtonWithAndAddToPanel(JButton button, String buttonLabel, JPanel originPanel) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        if (button.equals(deleteProduct)) {
            buttonPanel.setBackground(Color.LIGHT_GRAY);
        } else {
            buttonPanel.setBackground(Color.GRAY);
        }
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
        if (e.getSource().equals(deleteProduct)) {
            int prodNumb = products.getSelectedIndex();
            if (prodNumb != -1) {
                Good prod = productsList.get(prodNumb);
                storage.deleteGood(prod.getName());
                JOptionPane.showMessageDialog(null, "Товар видалено", "Успіх", JOptionPane.INFORMATION_MESSAGE);
                oldProductName.setText("");
                rememOldProdName = "NO text";
                workWithProductUI.returned();
            } else {
                JOptionPane.showMessageDialog(null, "Товар не обрано", "Упс", JOptionPane.INFORMATION_MESSAGE);
                this.setVisible(true);
            }
        } else {
            workWithProductUI.returned();
        }

    }
}