public class Main {
    //Handlers
    public static DatasetHandler datasetHandler;
    public static ArtistHandler artistHandler;
    public static SongHandler songHandler;


    public static void main(String[] args) {

        datasetHandler = new DatasetHandler();
        artistHandler = new ArtistHandler();
        songHandler = new SongHandler();

        new MainGUI();
    }

}
