import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    JButton workWithGoods;
    JButton workWithGroups;
    JButton statistics;
    JButton saveData;
    JButton closeProgram;
    EditProductUI prodUI;
    EditGroupUI groupUI;
    StatisticsUI statUI;
    JFrame mainFrame;

    public MainMenu() {
        super("Програма для роботи з складом");
        this.setSize(700, 500);
        this.setLayout(new GridLayout(1, 2));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon icon=new ImageIcon("lab2/wareHouseIcon.png");
        this.setIconImage(icon.getImage());
        init();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void init() {
        // add photo to left part of window
        addPhoto();
        //add menuPanel
        addMenuPanel();

    }

    private void addPhoto() {
        ImageIcon wareHousePicture = new ImageIcon("lab2/WareHouse.png");
        // Create a JLabel to display the image
        JLabel wareHouseLabel = new JLabel();
        wareHouseLabel.setIcon(wareHousePicture);
        wareHouseLabel.setHorizontalAlignment(JLabel.CENTER);
        wareHouseLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        this.add(wareHouseLabel);
    }

    private void addMenuPanel() {
        JPanel menuPanel=new JPanel();
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
        menuPanel.setLayout(new BorderLayout());
        //add label
        addMenuLabel(menuPanel);
        //add buttons
        setAllButtons(menuPanel);
        this.add(menuPanel);
    }

    private void addMenuLabel(JPanel menuPanel){
        JLabel menuLabel=new JLabel("Меню");
        menuLabel.setFont(new Font("Default", Font.BOLD, 50));
        menuLabel.setHorizontalAlignment(JLabel.CENTER);
        menuLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        menuPanel.add(menuLabel, "North");

    }
    private void setAllButtons(JPanel menuPanel) {
        JPanel centerButtonPanel=new JPanel();
        centerButtonPanel.setLayout(new GridLayout(3,1));

        JPanel workWithGoodsPanel=new JPanel();
        workWithGoodsPanel.setLayout(new FlowLayout());
        workWithGoods = new JButton("Робота з товарами");
        workWithGoods.addActionListener(this);
        workWithGoods.setPreferredSize(new Dimension(220,70));
        workWithGoods.setFont(new Font("Default", Font.BOLD, 17));
        workWithGoodsPanel.add(workWithGoods);

        JPanel workWithGroupsPanel=new JPanel();
        workWithGroupsPanel.setLayout(new FlowLayout());
        workWithGroups = new JButton("Робота з групами");
        workWithGroups.addActionListener(this);
        workWithGroups.setPreferredSize(new Dimension(220,70));
        workWithGroups.setFont(new Font("Default", Font.BOLD, 17));
        workWithGroupsPanel.add(workWithGroups);

        JPanel statisticsPanel=new JPanel();
        statisticsPanel.setLayout(new FlowLayout());
        statistics = new JButton("Вивести статистику");
        statistics.addActionListener(this);
        statistics.setPreferredSize(new Dimension(220,70));
        statistics.setFont(new Font("Default", Font.BOLD, 17));
        statisticsPanel.add(statistics);

        centerButtonPanel.add(workWithGoodsPanel, "North");
        centerButtonPanel.add(workWithGroupsPanel, "Center");
        centerButtonPanel.add(statisticsPanel, "South");
        menuPanel.add(centerButtonPanel);

        JPanel lowButtonPanel=new JPanel();
        lowButtonPanel.setLayout(new BorderLayout());
        saveData = new JButton("<html>Зберегти дані<br> в файл</html>");
        saveData.addActionListener(this);
        saveData.setPreferredSize(new Dimension(160,70));
        saveData.setFont(new Font("Default", Font.BOLD, 16));

        closeProgram = new JButton("<html>Закрити<br> програму</html>");
        closeProgram.addActionListener(this);
        closeProgram.setPreferredSize(new Dimension(160,70));
        closeProgram.setFont(new Font("Default", Font.BOLD, 16));

        lowButtonPanel.add(saveData,"West");
        lowButtonPanel.add(closeProgram,"East");
        menuPanel.add(lowButtonPanel, "South");
        setWindows();
    }

    private void setWindows() {
        prodUI = new EditProductUI();
        prodUI.setVisible(false);
        prodUI.setMainMenu(this);

        groupUI = new EditGroupUI();
        groupUI.setVisible(false);
        groupUI.setMainMenu(this);

        statUI = new StatisticsUI();
        statUI.setVisible(false);
        statUI.setMainMenu(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        if (e.getSource().equals(workWithGoods)) {
            prodUI.setVisible(true);
        } else if (e.getSource().equals(workWithGroups)) {
            groupUI.setVisible(true);
        } else if(e.getSource().equals(statistics)){
            statUI.setVisible(true);
        } else if (e.getSource().equals(saveData)) {
            //method to save data in file
            JOptionPane.showMessageDialog(null, "Дані успішно збережено.", "Успіх", JOptionPane.INFORMATION_MESSAGE);
            MainMenu temp=this;
            temp.setVisible(true);
        }
        else{
            int option=JOptionPane.showConfirmDialog(null, "Ви точно хочете закрити програму?\nНезбережені дані буде назавжди втрачено","!!!", JOptionPane.YES_NO_OPTION);
            if(option==JOptionPane.YES_OPTION){
                System.exit(0);
            }
            else{
                MainMenu temp=this;
                temp.setVisible(true);
            }
        }
    }

    public void returned() {
        this.setVisible(true);
    }
}
