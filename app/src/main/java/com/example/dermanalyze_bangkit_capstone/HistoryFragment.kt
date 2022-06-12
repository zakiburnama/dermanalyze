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

//        val xx = "https://dermanalyze-api-dev.herokuapp.com/static/images/02297417e6b71f6c7912.jpg"

//        Picasso.get().load(xx).into(binding.imageView2)

        return view
    }

    private fun getUsersData(token: String) {
        val client = ApiConfig().getApiService().getHistory(token)
        client.enqueue(object : Callback<ArrayList<PredictResponse>> {
            override fun onResponse(
                call: Call<ArrayList<PredictResponse>>,
                response: Response<ArrayList<PredictResponse>>
            )
            {
//                    showLoading(false)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    Log.i("TAG", "##### ${responseBody}")
                    Log.i("TAG", "##### SUKSES")

                    showRecyclerList(responseBody)

//                    val x = responseBody[0].created_at
//                    val y = responseBody[0].pred_results
//                    val z = responseBody[0].photo_url


//                    binding.textView5.text = y

//                    Picasso.get().load(z).into(binding.imageView2)

                } else {
                    Toast.makeText(context,response.message(), Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<ArrayList<PredictResponse>>, t: Throwable) {
//                    showLoading(false)
                Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showRecyclerList(data: ArrayList<PredictResponse>) {
        binding.rvHistory.layoutManager = LinearLayoutManager(context)
        val listHistoryAdapter = ListHistoryAdapter(data)
        binding.rvHistory.adapter = listHistoryAdapter

//        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
//            override fun onItemClicked(data: User) {
//                moveActivityUser(data)
//            }
//        })
    }

}