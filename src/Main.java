public class Main {
    //Static class Handlers
    public static DatasetHandler datasetHandler;// used for parsing the datasets
    public static ArtistHandler artistHandler;// used for handling artists
    public static SongHandler songHandler;// used for handling songs
    public static PlaylistHandler playlistHandler;// used for handling the playlists
    public static TrackHandler trackHandler;// used for handling tracks inside playlists
    public static RecSongHandler recSongHandler;// used for handling song recommendations
    public static RecArtistHandler recArtistHandler;// used for handling artist recommendation
    public static QueryHandler queryHandler;// used for handling the processing queue and order

    public static void main(String[] args) {
        datasetHandler = new DatasetHandler();
        artistHandler = new ArtistHandler();
        songHandler = new SongHandler();
        playlistHandler = new PlaylistHandler();
        trackHandler = new TrackHandler();
        recSongHandler = new RecSongHandler();
        recArtistHandler = new RecArtistHandler();
        queryHandler = new QueryHandler();
        new MainGUI();
    }
}
