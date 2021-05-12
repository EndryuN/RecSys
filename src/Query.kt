import java.io.Serializable
// data class used for handling the search query of the user up to 5 possible at the time
data class Query(
    var recID: String,
    var index: Int,
    var trackName: String,
    var artistName: String,
    var status: String,
    var playlistCount: Int,
    var isSong: Boolean
) : Serializable
