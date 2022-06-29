package clases_java.UI.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import clases_java.model_clases.Tarea
import com.example.organizadortareas.R

class TareaAdapter(
    private val context: Context,
    private val dataset: List<Tarea>
) : RecyclerView.Adapter<TareaAdapter.ItemViewHolder>() {

    private lateinit var mListener: onItemClickerListener

    interface onItemClickerListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListerner(listener: onItemClickerListener){
        mListener = listener
    }


    // creamos la clase auxiliar para seleccionar el formato que tendrá nuestra Tarea
    class ItemViewHolder(private val view: View, listener: onItemClickerListener) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.Tarea_description_adapter_title)

        init{

            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.tarea_adapter_layout, parent, false) // le indicamos que acaptador queremos usar

        return ItemViewHolder(adapterLayout, mListener)
    }

    override fun onBindViewHolder(holder: TareaAdapter.ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text =  dataset[position].descripcion // queremos mostrar la descripción para el elemtno indiccado.
    }

    override fun getItemCount() = dataset.size



}