package com.app.g_deck.complete_job

import android.app.Activity
import android.app.DatePickerDialog
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.app.g_deck.R
import com.app.g_deck.SignatureActivity
import com.app.g_deck.repo.AuthRepository
import kotlinx.android.synthetic.main.activity_complete.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class CompleteActivity : AppCompatActivity(),KodeinAware {

    lateinit var  image1MultipartBody: MultipartBody.Part
    lateinit var  image2MultipartBody: MultipartBody.Part
    lateinit var  image3MultipartBody: MultipartBody.Part
    var booking_id:String?=null
    val repository:AuthRepository by instance()
    var image1Path: File?=null
    var image2Path: File?=null
    var image3Path: File?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete)
        booking_id=intent.getStringExtra("booking_id")

        authrized_client_sign.setOnClickListener{
            val intent=Intent(this,SignatureActivity::class.java)
            intent.putExtra("booking_id",booking_id)
            intent.putExtra("role","authrized_client")
            startActivity(intent)
        }
        gedeck_represantative_sign.setOnClickListener{
            val intent=Intent(this,SignatureActivity::class.java)
            intent.putExtra("booking_id",booking_id)
            intent.putExtra("role","gdeck_represntative")
            startActivity(intent)
        }

        authrized_client_date.setOnClickListener {
            val dateFormat= SimpleDateFormat("dd-MM-yyyy")
            val newCalendar: Calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val newDate: Calendar = Calendar.getInstance()
                    newDate.set(year, monthOfYear, dayOfMonth)
                    authrized_client_date.setText(dateFormat.format(newDate.getTime()))
                },
                newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }

        gedeck_represantative_date.setOnClickListener {
            val dateFormat= SimpleDateFormat("dd-MM-yyyy")
            val newCalendar: Calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    val newDate: Calendar = Calendar.getInstance()
                    newDate.set(year, monthOfYear, dayOfMonth)
                    gedeck_represantative_date.setText(dateFormat.format(newDate.getTime()))
                },
                newCalendar.get(Calendar.YEAR),
                newCalendar.get(Calendar.MONTH),
                newCalendar.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }

        image1.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
//            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
        }

        image2.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
//            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2)
        }
        image3.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
