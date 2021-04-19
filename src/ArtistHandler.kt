class ArtistHandler {
    var artists = ArrayList<Artist>()

    fun createArtist(
        artistName: String,
        popularity: Double
    ){
        artists.add(Artist(artistName, popularity))//
    }


}