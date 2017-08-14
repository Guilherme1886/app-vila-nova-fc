package com.guilhermeantonio.vilanovafutebolclube.fragment


import android.os.AsyncTask
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.guilhermeantonio.vilanovafutebolclube.R
import com.guilhermeantonio.vilanovafutebolclube.model.NoticiasModel
import org.jsoup.Jsoup


class JogosFragment : Fragment() {

    private val URL_RODADAS = "http://globoesporte.globo.com/futebol/brasileirao-serie-b/"

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        JogosAsyncTask().execute()

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_jogos, container, false)
    }

    inner class JogosAsyncTask : AsyncTask<Void, Void, List<String>>() {

        override fun doInBackground(vararg params: Void?): List<String> {

            val noticiasList = ArrayList<String>()

            try {
                val getNoticias = Jsoup.connect(URL_RODADAS).get()


                val title = getNoticias.select("ul.lista-de-jogos-conteudo li.lista-de-jogos-item")


                title.forEachIndexed { index, element ->

                    val imagens = getNoticias.select("img.placar-jogo-equipes-escudo-mandante")[index]

                    Log.v("TAG", imagens.attr("src"))

                }


                //    Log.v("TAG", imagens)


            } catch (e: NetworkOnMainThreadException) {
                e.printStackTrace()
            }


            return noticiasList
        }

        override fun onPostExecute(result: List<String>?) {
            super.onPostExecute(result)


        }
    }

}
