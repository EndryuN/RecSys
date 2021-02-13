import javax.swing.table.DefaultTableModel

class PlaylistHandler {
    var playlists = ArrayList<Playlist>()
    var tracks = ArrayList<Track>()

    var currentPlaylist = Playlist(
        playlistID = 0,
        tracks
    )


    fun createPlaylist(
        playlistID: Int,
        //num_tracks: Int,
        //num_artist: Int,
        tracks: ArrayList<Track>
    ){
        playlists.add(Playlist(playlistID, tracks))
    }

    fun selectPlaylist(selectedPlaylist: Int){
        var playlistFound = false
        for (i in playlists){
            if(i.playlistID == selectedPlaylist){
                currentPlaylist = i
                playlistFound = true
                setPlaylistTracks()
            }
        }
        if (!playlistFound){
            println("Playlist not found")
        }
    }

    fun setPlaylistTracks(){
        Main.playlisthandler.tracks = currentPlaylist.tracks
    }


    fun updateTrackTables(songModel: DefaultTableModel){
        songModel.setNumRows(0)

    }

}