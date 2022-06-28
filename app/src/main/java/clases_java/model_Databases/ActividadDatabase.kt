package clases_java.model_Databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import clases_java.model_Dao.ActividadDao
import clases_java.model_clases.Actividad


@Database(entities = [Actividad::class], version = 1)
@TypeConverters(Converters::class)
abstract class ActividadDatabase : RoomDatabase() {
    abstract fun ActividadDao(): ActividadDao
}