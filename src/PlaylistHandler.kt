import java.lang.IndexOutOfBoundsException
import java.util.*
import javax.swing.table.DefaultTableModel
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class PlaylistHandler {
    val p = Persistence()
    var playlists = ArrayList<Playlist>()
    var playlists2 = ArrayList<Playlist>()

    var artistsSet = HashSet<Artist>()
    var songsSet = HashSet<String>()
    var testvar = mutableSetOf<ArtistRec>()


    var duplicateSong = ArrayList<TrackRec>()
    var duplicateArtist = ArrayList<ArtistRec>()
    var playlistholder = ArrayList<Artist>()
    var maptest = mutableMapOf<String, Int>()
    var artistArray = arrayOf(String)



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
        for(playlist in playlists2){//Setting Hashsets for comparison
            for(track in playlist.tracks){
                artistsSet.add(Artist(track.artistName.substring(2, track.artistName.length-1)))// maybe add it to a hash map over here
                songsSet.add(track.artistName.substring(2, track.artistName.length-1)+"£"+track.trackName.substring(2, track.trackName.length-1))
            }//.substring(2,song.artistName.length-1) .substring(2, song.trackName.length-1)
        }

        for(i in artistsSet){  // Setting artist array for counting
            duplicateArtist.add(ArtistRec(0, i.artistName, false))
        }
        for(i in songsSet){ //Setting song array for counting
            var artistVar = i.split("£")[0]
            var songVar = i.split("£")[1]
            duplicateSong.add(TrackRec(0, artistVar, songVar, false))
        }
        var progressCounter = 0

        for(playlist in playlists2) {
            for(track in playlist.tracks) {
                //make a seperate function to check this
                //ALSO.... .joinToString()
            }
        }


        for (playlist in playlists2){ // for each playlist
            //var playlistholder: ArrayList<Track> = playlist.tracks
            playlistholder.clear()

            for (track in playlist.tracks){
                playlistholder.add(Artist(track.artistName))
            }

            progressCounter++
            println("Playlist $progressCounter / ${playlists2.size} ")
            // over here
            for (artist in artistsSet){
                if (playlistholder.contains(artist.toString())){
                    duplicateArtist[artistsSet.indexOf(artist)].duplicateCheck = true
                    println("something is happening")
                }
            }
            for (song in songsSet){
                if (playlist.tracks.contains(song)){
                    duplicateSong[songsSet.indexOf(song)].duplicateCheck = true
                }
            }




        //    for (track in playlist.tracks){
        //        for (artist in artistsSet) {
        //            if (track.artistName == artist) {
        //                duplicateArtist[artistsSet.indexOf(artist)].duplicateCheck = true
        //            }
        //        }
        //        for (song in songsSet){
        //            if (song.contains(track.trackName) && song.contains(track.artistName)) { // AND ARTIST NAME NEEDS TO MATCH
        //                duplicateSong[songsSet.indexOf(song)].duplicateCheck = true
        //            }
        //        }
//}
            // All the duplicates are marked as true
            for(artist in duplicateArtist){
                if(artist.duplicateCheck){
                    artist.duplicateCount++
                    artist.duplicateCheck = false
                }
            }
            for(song in duplicateSong){
                if(song.duplicateCheck){
                    song.duplicateCount++
                    song.duplicateCheck = false
                }
            }

        }
        /*
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
        }*/
        println("Unique artists: "+artistsSet.size)
        println("Unique songs: "+songsSet.size)
        println("Total playlists found: "+playlists2.size)
        println(duplicateSong.size)
    }

    fun updateArtistTables(artistTable: DefaultTableModel){
        artistTable.setNumRows(0)
        for(artist in duplicateArtist.sortedByDescending { it.duplicateCount }){
            artistTable.addRow(arrayOf<Any>(artist.artistName, artist.duplicateCount))
        }
    }
    fun updateTrackTables(songTable: DefaultTableModel){
        songTable.setNumRows(0)
        duplicateSong.sortedBy { it.duplicateCount }
        for(song in duplicateSong.sortedByDescending { it.duplicateCount }){
            songTable.addRow(arrayOf<Any>(song.artistName, song.trackName, song.duplicateCount))
        }
    }

//--song based playlist crawling
    fun getSongRecommendation(artistName: String, songTitle: String){
        for(i in playlists){
            for(track in i.tracks){
                if(track.trackName.contains(songTitle) && track.artistName.contains(artistName)){
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
//----artist based crawling
    fun getArtistRecommendation(artistName: String){
        for(i in playlists){
            for(track in i.tracks){
                if(track.artistName.contains(artistName)){
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