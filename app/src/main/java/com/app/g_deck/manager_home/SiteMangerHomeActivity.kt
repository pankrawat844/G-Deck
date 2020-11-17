package com.app.g_deck.manager_home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.g_deck.R
import com.app.g_deck.login.LoginActivity
import com.app.g_deck.workrequest.WorkRequestActivity
import com.app.g_deck.model.SiteManagerItem
import com.app.g_deck.model.Site_Manager_List
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_home.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SiteMangerHomeActivity : AppCompatActivity(),KodeinAware,ManagetHomeListener {
    private lateinit var viewModel: ManagerHomeViewModel
    private val factory:ManagerHomeViewModelFactory by instance()
    private var listItem:List<SiteManagerItem>?= arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        viewModel=ViewModelProviders.of(this,factory).get(ManagerHomeViewModel::class.java)
        viewModel.listener=this

        val button:FloatingActionButton=findViewById(R.id.add)
        button.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                startActivity(Intent(this@SiteMangerHomeActivity,WorkRequestActivity::class.java))
            }

        })
        logout.setOnClickListener {
            getSharedPreferences("app", Context.MODE_PRIVATE).edit().clear().apply()
            startActivity(Intent(this@SiteMangerHomeActivity,LoginActivity::class.java))
            finish()
        }
    }

    private fun bindUI() {
    viewModel.getList(getSharedPreferences("app", Context.MODE_PRIVATE).getString("id","0")!!)
    }

    override val kodein by kodein()
    override fun onStrarted() {

    }

    override fun onResume() {
        super.onResume()
        bindUI()
    }
    override fun onSuccess(result: Site_Manager_List) {
        itnitRecyerView(result?.data!!.toListItems())
    }

    private fun itnitRecyerView(toListItems: List<SiteManagerItem>) {
        var groupadapter=GroupAdapter<ViewHolder>().apply {

            addAll(toListItems)
        }

        recylerview.apply {
            layoutManager=LinearLayoutManager(this@SiteMangerHomeActivity)
            adapter=groupadapter
        }
    }

    override fun onFailure(result: String) {

    }


    private fun List<Site_Manager_List.Data>.toListItems():List<SiteManagerItem>{
        return this.map {
            SiteManagerItem(it,this@SiteMangerHomeActivity)
        }
    }
}