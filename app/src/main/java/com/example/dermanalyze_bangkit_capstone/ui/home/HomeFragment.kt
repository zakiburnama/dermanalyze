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
import com.example.dermanalyze_bangkit_capstone.*
import com.example.dermanalyze_bangkit_capstone.databinding.FragmentHomeBinding

//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [HomeFragment.newInstance] factory method to
// * create an instance of this fragment.
// */

class HomeFragment : Fragment() {

//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null



    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var rvArticles: RecyclerView
    private val list = ArrayList<Articles>()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
//        val view = inflater.inflate(R.layout.fragment_home, container, false)

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        binding.textView3.text = "WAW"


        binding.rvArticles.setHasFixedSize(true)
        list.addAll(listArticles)
//        showRecyclerList()
        Log.i("TAG", "##### isi list artikel $list")

        return root
//        return inflater.inflate(R.layout.fragment_profile, container, false)
//        return view
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
        rvArticles.layoutManager = LinearLayoutManager(activity)
        val listArticlesAdapter = ListArticlesAdapter(list)
        rvArticles.adapter = listArticlesAdapter
    }

//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment ProfileFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            ProfileFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }

}