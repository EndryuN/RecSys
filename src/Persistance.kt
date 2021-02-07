import java.io.*

class Persistence {
    val path = System.getProperty("user.dir")
    var artistsFile = "$path/Artists.txt"
    //var teamsFile = "$path/Teams.txt"

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

        println("List of Projects Saved to file\n")
    }
    /*
    fun saveToFile(list : ArrayList<Team>, bool: Boolean) {
        println("-------------------------\n " +
                "Save Started - File path: \n $teamsFile " +
                "\n-------------------------")
        try {
            //Lambda used to save to file
            ObjectOutputStream(FileOutputStream(teamsFile)).use{ it -> it.writeObject(list)}
        }catch (ioe:IOException){
            println("Error: Unable to save - IO Exception")
        }

        println("List of Projects Saved to file\n")
    }
    */
    fun loadFromFile(): ArrayList<Artist> {

        println("-------------------------\n " +
                "Load Started - File path: \n ${artistsFile} " +
                "\n-------------------------")

        val nullList = ArrayList<Artist>()
        var projectList : ArrayList<Artist>

        try {
            //uses lambda to de-serialize the file into array of projects
            projectList = ObjectInputStream(FileInputStream(artistsFile)).use { it -> it.readObject() as ArrayList<Artist>}
            println("Projects Loaded into memory")

        }catch (ioe : IOException){
            println("Error: Could not load from file:\n $artistsFile \n Possibly no projects")
            return nullList
        }catch (c: ClassNotFoundException){
            println("Error: Class not found to cast")
            return nullList
        }
        return projectList
    }
/*
    fun loadFromFile(bool: Boolean): ArrayList<Team> {

        println("-------------------------\n " +
                "Load Started - File path: \n $teamsFile " +
                "\n-------------------------")

        val nullList = ArrayList<Team>()
        var teamList : ArrayList<Team>

        try {
            //uses lambda to de-serialize the file into array of projects
            teamList = ObjectInputStream(FileInputStream(teamsFile)).use { it -> it.readObject() as ArrayList<Team>}
            println("Projects Loaded into memory")

        }catch (ioe : IOException){
            println("Error: Could not load from file:\n $teamsFile")
            return nullList
        }catch (c: ClassNotFoundException){
            println("Error: Class not found to cast")
            return nullList
        }
        return teamList
    }

 */
}