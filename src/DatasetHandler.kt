import java.io.*
import java.util.ArrayList

private const val artistName_IDX = 0

fun loadArtists() {
    var fileReader: BufferedReader? = null
    val path = System.getProperty("user.dir")

    try {
        val artist = ArrayList<Artist>()
        var line: String?


        fileReader = BufferedReader(FileReader("$path/artistDataset.csv"))

        // Read CSV header
        fileReader.readLine()
        // Read the file line by line starting from the second line
        line = fileReader.readLine()
        while (line != null) {
            val tokens = line.split(",")
            if (tokens.size > 0) {
                val artist = Artist(
                    tokens[artistName_IDX]
                )
                Main.artistHandler.createArtist(tokens[artistName_IDX])
            }

            line = fileReader.readLine()
        }

        // Print the new customer list
        for (artist in artist) {
            println(artist)
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




class DatasetHandler {
    val path = System.getProperties()
    var artistDataset = "$path/data_by_artist.csv"


}