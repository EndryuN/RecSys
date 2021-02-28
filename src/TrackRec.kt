import java.io.Serializable

data class TrackRec(var duplicateCount: Int,
                    var artistName: String,
                    var trackName: String,
                    var duplicateCheck: Boolean) : Serializable
