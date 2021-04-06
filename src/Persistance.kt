import java.io.*

class Persistence {
    val path = System.getProperty("user.dir")
    val recPath = "$path/src/recommendations/"

    init {
    }

    fun saveToFile(list: java.util.ArrayList<RecSong>, ref : String) {
        var recPathRef = recPath+ref+".txt"
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

    fun loadFromFile(ref : String): ArrayList<RecSong> {
        var recPathRef = recPath+ref+".txt"
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


}