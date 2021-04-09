class TrackHandler {
    var tracks = ArrayList<Track>()

    fun createTrack(artistName: String, trackName: String, track_uri: String){
        tracks.add(Track(artistName, trackName, track_uri))
    }
}