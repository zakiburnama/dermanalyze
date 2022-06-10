package com.example.dermanalyze_bangkit_capstone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dermanalyze_bangkit_capstone.databinding.FragmentMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val list = ArrayList<Articles>()
    private val list2 = ArrayList<Articles>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        val view = binding.root

        val loginPreference = LoginPreference(requireContext())
        val token = loginPreference.getToken()
        val tokenauth = "Bearer $token"

        getUsersData(tokenauth)

        list.clear()
        list.addAll(listArticles)

        list2.clear()
        list2.addAll(listCancer)

        binding.rvArticles.setHasFixedSize(true)
        showRecyclerList()

        binding.rvArticlescancer.setHasFixedSize(true)
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
            val dataDescription = resources.getStringArray(R.array.data_cancer_more)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo_cancer)
            val listArticles = ArrayList<Articles>()
            for (i in dataName.indices) {
                val articles = Articles(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
                listArticles.add(articles)
            }
            return listArticles
        }

    private fun showRecyclerList() {
        binding.rvArticlescancer.layoutManager = LinearLayoutManager(activity)
        val listArticlesAdapter = ListArticlesAdapter(list)
        binding.rvArticlescancer.adapter = listArticlesAdapter

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
        binding.rvArticles.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        val listCancerAdapter = ListCancerAdapter(list2)
        binding.rvArticles.adapter = listCancerAdapter

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

    private fun getUsersData(token: String) {
        val client = ApiConfig().getApiService().getUsers(token)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            )
            {
//                    showLoading(false)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val fn = responseBody.first_name
                    val ln = responseBody.last_name

                    binding.tvUsername.text = "$fn $ln"

//                    setText(fn, ls, em)

                } else {
                    Toast.makeText(context,response.message(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                    showLoading(false)
                Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
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