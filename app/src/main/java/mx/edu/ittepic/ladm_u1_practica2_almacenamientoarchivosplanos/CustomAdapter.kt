package mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.Arreglos.arregloNombre
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.Arreglos.arregloNC
import mx.edu.ittepic.ladm_u1_practica2_almacenamientoarchivosplanos.Arreglos.arregloSem

import java.io.InputStreamReader

class CustomAdapter: RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
    val images = intArrayOf(R.drawable.ic_baseline_face_24,
        R.drawable.ic_baseline_face_24,
        R.drawable.ic_baseline_face_24,
        R.drawable.ic_baseline_face_24,
        R.drawable.ic_baseline_face_24,
        R.drawable.ic_baseline_face_24,
        R.drawable.ic_baseline_face_24,
        R.drawable.ic_baseline_face_24,
        R.drawable.ic_baseline_face_24,
        R.drawable.ic_baseline_face_24)
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout,viewGroup,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, j: Int) {
        viewHolder.itemNombre.text = arregloNombre[j]
        viewHolder.itemNC.text = arregloNC[j]
        viewHolder.itemSem.text = arregloSem[j]
        viewHolder.itemImage.setImageResource(images[j])

    }

    override fun getItemCount(): Int {
        return arregloNombre.size
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemNombre: TextView
        var itemNC: TextView
        var itemSem: TextView
        init{
            itemImage = itemView.findViewById(R.id.item_image)
            itemNombre = itemView.findViewById(R.id.item_nombre)
            itemNC = itemView.findViewById(R.id.item_nc)
            itemSem = itemView.findViewById(R.id.item_sem)
        }
    }
}