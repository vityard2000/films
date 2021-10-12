package com.films.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.films.R
import com.films.databinding.ItemFilmBinding
import com.films.domain.entities.Film

class FilmsAdapter(val context: Context):
    PagingDataAdapter<Film, FilmsAdapter.FilmsViewHolder>(ArticleDiffItemCallback) {
        var onClickItem = { film: Film ->
        }
        private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
            return FilmsViewHolder(layoutInflater.inflate(R.layout.item_film, parent, false), context)
        }

        override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
            getItem(position)?.let { holder.bind(it) }
        }

    inner class FilmsViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

        fun bind(film: Film) {
            preview?.let {
                Glide
                    .with(context)
                    .load(film.multimedia?.src)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_placeholder)
                    .error(R.drawable.ic_image_placeholder)
                    .into(it)
            }
            itemView.setOnClickListener{onClickItem(film)}
            title?.setText(film.displayTitle)
        }

        var title: TextView? = null
        var preview: ImageView? = null

        init {
            title = itemView.findViewById(R.id.item_film__title)
            preview = itemView.findViewById(R.id.item_film__preview)
        }
    }
    }



    private object ArticleDiffItemCallback : DiffUtil.ItemCallback<Film>() {

        override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
            return oldItem.displayTitle == newItem.displayTitle && oldItem.link?.url == newItem.link?.url
        }
}