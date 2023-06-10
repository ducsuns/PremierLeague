package com.example.btl_mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.btl_mobile.DAO.Controller
import com.example.btl_mobile.Fragment.Match.MatchFragment
import com.example.btl_mobile.Fragment.Me.MeFragment
import com.example.btl_mobile.Fragment.News.NewsFragment
import com.example.btl_mobile.Fragment.News.WebFragment
import com.example.btl_mobile.Fragment.Ranking.RankingFragment
import com.example.btl_mobile.Fragment.Team.TeamFragment

import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var navigationView: BottomNavigationView
    var teamFragment= TeamFragment()
    var matchFragment = MatchFragment()
    var rankingFragmet = RankingFragment()
    var newsFragment = NewsFragment()
    var meFragment= MeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadData()
        navigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        navigationView.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.action_news ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,newsFragment).commit()
                    true
                }

                R.id.action_team ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,teamFragment).commit()
                    true
                }

                R.id.action_match ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,matchFragment).commit()
                    true
                }

                R.id.action_ranking -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,rankingFragmet).commit()
                    true
                }
                R.id.action_me -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,meFragment).commit()
                    true
                }

                else -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container,teamFragment).commit()
                    true
                }
            }
        }
    }

    private fun loadData()
    {
        thread {
            Controller.getNews()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, NewsFragment()).commit()
            Controller.getData()
        }
    }

    fun goToWeb(link:String)
    {
        var webFragment: WebFragment = WebFragment(link)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,webFragment).addToBackStack("a").commit()
    }
}