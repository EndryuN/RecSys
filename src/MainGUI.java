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
    private JButton parseDataButton;
    private JTable songTbl;
    private JScrollPane songScrollPanel;
    private JButton getSongsBtn;
    private JButton loadDataBtn;
    private JButton saveDataBtn;
    private JButton getRecommendationButton;
    private JButton parsePlaylistsButton;
    private JTable songTable;
    private JTable artistTable;
    private JPanel eastPanel;
    private JScrollPane artistScrollPane;
    private JTable recommendationTable;
    private JScrollPane recScrollPanel;
    private JButton savePLaylistsButton;
    private JButton loadPLaylistsButton;
    private JRadioButton artistRadioButton;
    private JRadioButton songTitleRadioButton;

    private DefaultTableModel songModel;
    private DefaultTableModel artistModel;

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
        createTable("artists");


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
        getRecommendationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { getRecommendationButtonPressed();

            }
        });
        parsePlaylistsButton.addActionListener(new ActionListener() {
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

    //--PARSE ARTISTS
    private void parseDataButtonPressed(){
        System.out.println("Load Data button pressed");
        Main.datasetHandler.parseArtists();
        System.out.println("Artists Parsed");
        Main.datasetHandler.parseSongs();
        System.out.println("Songs Parsed");
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
    //--GET RECOMMENDATION
    private void getRecommendationButtonPressed(){
        String selectedSong = songSelectCombo.getSelectedItem().toString();
        Main.playlistHandler.getRecommendation(selectedSong);
    }

    //--PARSE PLAYLISTS
    private void parsePlaylistsButtonPressed(){
        Main.datasetHandler.parsePLaylists();
        //for(int i=0; i<Main.playlistHandler.getPlaylists().size(); i++){
        //    playlistCombo.addItem(Main.playlistHandler.getPlaylists().get(i).getPlaylistID());
        //}
    }

    //--SAVE PLAYLISTS BUTTON
    private void savePLaylistsButton(){
        System.out.println("savePLaylistsButtonPressed");
        Main.playlistHandler.savePlaylists();
        System.out.println("Playlists saved");
    }
    private void loadPLaylistsButtonPressed(){
        System.out.println("loadPLaylistsButtonPressed");
        Main.playlistHandler.savePlaylists();
        System.out.println("Playlists loaded");
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
        if(type == "artists"){
            String[] columnNames = {"Artist Name"};
            artistTable.setModel(new DefaultTableModel(null, columnNames));
            artistModel = (DefaultTableModel) artistTable.getModel();
        }

    }
}
