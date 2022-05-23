package com.example.dermanalyze_bangkit_capstone

import android.Manifest
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dermanalyze_bangkit_capstone.databinding.ActivityScanBinding
import com.example.dermanalyze_bangkit_capstone.utils.rotateBitmap
import java.io.File

class ScanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanBinding

    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
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


    companion object {
        const val CAMERA_X_RESULT = 200

//        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
//        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}