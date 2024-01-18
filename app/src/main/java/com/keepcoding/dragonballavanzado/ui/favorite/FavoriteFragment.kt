package com.keepcoding.dragonballavanzado.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.keepcoding.dragonballavanzado.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding

    private val viewModel: FavoriteViewModel by viewModels()

    private val adapter = FavoriteAdapter {
        findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToDetailsFragment(it.id))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getHeroFavoriteList()

        with(binding) {
            heroFavoriteList.adapter = adapter
            heroFavoriteList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        setListeners()
        setObservers()
    }

    private fun setListeners() {
        binding.homeIcon.setOnClickListener {
            findNavController().navigate(FavoriteFragmentDirections.actionFavoriteFragmentToHomeFragment())
        }
    }

    private fun setObservers() {

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.heros.collect { heros ->
                adapter.setHeros(heros)
            }
        }
    }
}