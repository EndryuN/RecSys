class ArtistHandler {
    //dataset handler for handling artists from the artist dataset
    var artists = ArrayList<Artist>()

    fun createArtist(
        artistName: String,
        popularity: Double
    ){
        artists.add(Artist(artistName, popularity))
    }
}