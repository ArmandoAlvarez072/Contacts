package com.example.contacts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.contacts.common.MyApp
import com.example.contacts.databinding.ActivityAddContactBinding
import com.example.contacts.db.entities.Contacts
import com.example.contacts.repository.remote.ContactsRemoteViewModel
import com.example.contacts.repository.local.ContactsViewModel
import com.example.contacts.repository.local.ContactsViewModelFactory

class AddContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddContactBinding
    private lateinit var contactsRemoteViewModel: ContactsRemoteViewModel

    private val contactsViewModel: ContactsViewModel by viewModels {
        ContactsViewModelFactory((application as MyApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        contactsRemoteViewModel = ViewModelProvider(this).get(ContactsRemoteViewModel::class.java)
        configButton()
    }

    private fun configButton() {
        binding.btnAdd.setOnClickListener {

            if (binding.etName.text.toString().isEmpty()) {
                binding.etName.setError("Ingrese datos")
            } else if (binding.etAddress.text.toString().isEmpty()) {
                binding.etAddress.setError("Ingrese datos")
            } else if (binding.etNumberPhone.text.toString().isEmpty()) {
                binding.etNumberPhone.setError("Ingrese datos")
            } else if (binding.etUrl.text.toString().isEmpty()) {
                binding.etUrl.setError("Ingrese datos")
            } else {
                val contact = Contacts(
                    binding.etName.text.toString().trim(),
                    binding.etNumberPhone.text.toString(),
                    binding.etUrl.text.toString().trim(),
                    binding.etAddress.text.toString().trim()
                )
                contactsViewModel.newContactDB(contact)
                //contactsRemoteViewModel.newContact(contact)
                finish()
            }
        }
    }

}