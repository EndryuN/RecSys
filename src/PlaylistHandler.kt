import java.lang.IndexOutOfBoundsException

class PlaylistHandler {
    val p = Persistence()
    var playlists = ArrayList<Playlist>()

    var currentPlaylist = Playlist(
        playlistID = 0,
        //collaborative = true
    )


    fun createPlaylist(
        playlistID: Int,
        //collaborative: Boolean,
        //num_artist: Int,
        //tracks: ArrayList<Track>
    ){
        //tracks2 = tracks
        playlists.add(Playlist(playlistID))
        //selectPlaylist()
        currentPlaylist = playlists.last()
        Main.trackHandler.tracks = currentPlaylist.tracks
    }

    fun selectPlaylist(){


    }



    fun getRecommendation(artistName: String, songTitle: String){
        for(i in playlists){
            for(track in i.tracks){
                if(track.trackName.contains(songTitle) && track.artistName.contains(artistName)){
                    print("ITS A MATCH")
                    println(i)
                }
            }
        }
        print("recommendation finished")
    }

    fun loadPlaylists(){
        try {
            playlists = p.loadPlaylists()
        }catch (iob : IndexOutOfBoundsException){
            println("Projects file was empty on load")
            println("-------------------------\n")
        }
    }

    fun savePlaylists(){
        p.savePlaylists(playlists)
        print("saved artists")
    }

}