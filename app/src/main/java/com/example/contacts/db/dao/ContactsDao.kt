package com.example.contacts.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.contacts.db.entities.Contacts
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactsDao {
    @Query("SELECT * FROM contacts")
    fun getContacts() : Flow<List<Contacts>>

    @Insert
    suspend fun newContact(contacts: Contacts)
}