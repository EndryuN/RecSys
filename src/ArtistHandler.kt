class ArtistHandler {
    var artists = ArrayList<Artist>()

    fun createArtist(
        artistName: String
    ){
        artists.add(Artist(artistName))
    }
}