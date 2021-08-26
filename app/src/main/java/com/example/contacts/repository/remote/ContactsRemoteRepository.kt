package com.example.contacts.repository.remote

import android.widget.Toast
import com.example.contacts.common.MyApp
import com.example.contacts.db.entities.Contacts
import com.example.contacts.retrofit.ContactsClient
import com.example.contacts.retrofit.ContactsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactsRemoteRepository {

    private var contactsService : ContactsService? = null
    private var contactsClient : ContactsClient? = null


    init  {
        //Remote
        contactsClient = ContactsClient.instance
        contactsService = contactsClient?.getConstactsService()
    }

    fun newContact(contacts: Contacts){
        val call : Call<Contacts>? = contactsService?.saveContact(contacts)
        call?.enqueue(object : Callback<Contacts> {
            override fun onResponse(call: Call<Contacts>, response: Response<Contacts>) {
                Toast.makeText(MyApp.instance, "Contacto Añadido con exito", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Contacts>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_LONG).show()
            }
        })
    }
}