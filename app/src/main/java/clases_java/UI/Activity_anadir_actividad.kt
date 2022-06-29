package clases_java.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.room.Room
import clases_java.model_Databases.TareaDatabase
import clases_java.model_clases.Categorias
import clases_java.model_clases.Prioridad
import clases_java.model_clases.Tarea
import clases_java.view_model.Anadir_actividad_viewModel
import com.example.organizadortareas.databinding.ActivityAnadirActividadBinding
import java.lang.reflect.Field
import java.sql.Date
import java.text.SimpleDateFormat


class Activity_anadir_actividad : AppCompatActivity() {

    // creamos una variable general para bindear total a este apartado
    lateinit var binding_add_actividad: ActivityAnadirActividadBinding
    // private val view_model: Anadir_actividad_viewModel by viewModels()

    // en teoría esto hace que se delege la creación del objeto a esta actividad

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_add_actividad = ActivityAnadirActividadBinding.inflate(layoutInflater)
        setContentView(binding_add_actividad.root)


        // creamos la lista de categorías que tenemos que meter en el spiner
        val lista_categorias : MutableList<String> = ArrayList<String>()
        for (elemento in Categorias.values())
            lista_categorias.add(elemento.toString())

        // creamos el adaptador para las categorias
        val adapter_categorias : ArrayAdapter<String> = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lista_categorias)

        // enlazamos el adaptados de categorias, con el spinner que tiene que mostrar las categorias
        binding_add_actividad.spnCategoria.adapter = adapter_categorias
        // llamamos a la función para hacer que el se limite la altura del drop down, para que no sea mucha. Mejora para futuro, por si hay más categorias.
        limit_drop_down_height(binding_add_actividad.spnCategoria)


        // creamos la lista de prioridades que tenemos que meter en el spiner
        val lista_priodades: MutableList<String> = ArrayList<String>()
        for (elemento in Prioridad.values())
            lista_priodades.add(elemento.toString())

        // creamos el adaptador para las prioridades
        val adapter_prioridad : ArrayAdapter<String> = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lista_priodades)

        // enlazamos el adaptados de prioridades, con el spinner que tiene que mostrar las prioridades
        binding_add_actividad.spnPrioridad.adapter = adapter_prioridad
        // llamamos a la función para hacer que el se limite la altura del drop down, para que no sea mucha. Mejora para futuro, por si hay más prioridades.
        limit_drop_down_height(binding_add_actividad.spnPrioridad)

        //.allowMainThreadQueries()
        // vamos a crear la base de datos, por debajo, como segundo hilo, para evitar que se colapse
        val database_tareas = Room.databaseBuilder(this, TareaDatabase::class.java, "Actividad").build()

        // vamos añadir el botón de añadir una nueva activdad
        binding_add_actividad.buttonAnadirTareaMenuTarea.setOnClickListener { anadir_actividad_base_datos(database_tareas) }




    }


    fun limit_drop_down_height(spiner: Spinner){
        val popup : Field = Spinner::class.java.getDeclaredField( "mPopup")
        popup.isAccessible = true

        val popup_menu : ListPopupWindow = popup.get(spiner) as ListPopupWindow
        popup_menu.height = (200 * resources.displayMetrics.density).toInt()

    }

    fun anadir_actividad_base_datos(database_tareas: TareaDatabase){


        if( validar_entradas() ){
            // si la verificación ha sido correcta, cambiamos para que se muestren los datos correctamente, y no se muestren mensaje de error en duracion_estimada
            binding_add_actividad.editTextNumberDuracionEstimadaLayout.isErrorEnabled = false
            binding_add_actividad.editTextNumberDuracionEstimadaLayout.error = null

            val categoria_string = binding_add_actividad.spnCategoria.selectedItem.toString()
            val prioridad_string = binding_add_actividad.spnPrioridad.selectedItem.toString()

            val categoria_recibida: Categorias = Categorias.values().find { it.name == binding_add_actividad.spnCategoria.selectedItem.toString() } ?: Categorias.media
            val prioridad_recibida: Prioridad = Prioridad.values().find { it.name == binding_add_actividad.spnPrioridad.selectedItem.toString() } ?: Prioridad.media
            val long_fecha_introducida: Long = SimpleDateFormat("dd/MM/yyyy").parse(binding_add_actividad.editTextDateFechaLimite.text.toString()).time
            val fecha_limite_recibida: Date =  Date(long_fecha_introducida)
            val tiempo_estimado_recibido: Int = binding_add_actividad.editTextNumberDuracionEstimada.text.toString().toInt()
            val comentario_recibido:String = binding_add_actividad.editTextTextMultiLineComentarios.text.toString()
            val lista_etiquetas_recibida: List<String> = binding_add_actividad.editTextTextEtiquetas.text.toString().split(" ")
            val ref_tarea_padre_recibida: Int = 0
            val descripcion_recibida: String = binding_add_actividad.editTextTextDescripcion.text.toString()


            // variable auxiliar, que vamos a crear para introducir en la base de datos
            val tarea_auxiliar: Tarea = Tarea(
                categoria = categoria_recibida,
                prioridad = prioridad_recibida,
                fecha_limite = fecha_limite_recibida,
                tiempo_estimado = tiempo_estimado_recibido,
                comentarios = comentario_recibido,
                lista_etiquetas = lista_etiquetas_recibida,
                ref_tarea_padre = ref_tarea_padre_recibida,
                descripcion = descripcion_recibida)

            database_tareas.TareaDao().insert_tarea(tarea = tarea_auxiliar)
        }else{
            // si falla la verificación, entonces, escribirmos los fallos que pueden haber, en este caso sabemos que siempre va a dar error aquí, pero habría que mirar más
            binding_add_actividad.editTextNumberDuracionEstimadaLayout.isErrorEnabled = true
            binding_add_actividad.editTextNumberDuracionEstimadaLayout.error = "La duración debe de ser un número positivo"
        }

    }

    fun validar_entradas() : Boolean {
        if (binding_add_actividad.editTextNumberDuracionEstimada.text.toString().toInt() <= 0) {
            return false
        } else {
            return true
        }
    }




}