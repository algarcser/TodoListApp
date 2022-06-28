package com.example.organizadortareas.Database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import clases_java.model_Dao.TareaDao
import clases_java.model_Databases.TareaDatabase
import clases_java.model_clases.Categorias
import clases_java.model_clases.Prioridad
import clases_java.model_clases.Tarea
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.sql.Date

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var userDao: TareaDao
    private lateinit var db: TareaDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, TareaDatabase::class.java).build()
        userDao = db.TareaDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
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
        assertThat(byName, equalTo(tarea_test))
    }
}