import java.io.Serializable
data class Playlist(var playlistName: String) : Serializable{
    var songs = ArrayList<Song>()
}
