import java.lang.IndexOutOfBoundsException

class ArtistHandler {

    //Variables
    val p = Persistence()
    var artists = ArrayList<Artist>()
    var artists2 = ArrayList<Artist>()
    //clear artist2
    var currentArtist = Artist(
        artistName = "artist name",

    )
    init{
        loadArtists()
    }

    fun createArtist(
        artistName: String,
    ){
        artists.add(Artist(artistName))
    }
    fun loadArtists(){
        try {
            artists = p.loadFromFile()
        }catch (iob : IndexOutOfBoundsException){
            println("Projects file was empty on load")
            println("-------------------------\n")
        }
    }

    fun saveArtists(){
        p.saveToFile(artists)
        print("saved artists")
    }
}