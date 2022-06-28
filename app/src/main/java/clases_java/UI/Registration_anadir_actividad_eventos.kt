package clases_java.UI

import clases_java.model_clases.Categorias
import clases_java.model_clases.Prioridad
import java.sql.Date

sealed class Registration_anadir_actividad_eventos{

    data class Descripcion_changed(val descripcion: String) : Registration_anadir_actividad_eventos()
    data class Categoria_changed(val categoria: Categorias) : Registration_anadir_actividad_eventos()
    data class Prioridad_changed(val prioridad: Prioridad) : Registration_anadir_actividad_eventos()
    data class Fecha_limite_changed(val fecha_lmite: Date) : Registration_anadir_actividad_eventos()
    data class Duracion_estimada_escripcion_changed(val duracion_estimada: Int) : Registration_anadir_actividad_eventos()
    data class Etiquetas_changed(val etiquetas: String) : Registration_anadir_actividad_eventos()
    data class Comentarios_changed(val comentarios: String) : Registration_anadir_actividad_eventos()
    data class Subtarea_changed(val id_actividada: Int) : Registration_anadir_actividad_eventos()

    object Submit: Registration_anadir_actividad_eventos()

}
