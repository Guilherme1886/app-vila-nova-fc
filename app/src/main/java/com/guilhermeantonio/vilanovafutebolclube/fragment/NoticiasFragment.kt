package com.guilhermeantonio.vilanovafutebolclube.fragment


import android.os.AsyncTask
import android.os.Bundle
import android.os.NetworkOnMainThreadException
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.guilhermeantonio.vilanovafutebolclube.R
import com.guilhermeantonio.vilanovafutebolclube.adapter.NoticiasAdapter
import com.guilhermeantonio.vilanovafutebolclube.model.NoticiasModel
import kotlinx.android.synthetic.main.fragment_noticias.*
import org.jsoup.Jsoup


class NoticiasFragment : Fragment() {

    private val URL_NOTICIAS = "http://globoesporte.globo.com/go/futebol/times/vila-nova/"
    private lateinit var mAdapter: NoticiasAdapter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        NoticiasAsyncTask().execute()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_noticias, container, false)
    }

    inner class NoticiasAsyncTask : AsyncTask<Void, Void, List<NoticiasModel>>() {

        override fun doInBackground(vararg params: Void?): List<NoticiasModel> {

            val noticiasList = ArrayList<NoticiasModel>()
            var objNoticias: NoticiasModel

            try {
                val getNoticias = Jsoup.connect(URL_NOTICIAS).get()

                val lista_com_as_noticias = getNoticias.select("div.bastian-page div._t div.bastian-feed-item")

                lista_com_as_noticias.forEachIndexed { index, element ->

                    objNoticias = NoticiasModel()

                    val imagens = getNoticias.select("img.bstn-fd-picture-image")[index].attr("src")
                    val title_noticia = getNoticias.select("p.feed-post-body-title")[index].text()
                    val section_noticias = getNoticias.select("span.feed-post-metadata-section")[index].text()
                    val url_noticia = getNoticias.select("a.feed-post-link")[index].attr("href")


                    objNoticias.url_noticia = url_noticia
                    objNoticias.url_foto_noticia = imagens
                    objNoticias.title_noticia = title_noticia
                    objNoticias.section_noticia = section_noticias

                    noticiasList.add(objNoticias)


                }


            } catch (e: NetworkOnMainThreadException) {
                e.printStackTrace()
            }

            Thread.sleep(1000)

            return noticiasList

        }

        override fun onPostExecute(result: List<NoticiasModel>?) {
            super.onPostExecute(result)

            val noticiasList = ArrayList<NoticiasModel>()
            var objNoticias: NoticiasModel

            result?.filter { noticiasModel -> noticiasModel.section_noticia == "vila nova" }?.forEach {

                objNoticias = NoticiasModel()

                objNoticias.url_noticia = it.url_noticia
                objNoticias.url_foto_noticia = it.url_foto_noticia
                objNoticias.title_noticia = it.title_noticia


                noticiasList.add(objNoticias)


            }



            mAdapter = NoticiasAdapter(noticiasList, context, object : NoticiasAdapter.OnItemClickListener {
                override fun OnItemClickFoto(itemTime: NoticiasModel) {

                    Toast.makeText(context, itemTime.url_noticia, Toast.LENGTH_LONG).show()


                }

            })

            progressBar_noticias.visibility = View.INVISIBLE
            recycler_view_noticias.layoutManager = GridLayoutManager(context, 1)

            recycler_view_noticias.adapter = mAdapter

        }
    }

}
