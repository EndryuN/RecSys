import java.io.Serializable
data class Playlist(var playlistID: Int): Serializable
{ var tracks = arrayListOf<Track>() }

