package com.keepcoding.dragonballavanzado.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.keepcoding.dragonballavanzado.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getHeroDetail(args.heroID)

        setListeners()
        setObservers()
    }

    private fun setListeners() {
        binding.back.setOnClickListener {
            findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToHomeFragment())
        }

        binding.favoriteIcon.setOnClickListener {
            // TODO: Toggle favorite
        }

    }

    private fun setObservers() {

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.hero.collect { hero ->

                hero?.let {
                    with(binding) {
                        photoHero.load(it.photo)
                        nameHero.text = it.name
                        descriptionHero.text = it.description

                        val icon = if (it.favorite) {
                            android.R.drawable.btn_star_big_on
                        } else {
                            android.R.drawable.btn_star_big_off
                        }

                        favoriteIcon.setImageResource(icon)
                    }
                }

            }
        }
    }
}