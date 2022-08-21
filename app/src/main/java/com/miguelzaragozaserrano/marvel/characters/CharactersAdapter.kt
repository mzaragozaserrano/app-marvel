package com.miguelzaragozaserrano.marvel.characters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miguelzaragozaserrano.marvel.R
import com.miguelzaragozaserrano.marvel.characters.TYPE.ALL
import com.miguelzaragozaserrano.marvel.databinding.ItemCharacterBinding
import com.miguelzaragozaserrano.marvel.models.CharacterView
import com.miguelzaragozaserrano.marvel.utils.extensions.inflate
import com.miguelzaragozaserrano.marvel.utils.extensions.loadFromUrl
import kotlin.properties.Delegates

class CharactersAdapter(private val listener: OnShowDetails) :
    RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    var type: TYPE = ALL

    internal var collection: List<CharacterView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharactersViewHolder(parent.inflate(R.layout.item_character))

    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(collection[position], listener)
    }

    inner class CharactersViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val binding = ItemCharacterBinding.bind(itemView)

        fun bind(item: CharacterView, listener: OnShowDetails) {
            with(binding) {
                nameCharacter.text = item.name
                avatarCharacter.loadFromUrl(item.image)
                buttonInfo.setOnClickListener { listener.onClick(item) }
            }
        }
    }
}

class OnShowDetails(val onShowDetails: (item: CharacterView) -> Unit) {
    fun onClick(item: CharacterView) = onShowDetails(item)
}

enum class TYPE { ALL, FAVORITE }