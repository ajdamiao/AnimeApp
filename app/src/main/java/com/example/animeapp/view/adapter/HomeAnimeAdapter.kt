package com.example.animeapp.view.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeapp.R
import com.example.animeapp.databinding.RviewAnimeListBinding
import com.example.animeapp.model.Anime

class HomeAnimeAdapter(private val anime: Anime): RecyclerView.Adapter<HomeAnimeAdapter.AnimeViewHolder>(){
    inner class AnimeViewHolder(val binding: RviewAnimeListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = RviewAnimeListBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return AnimeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        with(holder) {
            with(anime.data[position]) {

                Glide
                    .with(itemView)
                    .load(images.jpg.image_url)
                    .centerCrop()
                    .into(binding.animeImage)

                binding.animeTittle.text = title

                holder.itemView.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putInt("id",mal_id)
                    bundle.putString("imageURL", images.jpg.image_url)
                    bundle.putString("animeName", title)
                    bundle.putString("animeNameJapanese", title_japanese)
                    bundle.putString("synopsis", synopsis)
                    bundle.putString("url", trailer.url)
                    Navigation.findNavController(itemView).navigate(R.id.animeDetailsFragment, bundle)
                }
            }
        }
    }

    override fun getItemCount() = anime.data.size
}