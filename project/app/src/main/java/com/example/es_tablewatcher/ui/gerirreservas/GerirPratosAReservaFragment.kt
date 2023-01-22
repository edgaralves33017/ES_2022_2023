package com.example.es_tablewatcher.ui.gerirreservas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.es_tablewatcher.data.model.Prato
import com.example.es_tablewatcher.data.model.Reserva
import com.example.es_tablewatcher.databinding.FragmentGerirpratosareservaBinding
import com.example.es_tablewatcher.ui.MainActivity
import com.example.es_tablewatcher.ui.gerirreservas.adapter.ListPratosAdapter
import com.example.es_tablewatcher.ui.gerirreservas.adapter.ListPratosNaReservaAdapter
import com.example.es_tablewatcher.ui.gerirreservas.viewmodel.GerirPratosAReservaViewModel

class GerirPratosAReservaFragment : Fragment() {
    private lateinit var binding: FragmentGerirpratosareservaBinding
    private lateinit var reserva: Reserva
    private var viewModel : GerirPratosAReservaViewModel = GerirPratosAReservaViewModel()

    private val pratosNaReserva: MutableList<Prato> = mutableListOf()
    private val pratos: MutableList<Prato> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGerirpratosareservaBinding.inflate(inflater, container, false)
        reserva = arguments!!.getParcelable("reserva")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (MainActivity.context as MainActivity).changeAppBarTitle("Gerir Reserva")
    }

    override fun onResume() {
        super.onResume()
        initViews()
    }

    private fun initViews() {
        binding.btnFinalizar.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.terminate.setOnClickListener {
            viewModel.fecharReserva(reserva.id)
            findNavController().navigateUp()
        }

        binding.data.text = reserva.data
        binding.nomeCliente.text = reserva.nomeClient
        binding.mesa.text = "Mesa: ${reserva.mesaId}"

        binding.calculate.setOnClickListener {
            val total : Double = viewModel.calcularTotalReserva(reserva.id)
            Toast.makeText(this.context, "Total: $total", Toast.LENGTH_SHORT).show()
        }

        binding.pratosnareserva.layoutManager = LinearLayoutManager(context)
        val pratosNaReservaAdapter = ListPratosNaReservaAdapter(context!!, pratosNaReserva, ::removerPratoDaReserva)
        binding.pratosnareserva.adapter = pratosNaReservaAdapter
        pratosNaReserva.addAll(reserva.listaPratos)
        binding.pratosnareserva.adapter?.notifyDataSetChanged()

        binding.pratos.layoutManager = LinearLayoutManager(context)
        val pratosAdapter = ListPratosAdapter(context!!, pratos, ::adicionarPratoAReserva)
        binding.pratos.adapter = pratosAdapter
        pratos.addAll(viewModel.obterPratos())
        binding.pratos.adapter?.notifyDataSetChanged()
    }

    private fun removerPratoDaReserva (prato: Prato) {
        viewModel.removerPratoDaReserva(pratosNaReserva, reserva, prato, binding.pratosnareserva.adapter)
    }

    private fun adicionarPratoAReserva (prato: Prato) {
        viewModel.adicionarPratoAReserva(pratosNaReserva, reserva, prato, binding.pratosnareserva.adapter)
    }
}