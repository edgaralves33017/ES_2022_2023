package com.example.es_tablewatcher.ui.gerirreservas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.es_tablewatcher.R
import com.example.es_tablewatcher.data.model.Prato

class ListPratosAdapter(context: Context, data: List<Prato>, private val onClickListener: (prato: Prato)->Unit) :
    RecyclerView.Adapter<ListPratosAdapter.ViewHolder>() {
    private val mData: List<Prato> = data
    private val mInflater: LayoutInflater =
        LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.item_listpratowithadd, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val prato = mData[position]
        holder.prato_desc.text = prato.descricao
        holder.prato_price.text = prato.preco.toString()

        holder.add.setOnClickListener {
            onClickListener(prato)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var prato_desc: TextView
        var prato_price: TextView
        var container: ConstraintLayout
        var add: ImageView

        init {
            prato_desc = itemView.findViewById(R.id.prato_desc)
            prato_price = itemView.findViewById(R.id.prato_price)
            container = itemView.findViewById(R.id.container)
            add = itemView.findViewById(R.id.add)
        }
    }
}