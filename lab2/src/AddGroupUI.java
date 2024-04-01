import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddGroupUI extends JFrame implements ActionListener {
    Storage storage;
    WorkWithGroupUI workWithGroupUI;
    JTextField groupName;
    JButton addGroup;
    JButton back;

    public AddGroupUI(){
        super("Додати групу");
        this.setSize(700, 500);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("lab2/Images/wareHouseIcon.png");
        this.setIconImage(icon.getImage());

        storage = Storage.getInstance();
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

        JLabel workWithProductLabel = new JLabel("Додавання групи"); // Create a JLabel to display the name
        workWithProductLabel.setHorizontalAlignment(JLabel.CENTER);
        workWithProductLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        workWithProductLabel.setFont(new Font("Default", Font.BOLD, 17));
        upperPart.add(workWithProductLabel, "Center");

        this.add(upperPart, "North");
    }

    private void addCentralPart() {
        JPanel centralPart = new JPanel(new BorderLayout());
        centralPart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JPanel centralUp = new JPanel(new GridLayout(1, 0));
        groupName=new JTextField();
        createTextFiedWithLabelWithAndAddToPanel(groupName,"Назва групи:", centralUp);

        JPanel centralDown = new JPanel(new GridLayout(1, 0));
        addGroup=new JButton();
        createButtonWithAndAddToPanel(addGroup,"Додати групу", centralDown);


        centralPart.add(centralUp, "North");
        centralPart.add(centralDown, "Center");
        this.add(centralPart, "Center");

    }


    private void createTextFiedWithLabelWithAndAddToPanel(JTextField textField, String labelText, JPanel originPanel) {
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

    public void setWorkWithGroupUI(WorkWithGroupUI workWithGroupUI) {
        this.workWithGroupUI = workWithGroupUI;
    }
    private void createButtonWithAndAddToPanel(JButton button, String buttonLabel, JPanel originPanel) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        if(!button.equals(back)){
            buttonPanel.setBackground(Color.LIGHT_GRAY);
        }
        else{
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
        if(e.getSource().equals(addGroup)){
            if(Storage.findGroup(groupName.getText())==-1) {
                if (!groupName.getText().isEmpty()) {
                    storage.addGroup(new Group(groupName.getText()));
                    JOptionPane.showMessageDialog(null, "Групу додано", "Успіх", JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Не можна створити групу з порожнім ім'ям", "Помилка", JOptionPane.ERROR_MESSAGE);
                    this.setVisible(true);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Така назва групи вже існує", "Помилка", JOptionPane.ERROR_MESSAGE);
                this.setVisible(true);
            }
        }
        else {
            workWithGroupUI.returned();
        }

    }
}
