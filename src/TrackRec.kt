import java.io.Serializable

data class TrackRec(var artistName: String,
                    var trackName: String,
                    var duplicateCount: Int) : Serializable
