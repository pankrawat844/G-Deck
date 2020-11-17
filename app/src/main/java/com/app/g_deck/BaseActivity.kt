package com.app.g_deck

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.app.g_deck.repo.BaseRepository
import java.util.zip.Inflater

abstract class BaseActivity<VM:ViewModel,B:ViewBinding,R:BaseRepository>: Activity()
{
    protected lateinit var binding: B
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    abstract fun getViewModel():Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater,container:ViewGroup?):B

    abstract fun getActivityRepository():R
}