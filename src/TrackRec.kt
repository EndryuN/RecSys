import java.io.Serializable

data class TrackRec(var duplicateCount: Int,
                    var trackName: String,
                    var artistName: String) : Serializable
