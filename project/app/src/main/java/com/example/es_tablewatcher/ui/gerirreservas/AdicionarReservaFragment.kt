package com.example.es_tablewatcher.ui.gerirreservas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.es_tablewatcher.data.model.Mesa
import com.example.es_tablewatcher.databinding.FragmentAdicionarreservaBinding
import com.example.es_tablewatcher.ui.MainActivity
import com.example.es_tablewatcher.ui.gerirreservas.adapter.ListMesaAdapterWithSelectorAdapter
import com.example.es_tablewatcher.ui.gerirreservas.viewmodel.AdicionarReservaViewModel

class AdicionarReservaFragment : Fragment() {
    private lateinit var binding: FragmentAdicionarreservaBinding
    private var viewModel : AdicionarReservaViewModel = AdicionarReservaViewModel()

    private val mesasList: MutableList<Mesa> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdicionarreservaBinding.inflate(inflater, container, false)
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
        binding.btnAdicionarreserva.setOnClickListener {
            val mesaSelected = (binding.mesasList.adapter as ListMesaAdapterWithSelectorAdapter).getSelectedMesa()
            if (mesaSelected == null){
                Toast.makeText(this.context, "Precisa de selecionar uma mesa antes de prosseguir!", Toast.LENGTH_SHORT).show()
            }
            else {
                viewModel.adicionarReserva(mesaSelected.id, binding.nomeClienteEdit.text.toString(), binding.contactoClienteEdit.text.toString())
                findNavController().navigateUp()
            }
        }

        binding.mesasList.layoutManager = LinearLayoutManager(context)
        val mesaAdapter = ListMesaAdapterWithSelectorAdapter(context!!, mesasList)
        binding.mesasList.adapter = mesaAdapter
        mesasList.addAll(viewModel.obterMesas())
        binding.mesasList.adapter?.notifyDataSetChanged()
    }
}