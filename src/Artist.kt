import java.io.Serializable

data class Artist(var artistName : String) : Serializable{
    var songs = ArrayList<Artist>()

}
