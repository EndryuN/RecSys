import java.io.Serializable
//data class used for handling song recommendations
data class RecSong(var duplicateCount: Int,
                   var artistName: String,
                   var trackName: String,
                   var percent: Double,
                   var popularity: Int,
                   var danceability: Double,
                   var energy: Double,
                   ) : Serializable
