package com.example.es_tablewatcher.ui.gerirutilizadores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.es_tablewatcher.R
import com.example.es_tablewatcher.data.model.Utilizador
import com.example.es_tablewatcher.databinding.FragmentGerirutilizadoresBinding
import com.example.es_tablewatcher.ui.MainActivity
import com.example.es_tablewatcher.ui.gerirutilizadores.adapter.ListUtilizadoresAdapter
import ui.gerirutilizadores.viewmodel.GerirUtilizadoresViewModel

class GerirUtilizadoresFragment : Fragment() {

    private lateinit var binding: FragmentGerirutilizadoresBinding
    private lateinit var user : Utilizador
    private var viewModel : GerirUtilizadoresViewModel = GerirUtilizadoresViewModel()

    private val userList: MutableList<Utilizador> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGerirutilizadoresBinding.inflate(inflater, container, false)
        user = arguments!!.getParcelable("user")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (MainActivity.context as MainActivity).changeAppBarTitle("Gerir Utilizadores")
    }

    override fun onResume() {
        super.onResume()
        initViews()
    }

    private fun initViews() {
        if (!user.isAdmin) {
            binding.btnAdicionarutilizador.visibility = View.GONE
        }
        else {
            binding.btnAdicionarutilizador.visibility = View.VISIBLE
            binding.btnAdicionarutilizador.setOnClickListener {
                findNavController().navigate(R.id.action_GerirUtilizadoresFragment_to_AdicionarUtilizadorFragment)
            }
        }

        binding.listUtilizadores.layoutManager = LinearLayoutManager(context)
        val adapter = ListUtilizadoresAdapter(context!!, userList, user.isAdmin, ::removeClickListener)
        binding.listUtilizadores.adapter = adapter
        userList.clear()
        userList.addAll(viewModel.obterUtilizadores())
        binding.listUtilizadores.adapter?.notifyDataSetChanged()
    }

    private fun removeClickListener (utilizador: Utilizador) {
        viewModel.updateUtilizador(userList, utilizador, binding.listUtilizadores.adapter)
    }
}