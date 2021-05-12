import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    private JButton addQueryButton;
    private JButton processPlaylistsButton;
    private JTextField songTxt;
    private JComboBox modeCombo;
    private JButton onlyArtistButton;
    private JButton filterPopularButton;
    private JButton otherArtistsButton;
    private JButton songFeatureButton;
    private JButton filterMoreButton;
    private DefaultTableModel queryModel;
    private DefaultTableModel artistModel;
    private DefaultTableModel songModel;
    private boolean dataLoaded = false;
    private Integer popularity = 75;


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
        songFeatureButton.setEnabled(false);
        filterPopularButton.setEnabled(false);
        filterMoreButton.setEnabled(false);
        searchArtistButton.addActionListener(e -> searchArtistButtonPressed());
        searchSongsButton.addActionListener(e -> searchSongsButtonPressed());
        addQueryButton.addActionListener(e -> addQueryPressed());
        clearQueriesButton.addActionListener(e -> clearQueriesButtonPressed());
        findPlaylistsButton.addActionListener(e -> findPlaylistsButtonPressed());
        processPlaylistsButton.addActionListener(e -> processPlaylistsButtonPressed());
        saveQueriesButton.addActionListener(e -> saveQueriesPressed());
        loadQueriesButton.addActionListener(e -> loadQueriesButtonPressed());
        saveRecButton.addActionListener(e -> saveRecButtonPressed());
        loadRecButton.addActionListener(e -> loadRecButtonPressed());
        displayRecButton.addActionListener(e -> displayRecButtonPressed());
        onlyArtistButton.addActionListener(e -> onlyArtistButtonPressed());
        otherArtistsButton.addActionListener(e -> otherArtistsButtonPressed());
        songFeatureButton.addActionListener(e -> songFeatureButtonPressed());
        filterPopularButton.addActionListener(e -> filterPopularButtonPressed());
        filterMoreButton.addActionListener(e -> filterMoreButtonPressed());
    }
//////////////////////////////////////////////////////////////
    //--------PROCESSING METHODS-----------------------
