import javax.swing.table.DefaultTableModel

class RecSongHandler {
    var songs = ArrayList<RecSong>()

    fun createRecSong(
        duplicateCount: Int,
        artistName: String,
        trackName: String,

    ){
        songs.add(RecSong(duplicateCount, artistName, trackName))
    }

    fun incrementSong(artistName: String, trackName: String): RecSong{
        songs.forEach { song ->
            if(artistName == song.artistName && trackName == song.trackName){
                song.duplicateCount++
                return song
            }

        }
        return songs[0]
    }

    fun songExists(artistName: String, trackName: String): Boolean {
        for (song in songs){
            if(song.artistName==artistName && song.trackName==trackName){
                return true
            }
        }
        return false
    }



    fun getCount(artistName: String, trackName: String): Int {
        for (song in songs){
            if(song.artistName.contains(artistName) && song.trackName.contains(trackName)){
                return song.duplicateCount

            }
        }
        return 0
    }
    fun updateTrackTables(songTable: DefaultTableModel){
        songTable.setNumRows(0)
        songs.sortedBy { it.duplicateCount }
        for(song in songs.sortedByDescending { it.duplicateCount }){
            songTable.addRow(arrayOf<Any>(song.artistName, song.trackName, song.duplicateCount))
        }
    }//.substring(2,song.artistName.length-1).substring(2, song.trackName.length-1)

}
