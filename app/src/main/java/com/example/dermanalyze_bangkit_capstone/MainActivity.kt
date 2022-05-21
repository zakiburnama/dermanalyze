package com.example.dermanalyze_bangkit_capstone

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvArticles: RecyclerView
    private val list = ArrayList<Articles>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home, R.id.navigation_maps, R.id.navigation_camera, R.id.navigation_history, R.id.navigation_profile
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        rvArticles = findViewById(R.id.rv_articles)
        rvArticles.setHasFixedSize(true)
        list.addAll(listArticles)
        showRecyclerList()
    }

    private val listArticles: ArrayList<Articles>
        get() {
            val dataName = resources.getStringArray(R.array.data_title)
            val dataDescription = resources.getStringArray(R.array.readmore)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
            val listArticles = ArrayList<Articles>()
            for (i in dataName.indices) {
                val articles = Articles(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
                listArticles.add(articles)
            }
            return listArticles
        }
    private fun showRecyclerList() {
        rvArticles.layoutManager = LinearLayoutManager(this)
        val listArticlesAdapter = ListArticlesAdapter(list)
        rvArticles.adapter = listArticlesAdapter
    }

}
