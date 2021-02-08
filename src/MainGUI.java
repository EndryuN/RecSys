import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JButton searchBtn;
    private JTextField textField1;
    private JComboBox comboBox1;
    private JButton loadBtn;
    private JTable songTbl;
    private JTable resultTbl;

    public MainGUI() {
        System.out.println("--------------------------\nSetting up GUI:");
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Recommendation system");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { searchButtonPressed();

            }
        });
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { loadButtonPressed();

            }
        });
    }
    private void searchButtonPressed(){
        System.out.println("Search button pressed");

    }

    private void loadButtonPressed(){
        System.out.println("Load button pressed");

    }



}
