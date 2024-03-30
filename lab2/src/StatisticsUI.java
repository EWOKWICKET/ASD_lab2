import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsUI extends JFrame implements ActionListener {
    MainMenu menu;
    JButton showAllStorageGoods;
    JButton showAllGroupGoods;
    JButton showStoragePrice;
    JButton showGroupPrice;
    JButton back;

    public StatisticsUI() {
        super("Статистика");
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
        ImageIcon productPicture = new ImageIcon("lab2/Images/stonks.jpg");//add picture

        JLabel productLabel = new JLabel(); // Create a JLabel to display the image
        productLabel.setIcon(productPicture);
        productLabel.setHorizontalAlignment(JLabel.CENTER);
        productLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        upperPart.add(productLabel, "West");

        JLabel workWithProductLabel = new JLabel("Статистика"); // Create a JLabel to display the name
        workWithProductLabel.setHorizontalAlignment(JLabel.CENTER);
        workWithProductLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        workWithProductLabel.setFont(new Font("Default", Font.BOLD, 17));
        upperPart.add(workWithProductLabel, "Center");

        this.add(upperPart, "North");
    }

    private void addCentralPart(){
        JPanel centralPart=new JPanel(new GridLayout(0,1));
        centralPart.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));

        JPanel centralLeft=new JPanel(new GridLayout(4,0));

        showAllStorageGoods = new JButton();
        createButtonWithAndAddToPanel(showAllStorageGoods,"Вивід усіх товарів",centralLeft);

        showAllGroupGoods = new JButton();
        createButtonWithAndAddToPanel(showAllGroupGoods,"Вивід усіх товарів по групі",centralLeft);

        showStoragePrice = new JButton();
        createButtonWithAndAddToPanel(showStoragePrice,"Загальна вартість товарів на складі",centralLeft);

        showGroupPrice = new JButton();
        createButtonWithAndAddToPanel(showGroupPrice,"Загальна вартість товарів у групі товарів",centralLeft);

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
            button.setPreferredSize(new Dimension(400,55));
        }
        else{
            buttonPanel.setBackground(Color.GRAY);
            button.setPreferredSize(new Dimension(220,60));
        }
        button.setText(buttonLabel);
        button.setHorizontalAlignment(JButton.CENTER);
        button.addActionListener(this);

        button.setFont(new Font("Default", Font.BOLD, 17));
        buttonPanel.add(button);
        originPanel.add(buttonPanel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(showAllStorageGoods)) {
            System.out.println("Showing storage goods");
        } else if (e.getSource().equals(showAllGroupGoods)) {
            System.out.println("Showing group goods");
        } else if (e.getSource().equals(showStoragePrice)) {
            System.out.println("Showing storage price");
        } else if (e.getSource().equals(showGroupPrice)){
            System.out.println("Showing group price");
        } else {
            this.setVisible(false);
            menu.returned();
        }
    }
}