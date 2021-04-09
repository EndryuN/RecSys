import java.lang.IndexOutOfBoundsException
import javax.swing.table.DefaultTableModel
import kotlin.collections.ArrayList

class QueryHandler {
    private val p = Persistence()
    var queries = ArrayList<Query>()

    var currentQuery = Query(
        "rec1",
        0,
        "trackname",
        "artistname",
        "status",
        0,
        true
    )

    fun createQuery(
        recID: String,
        trackName: String,
        artistName: String,
        status: String,
        playlistCount: Int,
        isSong: Boolean
    ){
        queries.add(Query(recID, queries.size, trackName, artistName, status, playlistCount, isSong))
    }
    //Used for updating query table
    fun updateQueryTable(queryTable: DefaultTableModel){
        queryTable.setNumRows(0)
        for(query in queries){
            queryTable.addRow(arrayOf<Any>(query.recID, query.artistName, query.trackName, query.status, query.playlistCount))
        }
    }
    //Used for saving query list
    fun saveQuery(){
        p.saveQuery(queries)
    }
    //Used for loading query list
    fun loadQuery(){
        try {
            queries = p.loadQuery()
        }catch (iob : IndexOutOfBoundsException){
            println("Projects file was empty on load")
            println("-------------------------\n")
        }
    }
    //Used for clearing quaries
    fun clearQueries(){
        queries.clear()
    }
}