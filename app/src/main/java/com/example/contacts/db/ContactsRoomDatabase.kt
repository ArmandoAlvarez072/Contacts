package com.example.contacts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.contacts.db.dao.ContactsDao
import com.example.contacts.db.entities.Contacts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Contacts::class), version = 1, exportSchema = false)
abstract class ContactsRoomDatabase : RoomDatabase() {

    abstract fun contactsDao() : ContactsDao

    private class ContactsDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var customerDao = database.contactsDao()

                }
            }
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: ContactsRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): ContactsRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactsRoomDatabase::class.java,
                    "contacts_database"
                )
                    .addCallback(ContactsDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}