package clases_java.model_Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import clases_java.model_clases.Tarea

@Dao
interface TareaDao {

    @Query( "Select * from tarea_table")
    fun get_all_tareas(): List<Tarea>

    @Insert
    fun insert_tarea(tarea: Tarea)

    @Query("Select * from tarea_table where descripcion=:descripcion")
    fun get_tarea_from_descripcion(descripcion: String): Tarea

    @Query("Select * from tarea_table where id = :id_tarea")
    fun get_tarea_from_codigo(id_tarea: Int) : Tarea

    @Query("Select descripcion from tarea_table where id= :id_tarea")
    fun get_descripcion_from_codigo(id_tarea: Int) : String
}