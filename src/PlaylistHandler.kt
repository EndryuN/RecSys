import javax.swing.table.DefaultTableModel

class PlaylistHandler {
    var playlists = ArrayList<Playlist>()
    var tracks2 = ArrayList<Track>()

    var currentPlaylist = Playlist(
        playlistID = 0,
        tracks2
    )


    fun createPlaylist(
        playlistID: Int,
        //num_tracks: Int,
        //num_artist: Int,
        tracks: ArrayList<Track>
    ){
        tracks2 = tracks
        playlists.add(Playlist(playlistID, tracks2))
    }

    fun selectPlaylist(selectedPlaylist: Int){
        var playlistFound = false
        for (i in playlists){
            if(i.playlistID == selectedPlaylist){
                currentPlaylist = i
                playlistFound = true

                break
            }
        }
        if (!playlistFound){
            println("Playlist not found")
        }
    }

    fun testFunction(selectedPlaylist: Int){
        print(playlists[selectedPlaylist].tracks)
    }

}