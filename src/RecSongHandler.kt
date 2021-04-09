import java.lang.IndexOutOfBoundsException
import javax.swing.table.DefaultTableModel
import kotlin.collections.ArrayList

class RecSongHandler {
    val p = Persistence()
    var songs = ArrayList<RecSong>()
    var songs2 = ArrayList<RecSong>()
    var recommendation1 = ArrayList<RecSong>()
    var recommendation2 = ArrayList<RecSong>()
    var recommendation3 = ArrayList<RecSong>()
    var recommendation4 = ArrayList<RecSong>()
    var recommendation5 = ArrayList<RecSong>()
    var songfeatureList = ArrayList<Song>()

    fun createRecSong(
        duplicateCount: Int,
        artistName: String,
        trackName: String,
        track_uri: String
    ){
        songs.add(RecSong(duplicateCount, artistName, trackName, 0.0, track_uri, "null"))
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
    //Calculating percentage
    fun calcRec(){
        for (song in songs){
            song.percent = song.duplicateCount.toDouble()/Main.queryHandler.currentQuery.playlistCount*100
        }
    }
    //Showing only songs by the artist
    fun onlyFilter(){
        for(song in songs){
            if(song.artistName.contains(Main.queryHandler.currentQuery.artistName)){
                songs2.add(song)
            }
        }
        songs.clear()
        songs.addAll(songs2)
        songs2.clear()
    }

    fun otherFilter(){
        for(song in songs){
            if(!song.artistName.contains(Main.queryHandler.currentQuery.artistName)){
                songs2.add(song)
            }
        }
        songs.clear()
        songs.addAll(songs2)
        songs2.clear()
    }

    fun testFilter(){
        var counterPop = 0
        var songIDString = ""
        Main.datasetHandler.parseSongs()
        songfeatureList.addAll(Main.songHandler.songs)
        Main.songHandler.songs.clear()
        for(rec in songs){
            songIDString+=(rec.track_uri+", ")
        }
        for(song in songfeatureList){
            if(songIDString.contains(song.songID)){
                for(rec in songs){
                    if(rec.track_uri==song.songID){
                        rec.popularity = song.popularity
                        break
                    }
                }
            }
        }
        println("Test Complete. Found $counterPop songs")
    }

    fun updateTrackTables(songTable: DefaultTableModel){
        songTable.setNumRows(0)
        for(song in songs.sortedByDescending { it.duplicateCount }.drop(1)){
            songTable.addRow(arrayOf<Any>(song.artistName.substring(2,song.artistName.length-1),
                song.trackName.substring(2, song.trackName.length-1),
                song.percent.toString().substring(0,4) + "%", song.popularity))
        }//song.duplicateCount)
    }

    //Saving the song rec
    fun saveRecSong(recRef: java.util.ArrayList<RecSong>, refName: String) {
        p.saveRecSong(recRef, refName)
    }
    //Loading the song rec
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
