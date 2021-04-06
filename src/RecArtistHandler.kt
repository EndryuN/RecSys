class RecArtistHandler {
    var artists = ArrayList<RecArtist>()

    var recArtists1 = ArrayList<RecArtist>()

    fun createRecArtist(
        duplicateCount: Int,
        artistName: String,

    ){
        artists.add(RecArtist(duplicateCount, artistName))
    }

    fun endOfPlaylistIncrement(){

    }

}