package clases_java.model_clases

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.sql.Date
import java.sql.Timestamp

@Entity(tableName = "actividad_table")
data class Actividad(
    @PrimaryKey(autoGenerate = true) var id: Int = 1,
    var hora_inicio: Timestamp,
    var hora_fin: Timestamp,
    val duracion: Int,
    val ref_tarea: Int,
    var dia_ejecucion: Date,
    var descripcion: String,
    val prioridad: Prioridad
)