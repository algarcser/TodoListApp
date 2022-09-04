package clases_java.Logica_negocio

import android.app.Application
import android.content.Context
import clases_java.model_clases.Actividad
import clases_java.model_clases.Dia_laboral
import clases_java.model_clases.Prioridad
import clases_java.model_clases.Tarea
import java.sql.Date
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.ArrayList


// este será el motor que implementará toda la lógica de negocio, y estará en contacto con el back.
object Manager_TareasYActividades: Application() {


    // me esta dando problema la parde la obtener el contexto.

//    val database_actividad = Database_Actividades.getInstance(Manager_TareasYActividades.applicationContext).ActividadDao()
//    val database_tarea = Database_Tareas.getInstance(Manager_TareasYActividades.applicationContext).TareaDao()


    // creamos variable para generarlo en tiempo de ejecución en ram.
    var lista_actividades = mutableListOf<Actividad>()
    var lista_tareas = mutableListOf<Tarea>()


    // el metodo de intertar actividad deberá de separar la actividad en tareas e introducirlar en la aplicación.
    fun insert_actividad(actividad: Actividad){

        // si ha sido correcto introducimos la actividad dentro de la lista
//        database_actividad.insert_actividad(actividad)
        actividad.id = lista_actividades.size + 1
        lista_actividades.add(actividad)
    }

    fun get_all_actividades() : List<Actividad>{
//        return database_actividad.get_all_actividades()
        return lista_actividades
    }

    fun obtener_actividades_by_id(id_actitivida: Int): Actividad? {
//        return database_actividad.get_actividades_from_id(id_actitivida)
        for (actividad_aux  in lista_actividades){
            if (actividad_aux.id == id_actitivida){
                return actividad_aux
            }
        }

        return null


    }

    fun get_actividades_from_fecha(fecha: Date) : Actividad? {
//        return database_actividad.get_actividades_from_fecha(fecha)

        for (actividad_aux  in lista_actividades){
            if (actividad_aux.dia_ejecucion == fecha){
                return actividad_aux
            }
        }

        return null

    }

    fun delete_all_actividad_from_id_tarea(id_tarea: Int){
//        database_actividad.delete_all_actividad_from_id_tarea(id_tarea)

        lista_actividades = mutableListOf<Actividad>()
    }

    fun get_all_tareas(): List<Tarea>{
//        return database_tarea.get_all_tareas()

        return lista_tareas
    }

    fun insert_tarea(tarea: Tarea){
//        database_tarea.insert_tarea(tarea)

        tarea.id = lista_tareas.size + 1
        lista_tareas.add(tarea)

    }

    fun get_tarea_from_descripcion(descripcion: String): Tarea? {
//        return database_tarea.get_tarea_from_descripcion(descripcion)

        for (tarea_aux  in lista_tareas){
            if (tarea_aux.descripcion == descripcion){
                return tarea_aux
            }
        }

        return null
    }

    fun get_tarea_from_codigo(id_tarea: Int) : Tarea{
//        return database_tarea.get_tarea_from_codigo(id_tarea)

        for (tarea_aux  in lista_tareas){
            if (tarea_aux.id == id_tarea){
                return tarea_aux
            }
        }

        return lista_tareas[0]

    }

    fun get_descripcion_from_codigo(id_tarea: Int) : String?{
//        return database_tarea.get_descripcion_from_codigo(id_tarea)

        for (tarea_aux  in lista_tareas){
            if (tarea_aux.id == id_tarea){
                return tarea_aux.descripcion
            }
        }

        return "No encontrado"
    }

    fun update_tarea(tarea: Tarea){
//        database_tarea.update_tarea(tarea)

        for (position in lista_tareas.indices){
            if (lista_tareas[position].id == tarea.id){
                lista_tareas.removeAt(position)
                lista_tareas.add(position, tarea)
            }
        }
    }

