class RecArtistHandler {
    var artists = ArrayList<RecArtist>()

    fun createRecArtist(
        duplicateCount: Int,
        artistName: String,
       duplicateCheck: Boolean = false
    ){
        artists.add(RecArtist(duplicateCount, artistName, duplicateCheck))
    }

    fun endOfPlaylistIncrement(){

    }

}