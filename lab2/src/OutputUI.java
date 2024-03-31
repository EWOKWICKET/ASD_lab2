import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OutputUI extends JFrame implements ActionListener {
    MainMenu menu;
    private static OutputUI instance;
    StatisticsUI stats;
    JButton back;
    JTextArea output;
    JScrollPane scroll;

    private OutputUI() {
        super("Output");
        this.setSize(700, 500);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("lab2/Images/wareHouseIcon.png").getImage());

        setOutput();

        this.setVisible(false);
    }

    public static OutputUI getInstance() {
        if (instance == null) {
            instance  = new OutputUI();
        }
        return instance;
    }

    public void setStatisticsUI (StatisticsUI stats) {
        this.stats = stats;
    }
    public void setMainMenu(MainMenu menu) {
        this.menu = menu;
    }

    public void setText(String text) {
        this.setVisible(true);
        output.setText(text);
    }

    private void setOutput() {
        //set text area
        setUpperPanel();
        //set panel with button to return
        setLowerPanel();
    }

    private void setUpperPanel() {
        JPanel upperPanel = new JPanel(new GridLayout(0,1));

        setOutputTextArea();                                                           //set textArea
        scroll = new JScrollPane(output);

        upperPanel.add(scroll);

        this.add(upperPanel,"Center");
    }

    private void setOutputTextArea() {
        output = new JTextArea();
        output.setEditable(false);                                                     //blocks editing text
        output.setFont(new Font("Default", Font.PLAIN, 17));                //sets font
        output.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));      //sets border
        output.setCaret(new DefaultCaret() {                                           //sets invisible caret
            @Override
            public void paint(Graphics g) {}
        });
    }

    private void setLowerPanel() {
        JPanel lowerPanel = new JPanel(new FlowLayout());
        lowerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
        lowerPanel.setBackground(Color.GRAY);

        back = new JButton();
        createButtonWithAndAddToPanel(back,"Повернутися назад",lowerPanel);

        this.add(lowerPanel,"South");
    }

    private void createButtonWithAndAddToPanel(JButton button,String buttonLabel, JPanel originPanel){
        JPanel buttonPanel=new JPanel();

        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.GRAY);
        button.setPreferredSize(new Dimension(220,60));
        button.setText(buttonLabel);
        button.setHorizontalAlignment(JButton.CENTER);
        button.addActionListener(this);

        button.setFont(new Font("Default", Font.BOLD, 17));
        buttonPanel.add(button);
        originPanel.add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        stats.returned();
    }
}
