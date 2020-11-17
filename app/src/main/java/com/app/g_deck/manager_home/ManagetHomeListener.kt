package com.app.g_deck.manager_home

import com.app.g_deck.model.Site_Manager_List

interface ManagetHomeListener{
    fun onStrarted()
    fun onSuccess(result:Site_Manager_List)
    fun onFailure(result:String)
}