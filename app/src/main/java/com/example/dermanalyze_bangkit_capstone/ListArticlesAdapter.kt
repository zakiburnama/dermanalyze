package com.example.dermanalyze_bangkit_capstone

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListArticlesAdapter (private val listHero: ArrayList<Articles>) : RecyclerView.Adapter<ListArticlesAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_articles, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listHero[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvtitleArticles.text = name
        holder.tvreadmore.text = description
    }

    override fun getItemCount(): Int = listHero.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        var tvtitleArticles: TextView = itemView.findViewById(R.id.tv_item_titlearticle)
        var tvreadmore: TextView = itemView.findViewById(R.id.tv_item_readmore)
    }
}