package clases_java.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import clases_java.UI.Adapters.Adapter_dia_laboral
import clases_java.model_clases.Dia_laboral
import clases_java.view_model.ViewModel_lista_dias_laborales

import com.example.organizadortareas.databinding.ActivityMostrarActividadesDiaBinding


class Activity_mostrar_actividades_dia : AppCompatActivity() {

    lateinit var binding_mostrar_actividades_dia: ActivityMostrarActividadesDiaBinding
    lateinit var adapter_dia_laboral: Adapter_dia_laboral
    lateinit var view_model: ViewModel_lista_dias_laborales




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_mostrar_actividades_dia = ActivityMostrarActividadesDiaBinding.inflate(layoutInflater)
        setContentView(binding_mostrar_actividades_dia.root)


        // iniciamos el recyle view
        init_recycler_view_dias()

        //cargamos los datos en el recycle view
        load_data()
    }

    private fun init_recycler_view_dias() {
        binding_mostrar_actividades_dia.reclyclerViewDia.apply {
            layoutManager = LinearLayoutManager(this@Activity_mostrar_actividades_dia)
            val decoration =
                DividerItemDecoration(applicationContext, DividerItemDecoration.HORIZONTAL)
            addItemDecoration(decoration)

            adapter_dia_laboral = Adapter_dia_laboral()
            adapter = adapter_dia_laboral
        }
    }

    private fun load_data(){
        view_model = ViewModelProvider(this).get(ViewModel_lista_dias_laborales::class.java)
        view_model.get_data().observe(this, Observer<Dia_laboral> {
            // le hacer que el adaptador tenga la lista de actividades que deberá de mostrar, para que luehgo podamos hacer el doble reclicler view
            adapter_dia_laboral.location_list_data = it.list_actividades.toMutableList()
            adapter_dia_laboral.notifyDataSetChanged()
        })
        view_model.loadData()
    }

}