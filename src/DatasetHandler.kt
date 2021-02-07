import java.io.*
class DatasetHandler {
    val path = System.getProperties()
    var artistDataset = "$path/data_by_artist.csv"

    fun readFile()
            = File(artistDataset).forEachLine {print(it) }




}