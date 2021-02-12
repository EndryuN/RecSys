class PlaylistHandler {
    var playlists = ArrayList<Playlist>()

    fun createPlaylist(
        playlistID: Int,
        num_tracks: Int,
        //num_artist: Int,
        //tracks: ArrayList<Track>
    ){
        playlists.add(Playlist(playlistID, num_tracks))
    }


}