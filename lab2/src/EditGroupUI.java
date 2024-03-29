import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditGroupUI extends JFrame implements ActionListener {
    JButton addNewGroup;
    JButton removeGroup;
    JButton changeGroup;
    JButton back;

    public EditGroupUI() {
        super("Storage DB");
        this.setSize(700, 700);
        this.setLayout(new GridLayout(4, 1));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        setAllButtons();

        this.add(addNewGroup);
        this.add(removeGroup);
        this.add(changeGroup);
        this.add(back);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    private void setAllButtons() {
        addNewGroup = new JButton("Add new group");
        addNewGroup.addActionListener(this);


        removeGroup = new JButton("Remove group");
        removeGroup.addActionListener(this);

        changeGroup = new JButton("Change group");
        changeGroup.addActionListener(this);

        back = new JButton("Back");
        back.addActionListener(this);
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
//            MainMenu.returned();
        }
    }
}