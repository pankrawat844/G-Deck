package com.app.g_deck.job_detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import com.app.g_deck.R
import com.app.g_deck.complete_job.CompleteActivity
import com.app.g_deck.databinding.ActivityJobDetailBinding
import com.app.g_deck.model.Site_Manager_List
import com.app.g_deck.repo.AuthRepository
import kotlinx.android.synthetic.main.activity_job_detail.*
import kotlinx.android.synthetic.main.item_add_plot.view.*
import kotlinx.android.synthetic.main.item_home.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class JobDetailActivity : AppCompatActivity(),KodeinAware {
    val repo:AuthRepository by instance()
    var completed:String?=""
    var notcompleted:String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databind:ActivityJobDetailBinding=DataBindingUtil.setContentView(this,R.layout.activity_job_detail)
        val data=intent.getSerializableExtra("data") as Site_Manager_List.Data
        databind.model=data
        Log.e("respose",data.toString())
        submit.setOnClickListener {
            completed=""
            notcompleted=""
            for(data in relative.children){
                val workcomlted:CheckBox=data.findViewById(R.id.work_completed)
                val plotno:TextView=data.findViewById(R.id.plot)
                val notcompletedTxt:TextView=data.findViewById(R.id.not_completed)
                if(workcomlted.isChecked){

                    completed+=plotno.text.toString()+","
                }else{
                    notcompleted+=plotno.text.toString()+"-"+notcompletedTxt.text.toString()+","
                }
            }
            completed=completed?.dropLast(1)
            notcompleted=notcompleted?.dropLast(1)
            Log.e("completed",completed!!)
            Log.e("notcompleted",notcompleted!!)
            val intent=Intent(this,CompleteActivity::class.java)
            intent.putExtra("booking_id",data.booking_id)
            intent.putExtra("completed",completed)
            intent.putExtra("notcompleted",notcompleted)
            startActivity(intent)


        }
        if(intent.getStringExtra("page")=="sitemanager"){
            work_request_txt.text="Work Request"
            submit.visibility=View.GONE
        }
        if(intent.getStringExtra("page")=="sitemanager"){
            CoroutineScope(Dispatchers.Main).launch {
                val respose = repo.getworkDetails(data.booking_id!!)
                for (item in respose.data?.iterator()!!) {
                    val layout2: View =
                        LayoutInflater.from(this@JobDetailActivity)
                            .inflate(R.layout.item_add_plot, relative, false)
                    relative.addView(layout2)
                    layout2.plot.setText(item?.plot!!)
                    layout2.plot.isEnabled = false
                    layout2.erect.isChecked = if (item.erect == "1") true else false
                    layout2.erect.isClickable = false
                    layout2.dismentle.isChecked = if (item.dismantle == "1") true else false
                    layout2.dismentle.isClickable = false
                    layout2.gf.isChecked = if (item.gf == "1") true else false
                    layout2.gf.isClickable = false
                    layout2.ff.isChecked = if (item.ff == "1") true else false
                    layout2.ff.isClickable = false
                    layout2.sf.isChecked = if (item.sf == "1") true else false
                    layout2.sf.isClickable = false
                    layout2.props.isChecked = if (item.props == "1") true else false
                    layout2.props.isClickable = false
                    layout2.work_completed.isChecked = if (item.completed == "1") true else false
                    layout2.work_completed.isClickable = false
                    layout2.special_inst.setText(item?.special_instruction!!)
                    layout2.special_inst.isEnabled = false

                }
            }
        }else {
            CoroutineScope(Dispatchers.Main).launch {
                val respose = repo.getJobDetails(data.booking_id!!)
                for (item in respose.data?.iterator()!!) {
                    val layout2: View =
                        LayoutInflater.from(this@JobDetailActivity)
                            .inflate(R.layout.item_add_plot, relative, false)
                    relative.addView(layout2)
                    layout2.plot.setText(item?.plot!!)
                    layout2.plot.isEnabled = false
                    layout2.erect.isChecked = if (item.erect == "1") true else false
                    layout2.erect.isClickable = false
                    layout2.dismentle.isChecked = if (item.dismantle == "1") true else false
                    layout2.dismentle.isClickable = false
                    layout2.gf.isChecked = if (item.gf == "1") true else false
                    layout2.gf.isClickable = false
                    layout2.ff.isChecked = if (item.ff == "1") true else false
                    layout2.ff.isClickable = false
                    layout2.sf.isChecked = if (item.sf == "1") true else false
                    layout2.sf.isClickable = false
                    layout2.props.isChecked = if (item.props == "1") true else false
                    layout2.props.isClickable = false
                    layout2.work_completed.visibility = View.VISIBLE
                    layout2.not_completed.visibility=View.VISIBLE
                    layout2.special_inst.setText(item?.special_instruction!!)
                    layout2.special_inst.isEnabled = false

                }
            }
        }
    }

    override val kodein by kodein()
}