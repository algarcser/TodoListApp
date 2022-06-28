
package com.example.organizadortareas.Database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import clases_java.model_Databases.TareaDatabase
import clases_java.model_clases.Categorias
import clases_java.model_clases.Prioridad
import clases_java.model_clases.Tarea
import java.sql.Date

fun main(args: Array<String>) {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val db = Room.inMemoryDatabaseBuilder(
        context, TareaDatabase::class.java).build()
    val userDao = db.TareaDao()

    val tarea_test : Tarea = Tarea(
        descripcion = "Prueba1",
        ref_tarea_padre = 0,
        comentarios = "Esto debería de funcionar a las maravillas",
        tiempo_estimado = 10,
        fecha_limite = Date(System.currentTimeMillis() + 100000000),
        prioridad = Prioridad.media,
        categoria = Categorias.media,
        lista_etiquetas = listOf()
    )
    userDao.insert_tarea(tarea = tarea_test)
    val byName = userDao.get_tarea_from_descripcion("Prueba1")
}