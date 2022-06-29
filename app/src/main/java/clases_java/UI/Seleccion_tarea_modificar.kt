package clases_java.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import clases_java.UI.Adapters.TareaAdapter
import clases_java.model_Databases.lista_auxiliarTareas
import com.example.organizadortareas.databinding.ActivitySeleccionTareaModificarBinding

class Seleccion_tarea_modificar : AppCompatActivity() {

    lateinit var binding_select_actividad_modificar: ActivitySeleccionTareaModificarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_select_actividad_modificar = ActivitySeleccionTareaModificarBinding.inflate(layoutInflater)
        setContentView(binding_select_actividad_modificar.root)

        val lista_Tareas = lista_auxiliarTareas
        val recyclerView = binding_select_actividad_modificar.descripcionTareasRecyclerView


        var adapter = TareaAdapter(this, lista_Tareas)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListerner(object : TareaAdapter.onItemClickerListener{
            override fun onItemClick(position: Int) {
                // esto sería identificar que es lo que queremos hacer cuando, clicamos.

                val intent = Intent(this@Seleccion_tarea_modificar, Activity_modify_tarea::class.java)

                // al final al la siguiente activiy le vamos a pasar la id, porque no podemos meter serrializable facilmente.
                intent.putExtra("tarea_id", lista_Tareas.get(position).id)

                // empezamos la actividad entonces.
                startActivity(intent)
            }
        })


        recyclerView.setHasFixedSize(true)
    }
}