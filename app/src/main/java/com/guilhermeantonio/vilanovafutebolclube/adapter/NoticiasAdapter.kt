package com.guilhermeantonio.vilanovafutebolclube.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.guilhermeantonio.vilanovafutebolclube.R
import com.guilhermeantonio.vilanovafutebolclube.model.NoticiasModel
import com.squareup.picasso.Picasso

/**
 * Created by Bimore on 14/08/2017.
 */

class NoticiasAdapter(private val noticiasList: List<NoticiasModel>,
                      private val context: Context,
                      private val listener: NoticiasAdapter.OnItemClickListener) : RecyclerView.Adapter<NoticiasAdapter.NoticiasViewHolder>() {

    interface OnItemClickListener {
        fun OnItemClickFoto(itemTime: NoticiasModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiasViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_noticia, parent, false)
        return NoticiasViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoticiasViewHolder, position: Int) {
        holder.bind(noticiasList[position], listener)
    }

    override fun getItemCount(): Int {
        return noticiasList.size
    }

    inner class NoticiasViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var imageViewUrlNoticia: ImageView = itemView.findViewById(R.id.imagem_noticia) as ImageView
        var textViewTitleNoticia: TextView = itemView.findViewById(R.id.title_noticia) as TextView


        fun bind(item: NoticiasModel, listener: OnItemClickListener) {

            textViewTitleNoticia.text = item.title_noticia
            Picasso.with(context).load(item.url_noticia).into(imageViewUrlNoticia)

            imageViewUrlNoticia.setOnClickListener { listener.OnItemClickFoto(item) }
        }
    }
}