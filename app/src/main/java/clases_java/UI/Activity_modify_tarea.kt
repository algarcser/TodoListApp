package clases_java.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import clases_java.model_Dao.TareaDao
import clases_java.model_Databases.TareaDatabase
import clases_java.model_clases.Categorias
import clases_java.model_clases.Prioridad
import clases_java.model_clases.Tarea
import com.example.organizadortareas.R
import com.example.organizadortareas.databinding.ActivityModifyTareaBinding
import java.sql.Date
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter

class Activity_modify_tarea : AppCompatActivity() {


    lateinit var binding_modificar_tarea: ActivityModifyTareaBinding
    val database_tareas = Room.databaseBuilder(this, TareaDatabase::class.java, "Actividad").build().TareaDao()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_modificar_tarea = ActivityModifyTareaBinding.inflate(layoutInflater)
        setContentView(binding_modificar_tarea.root)


        // vamos a hacer que nos pase la vista entera, la tarea bindeada, a ver si es posible, que en principio no habría problema, y luego escribiremos sus datos
        // si no nos ha llegado una tarea 0 la tarea  primera de la base de datos, y seguramente un mensaje de error si es posible, aunque nunca debería de llegar
        val id_tarea_recibida: Int = intent?.extras?.getInt("tarea") ?: 0

        // construimos la varibles para acceder a la abse de datos que necesitamos
        /*
        val tarea_recibida: Tarea = database_tareas.get_tarea_from_codigo(id_tarea_recibida)
         */


        // de momento para pruebas, vamos a hacerlo con esta tarea
        val tarea_recibida: Tarea =  Tarea(
            descripcion = "Prueba1",
            ref_tarea_padre = 0,
            comentarios = "Esto debería de funcionar a las maravillas",
            tiempo_estimado = 10,
            fecha_limite = Date(System.currentTimeMillis() + 100000000),
            prioridad = Prioridad.media,
            categoria = Categorias.media,
            lista_etiquetas = listOf()
        )


        val formato_fecha = SimpleDateFormat("dd/MM/yyyy")
        val string_fecha: String = formato_fecha.format(tarea_recibida.fecha_limite)

        binding_modificar_tarea.editTextDateFechaLimite.setText(string_fecha)

        binding_modificar_tarea.editTextNumberDuracionEstimada.setText(tarea_recibida.tiempo_estimado)

        binding_modificar_tarea.editTextTextDescripcion.setText(tarea_recibida.descripcion)

        binding_modificar_tarea.spnCategoria.setSelection(Categorias.valueOf(tarea_recibida.categoria.toString() ).ordinal )

        binding_modificar_tarea.spnPrioridad.setSelection(Prioridad.valueOf(tarea_recibida.prioridad.toString()).ordinal  )

        binding_modificar_tarea.editTextTextMultiLineComentarios.setText(tarea_recibida.comentarios)

        binding_modificar_tarea.editTextTextEtiquetas.setText(tarea_recibida.lista_etiquetas.joinToString(separator = " ") )

        binding_modificar_tarea.buttonEliminarTarea.setOnClickListener{
            eliminar_tarea()
        }

