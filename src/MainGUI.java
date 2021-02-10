import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JButton searchArtistBtn;
    private JTextField searchTxt;
    private JComboBox artistSelectCombo;
    private JComboBox songSelectCombo;
    private JButton loadArtistsBtn;
    private JTable songTbl;
    private JTable resultTbl;
    private JButton getSongsBtn;
    private JButton loadSongsBtn;
    private JButton saveDataBtn;
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

        searchArtistBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { searchArtistButtonPressed();

            }
        });
        getSongsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { getSongsButtonPressed();

            }
        });
        loadArtistsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { loadArtistsButtonPressed();

            }
        });
        loadSongsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { loadSongsButtonPressed();

            }
        });
        saveDataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { saveDataButtonPressed();

            }
        });
    }
    //Button methods
    private void searchArtistButtonPressed(){
        System.out.println("Search button pressed");
        artistSelectCombo.removeAllItems();
        populateArtistsComboBox();
    }
    private void getSongsButtonPressed(){
        System.out.println("Load button pressed");
        songSelectCombo.removeAllItems();
        populateSongsComboBox();

    }
    private void loadArtistsButtonPressed(){
        Main.datasetHandler.loadArtists();

    }
    private void loadSongsButtonPressed(){
        Main.datasetHandler.loadSongs();
    }
    private void saveDataButtonPressed(){
        Main.artistHandler.saveArtists();
        Main.songHandler.saveSongs();
    }


    private void populateArtistsComboBox() {
        System.out.println("SearchArtist.populateComboBox");
        searchInput = searchTxt.getText();
        for(int i=0;i<Main.artistHandler.getArtists().size();i++) {
            if (Main.artistHandler.getArtists().get(i).getArtistName().toLowerCase().contains(searchInput.toLowerCase())) {
                artistSelectCombo.addItem(Main.artistHandler.getArtists().get(i).getArtistName());
            }
        }
    }

    private void populateSongsComboBox() {
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
