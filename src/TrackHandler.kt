class TrackHandler {
    var tracks = ArrayList<Track>()
    //creating tracklist inside playlist
    fun createTrack(artistName: String, trackName: String){
        tracks.add(Track(artistName, trackName))
    }
}