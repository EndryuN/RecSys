import java.io.Serializable
//data class used for the playlist dataset
data class Playlist(var playlistID: Int): Serializable
{ var tracks = arrayListOf<Track>() }

