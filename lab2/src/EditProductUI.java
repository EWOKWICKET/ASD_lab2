import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProductUI extends JFrame implements ActionListener {
    JButton addNewProduct;
    JButton removeProduct;
    JButton changeProduct;
    JButton addAmount;
    JButton reduceAmount;
    JButton back;

    public EditProductUI() {
        super("Storage DB");
        this.setSize(700, 700);
        this.setLayout(new GridLayout(6, 1));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setAllButtons();

        this.add(addNewProduct);
        this.add(removeProduct);
        this.add(changeProduct);
        this.add(addAmount);
        this.add(reduceAmount);
        this.add(back);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void setAllButtons() {
        addNewProduct = new JButton("Add new product");
        addNewProduct.addActionListener(this);


        removeProduct = new JButton("Remove product");
        removeProduct.addActionListener(this);

        changeProduct = new JButton("Change product");
        changeProduct.addActionListener(this);

        addAmount = new JButton("Add amount");
        addAmount.addActionListener(this);

        reduceAmount = new JButton("Reduce amount");
        reduceAmount.addActionListener(this);

        back = new JButton("Back");
        back.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(addNewProduct)) {
            System.out.println("Adding");
        } else if (e.getSource().equals(removeProduct)) {
            System.out.println("Removing");
        } else if (e.getSource().equals(changeProduct)) {
            System.out.println("Changing");
        } else if (e.getSource().equals(addAmount)) {
            System.out.println("Adding amount");
        } else if (e.getSource().equals(removeProduct)){
            System.out.println("Reducing amount");
        } else {
            this.setVisible(false);
//            MainMenu.returned();
        }
    }
}