    // para eliminar una tarea queremos eliminar, esta y todas las tareas, que pueda haber creado
    fun delete_tarea(tarea: Tarea){
//        database_tarea.delete_tarea(tarea)
//        database_actividad.delete_all_actividad_from_id_tarea(tarea.id)

        for (position in lista_tareas.indices){
            if (lista_tareas[position].id == tarea.id){
                lista_tareas.removeAt(position)
            }
        }
    }

    fun has_more_actividades(id_tarea: Int): Boolean{
//        return database_actividad.has_more_actividades(id_tarea) == 0

        return lista_actividades.size != 0
    }

    fun update_activida(actividad: Actividad){
//        database_actividad.update_actividad(actividad)

        for (position in lista_actividades.indices){
            if (lista_actividades[position].id == actividad.id){
                lista_actividades.removeAt(position)
                lista_actividades.add(position, actividad)
            }
        }
    }

    fun get_fecha_maxima(): Date?{
        if(lista_actividades.size == 0){
            return null
        }

        var resultado : Date = lista_actividades[0].dia_ejecucion

        for (actividad in lista_actividades){
            if(resultado < actividad.dia_ejecucion){
                resultado = actividad.dia_ejecucion
            }
        }

        return resultado
    }

    fun get_list_actividades_from_fecha(fecha: Date): ArrayList<Actividad>{
        var resultado = mutableListOf<Actividad>()

        for (actividad in lista_actividades){
            if (actividad.dia_ejecucion == fecha){
                resultado.add(actividad)
            }
        }

        return ArrayList(resultado)
    }


    // estaría bien que la función funcionace en un rango de fechas, y que construya los días laborales que no haya nada de forma vacia para poder iterar.
    fun get_dias_laborales(): MutableList<Dia_laboral>{
        val resultado: MutableList<Dia_laboral> = mutableListOf() // porque tenemos que meterle de cosas de nuevo, aquí

        val auxiliar_fecha: Date = Date(System.currentTimeMillis()) // escogemos la fecha actual

        // esto limita los días laborales que podemos mirar. no me gusta nada, creo que tiene que cambiar.
        // Todo si hay días laborales sin actividad, hay que crear un dia laboral vacio para seguir mirando
        val fecha_maxima: Date? = get_fecha_maxima()

        while( fecha_maxima != null && auxiliar_fecha < fecha_maxima ){
            val lista_actividades_dia = get_list_actividades_from_fecha(auxiliar_fecha).toMutableList()

            if( lista_actividades_dia.size>0){
                resultado.add(
                    Dia_laboral(
                        list_actividades =  lista_actividades_dia,
                        fecha = auxiliar_fecha
                    )
                )
            }
        }


        // vamos a crearla de forma dimnámica para evitar problemas de momento.
        val lista_tareas = mutableListOf<Actividad>()

        lista_tareas.add(
            Actividad(hora_inicio = Date( System.currentTimeMillis() ),
                hora_fin = Date(System.currentTimeMillis() + 60 * 60 * 1000),
                prioridad = Prioridad.menor,
                descripcion = "memes",
                dia_ejecucion = Date( System.currentTimeMillis() ),
                duracion = 60,
                ref_tarea = 1)
        )



        lista_tareas.add(
            Actividad(hora_inicio = Date( System.currentTimeMillis() + 65 * 60 * 1000),
                hora_fin = Date(System.currentTimeMillis() + 120 * 60 * 1000),
                prioridad = Prioridad.urgente,
                descripcion = "memelion",
                dia_ejecucion = Date( System.currentTimeMillis() ),
                duracion = 55,
                ref_tarea = 1)
        )

        lista_tareas.add(
            Actividad(hora_inicio = Date( System.currentTimeMillis() + 125 * 60 * 1000),
                hora_fin = Date(System.currentTimeMillis() + 180 * 60 * 1000),
                prioridad = Prioridad.menor,
                descripcion = "Prueba2",
                dia_ejecucion = Date( System.currentTimeMillis() ),
                duracion = 55,
                ref_tarea = 1)
        )

        var resultado_provisional = mutableListOf<Dia_laboral>()


        resultado_provisional.add(Dia_laboral(list_actividades = lista_tareas,
            fecha = Date(   System.currentTimeMillis())))


        resultado_provisional.add(Dia_laboral(list_actividades = lista_tareas,
            fecha = Date(   System.currentTimeMillis() +  24 *60 *60 *1000)))



        return resultado_provisional

        //return resultado
    }


