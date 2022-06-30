package clases_java.Logica_negocio

import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import clases_java.model_Databases.ActividadDatabase

object Database_Actividades {
    @Volatile
    private var INSTANCE: ActividadDatabase? = null
    fun getInstance(context: Context): ActividadDatabase {
        synchronized(this) {
            var instance = INSTANCE

            if (instance == null) {
                instance = databaseBuilder(
                    context.applicationContext,
                    ActividadDatabase::class.java,
                    "user_table"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }

}