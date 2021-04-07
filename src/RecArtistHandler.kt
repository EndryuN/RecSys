import java.lang.IndexOutOfBoundsException
import javax.swing.table.DefaultTableModel

class RecArtistHandler {
    var p = Persistence()
    var artists = ArrayList<RecArtist>()

    var recArtists1 = ArrayList<RecArtist>()
    var recArtists2 = ArrayList<RecArtist>()
    var recArtists3 = ArrayList<RecArtist>()
    var recArtists4 = ArrayList<RecArtist>()
    var recArtists5 = ArrayList<RecArtist>()

    var artistHash = HashSet<Artist>()
    var artistArray = String


    fun createRecArtist(
        duplicateCount: Int,
        artistName: String,

    ){
        artists.add(RecArtist(duplicateCount, artistName))
    }

    fun endOfPlaylistIncrement(hashset: HashSet<String>) {
        for (artist in hashset){
            if(!artistExists(artist)){
                createRecArtist(1, artist)
            } else {
                incrementArtist(artist)
//                var artistIndex = artists.indexOf(artist)
//                var artistHolder = artist.get(artistIndex)
//                artists.removeAt(artistIndex)
//                artists.add(0, artistHolder)
            }
        }
    }
    // Incrementing artist count by one
    private fun incrementArtist(artistName: String){
        artists.forEach { artist ->
            if(artistName == artist.artistName){
                artist.duplicateCount++
            }
        }
    }
    fun artistExists(artistName: String): Boolean {
        for (artist in artists){
            if(artist.artistName == artistName){
                return true
            }
        }
        return false
    }


    fun updateArtistTables(artistTable: DefaultTableModel){
        artistTable.setNumRows(0)
        for(artist in artists.sortedByDescending { it.duplicateCount }){
            artistTable.addRow(arrayOf<Any>(artist.artistName, artist.duplicateCount))
        }
    }

    fun saveRecArtist(recRef: java.util.ArrayList<RecArtist>, refName: String) {
        p.saveRecArtist(recRef, refName)
        print("saved $refName")
    }

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