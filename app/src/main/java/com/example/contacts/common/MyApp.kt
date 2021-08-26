package com.example.contacts.common

import android.app.Application
import com.example.contacts.db.ContactsRoomDatabase
import com.example.contacts.repository.local.ContactsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApp : Application() {

    companion object{
        lateinit var instance : MyApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { ContactsRoomDatabase.getDatabase(this, applicationScope) }

    val repository by lazy { ContactsRepository(database.contactsDao()) }


}