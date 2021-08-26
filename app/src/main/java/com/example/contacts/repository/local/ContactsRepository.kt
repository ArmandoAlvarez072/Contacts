package com.example.contacts.repository.local

import androidx.annotation.WorkerThread
import com.example.contacts.db.dao.ContactsDao
import com.example.contacts.db.entities.Contacts
import kotlinx.coroutines.flow.Flow

class ContactsRepository(private val contactsDao : ContactsDao) {

    val allContacts : Flow<List<Contacts>> = contactsDao.getContacts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun newContactDB(contacts: Contacts){
        contactsDao.newContact(contacts)
    }


}