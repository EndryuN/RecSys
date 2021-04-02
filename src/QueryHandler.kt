import javax.swing.table.DefaultTableModel
import kotlin.collections.ArrayList

class QueryHandler {
    var queries = ArrayList<Query>()

    fun createQuery(
        trackName: String,
        artistName: String,
        status: String
    ){
        queries.add(Query(queries.size, trackName, artistName, status))
    }

    fun updateQueryTable(queryTable: DefaultTableModel){
        queryTable.setNumRows(0)
        for(query in queries){
            queryTable.addRow(arrayOf<Any>(query.index, query.artistName, query.trackName, query.status))
        }
    }

    fun clearQueries(){
        queries.clear()
    }
}