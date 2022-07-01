package clases_java.Logica_negocio

import android.app.Application
import android.content.Context
import androidx.room.Room
import clases_java.model_Databases.TareaDatabase
import clases_java.model_clases.Actividad
import clases_java.model_clases.Dia_laboral
import clases_java.model_clases.Tarea
import java.sql.Date


// este será el motor que implementará toda la lógica de negocio, y estará en contacto con el back.
object Manager_TareasYActividades: Application() {

    val database_actividad = Database_Actividades.getInstance(applicationContext).ActividadDao()
    val database_tarea = Database_Tareas.getInstance(applicationContext).TareaDao()


    fun insert_actividad(actividad: Actividad){
        database_actividad.insert_actividad(actividad)
    }

    fun get_all_actividades() : List<Actividad>{
        return database_actividad.get_all_actividades()
    }

    fun obtener_actividades_by_id(id_actitivida: Int): List<Actividad> {
        return database_actividad.get_actividades_from_id(id_actitivida)
    }

    fun get_actividades_from_fecha(fecha: Date) : List<Actividad>{
        return database_actividad.get_actividades_from_fecha(fecha)
    }

    fun delete_all_actividad_from_id_tarea(id_tarea: Int){
        database_actividad.delete_all_actividad_from_id_tarea(id_tarea)
    }

    fun get_all_tareas(): List<Tarea>{
        return database_tarea.get_all_tareas()
    }

    fun insert_tarea(tarea: Tarea){
        database_tarea.insert_tarea(tarea)
    }

    fun get_tarea_from_descripcion(descripcion: String): Tarea{
        return database_tarea.get_tarea_from_descripcion(descripcion)
    }

    fun get_tarea_from_codigo(id_tarea: Int) : Tarea{
        return database_tarea.get_tarea_from_codigo(id_tarea)
    }

    fun get_descripcion_from_codigo(id_tarea: Int) : String{
        return database_tarea.get_descripcion_from_codigo(id_tarea)
    }

    fun update_tarea(tarea: Tarea){
        database_tarea.update_tarea(tarea)
    }

    // para eliminar una tarea queremos eliminar, esta y todas las tareas, que pueda haber creado
    fun delete_tarea(tarea: Tarea){
        database_tarea.delete_tarea(tarea)
        database_actividad.delete_all_actividad_from_id_tarea(tarea.id)
    }

    fun has_more_actividades(id_tarea: Int): Boolean{
        return database_actividad.has_more_actividades(id_tarea) == 0
    }

    fun get_dias_laborales(): MutableList<Dia_laboral>{
        val resultado: MutableList<Dia_laboral> = mutableListOf() // porque tenemos que meterle de cosas de nuevo, aquí

        val auxiliar_fecha: Date = Date(System.currentTimeMillis()) // escogemos la fecha actual
        val fecha_maxima: Date = database_actividad.get_fecha_maxima()

        while( auxiliar_fecha < fecha_maxima ){
            val lista_actividades_dia = database_actividad.get_actividades_from_fecha(auxiliar_fecha)

            if( lista_actividades_dia.size>0){
                resultado.add(
                    Dia_laboral(
                        list_actividades =  lista_actividades_dia,
                        fecha = auxiliar_fecha
                    )
                )
            }
        }
        return resultado
    }




}