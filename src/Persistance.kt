import java.io.*

class Persistence {
    val path = System.getProperty("user.dir")
    var artistsFile = "$path/Artists.txt"
    var songsFile = "$path/Songs.txt"

    init {
        print(artistsFile)
    }

    fun saveToFile(list : ArrayList<Artist>) {
        println("-------------------------\n " +
                "Save Started - File path: \n $artistsFile " +
                "\n-------------------------")
        try {
            //Lambda used to save to file
            ObjectOutputStream(FileOutputStream(artistsFile)).use{ it -> it.writeObject(list)}
        }catch (ioe: IOException){
            println("Error: Unable to save - IO Exception")
        }

        println("List of Artists Saved to file\n")
    }

    fun saveToFile(list : ArrayList<Song>, bool: Boolean) {
        println("-------------------------\n " +
                "Save Started - File path: \n $songsFile " +
                "\n-------------------------")
        try {
            //Lambda used to save to file
            ObjectOutputStream(FileOutputStream(songsFile)).use{ it -> it.writeObject(list)}
        }catch (ioe:IOException){
            println("Error: Unable to save - IO Exception")
        }

        println("List of Songs Saved to file\n")
    }

    fun loadFromFile(): ArrayList<Artist> {

        println("-------------------------\n " +
                "Load Started - File path: \n ${artistsFile} " +
                "\n-------------------------")

        val nullList = ArrayList<Artist>()
        var artistList : ArrayList<Artist>

        try {
            //uses lambda to de-serialize the file into array of projects
            artistList = ObjectInputStream(FileInputStream(artistsFile)).use { it -> it.readObject() as ArrayList<Artist>}
            println("Artists Loaded into memory")

        }catch (ioe : IOException){
            println("Error: Could not load from file:\n $artistsFile \n Possibly no artists")
            return nullList
        }catch (c: ClassNotFoundException){
            println("Error: Class not found to cast")
            return nullList
        }
        return artistList
    }

    fun loadFromFile(bool: Boolean): ArrayList<Song> {

        println("-------------------------\n " +
                "Load Started - File path: \n $songsFile " +
                "\n-------------------------")

        val nullList = ArrayList<Song>()
        var songList : ArrayList<Song>

        try {
            //uses lambda to de-serialize the file into array of projects
            songList = ObjectInputStream(FileInputStream(songsFile)).use { it -> it.readObject() as ArrayList<Song>}
            println("Songs loaded into memory")

        }catch (ioe : IOException){
            println("Error: Could not load from file:\n $songsFile")
            return nullList
        }catch (c: ClassNotFoundException){
            println("Error: Class not found to cast")
            return nullList
        }
        return songList
    }


}