//            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3)
        }
        submit.setOnClickListener {
            if(client_name.text.toString().isNullOrEmpty()){
                Toast.makeText(this@CompleteActivity, "Please enter client name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(location.text.toString().isNullOrEmpty()){
                Toast.makeText(this@CompleteActivity, "Please enter location", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if(description.text.toString().isNullOrEmpty()){
                Toast.makeText(this@CompleteActivity, "Please enter description", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if(comments.text.toString().isNullOrEmpty()){
                Toast.makeText(this@CompleteActivity, "Please enter comments", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(authrized_client.text.toString().isNullOrEmpty()){
                Toast.makeText(this@CompleteActivity, "Please enter authorised client name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(authrized_client_date.text.toString().isNullOrEmpty()){
                Toast.makeText(this@CompleteActivity, "Please enter authorised client date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(gedeck_represantative.text.toString().isNullOrEmpty()){
                Toast.makeText(this@CompleteActivity, "Please enter G-deck representative anme", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(gedeck_represantative.text.toString().isNullOrEmpty()){
                Toast.makeText(this@CompleteActivity, "Please enter G-deck representative name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(gedeck_represantative_date.text.toString().isNullOrEmpty()){
                Toast.makeText(this@CompleteActivity, "Please enter G-deck representative date", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            if(no_of_deck.text.toString().isNullOrEmpty()){
//                Toast.makeText(this@CompleteActivity, "Please enter no of decks", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            if(no_of_lifts.text.toString().isNullOrEmpty()){
//                Toast.makeText(this@CompleteActivity, "Please enter no of lifts", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            if(handrails.text.toString().isNullOrEmpty()){
//                Toast.makeText(this@CompleteActivity, "Please enter handrails", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            if(no_of_legs.text.toString().isNullOrEmpty()){
//                Toast.makeText(this@CompleteActivity, "Please enter no of legs", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            if(gates.text.toString().isNullOrEmpty()){
//                Toast.makeText(this@CompleteActivity, "Please enter gates", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            if(makeUpPanels.text.toString().isNullOrEmpty()){
//                Toast.makeText(this@CompleteActivity, "Please enter make up panels", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            if(braces.text.toString().isNullOrEmpty()){
//                Toast.makeText(this@CompleteActivity, "Please enter braces", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            if(ladderBackets.text.toString().isNullOrEmpty()){
//                Toast.makeText(this@CompleteActivity, "Please enter ladder backets", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//            if(limitation.text.toString().isNullOrEmpty()){
//                Toast.makeText(this@CompleteActivity, "Please enter limitation comments", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }

//            if(image1Path==null){
//                Toast.makeText(this@CompleteActivity, "Please select image", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//
//            if(image2Path==null){
//                Toast.makeText(this@CompleteActivity, "Please select image", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
//
//            if(image3Path==null){
//                Toast.makeText(this@CompleteActivity, "Please select image", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
            val mediaStorageDir = File(
                Environment.getExternalStorageDirectory()
                    .toString() + "/Android/data/"
                        + applicationContext.packageName
                        + "/Files"
            )
            val mImageNameForeman = intent.getStringExtra("booking_id")+"_authrized_client.jpg"
            val foreman_sign_file = File(mediaStorageDir.getPath() + File.separator.toString() + mImageNameForeman)
            val mImageNamegdeck = intent.getStringExtra("booking_id")+"_gdeck_represntative.jpg"
            val gdeck_sign_file = File(mediaStorageDir.getPath() + File.separator.toString() + mImageNamegdeck)

            val foremanRequestFile= foreman_sign_file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val foremanMultipart=MultipartBody.Part.createFormData("foreman_sign",foreman_sign_file.name,foremanRequestFile)
            val gdeckRequestFile= gdeck_sign_file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            val gdeckMultipart=MultipartBody.Part.createFormData("gdeck_sign",gdeck_sign_file.name,gdeckRequestFile)
            if(image1Path!=null) {
                val image1RequestFile =
                    image1Path?.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                 image1MultipartBody = MultipartBody.Part.createFormData(
                    "image1",
                    image1Path?.name,
                    image1RequestFile!!
                )
            }
            if(image2Path!=null) {
                val image2RequestFile =
                    image2Path?.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                 image2MultipartBody=MultipartBody.Part.createFormData("image2",image2Path?.name,image2RequestFile!!)

            }
            if(image3Path!=null) {
                val image3RequestFile =
                    image3Path?.asRequestBody("multipart/form-data".toMediaTypeOrNull())
                 image3MultipartBody = MultipartBody.Part.createFormData(
                    "image3",
                    image3Path?.name,
                    image3RequestFile!!
                )
            }
            val clientname= client_name.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val location= location.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val description= description.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val comments= comments.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val authorise_client_name= authrized_client.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val authorise_client_date= authrized_client_date.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val gdeck_represantative_name= gedeck_represantative.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())

            val gdeck_represantative_date= gedeck_represantative_date.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val no_of_deck=no_of_deck.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val no_of_lifts=no_of_lifts.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val handrails=handrails.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val no_of_legs=no_of_legs.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val gates=gates.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val makeUpPanels=makeUpPanels.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val braces=braces.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val ladderBackets=ladderBackets.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val limitation=limitation.text.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val bookingid=booking_id?.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val complete=intent.getStringExtra("completed")?.toRequestBody("multipart/form-data".toMediaTypeOrNull())
            val incomplete=intent.getStringExtra("notcompleted")?.toRequestBody("multipart/form-data".toMediaTypeOrNull())
        progressBar.visibility= View.VISIBLE
CoroutineScope(Dispatchers.Main).launch {
    if(image1Path!=null ) {
        val response = repository.completeJob1(
            bookingid!!,
            clientname,
            location,
            description,
            comments,
            authorise_client_name,
            authorise_client_date,
            gdeck_represantative_name,
            gdeck_represantative_date,
            no_of_deck,
            no_of_lifts,
            handrails,
            no_of_legs,
            gates,
            makeUpPanels,
            braces,
            ladderBackets,
            limitation,
            complete!!,
            incomplete!!,
            foremanMultipart,
            gdeckMultipart,
            image1MultipartBody
        )
        if (response?.status!!) {
            Toast.makeText(this@CompleteActivity, "Job completed successfully.", Toast.LENGTH_SHORT)
                .show()
            finish()
            progressBar.visibility = View.GONE
        } else {
            Toast.makeText(this@CompleteActivity, response?.msg, Toast.LENGTH_SHORT)
                .show()
            progressBar.visibility = View.GONE

        }
    } else if(image2Path!=null ) {
    val response = repository.completeJob2(
        bookingid!!,
        clientname,
        location,
        description,
        comments,
        authorise_client_name,
        authorise_client_date,
        gdeck_represantative_name,
        gdeck_represantative_date,
        no_of_deck,
        no_of_lifts,
        handrails,
        no_of_legs,
        gates,
        makeUpPanels,
        braces,
        ladderBackets,
        limitation,
        complete!!,
        incomplete!!,
        foremanMultipart,
        gdeckMultipart,
        image1MultipartBody,
        image2MultipartBody
    )
    if (response?.status!!) {
        Toast.makeText(this@CompleteActivity, "Job completed successfully.", Toast.LENGTH_SHORT)
            .show()
        finish()
        progressBar.visibility = View.GONE
    } else {
        Toast.makeText(this@CompleteActivity, response?.msg, Toast.LENGTH_SHORT)
            .show()
        progressBar.visibility = View.GONE

    }
}else if(image3Path!=null ) {
        val response = repository.completeJob3(
            bookingid!!,
            clientname,
            location,
            description,
            comments,
            authorise_client_name,
            authorise_client_date,
            gdeck_represantative_name,
            gdeck_represantative_date,
            no_of_deck,
            no_of_lifts,
            handrails,
            no_of_legs,
            gates,
            makeUpPanels,
            braces,
            ladderBackets,
            limitation,
            complete!!,
            incomplete!!,
            foremanMultipart,
            gdeckMultipart,
            image1MultipartBody,
            image2MultipartBody,
            image3MultipartBody
        )
        if (response?.status!!) {
            Toast.makeText(this@CompleteActivity, "Job completed successfully.", Toast.LENGTH_SHORT)
                .show()
            finish()
            progressBar.visibility = View.GONE
        } else {
            Toast.makeText(this@CompleteActivity, response?.msg, Toast.LENGTH_SHORT)
                .show()
            progressBar.visibility = View.GONE

        }
    }else{
        val response = repository.completeJob(
            bookingid!!,
            clientname,
            location,
            description,
            comments,
            authorise_client_name,
            authorise_client_date,
            gdeck_represantative_name,
            gdeck_represantative_date,
            no_of_deck,
            no_of_lifts,
            handrails,
            no_of_legs,
            gates,
            makeUpPanels,
            braces,
            ladderBackets,
            limitation,
            complete!!,
            incomplete!!,
            foremanMultipart,
            gdeckMultipart
        )
        if (response?.status!!) {
            Toast.makeText(this@CompleteActivity, "Job completed successfully.", Toast.LENGTH_SHORT)
                .show()
            finish()
            progressBar.visibility = View.GONE
        } else {
            Toast.makeText(this@CompleteActivity, response?.msg, Toast.LENGTH_SHORT)
                .show()
            progressBar.visibility = View.GONE

        }
    }
}

        }


    }

    override val kodein by kodein()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1) {
            if (resultCode === Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            getContentResolver(),
                            data.data
                        )
                        image1.setImageBitmap(bitmap)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            val parcelFileDescription=contentResolver.openFileDescriptor(data?.data!!,"r",null)?:return
                            image1Path=File(cacheDir,contentResolver.getFileName(data.data!!))
//
                            Log.e("TAG", "onActivityResult: "+image1Path?.path)

                        } else {
                            image1Path=File(getPath(data?.data))
                            Log.e("TAG", "onActivityResult: "+getPath(data?.data))

                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } else if (resultCode === Activity.RESULT_CANCELED) {
                Toast.makeText(this@CompleteActivity, "Canceled", Toast.LENGTH_SHORT).show()
            }
        }

        if (requestCode === 2) {
            if (resultCode === Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            contentResolver,
                            data.data
                        )
                        image2.setImageBitmap(bitmap)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            val parcelFileDescription=contentResolver.openFileDescriptor(data?.data!!,"r",null)?:return
                            image2Path=File(cacheDir,contentResolver.getFileName(data.data!!))
//
                            Log.e("TAG", "onActivityResult: "+image2Path?.path)

                        } else {
                            image2Path=File(getPath(data?.data))

                            Log.e("TAG", "onActivityResult: "+getPath(data?.data))

                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } else if (resultCode === Activity.RESULT_CANCELED) {
                Toast.makeText(this@CompleteActivity, "Canceled", Toast.LENGTH_SHORT).show()
            }
        }

        if (requestCode === 3) {
            if (resultCode === Activity.RESULT_OK) {
                if (data != null) {
                    try {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            getContentResolver(),
                            data.data
                        )
                        image3.setImageBitmap(bitmap)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                            val parcelFileDescription=contentResolver.openFileDescriptor(data?.data!!,"r",null)?:return
                            image3Path=File(cacheDir,contentResolver.getFileName(data.data!!))
//
                            Log.e("TAG", "onActivityResult: "+image1Path?.path)

                        } else {
                            image3Path=File(getPath(data?.data))

                            Log.e("TAG", "onActivityResult: "+getPath(data?.data))

                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } else if (resultCode === Activity.RESULT_CANCELED) {
                Toast.makeText(this@CompleteActivity, "Canceled", Toast.LENGTH_SHORT).show()
            }
        }
    }



    fun ContentResolver.getFileName(uri: Uri):String{
        var name=""
        val cursor=query(uri,null,null,null,null)
        cursor?.use {
            it.moveToFirst()
            name=cursor.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
        }
        return name
    }

    fun getPath(uri: Uri?): String? {
        val projection = arrayOf(MediaStore.MediaColumns.DATA)
        val cursor = contentResolver.query(uri!!, projection, null, null, null)
        if (cursor != null) {
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
            cursor.moveToFirst()
            return cursor.getString(column_index)
        } else
            return null
    }
}