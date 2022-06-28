package clases_java.use_case

class Validate_duracion_actividad {

    fun execute(duracion: Int) : Validation_result{
        if(duracion == 0){
            return Validation_result(
                suceessful = false,
                error_message = "La duración no puede ser 0"
            )
        }

        if(duracion < 0){
            return Validation_result(
                suceessful = false,
                error_message = "La duración no puede ser ser negativa"
            )
        }

        // en el caso de que se pase correctamente devolvemos una validación positiva
        return Validation_result( suceessful = true)
    }

}