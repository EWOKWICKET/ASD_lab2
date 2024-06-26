/*
UI for working with goods
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkWithProductUI extends JFrame implements ActionListener {
    MainMenu menu;
    JButton addNewProduct;
    JButton removeProduct;
    JButton changeProduct;
    JButton addAmount;
    JButton reduceAmount;
    JButton back;

    public WorkWithProductUI() {
        super("Робота з товарами");
        this.setSize(700, 500);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("lab2/Images/wareHouseIcon.png").getImage());

        setWindow();
        this.setVisible(false);

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
        ImageIcon productPicture = new ImageIcon("lab2/Images/workWithProduct.jpg");//add picture
        JLabel productLabel = new JLabel(); // Create a JLabel to display the image
        productLabel.setIcon(productPicture);
        productLabel.setHorizontalAlignment(JLabel.CENTER);
        productLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        upperPart.add(productLabel, "West");

        JLabel workWithProductLabel = new JLabel("Робота з продуктом"); // Create a JLabel to display the name
        workWithProductLabel.setHorizontalAlignment(JLabel.CENTER);
        workWithProductLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        workWithProductLabel.setFont(new Font("Default", Font.BOLD, 17));
        upperPart.add(workWithProductLabel, "Center");

        this.add(upperPart, "North");
    }

    private void addCentralPart() {
        JPanel centralPart = new JPanel(new GridLayout(0, 2));

        JPanel centralLeft = new JPanel(new GridLayout(3, 0));
        addNewProduct = new JButton();
        createButtonWithAndAddToPanel(addNewProduct, "Додати новий товар", centralLeft);

        removeProduct = new JButton();
        createButtonWithAndAddToPanel(removeProduct, "Видалити товар", centralLeft);

        changeProduct = new JButton();
        createButtonWithAndAddToPanel(changeProduct, "Редагувати товар", centralLeft);

        JPanel centralRight = new JPanel(new GridLayout(2, 0));
        addAmount = new JButton();
        createButtonWithAndAddToPanel(addAmount, "Додати кількість", centralRight);

        reduceAmount = new JButton();
        createButtonWithAndAddToPanel(reduceAmount, "Зменшити кількість", centralRight);

        centralPart.add(centralLeft);
        centralPart.add(centralRight);
        this.add(centralPart, "Center");
    }

    private void addLowerPart() {
        JPanel lowerPanel = new JPanel(new FlowLayout());
        lowerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        lowerPanel.setBackground(Color.GRAY);
        back = new JButton();
        createButtonWithAndAddToPanel(back, "Повернутися назад", lowerPanel);

        this.add(lowerPanel, "South");
    }

    public void setMainMenu(MainMenu menu) {
        this.menu = menu;
    }


    private void createButtonWithAndAddToPanel(JButton button, String buttonLabel, JPanel originPanel) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        if (!button.equals(back)) {
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

    public void returned() {
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        if (e.getSource().equals(addNewProduct)) {
            AddGoodUI addGoodUI = new AddGoodUI();
            addGoodUI.setWorkWithProductUI(this);
            addGoodUI.setVisible(true);
        } else if (e.getSource().equals(removeProduct)) {
            DeleteGoodUI delGoodUI = new DeleteGoodUI();
            delGoodUI.setWorkWithProductUI(this);
            delGoodUI.setVisible(true);
        } else if (e.getSource().equals(changeProduct)) {
            EditGoodUI editGoodUI = new EditGoodUI();
            editGoodUI.setWorkWithProductUI(this);
            editGoodUI.setVisible(true);
        } else if (e.getSource().equals(addAmount)) {
            AddQuantityOfProductUI addProd = new AddQuantityOfProductUI();
            addProd.setWorkWithProductUI(this);
            addProd.setVisible(true);
        } else if (e.getSource().equals(reduceAmount)) {
            RedQuantityOfProductUI reduceProd = new RedQuantityOfProductUI();
            reduceProd.setWorkWithProductUI(this);
            reduceProd.setVisible(true);
        } else {
            this.setVisible(false);
            menu.returned();
        }
    }
}
