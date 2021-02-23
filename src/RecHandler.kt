class RecHandler {
    var artists = HashSet<Artist>()
    var playlists3 = ArrayList<Track>()

    fun findPlaylists(){
        for(i in 1..10){
            Main.datasetHandler.parsePLaylists(i)
           // Main.playlistHandler.getRecommendation()
        }

    }

}