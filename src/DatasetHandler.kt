import java.io.*
import java.util.ArrayList

private const val artistName_IDX = 0


class DatasetHandler {

    val path = System.getProperty("user.dir")
    fun loadArtists() {
        var fileReader: BufferedReader? = null


        try {
            val artists = ArrayList<Artist>()
            var line: String?


            fileReader = BufferedReader(FileReader("$path/src/artistDataset.csv"))

            // Read CSV header
            fileReader.readLine()
            // Read the file line by line starting from the second line
            line = fileReader.readLine()
            while (line != null) {
                val tokens = line.split(",")
                if (tokens.size > 0) {
                    Main.artistHandler.createArtist(tokens[artistName_IDX])
                }

                line = fileReader.readLine()
            }

            // Print the new customer list
            for (artist in artists) {
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


}