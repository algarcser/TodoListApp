package clases_java.model_clases

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.sql.Date

@Entity(tableName = "actividad_table")
data class Actividad(
    @PrimaryKey(autoGenerate = true) var id: Int = 1,
    var hora_inicio: Date, // var para modificar
    var hora_fin: Date,
    val duracion: Int,
    val ref_tarea: Int,
    val dia_ejecucion: Date,
    val descripcion: String,
    val prioridad: Prioridad
)