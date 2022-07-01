package clases_java.UI.Adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import clases_java.model_clases.Actividad
import com.example.organizadortareas.R
import java.sql.Date
import java.text.SimpleDateFormat

class Adapter_dia_laboral(): RecyclerView.Adapter<Adapter_dia_laboral.MyViewHolder>() {

    // para meterle los datos, directamente
    var location_list_data = mutableListOf<Actividad>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter_dia_laboral.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_dia_laboral, parent, false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: Adapter_dia_laboral.MyViewHolder, position : Int) {
        holder.bind(location_list_data[position])

    }

    override fun getItemCount(): Int {
        return location_list_data.size
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        // vamos a bindear cosas aquí así que hay que cambiar un par de cosas de aquí
        val recycler_lista_actividades = view.findViewById<RecyclerView>(R.id.recycler_view_lista_actividades)


        // queremos bindear para cada actividad, a su posición correspondiente
        fun bind(data: Actividad, fecha: Date){
            // colocamos al textoview general la fecha

            if(  )
        }
    }
}