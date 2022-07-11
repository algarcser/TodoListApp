package clases_java.Logica_negocio

import android.content.Context
import androidx.room.Room
import clases_java.model_Databases.TareaDatabase

object Database_Tareas {
    @Volatile
    private var INSTANCE: TareaDatabase? = null
    fun getInstance(context: Context): TareaDatabase {
        synchronized(this) {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    TareaDatabase::class.java,
                    "tarea_table"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }

}