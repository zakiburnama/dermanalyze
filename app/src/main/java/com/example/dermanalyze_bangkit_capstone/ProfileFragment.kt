package com.example.dermanalyze_bangkit_capstone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.dermanalyze_bangkit_capstone.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment(){

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val firstName = binding.editTextTextPersonFirstName
        val lastName = binding.editTextTextPersonLastName
        val email = binding.editTextTextPersonEmail

        viewModel.getUser().observe(viewLifecycleOwner) {
            if (it != null) {
                val data = it as UserResponse

                firstName.setText(data.firstName)
                lastName.setText(data.lastName)
                email.setText(data.email)
            } else {
                Log.d("TAG", "onViewCreated: NULL")
            }
        }

//        ApiConfig().getApiService().getUser().enqueue(object : Callback<UserResponse> {
//            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    if (responseBody != null) {
//                        firstName.setText(responseBody.firstName)
//                        lastName.setText(responseBody.lastName)
//                        email.setText(responseBody.email)
//
//                    }
//                } else{
//                    Log.d("TAG", "onResponse: gagal1: ")
//                }
//            }
//
//            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                Log.d("TAG", "onResponse: gagal2")
//
//            }
//        })



        binding.btnUpdate.setOnClickListener{
            val intent = Intent(requireActivity(),UpdateActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener{
            val intent = Intent(requireActivity(),LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
