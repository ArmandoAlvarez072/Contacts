package com.example.contacts.repository.remote

import androidx.lifecycle.ViewModel
import com.example.contacts.db.entities.Contacts

class ContactsRemoteViewModel : ViewModel(){
    private val contactsRemoteRepository : ContactsRemoteRepository = ContactsRemoteRepository()

    fun newContact(contacts: Contacts) {
        contactsRemoteRepository.newContact(contacts)
    }
}