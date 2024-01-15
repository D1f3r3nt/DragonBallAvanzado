package com.keepcoding.dragonballavanzado.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.keepcoding.dragonballavanzado.R
import com.keepcoding.dragonballavanzado.databinding.FragmentRootBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootFragment : Fragment() {

    private lateinit var binding: FragmentRootBinding
    
    private val viewModel: RootViewModel by viewModels()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRootBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        if (viewModel.isLogged()) {
            findNavController().navigate(R.id.action_rootFragment_to_homeFragment)
        } else {
            findNavController().navigate(R.id.action_rootFragment_to_loginFragment)
        }
        
    }
}