    fun añadir_Tarea_base_datos(tarea: Tarea){

        // variable lista tareas será para guardar la lista de tareas dividada de la actividad y iterar hasta colocarlas todas
        val lista_actividades = mutableListOf<Actividad>()

        // variable lista tareas  sustituidas para guardar las tareas que han sido sustiuidas por otras.
        val lista_actividades_sustituidas = mutableListOf<Actividad>()

        // variable días laborales sobre los cuales vamos a iterar
        val lista_dias_laborales = get_dias_laborales()


        // dividir la actividad en diferentes tareas
        var tiempo_total_estimado : Int = tarea.tiempo_estimado

        while (tiempo_total_estimado - 60 > 0){
            lista_actividades.add(Actividad( duracion = 60 , descripcion = tarea.descripcion, ref_tarea = tarea.id,
                    dia_ejecucion = Date(System.currentTimeMillis()),
                    hora_fin = Date(System.currentTimeMillis()),
                    hora_inicio = Date(System.currentTimeMillis()), prioridad = tarea.prioridad )
            )

            tiempo_total_estimado = tiempo_total_estimado - 60
        }

        // una vez hemos quitado todos los  bloques de una hora, creamos una actividad con el tiempo restante.
        lista_actividades.add(Actividad( duracion = tiempo_total_estimado , descripcion = tarea.descripcion, ref_tarea = tarea.id,
            dia_ejecucion = Date(System.currentTimeMillis()),
            hora_fin = Date(System.currentTimeMillis()),
            hora_inicio = Date(System.currentTimeMillis()), prioridad = tarea.prioridad )
        )


        // primera pasada de asignación

        outer@for (dia_laboral in lista_dias_laborales){

            if( dia_laboral.hueco_disponible( lista_actividades[0])){
                // siempre vamos a intentar colocar la primera actividad que tengamos en la lista, si colocamos satisfactoriamente una actividad, vamos a continuar, colocando actividades
                lista_actividades.removeAt(0)
                continue@outer
            }
        }


        // segunda pasada con sustitución de tareas de prioridad baja.

        if( lista_actividades.size > 0){
            outer2@for (dia_laboral in lista_dias_laborales){
                var resultado : Actividad?= dia_laboral.sustituir_actividad(lista_actividades[0]);

                // si se ha sustituido, tenemos que quitarlo, de la lista.
                if( resultado != null){
                    // siempre vamos a intentar colocar la primera actividad que tengamos en la lista, si colocamos satisfactoriamente una actividad, vamos a continuar, colocando actividades
                    lista_actividades.removeAt(0)
                    lista_actividades_sustituidas.add(resultado)

                    continue@outer2
                }
            }

        }




        // intentamos recolocar las tareas sustituidas.
        if( lista_actividades_sustituidas.size != 0){

            outer@for (dia_laboral in lista_dias_laborales){

                if( dia_laboral.hueco_disponible( lista_actividades_sustituidas[0])){
                    // siempre vamos a intentar colocar la primera actividad que tengamos en la lista, si colocamos satisfactoriamente una actividad, vamos a continuar, colocando actividades
                    lista_actividades_sustituidas.removeAt(0)
                    continue@outer
                }
            }
        }


        // si ha sido correcto introducimos la Tarea dentro de la lista
        if( lista_actividades.size == 0) {
            insert_tarea(tarea)

            for (dia_laboral in lista_dias_laborales){
                for (actividad in dia_laboral.list_actividades)
                    update_activida(actividad)
            }

            // para cada una de las tareas modificadas abría que mostrarla por pantalla, pero no voy a hacer nada con ello, porque no se como hacer un pop, o desahecer cambios que sería lo más correcto.


        }

        // sería la forma de actualizar todas la lista de tareas, realizada.


    }





}