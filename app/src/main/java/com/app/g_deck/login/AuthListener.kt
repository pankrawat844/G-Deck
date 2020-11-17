package com.app.g_deck.login

import com.app.g_deck.model.TeamLoginResponse

interface AuthListener{
    fun onStrarted()
    fun onSuccess(result:TeamLoginResponse)
    fun onFailure(result:String)
}