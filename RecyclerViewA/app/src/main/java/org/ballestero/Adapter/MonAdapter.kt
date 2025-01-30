package org.ballestero.Adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.ballestero.databinding.MonItemBinding
import org.ballestero.model.Secret
import java.time.format.DateTimeFormatter

class MonAdapter : ListAdapter<Secret, MonAdapter.MonItemViewHolder>(MonItemDiffCallback) {
    // binding nous permet d'accéder à tout le champs de notre layout personne_item.xml
    inner class MonItemViewHolder(private val binding: MonItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        private  val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(secret: Secret) {
            binding.tvNom.text = secret.nom
            binding.tvDate.text = secret.date.format(formatter)
            binding.tvNbG.text = secret.nbGrand.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonItemViewHolder {
        val binding: MonItemBinding = MonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MonItemViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MonItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object MonItemDiffCallback : DiffUtil.ItemCallback<Secret>() {
    override fun areItemsTheSame(oldItem: Secret, newItem: Secret): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Secret, newItem: Secret): Boolean {
        return oldItem.nom == newItem.nom &&
                oldItem.date  == newItem.date &&
                oldItem.nbGrand == newItem.nbGrand
    }
}