package com.app.g_deck

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.app.g_deck.login.LoginActivity
import com.app.g_deck.manager_home.SiteMangerHomeActivity
import com.app.g_deck.team_home.TeamHomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(object :Runnable{
            override fun run() {
                val sharedPref=getSharedPreferences("app", Context.MODE_PRIVATE)
                if(sharedPref.getBoolean("islogin",false)){
                    if(sharedPref.getString("role","")=="Site Manager")
                    startActivity(Intent(this@SplashActivity, SiteMangerHomeActivity::class.java))
                    else
                        startActivity(Intent(this@SplashActivity, TeamHomeActivity::class.java))
                    finish()

                }else{
                    startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    finish()
                }

            }

        },3000)
    }
}