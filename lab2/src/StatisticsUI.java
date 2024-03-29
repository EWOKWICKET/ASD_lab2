import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatisticsUI extends JFrame implements ActionListener {
    JButton showAllStorageGoods;
    JButton showAllGroupGoods;
    JButton showStoragePrice;
    JButton showGroupPrice;
    JButton back;

    public StatisticsUI() {
        super("Storage DB");
        this.setSize(700, 700);
        this.setLayout(new GridLayout(5, 1));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setAllButtons();

        this.add(showAllStorageGoods);
        this.add(showAllGroupGoods);
        this.add(showStoragePrice);
        this.add(showGroupPrice);
        this.add(back);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void setAllButtons() {
        showAllStorageGoods = new JButton("Show all storage goods");
        showAllStorageGoods.addActionListener(this);


        showAllGroupGoods = new JButton("Show all group goods");
        showAllGroupGoods.addActionListener(this);

        showStoragePrice = new JButton("Show storage price");
        showStoragePrice.addActionListener(this);

        showGroupPrice = new JButton("Show group price");
        showGroupPrice.addActionListener(this);

        back = new JButton("Back");
        back.addActionListener(this);
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
//            MainMenu.returned();
        }
    }
}