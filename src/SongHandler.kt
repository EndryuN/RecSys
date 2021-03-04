import javax.swing.table.DefaultTableModel
import kotlin.collections.ArrayList

class SongHandler {
    var songs = ArrayList<Song>()
    var p = Persistence()

    init{
        loadSongs()

    }
    fun createSong(
        acousticness: String,
        artistName: String,
        danceability: String,
        durationMS: String,
        energy: String,
        explicit: String,
        songID: String,
        instrumentalness: String,
        key: String,
        liveness: String,
        loudness: String,
        mode: String,
        songTitle: String,
        popularity: String,
        releaseDate: String,
        speechiness: String,
        tempo: String,
        valence: String,
        year: String
    ){
        songs.add(Song(acousticness, artistName, danceability, durationMS, energy, explicit, songID, instrumentalness, key, liveness, loudness, mode, songTitle, popularity, releaseDate, speechiness, tempo, valence, year))
    }

    fun loadSongs(){
        songs = p.loadFromFile(true)
    }

    fun saveSongs() {
        p.saveToFile(songs, true)
    }
}