package clases_java.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import clases_java.Logica_negocio.Manager_TareasYActividades
import clases_java.model_clases.Dia_laboral

class ViewModel_lista_dias_laborales: ViewModel() {

    lateinit var manager_app: Manager_TareasYActividades

    lateinit var lista_dias: MutableLiveData<Dia_laboral>

    init{
        // porque es un objeto, no hay constructor creo, que es así.
        manager_app = Manager_TareasYActividades
    }

    fun loadData(){
        // debería de insertar todos los valores nuevos en la lista de este tipo
        for( dia_laboral_aux in manager_app.get_dias_laborales()){
            lista_dias.postValue(dia_laboral_aux)
        }
    }

    fun get_data(): MutableLiveData<Dia_laboral>{
        return lista_dias
    }
}