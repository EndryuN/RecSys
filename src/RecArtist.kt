import java.io.Serializable
data class RecArtist(var duplicateCount: Int,
                     var artistName: String,
                     var percent: Double,
                     var popularity: Double
                     ) : Serializable
