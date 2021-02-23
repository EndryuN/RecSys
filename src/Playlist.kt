import java.io.Serializable
data class Playlist(
    //var playlistName: String,
    var playlistID: Int
    //var collaborative: Boolean,
    //var num_albums: Int,
    //var num_artist: Int,
    //var tracks: ArrayList<Track>
): Serializable
{
    var tracks = arrayListOf<Track>()
}

