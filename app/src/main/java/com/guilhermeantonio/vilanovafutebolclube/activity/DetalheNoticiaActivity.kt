package com.guilhermeantonio.vilanovafutebolclube.activity

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView

import com.guilhermeantonio.vilanovafutebolclube.R
import com.guilhermeantonio.vilanovafutebolclube.adapter.NoticiasAdapter
import com.guilhermeantonio.vilanovafutebolclube.model.NoticiasModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhe_noticia.*
import kotlinx.android.synthetic.main.content_detalhe_noticia.*
import kotlinx.android.synthetic.main.fragment_noticias.*
import org.jsoup.Jsoup
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL

class DetalheNoticiaActivity : AppCompatActivity() {

    private var url_detalhe_noticia = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe_noticia)
        setSupportActionBar(toolbar)

        val background: ImageView = ImageView(this)

        Picasso.with(this).load(intent.getStringExtra("url_foto_noticia")).into(background, object : Callback {
            override fun onSuccess() {

                toolbar_layout.background = background.drawable

            }

            override fun onError() {

            }

        })

//        title_detalhe_noticia.text = intent.getStringExtra("title_noticia")

        url_detalhe_noticia = intent.getStringExtra("url_noticia")

        DetalheNoticiaAsyncTask().execute()
    }

    inner class DetalheNoticiaAsyncTask : AsyncTask<Void, Void, List<String>>() {

        override fun doInBackground(vararg params: Void?): List<String> {

            val detalheNoticiaList = ArrayList<String>()

            try {
                val getNoticias = Jsoup.connect(url_detalhe_noticia).get()

                val lista_com_o_detalhe_noticias = getNoticias.select("article")

                lista_com_o_detalhe_noticias.forEachIndexed { index, element ->

                    Log.v("TAG", element.text())


                    val content_noticia = getNoticias.select("div.mc-column.content-text.active-extra-styles p.content-text__container")[index].text()

                    detalheNoticiaList.add(element.text())


                }


            } catch (e: NetworkOnMainThreadException) {
                e.printStackTrace()
            }

            Thread.sleep(1000)

            return detalheNoticiaList

        }

        override fun onPostExecute(result: List<String>?) {
            super.onPostExecute(result)


            val arrayAdapter = ArrayAdapter<String>(this@DetalheNoticiaActivity, android.R.layout.simple_list_item_1, result)
            list_view_detalhe_noticia.adapter = arrayAdapter


        }
    }


}
