public class Main {
    //Handlers
    public static DatasetHandler datasetHandler;
    public static ArtistHandler artistHandler;
    public static SongHandler songHandler;
    public static PlaylistHandler playlistHandler;
    public static TrackHandler trackHandler;
    public static RecHandler recHandler;// the 2 below may be turned into
    public static RecSongHandler recSongHandler;// companion objects in the
    public static RecArtistHandler recArtistHandler;// future


    public static void main(String[] args) {

        datasetHandler = new DatasetHandler();
        artistHandler = new ArtistHandler();
        songHandler = new SongHandler();
        playlistHandler = new PlaylistHandler();
        trackHandler = new TrackHandler();
        recHandler = new RecHandler();
        recSongHandler = new RecSongHandler();
        recArtistHandler = new RecArtistHandler();

        new MainGUI();
    }

}
