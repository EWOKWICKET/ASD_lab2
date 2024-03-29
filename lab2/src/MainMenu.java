import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    JButton workWithGoods;
    JButton workWithGroups;
    JButton statistics;
    EditProductUI prodUI;
    EditGroupUI groupUI;
    StatisticsUI statUI;

    public MainMenu() {
        super("Storage DB");
        this.setSize(700, 700);
        this.setLayout(new GridLayout(3, 1));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setAllButtons();

        this.add(workWithGroups);
        this.add(workWithGoods);
        this.add(statistics);

        this.setVisible(true);
        this.setLocationRelativeTo(null);

        prodUI = new EditProductUI();
        groupUI = new EditGroupUI();
        statUI = new StatisticsUI();
        prodUI.setVisible(false);
        groupUI.setVisible(false);
        statUI.setVisible(false);
    }

    private void setAllButtons() {
        workWithGoods = new JButton("Work with goods");
        workWithGoods.addActionListener(this);


        workWithGroups = new JButton("Work with groups");
        workWithGroups.addActionListener(this);

        statistics = new JButton("Show statistics");
        statistics.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        if (e.getSource().equals(workWithGoods)) {
            prodUI.setVisible(true);
        } else if (e.getSource().equals(workWithGroups)) {
            groupUI.setVisible(true);
        } else {
            statUI.setVisible(true);
        }
    }

//    public static void returned() {
//        this.setVisible(true);
//    }
}
