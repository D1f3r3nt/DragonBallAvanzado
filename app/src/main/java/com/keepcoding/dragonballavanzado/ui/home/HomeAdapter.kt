package com.keepcoding.dragonballavanzado.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keepcoding.dragonballavanzado.databinding.ItemHomeBinding
import com.keepcoding.dragonballavanzado.models.HeroUI

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    
    private var heros = emptyList<HeroUI>()
    
    class HomeViewHolder(private val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(hero: HeroUI) {
            with(binding) {
                heroName.text = hero.name
                heroPhoto.load(hero.photo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return heros.size
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(heros[position])
    }
    
    fun setHeros(newHeros: List<HeroUI>) {
        this.heros = newHeros
        
        notifyDataSetChanged()
    }
    
}