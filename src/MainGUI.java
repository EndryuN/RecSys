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
    private JButton clearQueriesButton;
    private JButton saveRecButton;
    private JButton loadQueriesButton;
    private JComboBox resultCombo;
    private JComboBox songSelectCombo;
    private ArrayList songArray;
    private JComboBox recCombo;
    private JTable songTbl;
    private JButton getSongsBtn;
    private JButton loadRecButton;
    private JButton saveQueriesButton;
    private JButton songFindPlaylistsButton;
    private JButton displayRecButton;
    private JScrollPane queryPanel;
    private JTable queryTable;
    private JScrollPane artistPanel;
    private JTable artistTable;
    private JScrollPane songPanel;
    private JTable songTable;
    private JButton showResultsButton;
    private JComboBox recType;
    private JButton addQuery;
    private JButton processPlaylistsButton;
    private JButton testButton1;
    private JButton testButton2;
    private JTextField songTxt;

    private DefaultTableModel queryModel;
    private DefaultTableModel artistModel;
    private DefaultTableModel songModel;

    private boolean dataLoaded = false;
    private Integer queryCount = 0;


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
        clearQueriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { clearQueriesButtonPressed();

            }
        });
        loadRecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { loadRecButtonPressed();

            }
        });
        saveQueriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { saveQueriesPressed();

            }
        });
        songFindPlaylistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { songFindPlaylistsButtonPressed();

            }
        });
        addQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { addQueryPressed();

            }
        });
        displayRecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { displayRecButtonPressed();

            }
        });
        saveRecButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { saveRecButtonPressed();

            }
        });
        loadQueriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { loadQueriesButtonPressed();

            }
        });

        processPlaylistsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { processPlaylistsButtonPressed();

            }
        });
        testButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { testButton1Pressed();

            }
        });
        testButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { testButton2Pressed();

            }
        });

    }

    //--------BUTTON METHODS-----------------------

    //--SEARCH ARTISTS
    private void searchArtistButtonPressed(){

        if(!dataLoaded){ //parsing artists and songs on first press
            System.out.println("Load Data button pressed");
            Main.datasetHandler.parseArtists();
            int amount = Main.artistHandler.getArtists().size();
            System.out.println(amount+" Artists Parsed");
            //Main.datasetHandler.parseSongs();
            //amount = Main.songHandler.getSongs().size();
            //System.out.println(amount+" Songs Parsed");
            dataLoaded = true;
        }
        if(searchTxt.getText()==""){
            System.out.println("illegal term");// popup needed
        } else {
            System.out.println("Search button pressed");
            resultCombo.removeAllItems();
            //songSelectCombo.removeAllItems();
            String searchInput = searchTxt.getText();
            for (int i = 0; i < Main.artistHandler.getArtists().size(); i++) {
                if (Main.artistHandler.getArtists().get(i).getArtistName().toLowerCase().contains(searchInput.toLowerCase())) {
                    resultCombo.addItem(Main.artistHandler.getArtists().get(i).getArtistName());
                }
            }
        }
        searchTxt.setText("");
    }
    //--GET SONGS FROM THE ARTIST
    private void getSongsButtonPressed(){
        System.out.println("Get songs button pressed");
        //songSelectCombo.removeAllItems();
        //String selectedArtist = resultCombo.getSelectedItem().toString();
        //Main.songHandler.searchArtistSongs(selectedArtist);
        //for(int i=0;i<Main.songHandler.getSearchedSongs().size();i++) {
        //    songSelectCombo.addItem(Main.songHandler.getSearchedSongs().get(i).getSongTitle());
        //}
    }
    //--ADD SONG TO QUERY
    private void addQueryPressed(){
        queryCount++;
        String queryHolder = "rec"+queryCount;
        String selectedSong = songTxt.getText();
        String selectedArtist = resultCombo.getSelectedItem().toString();
        Main.queryHandler.createQuery(queryHolder, selectedSong, selectedArtist, "to be processed", 0);
        Main.queryHandler.updateQueryTable(queryModel);
        songTxt.setText("");
    }
    //--ClEAR QUERIES
    private void clearQueriesButtonPressed(){
        Main.queryHandler.clearQueries();
        Main.queryHandler.updateQueryTable(queryModel);
    }
    //--FIND PLAYLISTS BY SONG
    private void songFindPlaylistsButtonPressed(){
        for(int i=1; i<11; i++){
            Main.datasetHandler.parsePLaylists(i);
            Main.playlistHandler.getSongRecommendation(0, Main.queryHandler.getQueries().get(0).getArtistName(), Main.queryHandler.getQueries().get(0).getTrackName(), Main.playlistHandler.getPlaylists1());
            Main.playlistHandler.getSongRecommendation(1, Main.queryHandler.getQueries().get(1).getArtistName(), Main.queryHandler.getQueries().get(1).getTrackName(), Main.playlistHandler.getPlaylists2());
            Main.playlistHandler.getSongRecommendation(2, Main.queryHandler.getQueries().get(2).getArtistName(), Main.queryHandler.getQueries().get(2).getTrackName(), Main.playlistHandler.getPlaylists3());
            Main.playlistHandler.getSongRecommendation(3, Main.queryHandler.getQueries().get(3).getArtistName(), Main.queryHandler.getQueries().get(3).getTrackName(), Main.playlistHandler.getPlaylists4());
            Main.playlistHandler.getSongRecommendation(4, Main.queryHandler.getQueries().get(4).getArtistName(), Main.queryHandler.getQueries().get(4).getTrackName(), Main.playlistHandler.getPlaylists5());
            Main.playlistHandler.getPlaylists().clear();
            Main.queryHandler.updateQueryTable(queryModel);
        }
        Main.queryHandler.updateQueryTable(queryModel);
        songFindPlaylistsButton.setEnabled(false);
    }
    private void processPlaylistsButtonPressed(){ // improved recommendation algorithm
        Main.playlistHandler.processPlaylists(Main.playlistHandler.getPlaylists1(), Main.recSongHandler.getRecommendation1());

        Main.playlistHandler.processPlaylists(Main.playlistHandler.getPlaylists2(), Main.recSongHandler.getRecommendation2());

        Main.playlistHandler.processPlaylists(Main.playlistHandler.getPlaylists3(), Main.recSongHandler.getRecommendation3());

        Main.playlistHandler.processPlaylists(Main.playlistHandler.getPlaylists4(), Main.recSongHandler.getRecommendation4());

        Main.playlistHandler.processPlaylists(Main.playlistHandler.getPlaylists5(), Main.recSongHandler.getRecommendation5());
        processPlaylistsButton.setEnabled(false);
    }

    ////-------SHOW RESULTS-------------------!!!!

    private void loadQueriesButtonPressed(){
        //Setting up recCombo
        recCombo.removeAllItems();
        recCombo.addItem("rec1");
        recCombo.addItem("rec2");
        recCombo.addItem("rec3");
        recCombo.addItem("rec4");
        recCombo.addItem("rec5");
        System.out.println("loadPLaylistsButtonPressed");
        Main.queryHandler.loadQueries();
        Main.queryHandler.updateQueryTable(queryModel);
        loadRecButtonPressed();
    }


    //--SAVE QUERIES
    private void saveQueriesPressed(){
        System.out.println("Save Data pressed");
        Main.queryHandler.saveQuery();
    }



    //--SHOW SELECTED RECOMMENDATION
    private void displayRecButtonPressed(){
        Main.recSongHandler.getSongs().clear();
        Main.recSongHandler.updateTrackTables(songModel);
        String rec = recCombo.getSelectedItem().toString();
        if(rec == "rec1"){ Main.recSongHandler.getSongs().addAll(Main.recSongHandler.getRecommendation1());
        }else if(rec == "rec2"){ Main.recSongHandler.getSongs().addAll(Main.recSongHandler.getRecommendation2());
        }else if(rec == "rec3"){ Main.recSongHandler.getSongs().addAll(Main.recSongHandler.getRecommendation3());
        }else if(rec == "rec4"){ Main.recSongHandler.getSongs().addAll(Main.recSongHandler.getRecommendation4());
        }else if(rec == "rec5"){ Main.recSongHandler.getSongs().addAll(Main.recSongHandler.getRecommendation5());}
        Main.recSongHandler.updateTrackTables(songModel);
    }

    //--SAVE RECOMMENDATIONS
    private void saveRecButtonPressed(){
        Main.recSongHandler.saveRecommendation(Main.recSongHandler.getRecommendation1(), "rec1");
        Main.recSongHandler.saveRecommendation(Main.recSongHandler.getRecommendation2(), "rec2");
        Main.recSongHandler.saveRecommendation(Main.recSongHandler.getRecommendation3(), "rec3");
        Main.recSongHandler.saveRecommendation(Main.recSongHandler.getRecommendation4(), "rec4");
        Main.recSongHandler.saveRecommendation(Main.recSongHandler.getRecommendation5(), "rec5");
        System.out.println("Recommendations saved");
    }

    //--LOAD RECOMMENDATIONS
    private void loadRecButtonPressed(){
        Main.recSongHandler.loadRecommendation(Main.recSongHandler.getRecommendation1(), "rec1");
        Main.recSongHandler.loadRecommendation(Main.recSongHandler.getRecommendation2(), "rec2");
        Main.recSongHandler.loadRecommendation(Main.recSongHandler.getRecommendation3(), "rec3");
        Main.recSongHandler.loadRecommendation(Main.recSongHandler.getRecommendation4(), "rec4");
        Main.recSongHandler.loadRecommendation(Main.recSongHandler.getRecommendation5(), "rec5");
        System.out.println("Recommendations loaded");
    }


    private void createTable(String type) {
        System.out.println("Creating Table");
        if(type == "Query Table") {
            String[] columnNames = {"RecID", "Artist", "Title", "Status", "Count"};
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
    //------------TEST METHODS----------------------
    private void testButton1Pressed() { Main.testHandler.test1(); }

    private void testButton2Pressed(){
        Main.testHandler.test2();
    }

    //----------OLD METHODS-------------------------

    //GET RECOMMENDATION BY ARTIST
    private void artistFindPlaylistsButtonPressed(){
        //songFindPlaylistsButton.setEnabled(false);
        String selectedArtist = resultCombo.getSelectedItem().toString();
        for(int i=1; i<11; i++){
            Main.datasetHandler.parsePLaylists(i);
            Main.playlistHandler.getArtistRecommendation(selectedArtist);
        }
    }

}
