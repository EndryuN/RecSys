import java.lang.IndexOutOfBoundsException

class PlaylistHandler {
    val p = Persistence()
    var playlists = ArrayList<Playlist>()
    var playlists2 = ArrayList<Playlist>()

    var artistsSet = HashSet<String>()
    var songsSet = HashSet<String>()

    var duplicateSong = ArrayList<TrackRec>()
    var duplicateArtist = ArrayList<ArtistRec>()

    var artistvar = ""


    var currentPlaylist = Playlist(
        playlistID = 0,
        //collaborative = true
    )


    fun createPlaylist(
        playlistID: Int,
        //collaborative: Boolean,
        //num_artist: Int
    ){
        playlists.add(Playlist(playlistID))
        currentPlaylist = playlists.last()
        Main.trackHandler.tracks = currentPlaylist.tracks
    }

    fun printPlaylists(){
        for(playlist in playlists2){
            for(track in playlist.tracks){
                artistsSet.add(track.artistName)
                songsSet.add(track.trackName)
            }
        }
        for(i in artistsSet){
            duplicateArtist.add(ArtistRec(i, 0))
        }

        for (playlist in playlists2){
            for (track in playlist.tracks){
                for (hashpart in artistsSet){
                    if(track.artistName == hashpart){
                        duplicateArtist[artistsSet.indexOf(hashpart)].duplicateCount++
                    }
                }
            }
        }
        duplicateArtist.sort()
        for(element in duplicateArtist){
            println(element.artistName+" :"+element.duplicateCount)
        }
    }



    fun getRecommendation(artistName: String, songTitle: String){
        for(i in playlists){
            for(track in i.tracks){
                if(track.trackName.contains(songTitle) && track.artistName.contains(artistName)){
                    print("ITS A MATCH")
                    println(i)
                    playlists2.add(i)
                    break
                }
            }
        }
        playlists.clear()
        var playlistsize = playlists2.size
        println("////////////////////////////")
        println("playlist2 size $playlistsize")
        println("////////////////////////////")
    }

    fun loadPlaylists(){
        try {
            playlists2 = p.loadPlaylists()
        }catch (iob : IndexOutOfBoundsException){
            println("Projects file was empty on load")
            println("-------------------------\n")
        }
    }

    fun savePlaylists(){
        p.savePlaylists(playlists2)
        print("saved artists")
    }

}