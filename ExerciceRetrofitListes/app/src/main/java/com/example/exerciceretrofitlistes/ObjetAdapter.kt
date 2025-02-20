package com.example.exerciceretrofitlistes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exerciceretrofitlistes.databinding.ObjetcomplexItemBinding

class ObjetAdapter : ListAdapter<ObjetComplex, ObjetAdapter.ObjetComplexItemViewHolder>(ObjetComplexCallback) {

    // binding nous permet d'accéder à tout le champs de notre layout personne_item.xml
    inner class ObjetComplexItemViewHolder(private val binding: ObjetcomplexItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(objet: ObjetComplex) {
             binding.tvA.text = objet.A
            binding.tvB.text = objet.B
            binding.tvC.text = objet.C.count().toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjetComplexItemViewHolder {
        val binding: ObjetcomplexItemBinding = ObjetcomplexItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ObjetComplexItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ObjetComplexItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object ObjetComplexCallback : DiffUtil.ItemCallback<ObjetComplex>() {
    override fun areItemsTheSame(oldItem: ObjetComplex, newItem: ObjetComplex): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ObjetComplex, newItem: ObjetComplex): Boolean {
        return oldItem.A == newItem.A &&
                oldItem.B == newItem.B
    }
}