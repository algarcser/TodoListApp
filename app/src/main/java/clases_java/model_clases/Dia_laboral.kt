package clases_java.model_clases

import clases_java.Logica_negocio.Manager_TareasYActividades
import clases_java.model_Databases.lista_auxiliarTareas
import java.sql.Date
import java.sql.Timestamp
import java.util.*

data class Dia_laboral(val list_actividades: MutableList<Actividad>,
                       val fecha: Date){


    fun hueco_disponible(actividad  : Actividad) : Boolean{

        val calendario: Calendar = Calendar.getInstance()     

        if(list_actividades.size == 0){
            calendario.add(Calendar.MINUTE, 5)
            actividad.hora_inicio = Timestamp(calendario.timeInMillis )
            calendario.add(Calendar.MINUTE, actividad.duracion + 5)
            actividad.hora_fin = Timestamp( calendario.timeInMillis )


            // bloque para asignar el día de ejecución correcto.

            calendario.time = actividad.hora_inicio
            calendario.set(Calendar.HOUR_OF_DAY,0)
            calendario.set(Calendar.MINUTE,0)
            calendario.set(Calendar.SECOND, 0)
            calendario.set(Calendar.MILLISECOND,0)

            val fecha_maxima_formateada = Date(calendario.timeInMillis)

            actividad.dia_ejecucion = fecha_maxima_formateada
            // ------------------

            list_actividades.add(actividad)
            Manager_TareasYActividades.insert_actividad(actividad)
            return true
        }
         if( list_actividades.size == 1){
             calendario.time = list_actividades[0].hora_fin

             calendario.add(Calendar.MINUTE, 5)
             actividad.hora_inicio = Timestamp(calendario.timeInMillis )

             calendario.add(Calendar.MINUTE, actividad.duracion + 5)
             actividad.hora_fin = Timestamp( calendario.timeInMillis )


             // bloque para asignar el día de ejecución correcto.
             val calendario: Calendar = Calendar.getInstance()
             calendario.time = actividad.hora_inicio
             calendario.set(Calendar.HOUR_OF_DAY,0)
             calendario.set(Calendar.MINUTE,0)
             calendario.set(Calendar.SECOND, 0)
             calendario.set(Calendar.MILLISECOND,0)

             val fecha_maxima_formateada = Date(calendario.timeInMillis)

             actividad.dia_ejecucion = fecha_maxima_formateada
             // ------------------

             list_actividades.add(1, actividad)
             Manager_TareasYActividades.insert_actividad(actividad)
             return true
         }


        for( indice in list_actividades.indices){
            if(indice == list_actividades.size - 1){
                break
            }

            var hora_final_anterior: Timestamp = list_actividades[indice].hora_fin
            var hora_principio_siguiente: Timestamp = list_actividades[indice +1].hora_inicio


            if (hora_final_anterior.time - hora_principio_siguiente.time > actividad.duracion * 60 * 1000 + 5 * 60 * 1000){

                calendario.time = hora_final_anterior
                calendario.add(Calendar.MINUTE, 5)

                actividad.hora_inicio = Timestamp(calendario.timeInMillis)
                actividad.hora_fin = Timestamp( (actividad.hora_inicio.time + (actividad.duracion * 60 * 1000 )).toLong() )

                // bloque para asignar el día de ejecución correcto.
                val calendario: Calendar = Calendar.getInstance()
                calendario.time = actividad.hora_inicio
                calendario.set(Calendar.HOUR_OF_DAY,0)
                calendario.set(Calendar.MINUTE,0)
                calendario.set(Calendar.SECOND, 0)
                calendario.set(Calendar.MILLISECOND,0)

                val fecha_maxima_formateada = Date(calendario.timeInMillis)

                actividad.dia_ejecucion = fecha_maxima_formateada
                // ------------------

                list_actividades.add(1, actividad)
                Manager_TareasYActividades.insert_actividad(actividad)
                return true
            }
        }


        // para modificar la fecha hasta la cual podemos añadir actividades, un día más de la fecha, máxima
        val calendario_aux = Calendar.getInstance()
        calendario_aux.time = fecha
        calendario_aux.set(Calendar.HOUR_OF_DAY, 23)
        calendario_aux.set(Calendar.MINUTE,0)
        calendario_aux.set(Calendar.SECOND, 0)
        calendario_aux.set(Calendar.MILLISECOND,0)

        val fecha_maxima = Date(calendario_aux.timeInMillis)

        if( list_actividades[list_actividades.size -1].hora_fin.time < fecha_maxima.time){

            actividad.hora_inicio = Timestamp(list_actividades[list_actividades.size - 1].hora_fin.time + (5 * 60 * 1000 ))
            actividad.hora_fin = Timestamp( (actividad.hora_inicio.time + (actividad.duracion * 60 * 1000 )).toLong() )

            // bloque para asignar el día de ejecución correcto.
            val calendario: Calendar = Calendar.getInstance()
            calendario.time = actividad.hora_inicio
            calendario.set(Calendar.HOUR_OF_DAY,0)
            calendario.set(Calendar.MINUTE,0)
            calendario.set(Calendar.SECOND, 0)
            calendario.set(Calendar.MILLISECOND,0)

            val fecha_maxima_formateada = Date(calendario.timeInMillis)

            actividad.dia_ejecucion = fecha_maxima_formateada
            // ------------------

            list_actividades.add( list_actividades.size, actividad)
            Manager_TareasYActividades.insert_actividad(actividad)
            return true
        }




        // devolvemos false, para decir que no hemos podido devolver nada
        return false;
    }


    // si no ha podido sustituir en ese día devuelve null.
    fun sustituir_actividad(actividad: Actividad) : Actividad?{


        for( actividad_aux in list_actividades){

            if( actividad.prioridad != Prioridad.menor){
                if (actividad_aux.prioridad == Prioridad.menor){
                    if(actividad_aux.duracion <= actividad.duracion){
                        var indice = list_actividades.indexOf(actividad_aux)
                        list_actividades.removeAt(indice)
                        list_actividades.add(indice, actividad)

                        actividad.hora_inicio = actividad_aux.hora_inicio
                        actividad.hora_fin = Timestamp(actividad.hora_inicio.time + actividad.duracion * 60 * 1000)
                        actividad.dia_ejecucion = actividad_aux.dia_ejecucion

                        Manager_TareasYActividades.insert_actividad(actividad)

                        Manager_TareasYActividades.delete_actividad_from_id_actividad(actividad_aux.id)

                        return actividad_aux
                    }
                }
            }else{
                // si la prioridad no es la menor, no queremos seguir mirando.
                continue
            }
        }
        return null

    }



}



