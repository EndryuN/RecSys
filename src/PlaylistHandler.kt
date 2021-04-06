import java.lang.IndexOutOfBoundsException
import javax.swing.table.DefaultTableModel
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class PlaylistHandler {
    val p = Persistence()
    var playlists = ArrayList<Playlist>()
    var playlists1 = ArrayList<Playlist>()
    var playlists2 = ArrayList<Playlist>()
    var playlists3 = ArrayList<Playlist>()
    var playlists4 = ArrayList<Playlist>()
    var playlists5 = ArrayList<Playlist>()

    var artistsSet = HashSet<String>()
    var songsSet = HashSet<String>()

    var duplicateSong = ArrayList<RecSong>()
    var duplicateArtist = ArrayList<RecArtist>()


    var currentPlaylist = Playlist(
        playlistID = 0
    )


    fun createPlaylist(
        playlistID: Int
    ){
        playlists.add(Playlist(playlistID)) //adding playlist to the list
        currentPlaylist = playlists.last()
        Main.trackHandler.tracks = currentPlaylist.tracks
    }

    //--song based playlist crawling
    fun getSongRecommendation(
        index: Int,
        artistName: String,
        songTitle: String,
        playlistRef: java.util.ArrayList<Playlist>
    ){
        for(i in playlists){
            for(track in i.tracks){
                if(track.trackName.toLowerCase().contains(songTitle.toLowerCase()) && track.artistName.toLowerCase().contains(artistName.toLowerCase())){
                    playlistRef.add(i)//
                    break
                }
            }
        }
        var playlistsize = playlistRef.size
        println("////////////////////////////")
        println("playlist size $playlistsize")
        println("////////////////////////////")
        if(playlistsize==0){
            Main.queryHandler.queries[index].status = "not found"
        }else{
            Main.queryHandler.queries[index].status = "Playlists gathered"
        }
    }

    //--processing playlists to obtain the recommendation
    fun processPlaylists(playlistRef: java.util.ArrayList<Playlist>, recRef: java.util.ArrayList<RecSong>) {
        var progressCounter = 0
        for(playlist in playlistRef){
            progressCounter++
            println("Playlist $progressCounter / ${playlistRef.size} ")
            for(track in playlist.tracks){
                if(Main.recSongHandler.songExists(track.artistName, track.trackName)){// if the song
                    var SongIndex = Main.recSongHandler.songs.indexOf(Main.recSongHandler.incrementSong(track.artistName, track.trackName))
                    var SongHolder = Main.recSongHandler.songs.get(SongIndex)
                    Main.recSongHandler.songs.removeAt(SongIndex)
                    Main.recSongHandler.songs.add(0, SongHolder)
                }else{
                    Main.recSongHandler.createRecSong(1, track.artistName, track.trackName)
                }
                if(progressCounter == playlistRef.size){

                }
            }
        }
        recRef.addAll(Main.recSongHandler.songs)
        Main.recSongHandler.songs.clear()
    }

    fun printPlaylists(){
        for(playlist in playlists2){//Setting Hashsets for comparison
            for(track in playlist.tracks){
                artistsSet.add(track.artistName.substring(2, track.artistName.length-1))
                songsSet.add(track.artistName+"£"+track.trackName)
            }
        }
        for(i in artistsSet){  // Setting artist array for counting
            duplicateArtist.add(RecArtist(0, i, false))
            println(i)
        }
        for(i in songsSet){ //Setting song array for counting
            var artistVar = i.split("£")[0]
            var songVar = i.split("£")[1]
            duplicateSong.add(RecSong(0, artistVar, songVar))
            println(i)
        }
        var progressCounter = 0
        for (playlist in playlists2){ // for each playlist
            progressCounter++
            println("Playlist $progressCounter / ${playlists2.size} ")
            for (track in playlist.tracks){
                for (artist in artistsSet) {
                    if (track.artistName.contains(artist)) {
                        duplicateArtist[artistsSet.indexOf(artist)].duplicateCheck = true
                    }
                }
                for (song in songsSet){
                    if (song.contains(track.trackName) && song.contains(track.artistName)) { // AND ARTIST NAME NEEDS TO MATCH
                        duplicateSong[songsSet.indexOf(song)].duplicateCount++
                    }
                }

            }// All the duplicates are marked as true
            for(artist in duplicateArtist){
                if(artist.duplicateCheck){
                    artist.duplicateCount++
                    artist.duplicateCheck = false
                }
            }
    //        for(song in duplicateSong){
    //            if(song.duplicateCheck){
    //                song.duplicateCount++
    //                song.duplicateCheck = false
    //            }
    //        }

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
            songTable.addRow(arrayOf<Any>(song.artistName.substring(2,song.artistName.length-1), song.trackName.substring(2, song.trackName.length-1), song.duplicateCount))
        }
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
}