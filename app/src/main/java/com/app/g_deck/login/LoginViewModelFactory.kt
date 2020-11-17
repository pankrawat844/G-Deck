package com.app.g_deck.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.g_deck.repo.AuthRepository

class LoginViewModelFactory(val repo:AuthRepository):ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repo) as T
    }
}