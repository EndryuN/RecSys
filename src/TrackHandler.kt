import javax.swing.table.DefaultTableModel

class TrackHandler {
    var tracks = ArrayList<Track>()

    fun createTrack(artistName: String, trackName: String){
        tracks.add(Track(artistName, trackName))
    }


    fun updateTrackTables(songTable: DefaultTableModel){
        songTable.setNumRows(0)
        for(track in tracks){
            songTable.addRow(arrayOf<Any>(track.artistName, track.trackName))
        }
    }
}