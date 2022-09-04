package clases_java.model_clases

import clases_java.model_Databases.lista_auxiliarTareas
import java.sql.Date
import java.util.*

data class Dia_laboral(val list_actividades: MutableList<Actividad>,
                       val fecha: Date){

    fun hueco_disponible(actividad  : Actividad) : Boolean{
        if(list_actividades.size == 0){
            actividad.hora_inicio = Date(System.currentTimeMillis() + (5 * 60 * 1000 ))
            actividad.hora_fin = Date( (actividad.hora_inicio.time + (actividad.duracion * 60 * 1000 )).toLong() )

            list_actividades.add(actividad)
            return true
        }
         if( list_actividades.size == 1){
             actividad.hora_inicio = Date(System.currentTimeMillis() + (5 * 60 * 1000 ))
             actividad.hora_fin = Date( (actividad.hora_inicio.time + (actividad.duracion * 60 * 1000 )).toLong() )

             list_actividades.add(1, actividad)
             return true
         }


        for( indice in list_actividades.indices){
            if(indice == list_actividades.size - 1){
                break
            }

            var hora_final_anterior: Date = list_actividades[indice].hora_fin
            var hora_principio_siguiente: Date = list_actividades[indice +1].hora_inicio


            if (hora_final_anterior.time - hora_principio_siguiente.time > actividad.duracion * 60 * 1000){

                actividad.hora_inicio = Date(hora_final_anterior.time + (5 * 60 * 1000 ))
                actividad.hora_fin = Date( (actividad.hora_inicio.time + (actividad.duracion * 60 * 1000 )).toLong() )

                list_actividades.add(1, actividad)
                return true
            }
        }


        // para modificar la fecha hasta la cual podemos añadir actividades, un día más de la fecha, máxima
        val calendario_aux = Calendar.getInstance()
        calendario_aux.time = fecha
        calendario_aux.set(Calendar.HOUR_OF_DAY, 23)

        val fecha_maxima = Date(calendario_aux.timeInMillis)

        if( list_actividades[list_actividades.size -1].hora_fin.time < fecha_maxima.time){

            actividad.hora_inicio = Date(list_actividades[list_actividades.size - 1].hora_fin.time + (5 * 60 * 1000 ))
            actividad.hora_fin = Date( (actividad.hora_inicio.time + (actividad.duracion * 60 * 1000 )).toLong() )

            list_actividades.add( list_actividades.size, actividad)
            return true
        }




        // devolvemos false, para decir que no hemos podido devolver nada
        return false;
    }


    // si no ha podido sustituir en ese día devuelve null.
    fun sustituir_actividad(actividad: Actividad) : Actividad?{


        for( actividad_aux in list_actividades){

            if( actividad.prioridad != Prioridad.menor){
                // todo, lo mejor sería que tenga la prioridad aquí para poder comparar directo.
                if (actividad_aux.prioridad == Prioridad.menor){
                    if(actividad_aux.duracion <= actividad.duracion){
                        var indice = list_actividades.indexOf(actividad_aux)
                        list_actividades.removeAt(indice)
                        list_actividades.add(indice, actividad)

                        return actividad_aux
                    }
                }
            }else{
                break
            }

        }

        return null

    }



}



