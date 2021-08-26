package com.example.contacts.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.contacts.R
import com.example.contacts.databinding.ItemContactBinding
import com.example.contacts.db.entities.Contacts

class ContactsAdapter(private var contactList: List<Contacts>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>(){
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemContactBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position]
        holder.binding.tvName.text = contact.nombre
        holder.binding.tvAddress.text = contact.direccion
        holder.binding.tvTelNum.text = contact.telefono.toString()
        Glide.with(context)
            .load(contact.foto)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.ic_broken_image)
            .centerCrop()
            .into(holder.binding.ivContact)
    }

    fun setData(contacts: List<Contacts>?){
        contactList = contacts!!
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = contactList.size
}