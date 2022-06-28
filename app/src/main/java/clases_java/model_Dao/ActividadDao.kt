package clases_java.model_Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import clases_java.model_clases.Actividad
import java.sql.Date


@Dao
interface ActividadDao {

    @Insert
    fun inset_actividad(actividad: Actividad)

    @Query( "Select * from actividad_table")
    fun get_all_actividades() : List<Actividad>

    @Query("select * from actividad_table where id= :id_tarea")
    fun get_actividades_from_tarea(id_tarea: String) : List<Actividad>

    @Query("select * from actividad_table where hora_fin= :fecha")
    fun get_actividades_from_fecha(fecha: Date) : List<Actividad>

}