package com.app.g_deck.team_home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.g_deck.login.AuthViewModel
import com.app.g_deck.repo.AuthRepository

class TeamHomeViewModelFactory(val repo:AuthRepository):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TeamHomeViewModel(repo) as T
    }
}