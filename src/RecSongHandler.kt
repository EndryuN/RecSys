import java.lang.IndexOutOfBoundsException
import javax.swing.table.DefaultTableModel
import kotlin.collections.ArrayList
import kotlin.math.round

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
        trackName: String
    ){
        songs.add(RecSong(duplicateCount, artistName, trackName, 0.0, 0, 0.0, 0.0))
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
    //Showing only songs by other artists
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
    //function for filtering out 1 count songs
    fun filter1count(){

        println("Amount of songs before filter: "+songs.size)
        for(song in songs){
            if(song.duplicateCount!=1){
                songs2.add(song)
            }
        }
        songs.clear()
        songs.addAll(songs2)
        songs2.clear()
        println("Amount of songs after filter: "+songs.size)
    }

    //Extracting song audio features
    fun audioFeatures(){
        Main.songHandler.songs.clear()
        Main.datasetHandler.parseSongs()
        songfeatureList.clear()
        songfeatureList.addAll(Main.songHandler.songs)
        var progressCounter = 0
        var counterPop = 0
        songs.forEach { rec ->
            if(rec.popularity == 0){
                for(song in songfeatureList){
                    if(song.artistName.contains(rec.artistName.substring(2, rec.artistName.length-1)) &&
                        song.songTitle.contains(rec.trackName.substring(2, rec.trackName.length-1))){
                        rec.popularity = song.popularity.toInt()
                        rec.danceability = song.danceability.toDouble()
                        rec.energy = song.energy.toDouble()
                        counterPop++
                        break
                    }
                }
            }
            progressCounter++
            println("$progressCounter/${songs.size}")
        }
        println("Final song search has found $counterPop/${songs.size}")
        var customPop: Double
        var pogCounter = 0
        for(song in songs){
            if(song.popularity == 0){
                for(artist in Main.recArtistHandler.artists){
                    if(song.artistName.contains(artist.artistName)){
                        customPop = (artist.popularity*(song.percent/100))+artist.percent
                        song.popularity = customPop.toInt()
                        break
                    }
                }
            }
            pogCounter++
            println("Calculating custom popularity: $pogCounter / ${songs.size}")
        }
    }

    fun popularityFilter(limit: Int){
        for(song in songs){
            if(song.popularity < limit){
                songs2.add(song)
            }
        }
        songs.clear()
        songs.addAll(songs2)
        songs2.clear()
    }


    fun updateTrackTables(songTable: DefaultTableModel){
        songTable.setNumRows(0)
        for(song in songs.sortedByDescending { it.duplicateCount }/*here*/){//.drop(1)
            songTable.addRow(arrayOf<Any>(
                song.artistName.substring(2,song.artistName.length-1),
                song.trackName.substring(2, song.trackName.length-1),
                song.duplicateCount,
                "%.2f".format(song.percent) + "%",
                song.popularity,
                "%.2f".format(song.danceability),
                "%.2f".format(song.energy)))
        }//)
    }// add count, delete valence and argument it in explantion, question for count, popularity, danceability, energy

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
