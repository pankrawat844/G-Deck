package com.app.g_deck.team_home

import androidx.lifecycle.ViewModel
import com.app.g_deck.manager_home.ManagetHomeListener
import com.app.g_deck.repo.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamHomeViewModel(val repo:AuthRepository):ViewModel() {
        var listener:ManagetHomeListener?=null
    fun getTeamList(team_id:String){
        CoroutineScope(Dispatchers.Main).launch {
           val response= repo.teamRequestList(team_id)
            listener?.onSuccess(response)
        }

    }
}