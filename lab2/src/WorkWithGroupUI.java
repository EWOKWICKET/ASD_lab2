import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkWithGroupUI extends JFrame implements ActionListener {
    MainMenu menu;
    JButton addNewGroup;
    JButton removeGroup;
    JButton changeGroup;
    JButton back;

    public WorkWithGroupUI() {
        super("Робота з групами");
        this.setSize(700, 500);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ImageIcon icon=new ImageIcon("lab2/Images/wareHouseIcon.png");
        this.setIconImage(icon.getImage());

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
        JPanel upperPart=new JPanel(new BorderLayout());
        upperPart.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
        ImageIcon productPicture = new ImageIcon("lab2/Images/workWithGroup.jpg");//add picture
        JLabel productLabel = new JLabel(); // Create a JLabel to display the image
        productLabel.setIcon(productPicture);
        productLabel.setHorizontalAlignment(JLabel.CENTER);
        productLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        upperPart.add(productLabel, "West");

        JLabel workWithProductLabel = new JLabel("Робота з групами"); // Create a JLabel to display the name
        workWithProductLabel.setHorizontalAlignment(JLabel.CENTER);
        workWithProductLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        workWithProductLabel.setFont(new Font("Default", Font.BOLD, 17));
        upperPart.add(workWithProductLabel, "Center");

        this.add(upperPart, "North");
    }

    private void addCentralPart(){
        JPanel centralPart=new JPanel(new GridLayout(0,1));
        centralPart.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        JPanel centralLeft=new JPanel(new GridLayout(3,0));
        addNewGroup = new JButton();
        createButtonWithAndAddToPanel(addNewGroup,"Додати групу",centralLeft);

        removeGroup = new JButton();
        createButtonWithAndAddToPanel(removeGroup,"Видалити групу",centralLeft);

        changeGroup = new JButton();
        createButtonWithAndAddToPanel(changeGroup,"Редагувати групу",centralLeft);

        centralPart.add(centralLeft);
        this.add(centralPart,"Center");
    }
    private void addLowerPart(){
        JPanel lowerPanel=new JPanel(new FlowLayout());
        lowerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
        back = new JButton();
        createButtonWithAndAddToPanel(back,"Повернутися назад",lowerPanel);
    lowerPanel.setBackground(Color.GRAY);
        this.add(lowerPanel,"South");
    }

    public void setMainMenu(MainMenu menu) {
        this.menu = menu;
    }


    private void createButtonWithAndAddToPanel(JButton button,String buttonLabel, JPanel originPanel){
        JPanel buttonPanel=new JPanel();
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
        button.setPreferredSize(new Dimension(220,70));
        button.setFont(new Font("Default", Font.BOLD, 17));
        buttonPanel.add(button);
        originPanel.add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addNewGroup)) {
            System.out.println("Adding");
        } else if (e.getSource().equals(removeGroup)) {
            System.out.println("Removing");
        } else if (e.getSource().equals(changeGroup)){
            System.out.println("Changing");
        } else {
            this.setVisible(false);
            menu.returned();
        }
    }
}