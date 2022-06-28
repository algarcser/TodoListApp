package clases_java.model_clases

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName = "tarea_table")
data class Tarea(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    val categoria: Categorias,
    val prioridad: Prioridad,
    val fecha_limite: Date,
    val tiempo_estimado: Int,
    val comentarios: String,
    val lista_etiquetas: List<String>,
    val ref_tarea_padre: Int?,
    val descripcion: String
)