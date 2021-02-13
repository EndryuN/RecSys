
class PlaylistHandler {
    var playlists = ArrayList<Playlist>()

    var currentPlaylist = Playlist(
        playlistID = 0,
    )


    fun createPlaylist(
        playlistID: Int,
        //num_tracks: Int,
        //num_artist: Int,
        //tracks: ArrayList<Track>
    ){
        //tracks2 = tracks
        playlists.add(Playlist(playlistID))
        selectPlaylist(playlists.last().playlistID)
    }

    fun selectPlaylist(selectedPlaylist: Int){
        var playlistFound = false
        for (i in playlists){
            if(i.playlistID == selectedPlaylist){
                currentPlaylist = i
                playlistFound = true
                setPlaylistSongs()
                break
            }
        }
        if (!playlistFound){
            println("Playlist not found")
        }
    }
    private fun setPlaylistSongs(){
        Main.trackHandler.tracks = currentPlaylist.tracks
        println("Setting playlist songs")
    }

    fun testFunction(selectedPlaylist: Int){
        print(playlists[selectedPlaylist].tracks)
    }

    fun getRecommendation(songTitle: String){
        for(i in playlists){
            for(track in i.tracks){
                if(track.trackName.contains(songTitle, ignoreCase = true)){
                    print("ITS A MATCH")
                }
            }
        }
        print("recommendation finished")
    }

}