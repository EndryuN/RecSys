import java.io.Serializable
data class Playlist(var playlistName: String, var collaborative: Boolean, var playlistID: Int,  ) : Serializable{
    var songs = ArrayList<PlaylistSong>()
}
