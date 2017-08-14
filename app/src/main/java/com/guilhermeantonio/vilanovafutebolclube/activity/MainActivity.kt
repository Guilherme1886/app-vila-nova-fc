package com.guilhermeantonio.vilanovafutebolclube.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import com.guilhermeantonio.vilanovafutebolclube.R
import com.guilhermeantonio.vilanovafutebolclube.fragment.ArtilheirosFragment
import com.guilhermeantonio.vilanovafutebolclube.fragment.ClassificacaoFragment
import com.guilhermeantonio.vilanovafutebolclube.fragment.JogosFragment
import com.guilhermeantonio.vilanovafutebolclube.fragment.NoticiasFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupViewPager(viewpager)
        tabs.setupWithViewPager(viewpager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(NoticiasFragment(), "NOTICIAS")
        adapter.addFragment(ClassificacaoFragment(), "CLASSIFICAÇÃO")
        adapter.addFragment(JogosFragment(), "JOGOS")
        adapter.addFragment(ArtilheirosFragment(), "ARTILHEIROS")
        viewPager.adapter = adapter
    }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = arrayListOf<Fragment>()
        private val mFragmentTitleList = arrayListOf<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }
}
