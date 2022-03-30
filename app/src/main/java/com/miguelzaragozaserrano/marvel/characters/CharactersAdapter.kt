package com.miguelzaragozaserrano.marvel.characters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.databinding.ItemCharacterBinding
import com.miguelzaragozaserrano.marvel.models.CharacterView
import com.miguelzaragozaserrano.marvel.utils.extensions.inflate
import kotlin.properties.Delegates

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {
    internal var collection: List<CharacterView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }
    internal var characterListener: (CharacterView, View) -> Unit =
        { _: CharacterView, _: View -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharactersViewHolder(parent.inflate(R.layout.item_character))

    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(collection[position], characterListener)
    }

    inner class CharactersViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val binding = ItemCharacterBinding.bind(itemView)

        fun bind(item: CharacterView, characterListener: (CharacterView, View) -> Unit) {
            binding.characterName.text = item.name
        }
    }
}