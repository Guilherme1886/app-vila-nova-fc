package com.guilhermeantonio.vilanovafutebolclube.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.guilhermeantonio.vilanovafutebolclube.R
import com.guilhermeantonio.vilanovafutebolclube.model.NoticiasModel
import com.squareup.picasso.Callback
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

        var imageViewUrlFotoNoticia: ImageView = itemView.findViewById(R.id.imagem_noticia) as ImageView
        var textViewSubTitleNoticia: TextView = itemView.findViewById(R.id.subtitle_noticia) as TextView
        var textViewTitleNoticia: TextView = itemView.findViewById(R.id.title_noticia) as TextView
        var cardNoticia: CardView = itemView.findViewById(R.id.card_view) as CardView


        fun bind(item: NoticiasModel, listener: OnItemClickListener) {


            Picasso.with(context).load(item.url_foto_noticia).into(imageViewUrlFotoNoticia, object : Callback {
                override fun onSuccess() {
                    cardNoticia.visibility = View.VISIBLE
                    textViewSubTitleNoticia.text = item.subtitle_noticia
                    textViewTitleNoticia.text = item.title_noticia

                }

                override fun onError() {

                }

            })

            imageViewUrlFotoNoticia.setOnClickListener { listener.OnItemClickFoto(item) }
        }
    }
}