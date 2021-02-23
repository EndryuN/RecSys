public class Main {
    //Handlers
    public static DatasetHandler datasetHandler;
    public static ArtistHandler artistHandler;
    public static SongHandler songHandler;
    public static PlaylistHandler playlistHandler;
    public static TrackHandler trackHandler;
    public static RecHandler recHandler;


    public static void main(String[] args) {

        datasetHandler = new DatasetHandler();
        artistHandler = new ArtistHandler();
        songHandler = new SongHandler();
        playlistHandler = new PlaylistHandler();
        trackHandler = new TrackHandler();
        recHandler = new RecHandler();

        new MainGUI();
    }

}
