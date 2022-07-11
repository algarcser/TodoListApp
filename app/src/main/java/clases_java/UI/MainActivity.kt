package clases_java.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.organizadortareas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // creamos una variable general para bindear total a este apartado
    lateinit var binding_main_app: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_main_app = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding_main_app.root)

        binding_main_app.buttonAnadirTarea.setOnClickListener {
            // simplemente mandatos un intent vacio, porque no tenemos que mandar más info para ejecutar la segunda actividad.
            startActivity(Intent( this, Activity_anadir_actividad::class.java))
        }

        binding_main_app.buttonEditarTareas.setOnClickListener {
            // simplemente mandatos un intent vacio, porque no tenemos que mandar más info para ejecutar la segunda actividad.
            startActivity(Intent( this, Seleccion_tarea_modificar::class.java))
        }

    }
}