public class Main {
    //Handlers
    public static ArtistHandler artistHandler;
    public static DatasetHandler datasetHandler;


    public static void main(String[] args) {

        artistHandler = new ArtistHandler();
        datasetHandler = new DatasetHandler();

        new MainGUI();
    }

}
