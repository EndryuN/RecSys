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
    private JButton searchArtistButton;
    private JButton clearQueriesButton;
    private JButton saveRecButton;
    private JButton loadQueriesButton;
    private JComboBox resultCombo;
    private JComboBox songSelectCombo;
    private ArrayList songArray;
    private JComboBox recCombo;
    private JTable songTbl;
    private JButton searchSongsButton;
    private JButton loadRecButton;
    private JButton saveQueriesButton;
    private JButton findPlaylistsButton;
    private JButton displayRecButton;
    private JScrollPane queryPanel;
    private JTable queryTable;
    private JScrollPane artistPanel;
    private JTable artistTable;
    private JScrollPane songPanel;
    private JTable songTable;
    private JButton showResultsButton;
    private JComboBox recType;
    private JButton addQueryButton;
    private JButton processPlaylistsButton;
    private JButton testButton1;
    private JButton testButton2;
    private JTextField songTxt;
    private JComboBox modeCombo;
    private JButton onlyArtistButton;
    private JButton filterPopularButton;
    private JButton otherArtistsButton;
    private JButton testButtonButton;

    private DefaultTableModel queryModel;
    private DefaultTableModel artistModel;
    private DefaultTableModel songModel;

    private boolean dataLoaded = false;


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
        modeCombo.addItem("Song");
        modeCombo.addItem("Artist");
        searchSongsButton.setEnabled(false);
        addQueryButton.setEnabled(false);
        displayRecButton.setEnabled(false);
        findPlaylistsButton.setEnabled(false);
        processPlaylistsButton.setEnabled(false);
        saveRecButton.setEnabled(false);
        saveQueriesButton.setEnabled(false);
        onlyArtistButton.setEnabled(false);
        otherArtistsButton.setEnabled(false);
        searchArtistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { searchArtistButtonPressed();

            }
        });
        searchSongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { searchSongsButtonPressed();

            }
        });
        addQueryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { addQueryPressed();

            }
        });
        clearQueriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { clearQueriesButtonPressed();

            }
        });
        findPlaylistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { findPlaylistsButtonPressed();

            }
        });
        processPlaylistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { processPlaylistsButtonPressed();

            }
        });
        saveQueriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { saveQueriesPressed();

            }
        });
        loadQueriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { loadQueriesButtonPressed();

            }
        });
        saveRecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { saveRecButtonPressed();

            }
        });
        loadRecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { loadRecButtonPressed();

            }
        });
        displayRecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { displayRecButtonPressed();

            }
        });
        onlyArtistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { onlyArtistButtonPressed();

            }
        });
        otherArtistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { otherArtistsButtonPressed();

            }
        });
        testButtonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { testButtonButtonPressed();

            }
        });
    }
//////////////////////////////////////////////////////////////
    //--------PROCESSING METHODS-----------------------
