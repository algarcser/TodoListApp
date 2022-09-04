package clases_java.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import clases_java.Logica_negocio.Manager_TareasYActividades
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

    val manager_app = Manager_TareasYActividades



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding_modificar_tarea = ActivityModifyTareaBinding.inflate(layoutInflater)
        setContentView(binding_modificar_tarea.root)


        // vamos a hacer que nos pase la vista entera, la tarea bindeada, a ver si es posible, que en principio no habría problema, y luego escribiremos sus datos
        // si no nos ha llegado una tarea 0 la tarea  primera de la base de datos, y seguramente un mensaje de error si es posible, aunque nunca debería de llegar
        val id_tarea_recibida: Int = intent?.extras?.getInt("tarea") ?: 0


        // de momento para pruebas, vamos a hacerlo con esta tarea
//        val tarea_recibida: Tarea =  Tarea(
//            descripcion = "Prueba1",
//            ref_tarea_padre = 0,
//            comentarios = "Esto debería de funcionar a las maravillas",
//            tiempo_estimado = 10,
//            fecha_limite = Date(System.currentTimeMillis() + 100000000),
//            prioridad = Prioridad.media,
//            categoria = Categorias.media,
//            lista_etiquetas = listOf()
//        )

        val tarea_recibida = Manager_TareasYActividades.get_tarea_from_codigo(id_tarea_recibida)


        val formato_fecha = SimpleDateFormat("dd/MM/yyyy")
        val string_fecha: String = formato_fecha.format(tarea_recibida.fecha_limite)

        binding_modificar_tarea.editTextDateFechaLimite.setText(string_fecha)

        binding_modificar_tarea.editTextNumberDuracionEstimada.setText(tarea_recibida.tiempo_estimado.toString())

        binding_modificar_tarea.editTextTextDescripcion.setText(tarea_recibida.descripcion)

        binding_modificar_tarea.editTextTextCategoria.setText( tarea_recibida.categoria.toString())

        binding_modificar_tarea.editTextTextPrioridad.setText( tarea_recibida.prioridad.toString() )

        binding_modificar_tarea.editTextTextMultiLineComentarios.setText(tarea_recibida.comentarios)


        if( tarea_recibida.lista_etiquetas != null) {
            binding_modificar_tarea.editTextTextEtiquetas.setText(
                tarea_recibida.lista_etiquetas.joinToString(
                    separator = " "
                )
            )
        }

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

            val categoria_string = binding_modificar_tarea.editTextTextCategoria.text.toString()
            val prioridad_string = binding_modificar_tarea.editTextTextPrioridad.text.toString()

            val categoria_recibida: Categorias = Categorias.values().find { it.name == categoria_string } ?: Categorias.media
            val prioridad_recibida: Prioridad = Prioridad.values().find { it.name == prioridad_string } ?: Prioridad.media
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
            manager_app.update_tarea(tarea_auxiliar)

            startActivity(Intent( this, MainActivity::class.java))

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

            val categoria_string = binding_modificar_tarea.editTextTextCategoria.text.toString()
            val prioridad_string = binding_modificar_tarea.editTextTextPrioridad.text.toString()

            val categoria_recibida: Categorias = Categorias.values().find { it.name == categoria_string } ?: Categorias.media
            val prioridad_recibida: Prioridad = Prioridad.values().find { it.name == prioridad_string } ?: Prioridad.media
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
            manager_app.delete_tarea(tarea_auxiliar)

            startActivity(Intent( this, MainActivity::class.java))



        }else{
            // si falla la verificación, entonces, escribirmos los fallos que pueden haber, en este caso sabemos que siempre va a dar error aquí, pero habría que mirar más
            binding_modificar_tarea.editTextNumberDuracionEstimadaLayout.isErrorEnabled = true
            binding_modificar_tarea.editTextNumberDuracionEstimadaLayout.error = "La duración debe de ser un número positivo"
        }



    }


    fun validar_entradas() : Boolean{
        return true

    }
}