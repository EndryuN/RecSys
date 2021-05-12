class TrackHandler {
    var tracks = ArrayList<Track>()
    //class for handling tracks inside the playlist dataclass
    fun createTrack(artistName: String, trackName: String){
        tracks.add(Track(artistName, trackName))
    }
}