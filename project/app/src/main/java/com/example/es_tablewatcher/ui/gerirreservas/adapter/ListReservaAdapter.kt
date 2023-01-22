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
import com.example.es_tablewatcher.data.model.Reserva

class ListReservaAdapter(context: Context, data: List<Reserva>, private val containerClickListener: (reserva: Reserva)->Unit) :
    RecyclerView.Adapter<ListReservaAdapter.ViewHolder>() {
    private val mData: List<Reserva> = data
    private val mInflater: LayoutInflater =
        LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.item_listreserva, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reserva = mData[position]
        holder.mesa_id.text = reserva.mesaId.toString()
        holder.data.text = reserva.data
        holder.nome_cliente.text = reserva.nomeClient

        holder.container.setOnClickListener {
            containerClickListener(reserva)
        }
        holder.deleteBtn.visibility = View.VISIBLE
        holder.deleteBtn.setOnClickListener {
            deleteClickListener(reserva)
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var mesa_id: TextView
        var data: TextView
        var nome_cliente: TextView
        var container: ConstraintLayout
        var deleteBtn: ImageView

        init {
            mesa_id = itemView.findViewById(R.id.mesa_id)
            data = itemView.findViewById(R.id.data)
            nome_cliente = itemView.findViewById(R.id.nome_cliente)
            container = itemView.findViewById(R.id.container)
            deleteBtn = itemView.findViewById(R.id.delete)
        }
    }
}