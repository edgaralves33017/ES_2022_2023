package com.example.es_tablewatcher.ui.gerirpratos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.es_tablewatcher.R
import com.example.es_tablewatcher.data.model.Prato
import com.example.es_tablewatcher.data.model.Utilizador
import com.example.es_tablewatcher.databinding.FragmentGerirpratosBinding
import com.example.es_tablewatcher.ui.MainActivity
import com.example.es_tablewatcher.ui.gerirpratos.adapter.ListPratosAdapter
import com.example.es_tablewatcher.ui.gerirpratos.viewmodel.GerirPratosViewModel

class GerirPratosFragment : Fragment() {

    private lateinit var binding: FragmentGerirpratosBinding
    private lateinit var user : Utilizador
    private var viewModel : GerirPratosViewModel = GerirPratosViewModel()

    private val pratoList: MutableList<Prato> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGerirpratosBinding.inflate(inflater, container, false)
        user = arguments!!.getParcelable("user")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (MainActivity.context as MainActivity).changeAppBarTitle("Gerir Pratos")
    }

    override fun onResume() {
        super.onResume()
        initViews()
    }

    private fun initViews() {
        if (!user.isAdmin) {
            binding.btnAdicionarprato.visibility = View.GONE
        }
        else {
            binding.btnAdicionarprato.visibility = View.VISIBLE
            binding.btnAdicionarprato.setOnClickListener {
                findNavController().navigate(R.id.action_GerirPratosFragment_to_AdicionarPratoFragment)
            }
        }

        binding.listPratos.layoutManager = LinearLayoutManager(context)
        val adapter = ListPratosAdapter(context!!, pratoList, user.isAdmin, ::removeClickListener)
        binding.listPratos.adapter = adapter
        pratoList.clear()
        pratoList.addAll(viewModel.obterPratos())
        binding.listPratos.adapter?.notifyDataSetChanged()
    }

    private fun removeClickListener (prato: Prato) {
        viewModel.updatePrato(pratoList, prato, binding.listPratos.adapter)
    }
}