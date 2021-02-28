import java.io.Serializable
data class ArtistRec(var duplicateCount: Int,
                     var artistName: String,
                     var duplicateCheck: Boolean) : Serializable
