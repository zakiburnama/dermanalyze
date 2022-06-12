package com.example.dermanalyze_bangkit_capstone

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ListHistoryAdapter (private val listHistory: ArrayList<PredictResponse>) : RecyclerView.Adapter<ListHistoryAdapter.ListViewHolder>()  {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto2: ImageView = itemView.findViewById(R.id.img_item_photo2)
        var tvtitleArticles2: TextView = itemView.findViewById(R.id.tv_item_titlearticle2)
        var tvreadmore2: TextView = itemView.findViewById(R.id.tv_item_readmore2)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_history, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (id, photo, predict, create, _, _, _) = listHistory[position]

        if (predict == "unk")
            holder.tvtitleArticles2.setBackgroundResource(R.drawable.bg_predict)
         else
            holder.tvtitleArticles2.setBackgroundResource(R.drawable.bg_predict_red)

        Picasso.get().load(photo).into(holder.imgPhoto2)
        holder.tvtitleArticles2.text = predict
        holder.tvreadmore2.text = create
    }

    override fun getItemCount(): Int = listHistory.size
}