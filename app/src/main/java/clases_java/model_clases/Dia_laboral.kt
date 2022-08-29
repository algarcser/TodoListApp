package clases_java.model_clases

import java.sql.Date

data class Dia_laboral(val list_actividades: List<Actividad>,
                       val fecha: Date)