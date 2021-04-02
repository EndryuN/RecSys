public class Main {
    //Handlers
    public static DatasetHandler datasetHandler;// used for parsing the 3 datasets
    public static ArtistHandler artistHandler;// used for handling artists
    public static SongHandler songHandler;// used for handling songs
    public static PlaylistHandler playlistHandler;// used for handling the playlists
    public static TrackHandler trackHandler;// used for handling tracks inside playlists
    public static RecHandler recHandler;// the 2 below may be turned into
    public static RecSongHandler recSongHandler;// companion objects in the
    public static RecArtistHandler recArtistHandler;// future
    public static TestHandler testHandler;
    public static QueryHandler queryHandler;


    public static void main(String[] args) {

        datasetHandler = new DatasetHandler();
        artistHandler = new ArtistHandler();
        songHandler = new SongHandler();
        playlistHandler = new PlaylistHandler();
        trackHandler = new TrackHandler();//
        recHandler = new RecHandler();
        recSongHandler = new RecSongHandler();
        recArtistHandler = new RecArtistHandler();
        testHandler = new TestHandler();
        queryHandler = new QueryHandler();

        new MainGUI();
    }

}
