package com.example.dermanalyze_bangkit_capstone

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.dermanalyze_bangkit_capstone.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment(){

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!


    private lateinit var loginPreference: LoginPreference
    private lateinit var tokenauth: String



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        loginPreference = LoginPreference(requireContext())
        val token = loginPreference.getToken()
        tokenauth = "Bearer $token"

        getUsersData(tokenauth)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnUpdate.setOnClickListener{
            val intent = Intent(requireActivity(),UpdateActivity::class.java)
            startActivity(intent)
        }

        binding.tvLogout.setOnClickListener {
            logout(tokenauth)
        }

        binding.switchDarkmode.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if(isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.tvLanguage.setOnClickListener {
            Toast.makeText(context, "Not aviable yet", Toast.LENGTH_SHORT).show()
        }

        binding.tvAbout.setOnClickListener {
            Toast.makeText(context, "Not aviable yet", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getUsersData(token: String) {
        val client = ApiConfig().getApiService().getUsers(token)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>)
            {
//                    showLoading(false)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    val fn = responseBody.first_name
                    val ls = responseBody.last_name
                    val em = responseBody.email

                    setText(fn, ls, em)

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

    private fun logout(token: String) {
        val client = ApiConfig().getApiService().logout(token)
        client.enqueue(object : Callback<LogoutResponse> {
            override fun onResponse(
                call: Call<LogoutResponse>,
                response: Response<LogoutResponse>)
            {
//                    showLoading(false)
                val responseBody = response.body()
                if (response.isSuccessful && responseBody != null) {
                    moveLogout()
                } else {
                    moveLogout()
                }
            }
            override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                moveLogout()
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setText(firstname: String, lastName: String, email: String) {
        binding.tvUsernameProfile.text = """$firstname $lastName"""
        binding.tvEmailProfiel.text = email
    }

    private fun moveLogout() {
        loginPreference = LoginPreference(requireContext())
        loginPreference.clearUser()
        val intent = Intent(requireActivity(),LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


}
