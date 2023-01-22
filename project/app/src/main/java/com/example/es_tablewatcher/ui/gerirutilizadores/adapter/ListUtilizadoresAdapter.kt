package com.example.es_tablewatcher.ui.gerirutilizadores.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.es_tablewatcher.R
import com.example.es_tablewatcher.data.model.Utilizador

class ListUtilizadoresAdapter(context: Context, data: List<Utilizador>, private val isAdmin: Boolean, private val onClickListener: (utilizador: Utilizador)->Unit) :
    RecyclerView.Adapter<ListUtilizadoresAdapter.ViewHolder>() {
    private val mData: List<Utilizador> = data
    private val mInflater: LayoutInflater =
        LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.item_listutilizador, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val utilizador = mData[position]
        holder.user_id.text = utilizador.id.toString()
        holder.username.text = utilizador.username.toString()
        holder.isAdmin.text = if (utilizador.isAdmin) "Admin User" else "Normal User"

        if (!isAdmin) {
            holder.deleteBtn.visibility = View.GONE
        }
        else {
            holder.deleteBtn.visibility = View.VISIBLE
            holder.deleteBtn.setOnClickListener {
                onClickListener(utilizador)
            }
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var user_id: TextView
        var username: TextView
        var isAdmin: TextView
        var deleteBtn: ImageView

        init {
            user_id = itemView.findViewById(R.id.user_id)
            username = itemView.findViewById(R.id.username)
            isAdmin = itemView.findViewById(R.id.isAdmin)
            deleteBtn = itemView.findViewById(R.id.delete)
        }
    }
}