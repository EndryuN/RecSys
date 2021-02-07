class ArtistHandler {

    //Variables
    val p = Persistence()
    var artists = ArrayList<Artist>()
    var artists2 = ArrayList<Artist>()
    //clear artist2
    var currentArtist = Artist(
        artistName = "artist name",
        acousticness = 0,
        danceability = 0,
        energy = 0,
        instrumentalness = 0,
        liveness = 0,
        loudness = 0,
        speechiness = 0,
        tempo = 0,
        valence = 0,
        popularity = 0,
        key = 0,
        mode = 0,
        count = 0
    )

    fun createArtist(
        artistName: String,
        acousticness : Int,
        danceability : Int,
        energy : Int,
        instrumentalness: Int,
        liveness: Int,
        loudness: Int,
        speechiness: Int,
        tempo: Int,
        valence: Int,
        popularity: Int,
        key: Int,
        mode: Int,
        count: Int

    ){
        artists.add(Artist(artistName,acousticness, danceability,
            energy, instrumentalness, liveness, loudness,
            speechiness, tempo, valence, popularity, key, mode, count))
    }

}