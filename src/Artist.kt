import java.io.Serializable

data class Artist(var artistName : String,
                  var acousticness : Int,
                  var danceability : Int,
                  var energy : Int,
                  var instrumentalness: Int,
                  var liveness: Int,
                  var loudness: Int,
                  var speechiness: Int,
                  var tempo: Int,
                  var valence: Int,
                  var popularity: Int,
                  var key: Int,
                  var mode: Int,
                  var count: Int
) : Serializable{
    var artists = ArrayList<Artist>()

}
