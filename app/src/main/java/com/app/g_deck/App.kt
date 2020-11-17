package com.app.g_deck

import android.app.Application
import com.app.g_deck.login.AuthListener
import com.app.g_deck.login.AuthViewModel
import com.app.g_deck.login.LoginActivity
import com.app.g_deck.login.LoginViewModelFactory
import com.app.g_deck.manager_home.ManagerHomeViewModel
import com.app.g_deck.manager_home.ManagerHomeViewModelFactory
import com.app.g_deck.network.AuthApi
import com.app.g_deck.repo.AuthRepository
import com.app.g_deck.repo.BaseRepository
import com.app.g_deck.team_home.TeamHomeViewModel
import com.app.g_deck.team_home.TeamHomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App:Application(),KodeinAware {
    override val kodein by Kodein.lazy {
        import(androidXModule(this@App))
        bind()  from singleton { AuthApi() }
        bind() from provider { AuthRepository(instance()) }
        bind() from provider { AuthViewModel(instance()) }
        bind() from provider { LoginViewModelFactory(instance()) }
        bind()  from singleton { LoginActivity() }
        bind() from singleton { ManagerHomeViewModel(instance()) }
        bind() from singleton { ManagerHomeViewModelFactory(instance()) }
        bind() from singleton { TeamHomeViewModelFactory(instance()) }
        bind() from singleton { TeamHomeViewModel(instance()) }

    }
}