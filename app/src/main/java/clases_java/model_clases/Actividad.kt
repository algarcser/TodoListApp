package clases_java.model_clases

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.sql.Date

@Entity(tableName = "actividad_table")
data class Actividad(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    val hora_inicio: Date,
    val hora_fin: Date,
    val duracion: Float,
    val ref_tarea: Int,
    val dia_ejecucion: Date,
    val descripcion: String
)