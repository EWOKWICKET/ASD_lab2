import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DeleteGroupUI extends JFrame implements ActionListener {
    Storage storage;
    WorkWithGroupUI workWithGroupUI;
    ArrayList<Group> groupsList;
    JComboBox groups;
    JButton deleteGroup;
    JButton back;

    public DeleteGroupUI() {
        super("Видалити товар");
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
        ImageIcon productPicture = new ImageIcon("lab2/Images/deleteProduct.png");//add picture
        JLabel productLabel = new JLabel(); // Create a JLabel to display the image
        productLabel.setIcon(productPicture);
        productLabel.setHorizontalAlignment(JLabel.CENTER);
        productLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        upperPart.add(productLabel, "West");

        JLabel workWithProductLabel = new JLabel("Видалення групи"); // Create a JLabel to display the name
        workWithProductLabel.setHorizontalAlignment(JLabel.CENTER);
        workWithProductLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        workWithProductLabel.setFont(new Font("Default", Font.BOLD, 17));
        upperPart.add(workWithProductLabel, "Center");

        this.add(upperPart, "North");
    }

    private void addCentralPart() {
        JPanel centralPart = new JPanel(new GridLayout(0, 2));
        centralPart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JPanel centralLeft = new JPanel(new GridLayout(1, 0));
        centralLeft.setBackground(Color.lightGray);
        groups = new JComboBox();
        createComboBoxWithLabelWithAndAddToPanel(groups, "Групи", centralLeft);


        JPanel centralRight = new JPanel(new GridLayout(1, 0));
        centralRight.setBackground(Color.lightGray);
        deleteGroup = new JButton();
        createButtonWithAndAddToPanel(deleteGroup, "Видалити групу", centralRight);

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

    private void addLowerPart() {
        JPanel lowerPanel = new JPanel(new FlowLayout());
        lowerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        back = new JButton();
        createButtonWithAndAddToPanel(back, "Повернутися назад", lowerPanel);
        lowerPanel.setBackground(Color.GRAY);
        this.add(lowerPanel, "South");
    }

    public void setWorkWithGroupUI(WorkWithGroupUI workWithGroupUI) {
        this.workWithGroupUI = workWithGroupUI;
    }


    private void createButtonWithAndAddToPanel(JButton button, String buttonLabel, JPanel originPanel) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        if (button.equals(deleteGroup)) {
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
        if (e.getSource().equals(deleteGroup)) {
            int groupNumb = groups.getSelectedIndex();
            Group gr = groupsList.get(groupNumb);
            storage.deleteGroup(gr);
            JOptionPane.showMessageDialog(null, "Групу видалено", "Успіх", JOptionPane.INFORMATION_MESSAGE);

            workWithGroupUI.returned();

        } else {
            workWithGroupUI.returned();
        }

    }
}