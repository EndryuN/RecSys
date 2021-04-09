import java.io.Serializable
data class RecSong(var duplicateCount: Int,
                   var artistName: String,
                   var trackName: String,
                   var percent: Double,
                   var track_uri: String,
                   var popularity: String
                   ) : Serializable
