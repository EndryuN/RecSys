import java.io.*

class Persistence {
    val path = System.getProperty("user.dir")
    val recPath = "$path/src/recommendations/"

    init {
    }
    // SAVING QUERY
    fun saveQuery(list: ArrayList<Query>){
        var queryPath = recPath+"QueryList.txt"
        var queryList = ArrayList<Query>()
        queryList.addAll(list)

        println("-------------------------\n " +
                "Save Started - File path: \n $queryPath" +
                "\n-------------------------")
        try {
            ObjectOutputStream(FileOutputStream(queryPath)).use{ it -> it.writeObject(queryList)}
        }catch (ioe:IOException){
            println("Error: Unable to save - IO Exception")
        }
        println("List of Songs Saved to file\n")
    }
    // LOADING QUERY
    fun loadQuery(): ArrayList<Query>{
        var queryPath = recPath+"QueryList.txt"

        val nullList = ArrayList<Query>()
        var queryList : ArrayList<Query>
        try {
            queryList = ObjectInputStream(FileInputStream(queryPath)).use { it -> it.readObject() as ArrayList<Query>}
        }catch (ioe : IOException){
            println("Error: Could not load from file:\n $queryPath \n")
            return nullList
        }catch (c: ClassNotFoundException){
            println("Error: Class not found to cast")
            return nullList
        }
        return queryList
    }

    // SAVING SONG RECOMMENDATION
    fun saveRecSong(list: java.util.ArrayList<RecSong>, ref: String) {
        var recPathRef = recPath+"songs/"+ref+".txt"
        var recList = ArrayList<RecSong>()
        recList.addAll(list)

        println("-------------------------\n " +
                "Save Started - File path: \n $recPathRef" +
                "\n-------------------------")
        try {
            ObjectOutputStream(FileOutputStream(recPathRef)).use{ it -> it.writeObject(recList)}
        }catch (ioe:IOException){
            println("Error: Unable to save - IO Exception")
        }
        println("List of Songs Saved to file\n")
    }
    // LOADING SONG RECOMMENDATION
    fun loadRecSong(ref : String): ArrayList<RecSong> {
        var recPathRef = recPath+"songs/"+ref+".txt"
        println("-------------------------\n " +
                "Load Started - File path: \n ${recPathRef} " +
                "\n-------------------------")

        val nullList = ArrayList<RecSong>()
        var recList : ArrayList<RecSong>

        try {
            //uses lambda to de-serialize the file into array of recommendations
            recList = ObjectInputStream(FileInputStream(recPathRef)).use { it -> it.readObject() as ArrayList<RecSong>}
            println("$ref Loaded into memory")

        }catch (ioe : IOException){
            println("Error: Could not load from file:\n $recPathRef \n")
            return nullList
        }catch (c: ClassNotFoundException){
            println("Error: Class not found to cast")
            return nullList
        }
        return recList
    }

    // SAVING ARTIST RECOMMENDATION
    fun saveRecArtist(list: java.util.ArrayList<RecArtist>, ref: String) {
        var recPathRef = recPath+"artists/"+ref+".txt"
        var recList = ArrayList<RecArtist>()
        recList.addAll(list)

        println("-------------------------\n " +
                "Save Started - File path: \n $recPathRef" +
                "\n-------------------------")
        try {
            ObjectOutputStream(FileOutputStream(recPathRef)).use{ it -> it.writeObject(recList)}
        }catch (ioe:IOException){
            println("Error: Unable to save - IO Exception")
        }
        println("List of Songs Saved to file\n")
    }
    // LOADING ARTIST RECOMMENDATION
    fun loadRecArtist(ref : String): ArrayList<RecArtist> {
        var recPathRef = recPath+"artists/"+ref+".txt"
        println("-------------------------\n " +
                "Load Started - File path: \n ${recPathRef} " +
                "\n-------------------------")

        val nullList = ArrayList<RecArtist>()
        var recList : ArrayList<RecArtist>

        try {
            //uses lambda to de-serialize the file into array of recommendations
            recList = ObjectInputStream(FileInputStream(recPathRef)).use { it -> it.readObject() as ArrayList<RecArtist>}
            println("$ref Loaded into memory")

        }catch (ioe : IOException){
            println("Error: Could not load from file:\n $recPathRef \n")
            return nullList
        }catch (c: ClassNotFoundException){
            println("Error: Class not found to cast")
            return nullList
        }
        return recList
    }








}