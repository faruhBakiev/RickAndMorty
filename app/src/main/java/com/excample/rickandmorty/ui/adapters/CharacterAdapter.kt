package com.excample.rickandmorty.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.excample.rickandmorty.data.models.Characters
import com.excample.rickandmorty.databinding.ItemCharacterBinding


class CharacterAdapter (private val onClickListener: (id: Int) -> Unit) :
    ListAdapter<Characters,CharacterAdapter.CharactersViewHolder>(diffUtil) {

    inner class CharactersViewHolder(private val binding: ItemCharacterBinding) :
        ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(adapterPosition).apply { onClickListener (id) }
            }
        }

        fun onBind(item: Characters) {
            Glide.with(binding.ivCharacter.context)
                .load(item.image)
                .into(binding.ivCharacter)
            binding.tvCharacterName.text = item.name.replaceFirstChar {
                it.uppercase()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Characters>() {
            override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}