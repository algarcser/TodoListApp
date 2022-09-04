package clases_java.UI.Adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import clases_java.model_clases.Actividad
import clases_java.model_clases.Tarea
import com.example.organizadortareas.R
import java.text.SimpleDateFormat

class Adaptar_Tarea (private val context : Activity, private val arraylist : ArrayList <Tarea> ) : ArrayAdapter<Tarea>( context ,
    R.layout.xml_actividadl, arraylist ) {


    override fun getView (position : Int, convertView : View?, parent : ViewGroup) : View {
        val inflater : LayoutInflater = LayoutInflater.from ( context )
        val view : View = inflater.inflate( R.layout.xml_lista_tareas , null )

        val textview_descripcion : TextView = view.findViewById(R.id.descripcion_textview_lista_tareas)

        textview_descripcion.text = arraylist[position].descripcion

        return view

    }
}