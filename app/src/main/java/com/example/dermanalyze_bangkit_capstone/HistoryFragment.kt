package com.example.dermanalyze_bangkit_capstone

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dermanalyze_bangkit_capstone.databinding.FragmentHistoryBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentHistoryBinding.inflate(layoutInflater)
        val view = binding.root

        val loginPreference = LoginPreference(requireContext())
        val token = loginPreference.getToken()
        val tokenauth = "Bearer $token"

        getUsersData(tokenauth)

        return view
    }

    private fun getUsersData(token: String) {
        showLoading(true)
        val client = ApiConfig().getApiService().getHistory(token)
        client.enqueue(object : Callback<ArrayList<PredictResponse>> {
            override fun onResponse(
                call: Call<ArrayList<PredictResponse>>,
                response: Response<ArrayList<PredictResponse>>
            )
            {
                showLoading(false)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    showRecyclerList(responseBody)
                } else {
                    Toast.makeText(context,response.message(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ArrayList<PredictResponse>>, t: Throwable) {
                showLoading(false)
                Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showRecyclerList(data: ArrayList<PredictResponse>) {
        binding.rvHistory.layoutManager = LinearLayoutManager(context)
        val listHistoryAdapter = ListHistoryAdapter(data)
        binding.rvHistory.adapter = listHistoryAdapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

}