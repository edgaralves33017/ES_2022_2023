package com.example.es_tablewatcher.ui.gerirreservas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.es_tablewatcher.R
import com.example.es_tablewatcher.data.model.Mesa

class ListMesaAdapterWithSelectorAdapter(val context: Context, data: List<Mesa>) :
    RecyclerView.Adapter<ListMesaAdapterWithSelectorAdapter.ViewHolder>() {
    private val mData: List<Mesa> = data
    private val mInflater: LayoutInflater =
        LayoutInflater.from(context)

    private var selectedPosition : Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.item_listmesa, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mesa = mData[position]
        holder.mesa_id.text = "ID: ${mesa.id}"
        holder.mesa_lugares.text = "Lugares: ${mesa.lugares}"
        holder.container.setOnClickListener {
            selectedPosition = holder.adapterPosition
            notifyDataSetChanged()
        }
        if (selectedPosition == position) {
            holder.container.setBackgroundColor(context.getColor(R.color.teal_200))
        }
        else {
            holder.container.setBackgroundColor(context.getColor(R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun getSelectedMesa() : Mesa? {
        return try {
            mData[selectedPosition]
        } catch (e: Exception){
            null
        }
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var mesa_id: TextView
        var mesa_lugares: TextView
        var container : ConstraintLayout
        init {
            mesa_id = itemView.findViewById(R.id.mesa_id)
            mesa_lugares = itemView.findViewById(R.id.mesa_lugares)
            container = itemView.findViewById(R.id.container)
        }
    }
}