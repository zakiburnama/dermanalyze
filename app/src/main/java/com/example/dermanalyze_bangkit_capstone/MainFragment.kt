package com.example.dermanalyze_bangkit_capstone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainFragment : Fragment() {

    private lateinit var rvArticles: RecyclerView
    private lateinit var rvCancer: RecyclerView
    private val list = ArrayList<Articles>()
    private val list2 = ArrayList<Articles>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_main, container, false)
        val view = inflater.inflate(R.layout.fragment_main, container, false)

//        binding.rvArticles.setHasFixedSize(true)
//        list.addAll(listArticles)
////        showRecyclerList()
//        Log.i("TAG", "##### isi list artikel $list")


        list.clear()
        list.addAll(listArticles)

        list2.clear()
        list2.addAll(listCancer)

        rvArticles = view.findViewById(R.id.rv_articlescancer)
        rvArticles.setHasFixedSize(true)
        showRecyclerList()

        rvCancer = view.findViewById(R.id.rv_articles)
        rvCancer.setHasFixedSize(true)
        showRecyclerList2()

        return  view
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

    private val listCancer: ArrayList<Articles>
        get() {
            val dataName = resources.getStringArray(R.array.data_cancer)
            val dataDescription = resources.getStringArray(R.array.data_cancer)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo_cancer)
            val listArticles = ArrayList<Articles>()
            for (i in dataName.indices) {
                val articles = Articles(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
                listArticles.add(articles)
            }
            return listArticles
        }

    private fun showRecyclerList() {
        rvArticles.layoutManager = LinearLayoutManager(activity)
        val listArticlesAdapter = ListArticlesAdapter(list)
        rvArticles.adapter = listArticlesAdapter

//        listArticlesAdapter.setOnItemClickCallback(object : listArticlesAdapter.OnItemClickCallback {
//            override fun onItemClicked() {
////                moveActivity(data)
//            }
//        })
        listArticlesAdapter.setOnItemClickCallback(object : ListArticlesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Articles) {
//                showSelectedHero(data)
                moveActivity(data)
                Log.i("TAG", "####### ${data.titleArticles}")
                Log.i("TAG", "####### ${data.photo}")
                Log.i("TAG", "####### ${data.readmorearticle}")
//                Toast.makeText(context, "Kamu memilih " + data.titleArticles, Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun showRecyclerList2() {
        rvCancer.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val listCancerAdapter = ListCancerAdapter(list2)
        rvCancer.adapter = listCancerAdapter

        listCancerAdapter.setOnItemClickCallback(object : ListCancerAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Articles) {
                moveActivity(data)
//                Log.i("TAG", "####### ${data.titleArticles}")
//                Log.i("TAG", "####### ${data.photo}")
//                Log.i("TAG", "####### ${data.readmorearticle}")
//                Toast.makeText(context, "Kamu memilih " + data.titleArticles, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun moveActivity(data: Articles) {
        val intent = Intent(context, DetailMainActivity::class.java)
        intent.putExtra(DetailMainActivity.EXTRA_TITLE, data.titleArticles)
        intent.putExtra(DetailMainActivity.EXTRA_PHOTO, data.photo)
        intent.putExtra(DetailMainActivity.EXTRA_READ, data.readmorearticle)
        startActivity(intent)
    }


}