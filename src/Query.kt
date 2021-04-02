import java.io.Serializable
data class Query(
    var index: Int,
    var trackName: String,
    var artistName: String,
    var status: String
) : Serializable