//////////////////////////////////////////////////////////////

    //--SEARCH ARTISTS
    //this needs more error testing for consistency in selecting alternative songs or artists
    private void searchArtistButtonPressed(){
        resultCombo.setEnabled(true);
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
            resultCombo.setEnabled(false);
            //System.out.println("illegal term");// popup needed
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
            if(resultCombo.getItemCount() == 0){
                resultCombo.addItem("Artist not found in the Artist Dataset");
                resultCombo.setEnabled(false);
            } else {
                searchTxt.setText("");
            }
        }
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
    private void addQueryPressed() {
        findPlaylistsButton.setEnabled(true);
        saveQueriesButton.setEnabled(true);
        String queryHolder = "rec" + (Main.queryHandler.getQueries().size() + 1);
        String selectedArtist = resultCombo.getSelectedItem().toString();
        if (Main.queryHandler.getQueries().size() != 5) {
            if (modeCombo.getSelectedItem() == "Song") {// When the search query is selected to song
                if (songTxt.getText().isEmpty()) {
                    String selectedSong = songSelectCombo.getSelectedItem().toString();
                    Main.queryHandler.createQuery(queryHolder, selectedSong, selectedArtist, "input", 0, true);
                } else {
                    Main.queryHandler.createQuery(queryHolder, songTxt.getText(), selectedArtist, "input", 0, true);
                    songTxt.setText("");
                }
            } else {// When the search query is selected to artist
                if (resultCombo.isEnabled()) {
                    Main.queryHandler.createQuery(queryHolder, "----", selectedArtist, "input", 0, false);
                } else if (!resultCombo.isEnabled()) {
                    Main.queryHandler.createQuery(queryHolder, "----", searchTxt.getText(), "input", 0, false);
                }
            }
            resultCombo.setEnabled(false);
            Main.queryHandler.updateQueryTable(queryModel);
        }
    }
    //--ClEAR QUERIES
    private void clearQueriesButtonPressed(){
        Main.queryHandler.clearQueries();
        Main.queryHandler.updateQueryTable(queryModel);
        saveQueriesButton.setEnabled(true);
    }
    //--FIND PLAYLISTS BY SONG
    private void findPlaylistsButtonPressed() {
        songSelectCombo.removeAllItems();
        for (int i = 1; i < 11; i++) {
            Main.datasetHandler.parsePLaylists(i);// Putting 100k playlists into the memory
            if (Main.queryHandler.getQueries().size() > 0 && Main.queryHandler.getQueries().get(0).getStatus() == "input") {
                Main.playlistHandler.findPlaylists(0,
                Main.queryHandler.getQueries().get(0).getArtistName(),
                Main.queryHandler.getQueries().get(0).getTrackName(),
                Main.playlistHandler.getPlaylists1()); }
            if (Main.queryHandler.getQueries().size() > 1 && Main.queryHandler.getQueries().get(1).getStatus() == "input") {
                Main.playlistHandler.findPlaylists(1,
                Main.queryHandler.getQueries().get(1).getArtistName(),
                Main.queryHandler.getQueries().get(1).getTrackName(),
                Main.playlistHandler.getPlaylists2());}
            if (Main.queryHandler.getQueries().size() > 2 && Main.queryHandler.getQueries().get(2).getStatus() == "input") {
                Main.playlistHandler.findPlaylists(2,
                Main.queryHandler.getQueries().get(2).getArtistName(),
                Main.queryHandler.getQueries().get(2).getTrackName(),
                Main.playlistHandler.getPlaylists3());}
            if (Main.queryHandler.getQueries().size() > 3 && Main.queryHandler.getQueries().get(3).getStatus() == "input") {
                Main.playlistHandler.findPlaylists(3,
                Main.queryHandler.getQueries().get(3).getArtistName(),
                Main.queryHandler.getQueries().get(3).getTrackName(),
                Main.playlistHandler.getPlaylists4());}
            if (Main.queryHandler.getQueries().size() > 4 && Main.queryHandler.getQueries().get(4).getStatus().equals("input")) {
                Main.playlistHandler.findPlaylists(4,
                Main.queryHandler.getQueries().get(4).getArtistName(),
                Main.queryHandler.getQueries().get(4).getTrackName(),
                Main.playlistHandler.getPlaylists5());
            }
            Main.playlistHandler.getPlaylists().clear();// clearing playlist holder for next iteration
        }
        Main.queryHandler.updateQueryTable(queryModel);
        findPlaylistsButton.setEnabled(false);
        processPlaylistsButton.setEnabled(true);
        saveQueriesButton.setEnabled(true);
        resultCombo.removeAllItems();
    }
    // Improved recommendation parsing
    private void processPlaylistsButtonPressed(){
        // clearing playlist holder after all songs have been gathered
        if (Main.queryHandler.getQueries().size() > 0 && Main.queryHandler.getQueries().get(0).getStatus() == "found") {
            Main.playlistHandler.processPlaylists(0, Main.playlistHandler.getPlaylists1(),
            Main.recSongHandler.getRecommendation1(), Main.recArtistHandler.getRecArtists1()); Main.queryHandler.updateQueryTable(queryModel);}
        if (Main.queryHandler.getQueries().size() > 1 && Main.queryHandler.getQueries().get(1).getStatus() == "found"){
            Main.playlistHandler.processPlaylists(1, Main.playlistHandler.getPlaylists2(),
            Main.recSongHandler.getRecommendation2(), Main.recArtistHandler.getRecArtists2()); Main.queryHandler.updateQueryTable(queryModel);}
        if (Main.queryHandler.getQueries().size() > 2 && Main.queryHandler.getQueries().get(2).getStatus() == "found"){
            Main.playlistHandler.processPlaylists(2, Main.playlistHandler.getPlaylists3(),
            Main.recSongHandler.getRecommendation3(), Main.recArtistHandler.getRecArtists3()); Main.queryHandler.updateQueryTable(queryModel);}
        if (Main.queryHandler.getQueries().size() > 3 && Main.queryHandler.getQueries().get(3).getStatus() == "found"){
            Main.playlistHandler.processPlaylists(3, Main.playlistHandler.getPlaylists4(),
            Main.recSongHandler.getRecommendation4(), Main.recArtistHandler.getRecArtists4()); Main.queryHandler.updateQueryTable(queryModel);}
        if (Main.queryHandler.getQueries().size() > 4 && Main.queryHandler.getQueries().get(4).getStatus() == "found"){
            Main.playlistHandler.processPlaylists(4, Main.playlistHandler.getPlaylists5(),
            Main.recSongHandler.getRecommendation5(), Main.recArtistHandler.getRecArtists5()); Main.queryHandler.updateQueryTable(queryModel);}

        for (Query item: Main.queryHandler.getQueries()){
            if(item.getStatus() == "processed"){
                recCombo.addItem(item.getRecID());
            }
        }
        processPlaylistsButton.setEnabled(false);
        displayRecButton.setEnabled(true);
        saveRecButton.setEnabled(true);
        saveQueriesButton.setEnabled(true);
        songFeatureButton.setEnabled(true);
        filterPopularButton.setEnabled(true);
        filterMoreButton.setEnabled(true);
    }

