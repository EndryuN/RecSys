import java.io.Serializable
data class RecArtist(var duplicateCount: Int,
                     var artistName: String,
                     var duplicateCheck: Boolean) : Serializable
