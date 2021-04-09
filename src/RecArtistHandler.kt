import java.lang.IndexOutOfBoundsException
import javax.swing.table.DefaultTableModel
import kotlin.math.roundToLong

class RecArtistHandler {
    var p = Persistence()
    var artists = ArrayList<RecArtist>()

    var recArtists1 = ArrayList<RecArtist>()
    var recArtists2 = ArrayList<RecArtist>()
    var recArtists3 = ArrayList<RecArtist>()
    var recArtists4 = ArrayList<RecArtist>()
    var recArtists5 = ArrayList<RecArtist>()

    fun createRecArtist(
        duplicateCount: Int,
        artistName: String,

    ){
        artists.add(RecArtist(duplicateCount, artistName, 0.0))
    }
    // For processing the recommendation
    fun endOfPlaylistIncrement(hashset: HashSet<String>) {
        for (artist in hashset){
            if(!artistExists(artist)){
                createRecArtist(1, artist)
            } else {
                incrementArtist(artist)
            }
        }
    }
    // Incrementing artist count by one
     fun incrementArtist(artistName: String){
        artists.forEach { artist ->
            if(artistName == artist.artistName){
                artist.duplicateCount++
            }
        }
    }
    // Function for checking is artist exists
    fun artistExists(artistName: String): Boolean {
        for (artist in artists){
            if(artist.artistName == artistName){
                return true
            }
        }
        return false
    }

    //Calculating percentage
    fun calcRec(){
        for(artist in artists){
            artist.percent = artist.duplicateCount.toDouble()/Main.queryHandler.currentQuery.playlistCount*100
        }
    }

    fun updateArtistTables(artistTable: DefaultTableModel){
        artistTable.setNumRows(0)
        for(artist in artists.sortedByDescending { it.duplicateCount }.drop(1)){
            artistTable.addRow(arrayOf<Any>(artist.artistName.substring(2,artist.artistName.length-1), artist.percent.toString().substring(0,4) + "%"))
        }//artist.duplicateCount
    }
    // Saving the artist rec
    fun saveRecArtist(recRef: java.util.ArrayList<RecArtist>, refName: String) {
        p.saveRecArtist(recRef, refName)
    }
    // Loading the artist rec
    fun loadRecArtist(recRef: java.util.ArrayList<RecArtist>, recName: String) {
        try {
            artists = p.loadRecArtist(recName)
        }catch (iob : IndexOutOfBoundsException){
            println("Projects file was empty on load")
            println("-------------------------\n")
        }
        recRef.addAll(artists)
        artists.clear()
    }
}