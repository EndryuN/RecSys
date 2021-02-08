class ArtistHandler {

    //Variables
    val p = Persistence()
    var artists = ArrayList<Artist>()
    var artists2 = ArrayList<Artist>()
    //clear artist2
    var currentArtist = Artist(
        artistName = "artist name",

    )

    fun createArtist(
        artistName: String,
    ){
        artists.add(Artist(artistName))
        print(artistName)
    }


}