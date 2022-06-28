package clases_java.view_model


import androidx.lifecycle.ViewModel
import clases_java.model_clases.Categorias
import clases_java.model_clases.Prioridad
import clases_java.use_case.Validate_duracion_actividad
import java.util.*

// esta clase será responsable por guardar la información del form de añadir una nueva actividad a la aplicación
class Anadir_actividad_viewModel(
    // vamos a crear como atritubtos los validadores, que definimos para los user_case, no es la mejor práctica finalmente, pero si que funciona
    private val validate_duracion_actividad: Validate_duracion_actividad = Validate_duracion_actividad()
) : ViewModel() {
    private var _descripcion: String = ""
    private var _descripcion_error: String? = null
    private var _prioridad: Prioridad = Prioridad.media
    private var _prioridad_error: String? = null
    private var _caterogia: Categorias = Categorias.media
    private var _caterogia_error: String? ? = null
    private var _fecha_limite: Date = Date()
    private var _fecha_limite_error: String? = null
    private var _duracion_estimada: Int = 0
    private var _duracion_estimada_error : String? = null
    private var _etiquetas: String = ""
    private var _etiquetas_error: String? = null
    private var _comentarios: String = ""
    private var _comentarios_error: String? = null
    private var _subtarea_de: Int? = null
    private var _subtarea_de_error: String? = null

    // declaración de las propiedas públicas
    val descripcion: String
        get() = _descripcion

    val prioridad: Prioridad
        get() = _prioridad

    val caterogia: Categorias
        get() = _caterogia

    val fecha_limite: Date
        get() = _fecha_limite

    val duracion_estimada: Int
        get() = _duracion_estimada

    val etiquetas: String
        get() = _etiquetas

    val comentarios: String
        get() = _comentarios

    val subtarea_de: Int?
        get() = _subtarea_de

    // función para acceder a la lista de errores
    val error_duracion: String?
        get() = _duracion_estimada_error



    // función que vamos a usar para implementar nuestra lógica de negocio, y todos los cambios que vamos a tener
    fun validar_entradas() : Boolean{
        // vamos a hacer que la función recorra los erroes uno por uno, y después muestre un poop con con cada error que haya encontrado
        // si mostramos una lista de errores creo que la pantalla tendrá demasiado texto, y no será legible, por ello voy a ir de forma secuencial.

        if ( _duracion_estimada<=0){
            _duracion_estimada_error = "La estimación de horas de la actividad debe de ser un número entero positivo"
            return false
        }else{
            // si la validación ha sido correcta, restablecemos el valor, por si acaso queremos borrar validaciones anteriores
            _duracion_estimada_error = null
        }

        // validación de etiquetas, posibles?, no quiero que hayan muchos tipos de etiquetas, pero si que necesito que haya libertad para etiquetas


        return true
    }



    }








        /*
        // variable que va a contener, la sesión los datos introducidos
    var state by mutableStateOf(Alta_actividad_nueva_state())

    private val validation_event_channel = Channel<ValidationEvent>()
    val validation_events = validation_event_channel.receiveAsFlow()



    // podemos hacer que cuando ocurra un evento, le vamos a mapear que es lo queremos hacer en nuestro viewModel, para cambiar cosas, etc
    fun onEvent(event: Registration_anadir_actividad_eventos){
        when(event){
            is Registration_anadir_actividad_eventos.Duracion_estimada_escripcion_changed -> {
                state = state.copy(duracion_estimada = event.duracion_estimada)
            }

            is Registration_anadir_actividad_eventos.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val validate_duration_result = validate_duracion_actividad.execute(state.duracion_estimada)

        // creamos una lista para guardar los resultados y poder recorrerla, mirando si hay alguno que no hasa sido correcto
        val hasError = listOf(validate_duration_result).any{ ! it.suceessful}

        // si hay algún error, lo que vamos a hacer, es actualizar el estado con todos los mensajes de error que tengamos
        if( hasError ){
            state = state.copy(
                duracion_estimada_error = validate_duration_result.error_message
            )
            // como ya hemos terminado de actualiar, devolvemos error
            return
        }

        // si va bien queremos hacer los siguiente
        viewModelScope.launch {
            // enviamos un evento que sea correcto
            validation_event_channel.send(ValidationEvent.Success)
        }

         */