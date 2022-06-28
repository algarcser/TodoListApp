package clases_java.UI

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

        recyclerView.adapter = TareaAdapter(this, lista_Tareas)


        recyclerView.setHasFixedSize(true)
    }
}