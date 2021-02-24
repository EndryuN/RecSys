import java.lang.IndexOutOfBoundsException
import javax.swing.table.DefaultTableModel

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
        playlistID = 0
        //collaborative = true
    )


    fun createPlaylist(
        playlistID: Int
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
                songsSet.add(track.artistName+"£"+track.trackName)
            }
        }
        for(i in artistsSet){
            duplicateArtist.add(ArtistRec(0, i))
        }
        for(i in songsSet){
            var artistHolder = i.split("£")[0]
            var titleHolder = i.split("£")[1]
            duplicateSong.add(TrackRec(0, titleHolder, artistHolder))
        }

        for (playlist in playlists2){
            for (track in playlist.tracks){
                for (hashpart in artistsSet){
                    if(track.artistName == hashpart){
                        duplicateArtist[artistsSet.indexOf(hashpart)].duplicateCount++
                        break
                    }

                }
                for (hashpart in songsSet){
                    if(track.trackName.contains(hashpart)){
                        duplicateSong[songsSet.indexOf(hashpart)].duplicateCount++
                    }
                }
            }
        }
        duplicateArtist.sortedWith(compareBy {it.duplicateCount})
        duplicateSong.sortedWith(compareBy {it.duplicateCount})

        for(element in duplicateArtist){
            if(element.duplicateCount > 20){
                println(element.artistName+" :"+element.duplicateCount)
            }
        }
        println("////////SONGS///////////")
        for(element in duplicateSong){
            if(element.duplicateCount > 10){
                println(element.trackName+" :"+element.duplicateCount)
            }
        }
        println("Unique artists: "+artistsSet.size)
        println("Unique songs: "+songsSet.size)
        println("Total playlists found: "+playlists2.size)
        println(duplicateSong.size)
    }

    fun updateArtistTables(artistTable: DefaultTableModel){
        artistTable.setNumRows(0)
        for(artist in duplicateArtist){
            artistTable.addRow(arrayOf<Any>(artist.artistName, artist.duplicateCount))
        }
    }
    fun updateTrackTables(songTable: DefaultTableModel){
        songTable.setNumRows(0)
        for(song in duplicateSong){
            songTable.addRow(arrayOf<Any>(song.artistName, song.trackName, song.duplicateCount))
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