//////////////////////////////////////////////////////////////

    //--SEARCH ARTISTS
    private void searchArtistButtonPressed(){
        if(!dataLoaded){ //parsing artists and songs on first press
            System.out.println("Load Data button pressed");
            Main.datasetHandler.parseArtists();
            int amount = Main.artistHandler.getArtists().size();
            System.out.println(amount+" Artists Parsed");
            Main.datasetHandler.parseSongs();
            amount = Main.songHandler.getSongs().size();
            System.out.println(amount+" Songs Parsed");
            dataLoaded = true;
        }
        if(searchTxt.getText()==""){
            System.out.println("illegal term");// popup needed
        } else {
            System.out.println("Search button pressed");
            resultCombo.removeAllItems();
            songSelectCombo.removeAllItems();
            String searchInput = searchTxt.getText();
            for (int i = 0; i < Main.artistHandler.getArtists().size(); i++) {
                if (Main.artistHandler.getArtists().get(i).getArtistName().toLowerCase().contains(searchInput.toLowerCase())) {
                    resultCombo.addItem(Main.artistHandler.getArtists().get(i).getArtistName());
                }
            }
        }
        searchTxt.setText("");
        searchSongsButton.setEnabled(true);
        addQueryButton.setEnabled(true);
    }
    //--GET SONGS FROM THE ARTIST
    private void searchSongsButtonPressed(){
        songSelectCombo.removeAllItems();
        String selectedArtist = resultCombo.getSelectedItem().toString();
        Main.songHandler.searchArtistSongs(selectedArtist);
        for(int i=0;i<Main.songHandler.getSearchedSongs().size();i++) {
            songSelectCombo.addItem(Main.songHandler.getSearchedSongs().get(i).getSongTitle());
        }
    }
    //--ADD SONG TO QUERY
    private void addQueryPressed(){
        findPlaylistsButton.setEnabled(true);
        saveQueriesButton.setEnabled(true);
        String queryHolder = "rec"+(Main.queryHandler.getQueries().size()+1);
        String selectedArtist = resultCombo.getSelectedItem().toString();

        recCombo.addItem(queryHolder);
        if(modeCombo.getSelectedItem()=="Song"){
            if(songTxt.getText().isEmpty()){
                String selectedSong = songSelectCombo.getSelectedItem().toString();
                Main.queryHandler.createQuery(queryHolder,
                        selectedSong, selectedArtist,
                        "input", 0, true);
            } else {
                Main.queryHandler.createQuery(queryHolder,
                        songTxt.getText(), selectedArtist,
                        "input", 0, true);
                songTxt.setText("");
            }
        } else {
            Main.queryHandler.createQuery(queryHolder,
                    "null", selectedArtist,
                    "input", 0, false);
        }
        Main.queryHandler.updateQueryTable(queryModel);
    }
    //--ClEAR QUERIES
    private void clearQueriesButtonPressed(){
        Main.queryHandler.clearQueries();
        Main.queryHandler.updateQueryTable(queryModel);

    }
    //--FIND PLAYLISTS BY SONG
    private void findPlaylistsButtonPressed() {
        for (int i = 1; i < 11; i++) {
            if (Main.queryHandler.getQueries().size() > 0) {
                Main.datasetHandler.parsePLaylists(i);// Putting 100k playlists into the memory
                Main.playlistHandler.getSongRecommendation(0,
                        Main.queryHandler.getQueries().get(0).getArtistName(),
                        Main.queryHandler.getQueries().get(0).getTrackName(),
                        Main.playlistHandler.getPlaylists1());
                if (Main.queryHandler.getQueries().size() > 1) {
                    Main.playlistHandler.getSongRecommendation(1,
                            Main.queryHandler.getQueries().get(1).getArtistName(),
                            Main.queryHandler.getQueries().get(1).getTrackName(),
                            Main.playlistHandler.getPlaylists2());
                    if (Main.queryHandler.getQueries().size() > 2) {
                        Main.playlistHandler.getSongRecommendation(2,
                                Main.queryHandler.getQueries().get(2).getArtistName(),
                                Main.queryHandler.getQueries().get(2).getTrackName(),
                                Main.playlistHandler.getPlaylists3());
                        if (Main.queryHandler.getQueries().size() > 3) {
                            Main.playlistHandler.getSongRecommendation(3,
                                    Main.queryHandler.getQueries().get(3).getArtistName(),
                                    Main.queryHandler.getQueries().get(3).getTrackName(),
                                    Main.playlistHandler.getPlaylists4());
                            if (Main.queryHandler.getQueries().size() > 4) {
                                Main.playlistHandler.getSongRecommendation(4,
                                        Main.queryHandler.getQueries().get(4).getArtistName(),
                                        Main.queryHandler.getQueries().get(4).getTrackName(),
                                        Main.playlistHandler.getPlaylists5());
                            }
                        }
                    }
                }
            }
            Main.playlistHandler.getPlaylists().clear();
        }// ^clearing playlist holder after all songs have been gathered
        Main.queryHandler.updateQueryTable(queryModel);
        findPlaylistsButton.setEnabled(false);
        processPlaylistsButton.setEnabled(true);
        saveQueriesButton.setEnabled(true);
        resultCombo.removeAllItems();
    }
    // Improved recommendation parsing
    private void processPlaylistsButtonPressed(){
        // clearing playlist holder after all songs have been gathered
        if (Main.queryHandler.getQueries().size() > 0){
            Main.playlistHandler.processPlaylists(Main.playlistHandler.getPlaylists1(),
                    Main.recSongHandler.getRecommendation1(), Main.recArtistHandler.getRecArtists1());
            if (Main.queryHandler.getQueries().size() > 1){
                Main.playlistHandler.processPlaylists(Main.playlistHandler.getPlaylists2(),
                        Main.recSongHandler.getRecommendation2(), Main.recArtistHandler.getRecArtists2());
                if (Main.queryHandler.getQueries().size() > 2){
                    Main.playlistHandler.processPlaylists(Main.playlistHandler.getPlaylists3(),
                            Main.recSongHandler.getRecommendation3(), Main.recArtistHandler.getRecArtists3());
                    if (Main.queryHandler.getQueries().size() > 3){
                        Main.playlistHandler.processPlaylists(Main.playlistHandler.getPlaylists4(),
                                Main.recSongHandler.getRecommendation4(), Main.recArtistHandler.getRecArtists4());
                        if (Main.queryHandler.getQueries().size() > 4){
                            Main.playlistHandler.processPlaylists(Main.playlistHandler.getPlaylists5(),
                                    Main.recSongHandler.getRecommendation5(), Main.recArtistHandler.getRecArtists5());
                        }
                    }
                }
            }
        }
        processPlaylistsButton.setEnabled(false);
        displayRecButton.setEnabled(true);
        saveRecButton.setEnabled(true);
    }

