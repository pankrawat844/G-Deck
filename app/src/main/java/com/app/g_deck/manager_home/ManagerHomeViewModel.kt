package com.app.g_deck.manager_home

import androidx.lifecycle.ViewModel
import com.app.g_deck.repo.AuthRepository
import com.app.g_deck.utils.APIExceptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ManagerHomeViewModel(val repo:AuthRepository):ViewModel() {
    var listener:ManagetHomeListener?=null
    fun getList( id:String){
        try {
            listener?.onStrarted()
            CoroutineScope(Dispatchers.Main).launch {
               val response= repo.siteManagerRequestList(id)
                listener?.onSuccess(response)
            }
        }catch (e:APIExceptions)
        {
            listener?.onFailure(e.message!!)
        }
    }
}