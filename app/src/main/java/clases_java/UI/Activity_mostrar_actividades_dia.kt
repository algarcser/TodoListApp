package clases_java.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import clases_java.Logica_negocio.Manager_TareasYActividades
import clases_java.UI.Adapters.Adapter_dia_laboral
import clases_java.model_clases.Dia_laboral
import clases_java.view_model.ViewModel_lista_dias_laborales

import com.example.organizadortareas.databinding.ActivityMostrarActividadesDiaBinding
import com.example.organizadortareas.databinding.ActivityMostrarListaDiasLaboralesBinding


class Activity_mostrar_actividades_dia : AppCompatActivity() {

    lateinit var binding_datos: ActivityMostrarListaDiasLaboralesBinding
    lateinit var adapter_dia_laboral: Adapter_dia_laboral
    lateinit var view_model: ViewModel_lista_dias_laborales
    var manager_app = Manager_TareasYActividades




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_datos = ActivityMostrarListaDiasLaboralesBinding.inflate(layoutInflater)
        setContentView(binding_datos.root)

        var array_dias_laborales = manager_app.get_dias_laborales()


        binding_datos.listviewDiaLaborales.isClickable = true

        // tenemos que convertir nuestro mutable array list en un array list, para que lo acepte correctamente.
        binding_datos.listviewDiaLaborales.adapter = Adapter_dia_laboral(this,ArrayList(array_dias_laborales) )



        binding_datos.listviewDiaLaborales.setOnItemClickListener{parent, view, position, id ->

            val array_actividades = array_dias_laborales[position].list_actividades


            val i = Intent(this,  Activity_mostrar_lista_actividades::class.java)
            i.putExtra("position_array",position)

            // comenzamos la actividad que queremos
            startActivity(i)

        }


    }





}