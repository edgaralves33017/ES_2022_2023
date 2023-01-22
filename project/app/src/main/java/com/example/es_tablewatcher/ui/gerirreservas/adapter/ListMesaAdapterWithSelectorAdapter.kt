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
    private var selectedMesa : Mesa? = null
    private var selectedPosition : Int = -1
    private var selectedView : ViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.item_listreserva, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val mesa = mData[position]
        holder.mesa_id.text = mesa.id.toString()
        holder.mesa_lugares.text = mesa.lugares.toString()
        holder.container.setOnClickListener {
            checkSelection(holder, position)
        }
        checkSelection(holder, position)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    fun getSelectedMesa() : Mesa? {
        return selectedMesa
    }

    private fun checkSelection(holder: ViewHolder, position: Int) {
        if (selectedMesa != null) {
            if (selectedMesa!!.id == mData[position].id) {
                holder.container.setBackgroundColor(context.getColor(R.color.white))
                selectedMesa = null
                selectedView = null
                selectedPosition = -1
                notifyItemChanged(position)
            }
            else {
                selectedView?.container?.setBackgroundColor(context.getColor(R.color.white))
                notifyItemChanged(selectedPosition)
                selectedPosition = position
                selectedMesa = mData[position]
                selectedView = holder
                holder.container.setBackgroundColor(context.getColor(androidx.transition.R.color.material_blue_grey_800))
                notifyItemChanged(position)
            }
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