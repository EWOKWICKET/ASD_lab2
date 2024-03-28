import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        super("Storage DB");
        this.setSize(700, 700);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
