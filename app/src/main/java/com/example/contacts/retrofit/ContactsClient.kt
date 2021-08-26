package com.example.contacts.retrofit

import com.example.contacts.common.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class ContactsClient {
    private val contactsService : ContactsService
    private val retrofit : Retrofit

    companion object {
        var instance : ContactsClient? = null
            get() {
                if (field == null){
                    instance = ContactsClient()
                }
                return field
            }
    }

    init {
        val client = OkHttpClient.Builder().build()

        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .build()

        contactsService = retrofit.create(ContactsService::class.java)
    }

    fun getConstactsService() = contactsService
}