////////////////////////////////////////////////////////////////
    ////-------POST-PROCESSING-------------------!!!!
////////////////////////////////////////////////////////////////

    //--SAVE QUERIES
    private void saveQueriesPressed() {
        Main.queryHandler.saveQuery();
    }
    //--LOAD QUERIES
    private void loadQueriesButtonPressed(){
        findPlaylistsButton.setEnabled(true);
        //Setting up recCombo
        Main.queryHandler.getQueries().clear();
        recCombo.removeAllItems();
        Main.queryHandler.loadQuery();
        int value = 1;
        for(int queryCounter = Main.queryHandler.getQueries().size(); queryCounter!=0; queryCounter--){
            recCombo.addItem("rec"+value);
            value++;
        }
        Main.queryHandler.updateQueryTable(queryModel);
        System.out.println("Query loaded");
    }

    //--SAVE RECOMMENDATIONS
    private void saveRecButtonPressed(){
        Main.recSongHandler.saveRecSong(Main.recSongHandler.getRecommendation1(), "rec1");
        Main.recSongHandler.saveRecSong(Main.recSongHandler.getRecommendation2(), "rec2");
        Main.recSongHandler.saveRecSong(Main.recSongHandler.getRecommendation3(), "rec3");
        Main.recSongHandler.saveRecSong(Main.recSongHandler.getRecommendation4(), "rec4");
        Main.recSongHandler.saveRecSong(Main.recSongHandler.getRecommendation5(), "rec5");
        Main.recArtistHandler.saveRecArtist(Main.recArtistHandler.getRecArtists1(), "rec1");
        Main.recArtistHandler.saveRecArtist(Main.recArtistHandler.getRecArtists2(), "rec2");
        Main.recArtistHandler.saveRecArtist(Main.recArtistHandler.getRecArtists3(), "rec3");
        Main.recArtistHandler.saveRecArtist(Main.recArtistHandler.getRecArtists4(), "rec4");
        Main.recArtistHandler.saveRecArtist(Main.recArtistHandler.getRecArtists5(), "rec5");
        System.out.println("Recommendations saved");
    }
    //--LOAD RECOMMENDATIONS
    private void loadRecButtonPressed(){
        Main.recSongHandler.loadRecSong(Main.recSongHandler.getRecommendation1(), "rec1");
        Main.recSongHandler.loadRecSong(Main.recSongHandler.getRecommendation2(), "rec2");
        Main.recSongHandler.loadRecSong(Main.recSongHandler.getRecommendation3(), "rec3");
        Main.recSongHandler.loadRecSong(Main.recSongHandler.getRecommendation4(), "rec4");
        Main.recSongHandler.loadRecSong(Main.recSongHandler.getRecommendation5(), "rec5");
        Main.recArtistHandler.loadRecArtist(Main.recArtistHandler.getRecArtists1(), "rec1");
        Main.recArtistHandler.loadRecArtist(Main.recArtistHandler.getRecArtists2(), "rec2");
        Main.recArtistHandler.loadRecArtist(Main.recArtistHandler.getRecArtists3(), "rec3");
        Main.recArtistHandler.loadRecArtist(Main.recArtistHandler.getRecArtists4(), "rec4");
        Main.recArtistHandler.loadRecArtist(Main.recArtistHandler.getRecArtists5(), "rec5");
        System.out.println("Recommendations loaded");
        displayRecButton.setEnabled(true);
        loadRecButton.setEnabled(false);
    }

    //--SHOW SELECTED RECOMMENDATION
    private void displayRecButtonPressed(){
        Main.recSongHandler.getSongs().clear();//clearing previous instance
        Main.recArtistHandler.getArtists().clear();//clearing previous instance
        Main.recSongHandler.updateTrackTables(songModel);
        Main.recArtistHandler.updateArtistTables(artistModel);
        String rec = recCombo.getSelectedItem().toString();
        if(rec.contains("rec1")){
            Main.queryHandler.setCurrentQuery(Main.queryHandler.getQueries().get(0));
            Main.recSongHandler.getSongs().addAll(Main.recSongHandler.getRecommendation1());
            Main.recArtistHandler.getArtists().addAll(Main.recArtistHandler.getRecArtists1());
        }else if(rec.contains("rec2")){
            Main.queryHandler.setCurrentQuery(Main.queryHandler.getQueries().get(1));
            Main.recSongHandler.getSongs().addAll(Main.recSongHandler.getRecommendation2());
            Main.recArtistHandler.getArtists().addAll(Main.recArtistHandler.getRecArtists2());
        }else if(rec.contains("rec3")){
            Main.queryHandler.setCurrentQuery(Main.queryHandler.getQueries().get(2));
            Main.recSongHandler.getSongs().addAll(Main.recSongHandler.getRecommendation3());
            Main.recArtistHandler.getArtists().addAll(Main.recArtistHandler.getRecArtists3());
        }else if(rec.contains("rec4")){
            Main.queryHandler.setCurrentQuery(Main.queryHandler.getQueries().get(3));
            Main.recSongHandler.getSongs().addAll(Main.recSongHandler.getRecommendation4());
            Main.recArtistHandler.getArtists().addAll(Main.recArtistHandler.getRecArtists4());
        }else if(rec.contains("rec5")){
            Main.queryHandler.setCurrentQuery(Main.queryHandler.getQueries().get(4));
            Main.recSongHandler.getSongs().addAll(Main.recSongHandler.getRecommendation5());
            Main.recArtistHandler.getArtists().addAll(Main.recArtistHandler.getRecArtists5());
        }
        Main.recArtistHandler.calcRec();
        Main.recSongHandler.calcRec();
        Main.recSongHandler.updateTrackTables(songModel);
        Main.recArtistHandler.updateArtistTables(artistModel);
        onlyArtistButton.setEnabled(true);
        otherArtistsButton.setEnabled(true);
        //filterPopularButton.setEnabled(true);
    }

    //------------REFINE RESULTS----------------------
    private void onlyArtistButtonPressed() {
        if(!otherArtistsButton.isEnabled()){
            displayRecButtonPressed();
            otherArtistsButton.setEnabled(true);
        }
        Main.recSongHandler.onlyFilter();
        Main.recSongHandler.updateTrackTables(songModel);
        onlyArtistButton.setEnabled(false);

    }

    private void otherArtistsButtonPressed(){
        if(!onlyArtistButton.isEnabled()){
            displayRecButtonPressed();
            onlyArtistButton.setEnabled(true);
        }
        Main.recSongHandler.otherFilter();
        Main.recSongHandler.updateTrackTables(songModel);
        otherArtistsButton.setEnabled(false);
    }

    private void testButtonButtonPressed(){
        Main.recSongHandler.testFilter();
        Main.recSongHandler.updateTrackTables(songModel);
        saveRecButton.setEnabled(true);
    }


    private void createTable(String type) {
        if(type == "Query Table") {
            String[] columnNames = {"RecID", "Artist", "Title", "Status", "Count"};
            queryTable.setModel(new DefaultTableModel(null, columnNames));
            queryTable.getColumnModel().getColumn(0).setPreferredWidth(28);
            queryTable.getColumnModel().getColumn(3).setPreferredWidth(40);
            queryTable.getColumnModel().getColumn(4).setPreferredWidth(15);
            queryModel = (DefaultTableModel) queryTable.getModel();
        }
        if(type == "Artist Table") {
            String[] columnNames = {"Artist Name", "Percent"};
            artistTable.setModel(new DefaultTableModel(null, columnNames));
            artistTable.getColumnModel().getColumn(1).setPreferredWidth(40);
            artistModel = (DefaultTableModel) artistTable.getModel();
        }
        if(type == "Song Table") {
            String[] columnNames = {"Artist", "Title", "% of playlists", "Popularity", "Danceability", "energy", "loudness", "valence"};
            songTable.setModel(new DefaultTableModel(null, columnNames));
            songTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            songTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            songModel = (DefaultTableModel) songTable.getModel();
        }
    }
}
