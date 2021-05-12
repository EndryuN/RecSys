import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class PlaylistHandler {
    var playlists = ArrayList<Playlist>()//playlist import array

    var playlists1 = ArrayList<Playlist>()//playlist array for query 1
    var playlists2 = ArrayList<Playlist>()//playlist array for query 2
    var playlists3 = ArrayList<Playlist>()//playlist array for query 3
    var playlists4 = ArrayList<Playlist>()//playlist array for query 4
    var playlists5 = ArrayList<Playlist>()//playlist array for query 5

    //this variable is used for holding the ID of the playlist that is being parsed
    var currentPlaylist = Playlist(
        playlistID = 0
    )

    fun createPlaylist(
        playlistID: Int
    ){
        playlists.add(Playlist(playlistID))
        currentPlaylist = playlists.last()
        Main.trackHandler.tracks = currentPlaylist.tracks
    }

    //--song or artist based playlist crawling
    fun findPlaylists(index: Int, artistName: String, songTitle: String, playlistRef: java.util.ArrayList<Playlist>){
        if(Main.queryHandler.queries[index].isSong){// Song query
            for(i in playlists){// Song based crawling
                for(track in i.tracks){
                    if(track.trackName.toLowerCase().contains(songTitle.toLowerCase()) && track.artistName.contains(artistName)){
                        playlistRef.add(i)
                        break
                    }
                }
            }
        } else if(!Main.queryHandler.queries[index].isSong){ // Artist query
            for(i in playlists){// Artist based crawling
                for(track in i.tracks){
                    if(track.artistName.contains(artistName)){
                        playlistRef.add(i)
                        break
                    }
                }
            }
        }//if the playlist array is empty or not empty the status variable is changed to reflect the result of the search
        if(playlistRef.size==0){
            Main.queryHandler.queries[index].status = "not found"
        }else{
            Main.queryHandler.queries[index].status = "found"
            Main.queryHandler.queries[index].playlistCount = playlistRef.size
        }
    }

    //--process playlists
    fun processPlaylists(index: Int, playlistRef: java.util.ArrayList<Playlist>, recRefSong: java.util.ArrayList<RecSong>, recRefArtist: ArrayList<RecArtist>) {
        var progressCounter = 0
        var artistsSet = HashSet<String>()
        for(playlist in playlistRef){
            progressCounter++
            println("Playlist $progressCounter / ${playlistRef.size} ")
            for(track in playlist.tracks){
                artistsSet.add(track.artistName)//artist
                if(Main.recSongHandler.songExists(track.artistName, track.trackName)){
                    var SongIndex = Main.recSongHandler.songs.indexOf(Main.recSongHandler.incrementSong(track.artistName, track.trackName))
                    var SongHolder = Main.recSongHandler.songs.get(SongIndex)
                    Main.recSongHandler.songs.removeAt(SongIndex)
                    Main.recSongHandler.songs.add(0, SongHolder)
                }else{
                    Main.recSongHandler.createRecSong(1, track.artistName, track.trackName)
                }
            }
            Main.recArtistHandler.endOfPlaylistIncrement(artistsSet)
            artistsSet.clear()
        }
        // filtering the recommendations with count 1
        if(Main.queryHandler.queries[index].playlistCount>=100){//TODO THIS CAN CHANGE
            Main.recArtistHandler.filter1count()
            Main.recSongHandler.filter1count()
        }
        recRefSong.addAll(Main.recSongHandler.songs)
        Main.recSongHandler.songs.clear()
        recRefArtist.addAll(Main.recArtistHandler.artists)
        Main.recArtistHandler.artists.clear()
        Main.queryHandler.queries[index].status = "processed"

    }
}

