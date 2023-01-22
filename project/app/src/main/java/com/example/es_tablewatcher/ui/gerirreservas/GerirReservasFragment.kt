package com.example.es_tablewatcher.ui.gerirreservas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.es_tablewatcher.R
import com.example.es_tablewatcher.data.model.Mesa
import com.example.es_tablewatcher.data.model.Reserva
import com.example.es_tablewatcher.data.model.Utilizador
import com.example.es_tablewatcher.databinding.FragmentGerirreservasBinding
import com.example.es_tablewatcher.ui.MainActivity
import com.example.es_tablewatcher.ui.gerirreservas.adapter.ListMesaAdapter
import com.example.es_tablewatcher.ui.gerirreservas.adapter.ListReservaAdapter
import com.example.es_tablewatcher.ui.gerirreservas.viewmodel.GerirReservasViewModel

class GerirReservasFragment : Fragment() {
    private lateinit var binding: FragmentGerirreservasBinding
    private lateinit var user : Utilizador
    private var viewModel : GerirReservasViewModel = GerirReservasViewModel()

    private val reservasList: MutableList<Reserva> = mutableListOf()
    private val mesasList: MutableList<Mesa> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGerirreservasBinding.inflate(inflater, container, false)
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
        binding.btnAdicionarreserva.setOnClickListener {
            findNavController().navigate(R.id.action_GerirReservasFragment_to_AdicionarReservaFragment)
        }

        binding.reservasList.layoutManager = LinearLayoutManager(context)
        val reservaAdapter = ListReservaAdapter(context!!, reservasList, ::detailsReserva)
        binding.reservasList.adapter = reservaAdapter
        reservasList.addAll(viewModel.obterReservas())
        binding.reservasList.adapter?.notifyDataSetChanged()

        binding.mesasList.layoutManager = LinearLayoutManager(context)
        val mesaAdapter = ListMesaAdapter(context!!, mesasList)
        binding.mesasList.adapter = mesaAdapter
        mesasList.addAll(viewModel.obterMesas())
        binding.mesasList.adapter?.notifyDataSetChanged()
    }

    private fun detailsReserva(reserva:Reserva) {
        val bundle = Bundle()
        bundle.putParcelable("reserva", reserva)
        findNavController().navigate(R.id.action_GerirReservasFragment_to_GerirPratosAReservaFragment, bundle)
    }
}