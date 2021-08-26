package com.example.contacts.retrofit

import com.example.contacts.db.entities.Contacts
import retrofit2.Call
import retrofit2.http.POST

interface ContactsService {
    @POST("saveContact")
    fun saveContact(contacts: Contacts) : Call<Contacts>
}