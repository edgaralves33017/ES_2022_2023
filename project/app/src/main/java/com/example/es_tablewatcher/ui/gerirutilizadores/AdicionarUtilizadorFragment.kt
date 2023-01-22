package com.example.es_tablewatcher.ui.gerirutilizadores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.es_tablewatcher.data.model.Utilizador
import com.example.es_tablewatcher.databinding.FragmentAdicionarutilizadorBinding
import com.example.es_tablewatcher.ui.MainActivity
import com.example.es_tablewatcher.ui.gerirutilizadores.viewmodel.AdicionarUtilizadorViewModel

class AdicionarUtilizadorFragment : Fragment() {
    private lateinit var binding: FragmentAdicionarutilizadorBinding
    private lateinit var user : Utilizador
    private var viewModel : AdicionarUtilizadorViewModel = AdicionarUtilizadorViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdicionarutilizadorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        (MainActivity.context as MainActivity).changeAppBarTitle("Adicionar Utilizador")
    }

    private fun initViews() {
        binding.btnAdicionarutilizador.setOnClickListener {
            viewModel.adicionarUtilizador(binding.usernameEdit.text.toString(), binding.passwordEdit.text.toString(), binding.adminCheck.isChecked)
            findNavController().navigateUp()
        }
    }
}