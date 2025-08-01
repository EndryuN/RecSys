import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

private const val acousticness_IDX = 0
private const val artistName_IDX = 1
private const val danceability_IDX = 2
private const val duration_ms_IDX = 3
private const val energy_IDX = 4
private const val explicit_IDX = 5
private const val song_id_IDX = 6
private const val instrumentalness_IDX = 7
private const val key_IDX = 8
private const val liveness_IDX = 9
private const val loudness_IDX = 10
private const val mode_IDX = 11
private const val songTitle_IDX = 12
private const val popularity_IDX = 13
private const val release_date_IDX = 14
private const val speechiness_IDX = 15
private const val tempo_IDX = 16
private const val valence_IDX = 17
private const val year_IDX = 18
//this part of the code can still be improved
//changes will be made once the datasets have been updated

class DatasetHandler {
    private val path = System.getProperty("user.dir")

    fun parseArtists() {// Parsing artists from the artist dataset
        var fileReader: BufferedReader? = null
        try {
            var line: String?
            fileReader = BufferedReader(FileReader("$path/src/datasets/artistDataset.csv"))
            fileReader.readLine()// Read CSV header
            line = fileReader.readLine()// Read the file line by line starting from the second line
            while (line != null) {
                val tokens = line.split(",")
                if (tokens.size > 0) {
                    Main.artistHandler.createArtist(tokens[0], tokens[11].toDouble())//
                }
                line = fileReader.readLine()
            }
        } catch (e: Exception) {
            println("Reading CSV Error!")
            e.printStackTrace()
        } finally {
            try {
                fileReader!!.close()
            } catch (e: IOException) {
                println("Closing fileReader Error!")
                e.printStackTrace()
            }
        }
    }

    fun parseSongs() { // Parsing songs from song dataset
        var fileReader: BufferedReader? = null
        try {
            var line: String?
            fileReader = BufferedReader(FileReader("$path/src/datasets/songDataset.csv"))
            fileReader.readLine()// Read CSV header
            line = fileReader.readLine()// Read the file line by line starting from the second line
            while (line != null) {
                val tokens = line.split(",")
                var stopFlag: Boolean = true
                if (tokens.size>19) {
                    var customArtists: String = ""
                    var artistLength: Int = 0
                    var customTitle: String = ""
                    var titlePart: String = ""
                    var titleBase: String = ""
                    var titleLength: Int = 0
                    for(i in 1..tokens.size-1){
                        if(tokens[i].matches("-?\\d+(\\.\\d+)?".toRegex())){
                            stopFlag = false
                        }
                        if(stopFlag){// determining the length of artist credits split by commas
                            artistLength++
                            customArtists += tokens[i]
                            if(tokens[i+1].matches("-?\\d+(\\.\\d+)?".toRegex())){
                                break
                            }else{
                                customArtists +=","
                            }
                        }
                    }
                    if(tokens.size-artistLength+1<19){
                    } else{
                        stopFlag=true
                        for(i in 6..tokens.size-1){
                            if(tokens[tokens.size-i-1].matches("-?\\d+(\\.\\d+)?".toRegex())){
                                stopFlag = false
                            }
                            if (stopFlag){
                                titleLength++
                                if(tokens[tokens.size-i-2].matches("-?\\d+(\\.\\d+)?".toRegex())){
                                    customTitle = tokens[tokens.size-i-1]+titleBase
                                    break
                                }else{
                                    titlePart = tokens[tokens.size-i-1]
                                    titleBase = "$titleBase,$titlePart"
                                }
                            }
                        }
                    }
                    Main.songHandler.createSong(
                        tokens [acousticness_IDX],
                        customArtists,
                        tokens [danceability_IDX+artistLength-1],
                        tokens [duration_ms_IDX+artistLength-1],
                        tokens [energy_IDX+artistLength-1],
                        tokens [explicit_IDX+artistLength-1],
                        tokens [song_id_IDX+artistLength-1],
                        tokens [instrumentalness_IDX+artistLength-1],
                        tokens [key_IDX+artistLength-1],
                        tokens [liveness_IDX+artistLength-1],
                        tokens [loudness_IDX+artistLength-1],
                        tokens [mode_IDX+artistLength-1],
                        customTitle,
                        tokens [popularity_IDX+artistLength+titleLength-2],
                        tokens [release_date_IDX+artistLength+titleLength-2],
                        tokens [speechiness_IDX+artistLength+titleLength-2],
                        tokens [tempo_IDX+artistLength+titleLength-2],
                        tokens [valence_IDX+artistLength+titleLength-2],
                        tokens [year_IDX+artistLength+titleLength-2])
                } else if (tokens.size > 0) {//dividing array into variables
                    Main.songHandler.createSong(
                        tokens [acousticness_IDX],
                        tokens [artistName_IDX],
                        tokens [danceability_IDX],
                        tokens [duration_ms_IDX],
                        tokens [energy_IDX],
                        tokens [explicit_IDX],
                        tokens [song_id_IDX],
                        tokens [instrumentalness_IDX],
                        tokens [key_IDX],
                        tokens [liveness_IDX],
                        tokens [loudness_IDX],
                        tokens [mode_IDX],
                        tokens [songTitle_IDX],
                        tokens [popularity_IDX],
                        tokens [release_date_IDX],
                        tokens [speechiness_IDX],
                        tokens [tempo_IDX],
                        tokens [valence_IDX],
                        tokens [year_IDX]
                    )
                }
                line = fileReader.readLine()
            }
        } catch (e: Exception) {
            println("Reading CSV Error!")
            e.printStackTrace()
        } finally {
            try {
                fileReader!!.close()
            } catch (e: IOException) {
                println("Closing fileReader Error!")
                e.printStackTrace()
            }
        }
    }

    // Parsing the playlist dataset for playlist extraction,
    // limit of loaded playlist is around 350k
    // the parser deals with the dataset 1 out of 10 parts at a time 100k with each iteration
    fun parsePLaylists(number: Int){
        var fileReader: BufferedReader? = null
        var counter: Int = number*100-100//1*100-100 = 0 || 2*100-100=100
        var countercounter: Int = number*100000-100000//1*100000-100000=0 || 2*100000-100000 = 100000
        var artist: String = ""
        var title: String = ""
        var line: String?
        while(counter!=number*100){
            counter++
            try {
                fileReader = BufferedReader(FileReader("$path/src/datasets/sorted/playlistDataset ($counter).json"))
                // Read JSON header 8x
                fileReader.readLine()
                fileReader.readLine()
                fileReader.readLine()
                fileReader.readLine()
                fileReader.readLine()
                fileReader.readLine()
                fileReader.readLine()
                fileReader.readLine()
                line = fileReader.readLine()
                while(line!=null) {
                    if(line.contains(":")){
                        var tokens = line.trim().split(":".toRegex(), 2)
                        if(tokens[0].contains("num_tracks")){
                            countercounter++
                            Main.playlistHandler.createPlaylist(countercounter)
                            println("Creating playlist $countercounter")
                        }
                        if(tokens[0].contains("artist_name")){
                            artist = tokens[1].dropLast(1)
                        }
                        if(tokens[0].contains("track_name")){
                            title = tokens[1].dropLast(1)
                            Main.trackHandler.createTrack(artist, title)
                        }
                    }
                    line = fileReader.readLine()
                }
            } catch (e: Exception) {
                println("Reading CSV Error. Playlist $countercounter")
                e.printStackTrace()
            } finally {
                try {
                    fileReader!!.close()
                } catch (e: IOException) {
                    println("Closing fileReader Error!")
                    e.printStackTrace()
                }
            }
        }
    }
}