package com.app.g_deck.team_home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.g_deck.R
import com.app.g_deck.login.LoginActivity
import com.app.g_deck.manager_home.ManagerHomeViewModel
import com.app.g_deck.manager_home.ManagerHomeViewModelFactory
import com.app.g_deck.manager_home.ManagetHomeListener
import com.app.g_deck.model.SiteManagerItem
import com.app.g_deck.model.Site_Manager_List
import com.app.g_deck.model.TeamItem
import com.app.g_deck.workrequest.WorkRequestActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_team_home.*
import kotlinx.android.synthetic.main.activity_team_home.logout
import kotlinx.android.synthetic.main.activity_team_home.recylerview
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class TeamHomeActivity : AppCompatActivity(),KodeinAware,ManagetHomeListener {
    private lateinit var viewModel: TeamHomeViewModel
    private val factory: TeamHomeViewModelFactory by instance()
    private var listItem:List<SiteManagerItem>?= arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_home)
        viewModel= ViewModelProviders.of(this,factory).get(TeamHomeViewModel::class.java)
        viewModel.listener=this
        bindUI()
        logout.setOnClickListener {
            getSharedPreferences("app", Context.MODE_PRIVATE).edit().clear().apply()
            startActivity(Intent(this@TeamHomeActivity, LoginActivity::class.java))
            finish()
        }
    }

    private fun bindUI() {
        viewModel.getTeamList(getSharedPreferences("app", Context.MODE_PRIVATE).getString("id","0")!!)
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

    private fun itnitRecyerView(toListItems: List<TeamItem>) {
        var groupadapter= GroupAdapter<ViewHolder>().apply {

            addAll(toListItems)
        }

        recylerview.apply {
            layoutManager= LinearLayoutManager(this@TeamHomeActivity)
            adapter=groupadapter
        }
    }

    override fun onFailure(result: String) {

    }


    private fun List<Site_Manager_List.Data>.toListItems():List<TeamItem>{
        return this.map {
            TeamItem(it,this@TeamHomeActivity)
        }
    }
}