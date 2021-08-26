package com.example.contacts.repository.local

import androidx.lifecycle.*
import com.example.contacts.db.entities.Contacts
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ContactsViewModel(private val contactsRepository: ContactsRepository) : ViewModel() {
    val allContacts : LiveData<List<Contacts>> = contactsRepository.allContacts.asLiveData()

    fun newContactDB(contacts: Contacts) = viewModelScope.launch {
        contactsRepository.newContactDB(contacts)
    }

}

class ContactsViewModelFactory(private val repository: ContactsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContactsViewModel::class.java)){
            @Suppress("UNCHEKED_CAST")
            return ContactsViewModel(repository) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}