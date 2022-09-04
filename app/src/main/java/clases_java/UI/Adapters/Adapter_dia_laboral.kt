package clases_java.UI.Adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import clases_java.model_clases.Actividad
import clases_java.model_clases.Dia_laboral
import com.example.organizadortareas.R
import java.sql.Date
import java.text.SimpleDateFormat

class Adapter_dia_laboral(private val context : Activity, private val arraylist : ArrayList < Dia_laboral > ) : ArrayAdapter<Dia_laboral>( context ,
    R.layout.recycler_list_dia_laboral, arraylist ) {


    override fun getView ( position : Int , convertView : View ?, parent : ViewGroup ) : View {
        val inflater : LayoutInflater = LayoutInflater.from ( context )
        val view : View = inflater.inflate( R.layout.recycler_list_dia_laboral , null )

        val textview_dia : TextView = view.findViewById(R.id.textview_dia)


        // colocamos la fecha del dia laboral dentro del texto que se va a mostrar.
        textview_dia.setText(arraylist[position].fecha.toString() )



        return view

    }
}

