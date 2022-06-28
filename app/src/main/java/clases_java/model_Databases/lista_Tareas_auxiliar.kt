package clases_java.model_Databases

import clases_java.model_clases.Categorias
import clases_java.model_clases.Prioridad
import clases_java.model_clases.Tarea
import java.sql.Date


var lista_auxiliarTareas = listOf<Tarea>(Tarea(
        descripcion = "Prueba1",
        ref_tarea_padre = 0,
        comentarios = "Esto debería de funcionar a las maravillas",
        tiempo_estimado = 10,
        fecha_limite = Date(System.currentTimeMillis() + 100000000),
        prioridad = Prioridad.media,
        categoria = Categorias.media,
        lista_etiquetas = listOf()
    ),Tarea(
        descripcion = "Prueba2",
        ref_tarea_padre = 0,
        comentarios = "Esto Super bien",
        tiempo_estimado = 10,
        fecha_limite = Date(System.currentTimeMillis() + 1000000),
        prioridad = Prioridad.urgente,
        categoria = Categorias.corta,
        lista_etiquetas = listOf()
    ),Tarea(
    descripcion = "Prueba3",
    ref_tarea_padre = 0,
    comentarios = "Esto Super chupelerendi",
    tiempo_estimado = 10,
    fecha_limite = Date(System.currentTimeMillis() + 10000000000),
    prioridad = Prioridad.menor,
    categoria = Categorias.lenta,
    lista_etiquetas = listOf()
    )
)

/*
class lista_Tareas_auxiliar() {

    fun obtener_lista_tareas(): List<Tarea>{
        val resultado : MutableList<Tarea> = mutableListOf()

        resultado.add(
            Tarea(
                descripcion = "Prueba1",
                ref_tarea_padre = 0,
                comentarios = "Esto debería de funcionar a las maravillas",
                tiempo_estimado = 10,
                fecha_limite = Date(System.currentTimeMillis() + 100000000),
                prioridad = Prioridad.media,
                categoria = Categorias.media,
                lista_etiquetas = listOf()
            )
        )

        resultado.add(
            Tarea(
                descripcion = "Prueba2",
                ref_tarea_padre = 0,
                comentarios = "Esto Super bien",
                tiempo_estimado = 10,
                fecha_limite = Date(System.currentTimeMillis() + 1000000),
                prioridad = Prioridad.urgente,
                categoria = Categorias.corta,
                lista_etiquetas = listOf()
            )
        )


        resultado.add(
            Tarea(
                descripcion = "Prueba3",
                ref_tarea_padre = 0,
                comentarios = "Esto Super chupelerendi",
                tiempo_estimado = 10,
                fecha_limite = Date(System.currentTimeMillis() + 10000000000),
                prioridad = Prioridad.menor,
                categoria = Categorias.lenta,
                lista_etiquetas = listOf()
            )
        )

        return resultado
    }
}

*/