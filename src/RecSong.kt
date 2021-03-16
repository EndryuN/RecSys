import java.io.Serializable

data class RecSong(var duplicateCount: Int,
                   var artistName: String,
                   var trackName: String,
                   //var duplicateCheck: Boolean
                   ) : Serializable
