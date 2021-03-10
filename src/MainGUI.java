import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel northPanel;
    private JPanel centerPanel;
    private JPanel eastPanel;
    private JTextField searchTxt;
    private JButton searchBtn;
    private JButton parseDataButton;
    private JButton savePLaylistsButton;
    private JButton loadPLaylistsButton;
    private JComboBox resultCombo;
    private JComboBox songSelectCombo;
    private ArrayList songArray;
    private JComboBox playlistCombo;
    private JTable songTbl;
    private JButton getSongsBtn;
    private JButton loadDataBtn;
    private JButton saveDataBtn;
    private JButton songFindPlaylistsButton;
    private JButton findPlaylistsButton;
    private JScrollPane queryPanel;
    private JTable queryTable;
    private JScrollPane artistPanel;
    private JTable artistTable;
    private JScrollPane songPanel;
    private JTable songTable;
    private JButton showResultsButton;
    private JComboBox recType;
    private JTextArea textArea1;
    private JButton artistFindPlaylistsButton;

    private DefaultTableModel queryModel;
    private DefaultTableModel artistModel;
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
        createTable("Query Table");
        createTable("Artist Table");
        createTable("Song Table");


        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { searchArtistButtonPressed();

            }
        });
        getSongsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { getSongsButtonPressed();

            }
        });
        parseDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { parseDataButtonPressed();

            }
        });
        loadDataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { loadDataButtonPressed();

            }
        });
        saveDataBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { saveDataButtonPressed();

            }
        });
        songFindPlaylistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { songFindPlaylistsButtonPressed();

            }
        });
        artistFindPlaylistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { artistFindPlaylistsButtonPressed();

            }
        });
        findPlaylistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { parsePlaylistsButtonPressed();

            }
        });
        savePLaylistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { savePLaylistsButton();

            }
        });
        loadPLaylistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { loadPLaylistsButtonPressed();

            }
        });
        showResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { showResultsButtonPressed();
            }
        });

    }
    //------------GUI METHODS----------------------



    //--------BUTTON METHODS-----------------------

    //--PARSE DATA
    private void parseDataButtonPressed(){
        System.out.println("Load Data button pressed");
        Main.datasetHandler.parseArtists();
        int amount = Main.artistHandler.getArtists().size();
        System.out.println(amount+" Artists Parsed");
        Main.datasetHandler.parseSongs();
        amount = Main.songHandler.getSongs().size();
        System.out.println(amount+" Songs Parsed");
    }

    //--SEARCH ARTISTS
    private void searchArtistButtonPressed(){
        if(searchTxt.getText()==""){
            System.out.println("illegal term");// popup needed
        } else {
            System.out.println("Search button pressed");
            resultCombo.removeAllItems();
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

    //--GET RECOMMENDATION BY SONG
    private void songFindPlaylistsButtonPressed(){
        String selectedSong = songSelectCombo.getSelectedItem().toString();
        String selectedArtist = resultCombo.getSelectedItem().toString();
        for(int i=1; i<11; i++){
            Main.datasetHandler.parsePLaylists(i);
            Main.playlistHandler.getSongRecommendation(selectedArtist, selectedSong);
        }
    }
    //GET RECOMMENDATION
    private void artistFindPlaylistsButtonPressed(){
        //songFindPlaylistsButton.setEnabled(false);
        String selectedArtist = resultCombo.getSelectedItem().toString();
        for(int i=1; i<11; i++){
            Main.datasetHandler.parsePLaylists(i);
            Main.playlistHandler.getArtistRecommendation(selectedArtist);
        }
    }
////-------SHOW RESULTS-------------------!!!!
    private void showResultsButtonPressed(){

        Main.playlistHandler.printPlaylists();
        Main.playlistHandler.updateArtistTables(artistModel);
        Main.playlistHandler.updateTrackTables(songModel);
    }

    //--SAVE DATA
    private void saveDataButtonPressed(){
        System.out.println("Save Data pressed");
        Main.artistHandler.saveArtists();
        Main.songHandler.saveSongs();
    }
    //--LOAD SONGS
    private void loadDataButtonPressed(){
        System.out.println("Load Data pressed");
        Main.artistHandler.loadArtists();
        Main.songHandler.loadSongs();
    }

    //--PARSE PLAYLISTS USING SONG
    private void parsePlaylistsButtonPressed(){

    }

    //--SAVE PLAYLISTS BUTTON
    private void savePLaylistsButton(){
        System.out.println("savePLaylistsButtonPressed");
        Main.playlistHandler.savePlaylists();
        System.out.println("Playlists saved");
    }
    private void loadPLaylistsButtonPressed(){
        System.out.println("loadPLaylistsButtonPressed");
        Main.playlistHandler.loadPlaylists();
        System.out.println("Playlists loaded");
    }


    private void populateArtistsComboBox() {
        System.out.println("SearchArtist.populateComboBox");
        String searchInput = searchTxt.getText();
        for (int i = 0; i < Main.artistHandler.getArtists().size(); i++) {
            if (Main.artistHandler.getArtists().get(i).getArtistName().toLowerCase().contains(searchInput.toLowerCase())) {
                resultCombo.addItem(Main.artistHandler.getArtists().get(i).getArtistName());
            }
        }

    }

    private void populateSongsComboBox() {
        System.out.println("SearchSong.populateComboBox");
        String selectedArtist = resultCombo.getSelectedItem().toString();

        Main.songHandler.searchArtistSongs(selectedArtist);
        for(int i=0;i<Main.songHandler.getSearchedSongs().size();i++) {
            songSelectCombo.addItem(Main.songHandler.getSearchedSongs().get(i).getSongTitle());
        }
    }

    private void createTable(String type) {
        System.out.println("Creating Table");
        if(type == "Query Table") {
            String[] columnNames = {"Search query", "type", "input"};
            queryTable.setModel(new DefaultTableModel(null, columnNames));
            queryModel = (DefaultTableModel) queryTable.getModel();
        }
        if(type == "Artist Table") {
            String[] columnNames = {"Artist Name", "Count"};
            artistTable.setModel(new DefaultTableModel(null, columnNames));
            artistModel = (DefaultTableModel) artistTable.getModel();
        }
        if(type == "Song Table") {
            String[] columnNames = {"Artist", "Title", "Count"};
            songTable.setModel(new DefaultTableModel(null, columnNames));
            songModel = (DefaultTableModel) songTable.getModel();
        }

    }
}
