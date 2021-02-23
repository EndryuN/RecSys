class RecHandler {
    var artists = HashSet<Artist>()

    fun findPlaylists(){
        for(i in 1..10){
            Main.datasetHandler.parsePLaylists(i)
           // Main.playlistHandler.getRecommendation()
        }

    }

}