        binding_modificar_tarea.buttonGuardarTarea.setOnClickListener {
            guardar_tarea()
        }



    }


    fun guardar_tarea(){

        if( validar_entradas() ){

            binding_modificar_tarea.editTextNumberDuracionEstimadaLayout.isErrorEnabled = false
            binding_modificar_tarea.editTextNumberDuracionEstimadaLayout.error = null

            val categoria_string = binding_modificar_tarea.spnCategoria.selectedItem.toString()
            val prioridad_string = binding_modificar_tarea.spnPrioridad.selectedItem.toString()

            val categoria_recibida: Categorias = Categorias.values().find { it.name == binding_modificar_tarea.spnCategoria.selectedItem.toString() } ?: Categorias.media
            val prioridad_recibida: Prioridad = Prioridad.values().find { it.name == binding_modificar_tarea.spnPrioridad.selectedItem.toString() } ?: Prioridad.media
            val long_fecha_introducida: Long = SimpleDateFormat("dd/MM/yyyy").parse(binding_modificar_tarea.editTextDateFechaLimite.text.toString()).time
            val fecha_limite_recibida: Date =  Date(long_fecha_introducida)
            val tiempo_estimado_recibido: Int = binding_modificar_tarea.editTextNumberDuracionEstimada.text.toString().toInt()
            val comentario_recibido:String = binding_modificar_tarea.editTextTextMultiLineComentarios.text.toString()
            val lista_etiquetas_recibida: List<String> = binding_modificar_tarea.editTextTextEtiquetas.text.toString().split(" ")
            val ref_tarea_padre_recibida: Int = 0
            val descripcion_recibida: String = binding_modificar_tarea.editTextTextDescripcion.text.toString()


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

            // una vez hemos contruido de nuevo el objeto llamamos al método update
            database_tareas.update_tarea(tarea_auxiliar)
        }else{
            // si falla la verificación, entonces, escribirmos los fallos que pueden haber, en este caso sabemos que siempre va a dar error aquí, pero habría que mirar más
            binding_modificar_tarea.editTextNumberDuracionEstimadaLayout.isErrorEnabled = true
            binding_modificar_tarea.editTextNumberDuracionEstimadaLayout.error = "La duración debe de ser un número positivo"
        }



    }

    fun eliminar_tarea(){

        if( validar_entradas() ){

            binding_modificar_tarea.editTextNumberDuracionEstimadaLayout.isErrorEnabled = false
            binding_modificar_tarea.editTextNumberDuracionEstimadaLayout.error = null

            val categoria_string = binding_modificar_tarea.spnCategoria.selectedItem.toString()
            val prioridad_string = binding_modificar_tarea.spnPrioridad.selectedItem.toString()

            val categoria_recibida: Categorias = Categorias.values().find { it.name == binding_modificar_tarea.spnCategoria.selectedItem.toString() } ?: Categorias.media
            val prioridad_recibida: Prioridad = Prioridad.values().find { it.name == binding_modificar_tarea.spnPrioridad.selectedItem.toString() } ?: Prioridad.media
            val long_fecha_introducida: Long = SimpleDateFormat("dd/MM/yyyy").parse(binding_modificar_tarea.editTextDateFechaLimite.text.toString()).time
            val fecha_limite_recibida: Date =  Date(long_fecha_introducida)
            val tiempo_estimado_recibido: Int = binding_modificar_tarea.editTextNumberDuracionEstimada.text.toString().toInt()
            val comentario_recibido:String = binding_modificar_tarea.editTextTextMultiLineComentarios.text.toString()
            val lista_etiquetas_recibida: List<String> = binding_modificar_tarea.editTextTextEtiquetas.text.toString().split(" ")
            val ref_tarea_padre_recibida: Int = 0
            val descripcion_recibida: String = binding_modificar_tarea.editTextTextDescripcion.text.toString()


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

            // una vez hemos contruido de nuevo el objeto llamamos al método update
            database_tareas.delete_tarea(tarea_auxiliar)
            // todo: Hay que eliminar todas las actividades relaccionadas con la tarea también.
        }else{
            // si falla la verificación, entonces, escribirmos los fallos que pueden haber, en este caso sabemos que siempre va a dar error aquí, pero habría que mirar más
            binding_modificar_tarea.editTextNumberDuracionEstimadaLayout.isErrorEnabled = true
            binding_modificar_tarea.editTextNumberDuracionEstimadaLayout.error = "La duración debe de ser un número positivo"
        }



    }


    fun validar_entradas() : Boolean{
        if( binding_modificar_tarea.editTextNumberDuracionEstimada.text.toString().toInt() <= 0){
            return false
        }else{
            return true
        }

    }
}