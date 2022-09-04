package clases_java.UI.Adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import clases_java.model_clases.Actividad
import clases_java.model_clases.Dia_laboral
import com.example.organizadortareas.R
import java.text.SimpleDateFormat


class Adapter_Actividad_mostrar (private val context : Activity, private val arraylist : ArrayList <Actividad> ) : ArrayAdapter<Actividad>( context ,
    R.layout.xml_actividadl, arraylist ) {


    override fun getView (position : Int, convertView : View?, parent : ViewGroup) : View {
        val inflater : LayoutInflater = LayoutInflater.from ( context )
        val view : View = inflater.inflate( R.layout.xml_actividadl , null )

        val textview_hora_inicio : TextView = view.findViewById(R.id.hora_comienzo)
        val textview_hora_final : TextView = view.findViewById(R.id.hora_finalizacion)
        val textview_descripcion_tarea : TextView = view.findViewById(R.id.textiview_descripcion_actividad)

        val hora_inicio_formato = SimpleDateFormat("H:mm").format(arraylist[position].hora_inicio)
        val hora_final_formato = SimpleDateFormat("H:mm").format(arraylist[position].hora_fin)

        textview_hora_final.text = hora_final_formato.toString()
        textview_hora_inicio.text = hora_inicio_formato.toString()
        textview_descripcion_tarea.text = arraylist[position].descripcion

        return view

    }
}