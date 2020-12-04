package com.example.desafioandroidapis.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafioandroidapis.R
import com.example.desafioandroidapis.model.Comic


class ComicDisplayAdapter(
    private val onComicClicked: (c: Comic) -> Unit
)
    : RecyclerView.Adapter<ComicDisplayAdapter.ComicViewHoldwer>() {

    private var listComics: List<Comic> = listOf()

    inner class ComicViewHoldwer(comicView: View) : RecyclerView.ViewHolder(comicView) {
        val ivComicThumb: ImageView = comicView.findViewById(R.id.ivComicThumb)
        val tvComicEdition: TextView = comicView.findViewById(R.id.tvComicEdition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHoldwer {
        val comicView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_comic,
            parent,
            false
        )
        return ComicViewHoldwer(comicView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ComicViewHoldwer, position: Int) {
        val comic = listComics[position]

//        holder.ivComicThumb.setImageBitmap(comic.imagesBitMap)
        holder.tvComicEdition.text = "# ${comic.issueNumber}"

        holder.itemView.setOnClickListener {
            onComicClicked(comic)
        }
    }

    override fun getItemCount(): Int = listComics.size

    fun setComicList(listComics: List<Comic>) {
        this.listComics = listComics
        notifyDataSetChanged()
    }
}