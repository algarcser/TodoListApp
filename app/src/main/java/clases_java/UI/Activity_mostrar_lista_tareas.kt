package clases_java.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import clases_java.Logica_negocio.Manager_TareasYActividades
import clases_java.UI.Adapters.Adaptar_Tarea
import clases_java.UI.Adapters.TareaAdapter
import clases_java.model_Databases.lista_auxiliarTareas
import clases_java.model_clases.Categorias
import clases_java.model_clases.Prioridad
import clases_java.model_clases.Tarea
import com.example.organizadortareas.databinding.ActivitySeleccionTareaModificarBinding
import java.sql.Date

class Activity_mostrar_lista_tareas : AppCompatActivity() {

    lateinit var binding_select_actividad_modificar: ActivitySeleccionTareaModificarBinding

    var manager_app = Manager_TareasYActividades

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_select_actividad_modificar = ActivitySeleccionTareaModificarBinding.inflate(layoutInflater)
        setContentView(binding_select_actividad_modificar.root)



//        manager_app.añadir_Tarea_base_datos(Tarea(id = 1,
//            categoria = Categorias.corta,
//            comentarios = "deberia de haber iniciado bien",
//            descripcion = "memes super memes",
//            fecha_limite = Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000),
//            prioridad = Prioridad.urgente,
//            tiempo_estimado = 60,
//            ref_tarea_padre = null,
//        lista_etiquetas = null)
//        )
//
//
//        manager_app.añadir_Tarea_base_datos(Tarea(id = 1,
//            categoria = Categorias.corta,
//            comentarios = "deberia de haber iniciado bien",
//            descripcion = "memes super memes v2.0",
//            fecha_limite = Date(System.currentTimeMillis() + 2* 24 * 60 * 60 * 1000),
//            prioridad = Prioridad.urgente,
//            tiempo_estimado = 60,
//            ref_tarea_padre = null,
//            lista_etiquetas = null)
//        )

        var lista_tareas = manager_app.get_all_tareas()




        binding_select_actividad_modificar.listviewListaTareas.adapter = Adaptar_Tarea(this, ArrayList(lista_tareas) )


        binding_select_actividad_modificar.listviewListaTareas.setOnItemClickListener{parent, view, position, id ->

            val i = Intent(this,  Activity_modify_tarea::class.java)
            i.putExtra("tarea",lista_tareas[position].id)

            // comenzamos la actividad que queremos
            startActivity(i)

        }
    }
}