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
    private JComboBox playlistCombo;
    private JButton loadArtistsBtn;
    private JTable songTbl;
    private JScrollPane songScrollPanel;
    private JButton getSongsBtn;
    private JButton loadSongsBtn;
    private JButton saveDataBtn;
    private JButton getRecommendationButton;
    private JButton loadPlaylistsButton;
    private JButton test;
    private JTable songTable;

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
        createTable("songs");


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
        getRecommendationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { getRecommendationButtonPressed();

            }
        });
        loadPlaylistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { loadPlaylistsButtonPressed();

            }
        });
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { testButtonPressed();

            }
        });
    }
    //--------BUTTON METHODS-----------------------

    //--SEARCH ARTISTS
    private void searchArtistButtonPressed(){
        if(searchTxt.getText()==""){
            System.out.println("illegal term");
        } else {
            System.out.println("Search button pressed");
            artistSelectCombo.removeAllItems();
            songSelectCombo.removeAllItems();
            populateArtistsComboBox();
        }
    }

    //--GET SONGS
    private void getSongsButtonPressed(){
        System.out.println("Get songs button pressed");
        songSelectCombo.removeAllItems();
        populateSongsComboBox();

    }

    //--LOAD ARTISTS
    private void loadArtistsButtonPressed(){
        System.out.println("Load Artists button pressed");
        Main.datasetHandler.loadArtists();
        System.out.println("Artists Loaded");
    }

    //--LOAD SONGS
    private void loadSongsButtonPressed(){
        System.out.println("Load Songs pressed");
        Main.datasetHandler.loadSongs();
        System.out.println("Songs Loaded");
    }

    //--SAVE DATA
    private void saveDataButtonPressed(){
        Main.artistHandler.saveArtists();
        Main.songHandler.saveSongs();
    }

    //--GET RECOMMENDATION
    private void getRecommendationButtonPressed(){
        String selectedSong = songSelectCombo.getSelectedItem().toString();
        Main.playlistHandler.getRecommendation(selectedSong);
    }

    //--LOAD PLAYLISTS
    private void loadPlaylistsButtonPressed(){
        System.out.println("Load playlists pressed");
        Main.datasetHandler.loadPLaylists();
        System.out.println("Playlists loaded");
        for(int i=0; i<Main.playlistHandler.getPlaylists().size(); i++){
            playlistCombo.addItem(Main.playlistHandler.getPlaylists().get(i).getPlaylistID());
        }
    }
    //--TEST BUTTON
    private void testButtonPressed(){
        int selectedPLaylist = (int) playlistCombo.getSelectedItem();
        Main.playlistHandler.selectPlaylist(selectedPLaylist);
        Main.playlistHandler.testFunction(selectedPLaylist);
        System.out.println(selectedPLaylist);

        Main.trackHandler.updateTrackTables(songModel);

    }

    private void populateArtistsComboBox() {
        System.out.println("SearchArtist.populateComboBox");
        String searchInput = searchTxt.getText();
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
            }
        }
    }

    private void createTable(String type) {
        System.out.println("Creating Table");
        if(type == "songs") {
            String[] columnNames = {"Artist Name", "Title"};
            songTable.setModel(new DefaultTableModel(null, columnNames));
            songModel = (DefaultTableModel) songTable.getModel();
        }
    }
}
