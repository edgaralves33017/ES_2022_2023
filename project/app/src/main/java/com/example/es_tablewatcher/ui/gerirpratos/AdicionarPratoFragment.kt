package com.example.es_tablewatcher.ui.gerirpratos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.es_tablewatcher.data.model.Utilizador
import com.example.es_tablewatcher.databinding.FragmentAdicionarpratoBinding
import com.example.es_tablewatcher.ui.MainActivity
import com.example.es_tablewatcher.ui.gerirpratos.viewmodel.AdicionarPratosViewModel

class AdicionarPratoFragment: Fragment() {
    private lateinit var binding: FragmentAdicionarpratoBinding
    private lateinit var user : Utilizador
    private var viewModel : AdicionarPratosViewModel = AdicionarPratosViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdicionarpratoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        (MainActivity.context as MainActivity).changeAppBarTitle("Adicionar Prato")
    }

    private fun initViews() {
        binding.btnAdicionarprato.setOnClickListener {
            viewModel.adicionarPrato(binding.pratoDescEdit.text.toString(), binding.pratoPreco.text.toString())
            findNavController().navigateUp()
        }
    }
}