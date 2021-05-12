import java.io.Serializable

data class Song(var acousticness: String,
                var artistName: String,
                var danceability: String,//
                var durationMS: String,
                var energy: String,//
                var explicit: String,
                var songID: String, // flawed method if identifying songs as they can get updated
                var instrumentalness: String,
                var key: String,
                var liveness: String,
                var loudness: String,//
                var mode: String,
                var songTitle: String,
                var popularity: String, //
                var releaseDate: String,
                var speechiness: String,
                var tempo: String,
                var valence: String, //
                var year: String) : Serializable
