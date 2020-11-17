package com.app.g_deck.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.app.g_deck.R
import com.app.g_deck.databinding.ActivityLogin1Binding
import com.app.g_deck.databinding.ActivityLoginBinding
import com.app.g_deck.manager_home.SiteMangerHomeActivity
import com.app.g_deck.model.TeamLoginResponse
import com.app.g_deck.team_home.TeamHomeActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(),AuthListener,KodeinAware {
    var authViewModel:AuthViewModel?=null
    val factory:LoginViewModelFactory by instance()
    val arrayItmes= arrayOf("Site Manager","Team")
   lateinit var bindind:ActivityLogin1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      bindind= DataBindingUtil.setContentView(this,R.layout.activity_login1)
        authViewModel= ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
      authViewModel!!.authListener=this
        bindind.data=authViewModel
        bindind.spinner.adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayItmes)
        authViewModel?.itemSelected="Site Manager"
        bindind.spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                authViewModel?.itemSelected=arrayItmes.get(p2)
            }

        }
    }

    override fun onStrarted() {
    bindind.progress.visibility=View.VISIBLE
    }

    override fun onSuccess(result: TeamLoginResponse) {
        bindind.progress.visibility=View.GONE

        Toast.makeText(this@LoginActivity, "Login successfully", Toast.LENGTH_SHORT).show()
        val sharedPref=getSharedPreferences("app", Context.MODE_PRIVATE)
        val editor=sharedPref.edit()
            editor.putBoolean("islogin",true)
            editor.putString("id",result.response?.id)
            editor.putString("name",result.response?.name)
        editor.putString("role",spinner.selectedItem.toString())
        editor.apply()
        if(spinner.selectedItem.toString()=="Site Manager")
            startActivity(Intent(this@LoginActivity,SiteMangerHomeActivity::class.java))
        else
            startActivity(Intent(this@LoginActivity,TeamHomeActivity::class.java))
        finish()
    }

    override fun onFailure(result: String) {
        bindind.progress.visibility=View.GONE

        Toast.makeText(this@LoginActivity, result, Toast.LENGTH_SHORT).show()
    }

    override val kodein by kodein()
}