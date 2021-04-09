import java.io.Serializable
data class RecSong(var duplicateCount: Int,
                   var artistName: String,
                   var trackName: String,
                   var percent: Double,
                   var track_uri: String,
                   var popularity: String,
                   var danceability: String,
                   var energy: String,
                   var loudness: String,
                   var valence: String
                   ) : Serializable
