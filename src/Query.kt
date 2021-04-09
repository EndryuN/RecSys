import java.io.Serializable
data class Query(
    var recID: String,
    var index: Int,
    var trackName: String,
    var artistName: String,
    var status: String,
    var playlistCount: Int,
    var isSong: Boolean
) : Serializable
