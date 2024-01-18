package com.keepcoding.dragonballavanzado.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.keepcoding.dragonballavanzado.databinding.ItemHomeBinding
import com.keepcoding.dragonballavanzado.models.HeroUI

class FavoriteAdapter(
    private val onClick: (HeroUI) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    private var heros = emptyList<HeroUI>()

    inner class FavoriteViewHolder(private val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var heroUI: HeroUI

        init {
            binding.root.setOnClickListener {
                onClick(this.heroUI)
            }
        }

        fun bind(hero: HeroUI) {
            this.heroUI = hero

            with(binding) {
                heroName.text = hero.name
                heroPhoto.load(hero.photo)
                heroDescription.text = hero.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return heros.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(heros[position])
    }

    fun setHeros(newHeros: List<HeroUI>) {
        this.heros = newHeros

        notifyDataSetChanged()
    }

}