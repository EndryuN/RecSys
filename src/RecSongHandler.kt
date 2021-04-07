import java.lang.IndexOutOfBoundsException
import javax.swing.table.DefaultTableModel
import kotlin.collections.ArrayList

class RecSongHandler {
    val p = Persistence()
    var songs = ArrayList<RecSong>()
    var recommendation1 = ArrayList<RecSong>()
    var recommendation2 = ArrayList<RecSong>()
    var recommendation3 = ArrayList<RecSong>()
    var recommendation4 = ArrayList<RecSong>()
    var recommendation5 = ArrayList<RecSong>()

    fun createRecSong(
        duplicateCount: Int,
        artistName: String,
        trackName: String,

    ){
        songs.add(RecSong(duplicateCount, artistName, trackName))
    }

    // Incrementing song count by one
    fun incrementSong(artistName: String, trackName: String): RecSong{
        songs.forEach { song ->
            if(artistName == song.artistName && trackName == song.trackName){
                song.duplicateCount++
                return song
            }

        }
        return songs[0]
    }
    //Function for checking if the song already exists
    fun songExists(artistName: String, trackName: String): Boolean {
        for (song in songs){
            if(song.artistName==artistName && song.trackName==trackName){
                return true
            }
        }
        return false
    }

    fun updateTrackTables(songTable: DefaultTableModel){
        songTable.setNumRows(0)
        songs.sortedBy { it.duplicateCount }
        for(song in songs.sortedByDescending { it.duplicateCount }){
            songTable.addRow(arrayOf<Any>(song.artistName.substring(2,song.artistName.length-1), song.trackName.substring(2, song.trackName.length-1), song.duplicateCount))
        }
    }

    fun saveRecSong(recRef: java.util.ArrayList<RecSong>, refName: String) {
        p.saveRecSong(recRef, refName)
        print("saved $refName")
    }

    fun loadRecSong(recRef: java.util.ArrayList<RecSong>, recName: String) {
        try {
            songs = p.loadRecSong(recName)
        }catch (iob : IndexOutOfBoundsException){
            println("Projects file was empty on load")
            println("-------------------------\n")
        }
        recRef.addAll(songs)
        songs.clear()
    }
}
