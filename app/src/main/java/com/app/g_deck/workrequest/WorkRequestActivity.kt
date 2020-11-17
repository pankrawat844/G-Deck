package com.app.g_deck.workrequest

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.iterator
import com.app.g_deck.R
import com.app.g_deck.repo.AuthRepository
import kotlinx.android.synthetic.main.activity_work_request.*
import kotlinx.android.synthetic.main.item_add_plot.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


class WorkRequestActivity : AppCompatActivity(),KodeinAware {
    val repo:AuthRepository by instance()
    var booking_id:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_request)
        val relativeLayout:LinearLayout=findViewById(R.id.relative)
        val add:Button=findViewById(R.id.addview)
        getBookingid()
        add.setOnClickListener(object :View.OnClickListener
        {
            override fun onClick(p0: View?) {
                val layout2: View =
                    LayoutInflater.from(this@WorkRequestActivity).inflate(R.layout.item_add_plot, relativeLayout, false)
            relativeLayout.addView(layout2)
            }
        })
        val dateFormat= SimpleDateFormat("dd-MM-yyyy")
        val newCalendar: Calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val newDate: Calendar = Calendar.getInstance()
                newDate.set(year, monthOfYear, dayOfMonth)
                date.setText(dateFormat.format(newDate.getTime()))
            },
            newCalendar.get(Calendar.YEAR),
            newCalendar.get(Calendar.MONTH),
            newCalendar.get(Calendar.DAY_OF_MONTH)
        )

        dateTxt.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                datePicker.show()
            }
        })

        submit.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                if(date.text.toString().isNullOrEmpty()){
                    Toast.makeText(
                        this@WorkRequestActivity,
                        "Please select any date.",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                if(site_name.text.toString().isNullOrEmpty()){
                    Toast.makeText(
                        this@WorkRequestActivity,
                        "Please enter site name.",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                if(booked_by.text.toString().isNullOrEmpty()){
                    Toast.makeText(
                        this@WorkRequestActivity,
                        "Please enter booked by name.",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                progress.visibility=View.VISIBLE
                CoroutineScope(Dispatchers.Main).launch {
                    for (i in relativeLayout.iterator()){
                        val child=i
                       val plot= child.findViewById<EditText>(R.id.plot)
                       val erect= child.findViewById<CheckBox>(R.id.erect)
                       val dismentle= child.findViewById<CheckBox>(R.id.dismentle)
                       val gf= child.findViewById<CheckBox>(R.id.gf)
                       val ff= child.findViewById<CheckBox>(R.id.ff)
                       val sf= child.findViewById<CheckBox>(R.id.sf)
                       val props= child.findViewById<CheckBox>(R.id.props)
                       val inst= child.findViewById<EditText>(R.id.special_inst)
                        Log.e("plot",erect.isChecked.toString())
                      val response=  repo.addRequest(booking_id,getSharedPreferences("app", Context.MODE_PRIVATE).getString("id","0")!!,date.text.toString(),booked_by.text.toString(),site_name.text.toString(),plot.text.toString(),if(erect.isChecked) "1" else "0",if(dismentle.isChecked) "1" else "0",if(gf.isChecked) "1" else "0",if(sf.isChecked) "1" else "0",if(ff.isChecked) "1" else "0",if(props.isChecked) "1" else "0",inst.text.toString())

                    }
                    progress.visibility=View.GONE
                    Toast.makeText(this@WorkRequestActivity, "Request add successfully.", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        })
    }

    private fun getBookingid() {
        CoroutineScope(Dispatchers.Main).launch {
                val response=  repo.getBookingID()
                booking_id= response?.data[0].msg.toString()!!
        }
    }

    override val kodein by kodein()
}