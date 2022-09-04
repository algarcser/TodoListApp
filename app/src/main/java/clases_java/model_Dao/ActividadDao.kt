package clases_java.model_Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import clases_java.model_clases.Actividad
import java.sql.Date


@Dao
interface ActividadDao {

    @Insert
    fun insert_actividad(actividad: Actividad)

    @Query( "Select * from actividad_table")
    fun get_all_actividades() : List<Actividad>

    @Query("select * from actividad_table where id= :id_tarea")
    fun get_actividades_from_id(id_tarea: Int) : List<Actividad>

    @Query("select * from actividad_table where dia_ejecucion= :fecha")
    fun get_actividades_from_fecha(fecha: Date) : List<Actividad>

    @Query("Delete from actividad_table where id = :id_tarea")
    fun delete_all_actividad_from_id_tarea(id_tarea: Int)


    @Query( "select COUNT(*) as count from actividad_table where id = :id_tarea")
    fun has_more_actividades(id_tarea: Int): Int


    @Query("select * from actividad_table order by dia_ejecucion")
    fun get_actividades_ordenada_fecha(): List<Actividad>


    @Query("select MAX(dia_ejecucion) from actividad_table")
    fun get_fecha_maxima(): Date

    @Update
    fun update_actividad(actividad: Actividad)

}