package com.example.contacts.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
class Contacts(
    @PrimaryKey(autoGenerate = false)
    val nombre : String,

    val telefono : String,
    val foto : String,
    val direccion : String
)
