package com.example.contacts.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.contacts.common.MyApp
import com.example.contacts.databinding.ActivityMainBinding
import com.example.contacts.db.entities.Contacts
import com.example.contacts.repository.local.ContactsViewModel
import com.example.contacts.repository.local.ContactsViewModelFactory
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: ContactsAdapter

    private lateinit var binding : ActivityMainBinding

    private var contactsList : List<Contacts> = ArrayList()


    private val contactsViewModel: ContactsViewModel by viewModels {
        ContactsViewModelFactory((application as MyApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configRecyclerView()
        getContacts()
        configButton()
    }

    private fun getContacts(){
        contactsViewModel.allContacts.observe(this, Observer {
            contactsList = it
            adapter.setData(contactsList)
        })
    }


    private fun configRecyclerView() {
        adapter = ContactsAdapter(mutableListOf())
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2,
                GridLayoutManager.VERTICAL,
                false)
            adapter = this@MainActivity.adapter
        }
    }

    fun configButton() {
        binding.efab.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }
    }
}