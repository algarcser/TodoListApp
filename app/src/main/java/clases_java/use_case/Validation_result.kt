package clases_java.use_case


// eso será una clase para modelizar el resultado de todas las validaciones de parámetros que vamos a realizar
data class Validation_result(
    val suceessful: Boolean,  // indicar si una validación ha sido correcta o no
    val error_message: String? = null //  indicar si ha sido erronea, un mensaje de error
)
