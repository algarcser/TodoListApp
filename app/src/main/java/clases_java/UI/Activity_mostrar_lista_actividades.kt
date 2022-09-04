package clases_java.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import clases_java.Logica_negocio.Manager_TareasYActividades
import clases_java.UI.Adapters.Adapter_Actividad_mostrar
import com.example.organizadortareas.databinding.ActivityMostrarListaActividadesBinding

class Activity_mostrar_lista_actividades : AppCompatActivity() {

    lateinit var binding_datos: ActivityMostrarListaActividadesBinding

    var manager_app = Manager_TareasYActividades

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_datos = ActivityMostrarListaActividadesBinding.inflate(layoutInflater)
        setContentView(binding_datos.root)

        // tenemos que recibir la lista de actividades a mostrar, para pasarsela al adaptador.
        var posicion_dial_laboral: Int = intent.getIntExtra("position", 0)

        var lista_dias_laborales = manager_app.get_dias_laborales()


        binding_datos.listviewListaActividades.adapter = Adapter_Actividad_mostrar(this, ArrayList(lista_dias_laborales[posicion_dial_laboral].list_actividades) )



    }
}