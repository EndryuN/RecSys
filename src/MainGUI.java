import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JButton searchBtn;
    private JTextField searchTxt;
    private JComboBox artistSelectCombo;
    private JComboBox songSelectCombo;
    private JButton saveBtn;
    private JTable songTbl;
    private JTable resultTbl;
    private JButton getSongsButton;
    private String searchInput;
    private DefaultTableModel songModel;

    public MainGUI() {
        System.out.println("--------------------------\nSetting up GUI:");
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Recommendation system");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //Setting up tables
        createTable();

        //Loading dataset

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { searchButtonPressed();

            }
        });
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { loadButtonPressed();

            }
        });
        getSongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { getSongsButtonPressed();

            }
        });
    }
    private void searchButtonPressed(){
        System.out.println("Search button pressed");
        artistSelectCombo.removeAllItems();
        populateComboBox();
    }
    private void getSongsButtonPressed(){
        Main.datasetHandler.loadSongs();
        Main.songHandler.saveSongs();
        Main.songHandler.loadSongs();
    }

    private void loadButtonPressed(){
        System.out.println("Load button pressed");
        songSelectCombo.removeAllItems();
        populateComboBox2();

    }


    private void populateComboBox() {
        System.out.println("SearchArtist.populateComboBox");
        searchInput = searchTxt.getText();
        for(int i=0;i<Main.artistHandler.getArtists().size();i++) {
            if (Main.artistHandler.getArtists().get(i).getArtistName().toLowerCase().contains(searchInput.toLowerCase())) {
                artistSelectCombo.addItem(Main.artistHandler.getArtists().get(i).getArtistName());
            }
        }
    }

    private void populateComboBox2() {
        System.out.println("SearchSong.populateComboBox");
        String selectedArtist = artistSelectCombo.getSelectedItem().toString();
        for(int i=0;i<Main.songHandler.getSongs().size();i++) {
            if (Main.songHandler.getSongs().get(i).getArtistName().toLowerCase().contains(selectedArtist.toLowerCase())) {
                songSelectCombo.addItem(Main.songHandler.getSongs().get(i).getSongTitle());
                System.out.println(Main.songHandler.getSongs().get(i).getSongTitle());
                System.out.println(Main.songHandler.getSongs().get(i));
            }
        }
    }

    private void createTable() {
        System.out.println("Creating Table");
        String[] columnNames = {"Artist Name", "Title"};
        songTbl.setModel(new DefaultTableModel(null, columnNames));
        songModel = (DefaultTableModel) songTbl.getModel();

    }


}
