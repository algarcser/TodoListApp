package clases_java.states

import clases_java.model_clases.Categorias
import clases_java.model_clases.Prioridad
import java.util.*

// esta clase va a servir para representar el estado del formulario de alta de nueva actividad en el cual estamos trabajando, para luego hacer que el viewmodel, de este funcione bien
data class Alta_actividad_nueva_state(
    val descripcion: String = "",
    val descripcion_error: String? = null,
    val prioridad: Prioridad = Prioridad.media,
    val prioridad_error: String? = null,
    val caterogia: Categorias = Categorias.media,
    val caterogia_error: String? ? = null,
    val fecha_limite: Date = Date(),
    val fecha_limite_error: String? = null,
    val duracion_estimada: Int = 0,
    val duracion_estimada_error : String? = null,
    val etiquetas: String = "",
    val etiquetas_error: String? = null,
    val comentarios: String = "",
    val comentarios_error: String? = null,
    val subtarea_de: Int? = null,
    val subtarea_de_error: String? = null
)
