package com.example.es_tablewatcher.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.es_tablewatcher.R
import com.example.es_tablewatcher.data.model.Utilizador
import com.example.es_tablewatcher.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var user : Utilizador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        user = arguments!!.getParcelable("user")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.btnGerirpratos.setOnClickListener {
            navigateTo(R.id.action_MenuFragment_to_GerirPratosFragment)
        }

        binding.btnGerirreservas.setOnClickListener {
            navigateTo(R.id.action_MenuFragment_to_GerirReservas)
        }

        if (!user.isAdmin) {
            binding.btnGerirutilizadores.visibility = View.GONE
        }
        else {
            binding.btnGerirutilizadores.visibility = View.VISIBLE
            binding.btnGerirutilizadores.setOnClickListener {
                navigateTo(R.id.action_MenuFragment_to_GerirUtilizadores)
            }
        }
    }

    private fun navigateTo(action: Int) {
        val bundle = Bundle()
        bundle.putParcelable("user", user)
        findNavController().navigate(action, bundle)
    }
}