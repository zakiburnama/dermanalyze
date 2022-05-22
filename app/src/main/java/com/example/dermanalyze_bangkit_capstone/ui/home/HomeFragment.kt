package com.example.dermanalyze_bangkit_capstone.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dermanalyze_bangkit_capstone.Articles
import com.example.dermanalyze_bangkit_capstone.ListArticlesAdapter
import com.example.dermanalyze_bangkit_capstone.R
import com.example.dermanalyze_bangkit_capstone.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var rvArticles: RecyclerView
    private val list = ArrayList<Articles>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }


        binding.rvArticles.setHasFixedSize(true)
        list.addAll(listArticles)
//        showRecyclerList()
        Log.i("TAG", "##### isi list artikel $list")

        return root
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
        rvArticles.layoutManager = LinearLayoutManager(context)
        val listArticlesAdapter = ListArticlesAdapter(list)
        rvArticles.adapter = listArticlesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}