////////////////////////////////////////////////////////////////
    ////-------POST-PROCESSING-------------------
////////////////////////////////////////////////////////////////

    //--SAVE QUERIES
    private void saveQueriesPressed() {
        Main.queryHandler.saveQuery();
        saveQueriesButton.setEnabled(false);
    }
    //--LOAD QUERIES
    private void loadQueriesButtonPressed(){
        findPlaylistsButton.setEnabled(true);
        Main.queryHandler.getQueries().clear();
        recCombo.removeAllItems();
        Main.queryHandler.loadQuery();
        Main.queryHandler.updateQueryTable(queryModel);
        fillComboBox();
        System.out.println("Query loaded");
    }
    private void fillComboBox(){
        //check this
        for (Query item: Main.queryHandler.getQueries()){
            if(item.getStatus().equals("processed")){
                recCombo.addItem(item.getRecID());
            }
        }
    }

    //--SAVE RECOMMENDATIONS
    private void saveRecButtonPressed(){
        if(Main.queryHandler.getQueries().size() > 0 ){//&& Main.queryHandler.getQueries().get(0).getStatus() == "processed"
            Main.recSongHandler.saveRecSong(Main.recSongHandler.getRecommendation1(), "rec1");
            Main.recArtistHandler.saveRecArtist(Main.recArtistHandler.getRecArtists1(), "rec1");}
        if(Main.queryHandler.getQueries().size() > 1 ){//&& Main.queryHandler.getQueries().get(1).getStatus() == "processed"
            Main.recSongHandler.saveRecSong(Main.recSongHandler.getRecommendation2(), "rec2");
            Main.recArtistHandler.saveRecArtist(Main.recArtistHandler.getRecArtists2(), "rec2");}
        if(Main.queryHandler.getQueries().size() > 2 ){//&& Main.queryHandler.getQueries().get(2).getStatus() == "processed"
            Main.recSongHandler.saveRecSong(Main.recSongHandler.getRecommendation3(), "rec3");
            Main.recArtistHandler.saveRecArtist(Main.recArtistHandler.getRecArtists3(), "rec3");}
        if(Main.queryHandler.getQueries().size() > 3 ){//&& Main.queryHandler.getQueries().get(3).getStatus() == "processed"
            Main.recSongHandler.saveRecSong(Main.recSongHandler.getRecommendation4(), "rec4");
            Main.recArtistHandler.saveRecArtist(Main.recArtistHandler.getRecArtists4(), "rec4");}
        if(Main.queryHandler.getQueries().size() > 4 ){//&& Main.queryHandler.getQueries().get(4).getStatus() == "processed"
            Main.recSongHandler.saveRecSong(Main.recSongHandler.getRecommendation5(), "rec5");
            Main.recArtistHandler.saveRecArtist(Main.recArtistHandler.getRecArtists5(), "rec5");}
        System.out.println("Recommendations saved");
    }
    //--LOAD RECOMMENDATIONS
    private void loadRecButtonPressed(){
        if (Main.queryHandler.getQueries().size() > 0){
            Main.recSongHandler.loadRecSong(Main.recSongHandler.getRecommendation1(), "rec1");
            Main.recArtistHandler.loadRecArtist(Main.recArtistHandler.getRecArtists1(), "rec1");}
        if(Main.queryHandler.getQueries().size() > 1){
            Main.recSongHandler.loadRecSong(Main.recSongHandler.getRecommendation2(), "rec2");
            Main.recArtistHandler.loadRecArtist(Main.recArtistHandler.getRecArtists2(), "rec2");}
        if(Main.queryHandler.getQueries().size() > 2) {
            Main.recSongHandler.loadRecSong(Main.recSongHandler.getRecommendation3(), "rec3");
            Main.recArtistHandler.loadRecArtist(Main.recArtistHandler.getRecArtists3(), "rec3");}
        if(Main.queryHandler.getQueries().size() > 3){
            Main.recSongHandler.loadRecSong(Main.recSongHandler.getRecommendation4(), "rec4");
            Main.recArtistHandler.loadRecArtist(Main.recArtistHandler.getRecArtists4(), "rec4");}
        if(Main.queryHandler.getQueries().size() > 4){
            Main.recSongHandler.loadRecSong(Main.recSongHandler.getRecommendation5(), "rec5");
            Main.recArtistHandler.loadRecArtist(Main.recArtistHandler.getRecArtists5(), "rec5");}
        System.out.println("Recommendations loaded");
        displayRecButton.setEnabled(true);
        loadRecButton.setEnabled(false);
        filterPopularButton.setEnabled(true);
        songFeatureButton.setEnabled(true);
        saveRecButton.setEnabled(true);

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
        filterPopularButton.setEnabled(true);
        filterMoreButton.setEnabled(true);
    }

    //------------REFINE RESULTS----------------------
    //Show only songs by the selected artist
    private void onlyArtistButtonPressed() {
        displayRecButtonPressed();
        if(!otherArtistsButton.isEnabled()){
            displayRecButtonPressed();
            otherArtistsButton.setEnabled(true);
        }
        Main.recSongHandler.onlyFilter();
        Main.recSongHandler.updateTrackTables(songModel);
        onlyArtistButton.setEnabled(false);

    }
    //Show only songs by other than the selected artist
    private void otherArtistsButtonPressed(){
        if(!onlyArtistButton.isEnabled()){
            displayRecButtonPressed();
            onlyArtistButton.setEnabled(true);
        }
        Main.recSongHandler.otherFilter();
        Main.recSongHandler.updateTrackTables(songModel);
        otherArtistsButton.setEnabled(false);
    }
    //Extract audio features from artist and song datasets
    private void songFeatureButtonPressed(){
        Main.recArtistHandler.audioFeatures();
        Main.recSongHandler.audioFeatures();
        Main.recArtistHandler.updateArtistTables(artistModel);
        Main.recSongHandler.updateTrackTables(songModel);
        saveRecButton.setEnabled(true);
    }

    private void filterPopularButtonPressed(){
        displayRecButtonPressed();
        popularity = 75;
        Main.recSongHandler.popularityFilter(popularity);
        Main.recSongHandler.updateTrackTables(songModel);
        filterPopularButton.setEnabled(false);
    }
    private void filterMoreButtonPressed(){
        popularity--;
        Main.recSongHandler.popularityFilter(popularity);
        Main.recSongHandler.updateTrackTables(songModel);
    }

    //------------TABLE METHOD------------------------
    private void createTable(String type) {
        if(type == "Query Table") {
            String[] columnNames = {"RecID", "Artist", "Title", "Status", "Count"};
            queryTable.setModel(new DefaultTableModel(null, columnNames));
            queryTable.getColumnModel().getColumn(0).setPreferredWidth(28);
            queryTable.getColumnModel().getColumn(3).setPreferredWidth(40);
            queryTable.getColumnModel().getColumn(4).setPreferredWidth(15);
            queryModel = (DefaultTableModel) queryTable.getModel();
        } else if(type == "Artist Table") {
            String[] columnNames = {"Artist Name", "% of playlists", "Popularity"};
            artistTable.setModel(new DefaultTableModel(null, columnNames));
            artistTable.getColumnModel().getColumn(1).setPreferredWidth(40);
            artistTable.getColumnModel().getColumn(2).setPreferredWidth(45);
            artistModel = (DefaultTableModel) artistTable.getModel();
        } else if(type == "Song Table") {
            String[] columnNames = {"Artist", "Song Title", "Count", "% of playlists", "Popularity", "Danceability", "Energy"};
            songTable.setModel(new DefaultTableModel(null, columnNames));
            //songTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            songTable.getColumnModel().getColumn(2).setPreferredWidth(40);
            songTable.getColumnModel().getColumn(3).setPreferredWidth(35);
            songTable.getColumnModel().getColumn(4).setPreferredWidth(35);
            songTable.getColumnModel().getColumn(5).setPreferredWidth(30);
            songTable.getColumnModel().getColumn(6).setPreferredWidth(30);
            songModel = (DefaultTableModel) songTable.getModel();
        }
    }
}
