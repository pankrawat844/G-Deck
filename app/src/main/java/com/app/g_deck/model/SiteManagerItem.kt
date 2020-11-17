package com.app.g_deck.model

import android.content.Context
import android.content.Intent
import com.app.g_deck.R
import com.app.g_deck.databinding.ItemHomeBinding
import com.app.g_deck.job_detail.JobDetailActivity
import com.xwray.groupie.databinding.BindableItem

class SiteManagerItem(
    val item:Site_Manager_List.Data,
    val context: Context
):BindableItem<ItemHomeBinding>(){
    override fun getLayout(): Int {
        return R.layout.item_home
    }

    override fun bind(viewBinding: ItemHomeBinding, position: Int) {
        viewBinding.model = item
        viewBinding.cardView.setOnClickListener {
            val intent= Intent(context, JobDetailActivity::class.java)
            intent.putExtra("data",item)
            intent.putExtra("page","sitemanager")
            context.startActivity(intent)
        }

    }

}