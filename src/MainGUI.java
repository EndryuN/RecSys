import javax.swing.*;

public class MainGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JButton button1;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton button2;
    private JTable table1;
    private JTable table2;

    public MainGUI() {
        System.out.println("--------------------------\nSetting up GUI:");
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Recommendation system");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
