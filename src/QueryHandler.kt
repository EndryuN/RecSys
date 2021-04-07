import java.lang.IndexOutOfBoundsException
import javax.swing.table.DefaultTableModel
import kotlin.collections.ArrayList

class QueryHandler {
    val p = Persistence()
    var queries = ArrayList<Query>()

    var currentQuery = Query(
        "rec1",
        0,
        "trackname",
        "artistname",
        "status",
        0)

    fun createQuery(
        recID: String,
        trackName: String,
        artistName: String,
        status: String,
        playlistCount: Int
    ){
        queries.add(Query(recID, queries.size, trackName, artistName, status, playlistCount))
    }


    fun updateQueryTable(queryTable: DefaultTableModel){
        queryTable.setNumRows(0)
        for(query in queries){
            queryTable.addRow(arrayOf<Any>(query.recID, query.artistName, query.trackName, query.status))
        }
    }
    fun saveQuery(){
        p.saveQuery(queries)
    }

    fun loadQueries(){
        try {
            queries = p.loadQuery()
        }catch (iob : IndexOutOfBoundsException){
            println("Projects file was empty on load")
            println("-------------------------\n")
        }
    }


    fun clearQueries(){
        queries.clear()
    }
}