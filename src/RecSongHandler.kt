class RecSongHandler {
    var songs = ArrayList<RecSong>()

    fun createRecSong(
        duplicateCount: Int,
        artistName: String,
        trackName: String
    ){
        songs.add(RecSong(duplicateCount, artistName, trackName))
    }
    fun incrementSong(){}

    fun songExists(artistName: String, trackName: String){
        if(songs.lastIndex(RecSong())
    }

}