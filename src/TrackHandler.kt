class TrackHandler {
    var tracks = ArrayList<Track>()
    //creating tracklist inside playlist
    fun createTrack(artistName: String, trackName: String, track_uri: String){
        tracks.add(Track(artistName, trackName, track_uri))
    }
}