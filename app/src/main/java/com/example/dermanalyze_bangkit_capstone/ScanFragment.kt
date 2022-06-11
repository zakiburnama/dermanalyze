package com.example.dermanalyze_bangkit_capstone

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.dermanalyze_bangkit_capstone.databinding.FragmentScanBinding
import com.example.dermanalyze_bangkit_capstone.utils.reduceFileImage
import com.example.dermanalyze_bangkit_capstone.utils.rotateBitmap
import com.example.dermanalyze_bangkit_capstone.utils.uriToFile
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class ScanFragment : Fragment() {

    private var _binding: FragmentScanBinding? = null
    private val binding get() = _binding!!

    private var getFile: File? = null

    companion object {
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    context,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
//                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentScanBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                requireContext() as Activity,
                ScanActivity.REQUIRED_PERMISSIONS,
                ScanActivity.REQUEST_CODE_PERMISSIONS
            )
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cameraXButton.setOnClickListener { startCameraX() }
        binding.galleryButton.setOnClickListener { startGallery() }
        binding.uploadButton.setOnClickListener { uploadImage() }
    }


    private fun startCameraX() {
        val intent = Intent(context, CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun uploadImage() {

//        val descBlank = binding.etDescription.text.isBlank()
//        val desc = binding.etDescription.text.toString()

        val loginPreference = LoginPreference(requireContext())
        val token = loginPreference.getToken()
        val tokenauth = "Bearer $token"

        if (getFile != null) {
//            showLoading(true)
            val file = reduceFileImage(getFile as File)
//            val description = desc.toRequestBody("text/plain".toMediaType())
//            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val requestImageFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "image",
                file.name,
                requestImageFile
            )

            Log.i("TAG", "##### multipart $tokenauth")
            Log.i("TAG", "##### multipart $imageMultipart")

            val service = ApiConfig().getApiService().uploadImage(
                tokenauth,
                imageMultipart,
            )
//            val service = ApiConfig3().getApiService3().uploadImage(
//                tokenauth,
//                imageMultipart,
//            )
            service.enqueue(object : Callback<PredictResponse> {
                override fun onResponse(
                    call: Call<PredictResponse>,
                    response: Response<PredictResponse>
                ) {
//                    showLoading(false)
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
//                            val i = Intent(this@ScanActivity, MainActivity::class.java)
//                            startActivity(i, ActivityOptionsCompat.makeSceneTransitionAnimation(this@ScanActivity).toBundle())
//                            finishAfterTransition()
                            Log.i("TAG", "###### SUKSES")
                        }
                    } else {
                        Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()

                        Log.i("TAG", "###### GAGAL response.body ${response.body()}")
                        Log.i("TAG", "###### GAGAL response.body ${response.body()?.detail}")
                        Log.i("TAG", "###### GAGAL response.message ${response.message()}")
                        Log.i("TAG", "###### GAGAL response.errorBody ${response.errorBody()}")
//                        Log.i("TAG", "###### GAGAL $token")
                    }
                }
                override fun onFailure(call: Call<PredictResponse>, t: Throwable) {
//                    showLoading(false)
                    Log.i("TAG", "###### GAGAL ${t}")
//                    Toast.makeText(context, "Gagal instance Retrofit", Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            if (getFile == null)
                Toast.makeText(context, "Silakan masukkan berkas gambar terlebih dahulu.", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(context, "Silakan isi deskripsi terlebih dahulu.", Toast.LENGTH_SHORT).show()
        }
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == ScanFragment.CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            getFile = myFile

            val result = rotateBitmap(
                BitmapFactory.decodeFile(myFile.path),
                isBackCamera
            )

            binding.previewImageView.setImageBitmap(result)
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, requireContext())

            getFile = myFile

            binding.previewImageView.setImageURI(selectedImg)
        }
    }

    private fun showLoading(isLoading: Boolean) {
//        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}