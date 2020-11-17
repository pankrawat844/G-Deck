package com.app.g_deck

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import kotlinx.android.synthetic.main.activity_signature.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.security.Permission
import java.text.SimpleDateFormat
import java.util.*


class SignatureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signature)
        clear.setOnClickListener {
            signature_view.clearCanvas()
        }
        save.setOnClickListener {
            if(!signature_view.isBitmapEmpty) {
                if (ContextCompat.checkSelfPermission(
                        this@SignatureActivity,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )==PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                        this@SignatureActivity,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    )==PackageManager.PERMISSION_GRANTED
                )
                    storeImage(signature_view.signatureBitmap)
                else{
                   ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE),1001)
                }
            }
            else
                Toast.makeText(this@SignatureActivity, "Please add your signature.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun storeImage(image: Bitmap) {
        val pictureFile = getOutputMediaFile()
        if (pictureFile == null) {
            Log.d(
                "storeImage",
                "Error creating media file, check storage permissions: "
            ) // e.getMessage());
            return
        }
        try {
            if(pictureFile.exists())
                pictureFile.delete()
            val fos = FileOutputStream(pictureFile)
            image.compress(Bitmap.CompressFormat.PNG, 90, fos)
            fos.close()
            Toast.makeText(this@SignatureActivity, "Signature Saved Successfully", Toast.LENGTH_SHORT).show()
        } catch (e: FileNotFoundException) {
            Log.d("storeImage", "File not found: " + e.message)
        } catch (e: IOException) {
            Log.d("storeImage", "Error accessing file: " + e.message)
        }
    }

    private fun getOutputMediaFile(): File? {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        val mediaStorageDir = File(
            Environment.getExternalStorageDirectory()
                .toString() + "/Android/data/"
                    + applicationContext.packageName
                    + "/Files"
        )

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }
        // Create a media file name
        val timeStamp: String = SimpleDateFormat("ddMMyyyy_HHmm").format(Date())
        val mediaFile: File
        val mImageName = intent.getStringExtra("booking_id")+"_"+intent.getStringExtra("role")+".jpg"
        mediaFile = File(mediaStorageDir.getPath() + File.separator.toString() + mImageName)
        return mediaFile
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==1001){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                
            }else{
                Toast.makeText(this@SignatureActivity, "Permission is required for save image.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}