package com.app.g_deck.login

import android.view.View
import androidx.lifecycle.ViewModel
import com.app.g_deck.repo.AuthRepository
import com.app.g_deck.utils.APIExceptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
):ViewModel() {

    var userid:String?=null
    var password:String?=null
    var authListener:AuthListener?=null
    var itemSelected:String?=null
    fun teamLogin(view:View){
        if(userid.isNullOrEmpty()){
            authListener?.onFailure("Please enter userid.")
            return
        }

        if(password.isNullOrEmpty()){
            authListener?.onFailure("Please enter password.")
            return
        }

        authListener?.onStrarted()
        if(itemSelected=="Site Manager") {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = repository?.siteManagerLogin(userid!!, password!!)
                    if (response.status!!) {
                        authListener?.onSuccess(response)
                    } else {
                        authListener?.onFailure(response.message!!)
                    }
                } catch (e: APIExceptions) {
                    authListener?.onFailure(e.message!!)
                }
            }
        }else{
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val response = repository?.teamLogin(userid!!, password!!)
                    if (response.status!!) {
                        authListener?.onSuccess(response)
                    } else {
                        authListener?.onFailure(response.message!!)
                    }
                } catch (e: APIExceptions) {
                    authListener?.onFailure(e.message!!)
                }
            }
        }

    }
}