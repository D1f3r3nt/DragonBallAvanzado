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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions
import com.keepcoding.dragonballavanzado.R
import com.keepcoding.dragonballavanzado.databinding.FragmentDetailsBinding
import com.keepcoding.dragonballavanzado.models.LocationUI
import com.keepcoding.dragonballavanzado.models.getLatLng
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var mMap: GoogleMap

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
        viewModel.getLocations(args.heroID)
        
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        setListeners()
        setObservers()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    private fun fullMaps(locations: List<LocationUI>) {
        
        locations.forEach { location ->
            mMap.addMarker(
                MarkerOptions()
                    .position(location.getLatLng())
                    .title(location.dateShow.split('T')[0])
            )
        }
        
        mMap.moveCamera(CameraUpdateFactory.newLatLng(locations[0].getLatLng()))
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

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.locations.collect { locations ->
                if (locations.isNotEmpty()) {
                    fullMaps(locations)
                }
            }
        }
    }
}