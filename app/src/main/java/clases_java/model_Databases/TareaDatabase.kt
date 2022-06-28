package clases_java.model_Databases

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import clases_java.model_Dao.TareaDao
import clases_java.model_clases.Tarea


@Database(entities = [Tarea::class], version = 1)
@TypeConverters(Converters::class)
abstract class TareaDatabase : RoomDatabase() {
    abstract fun TareaDao(): TareaDao
}