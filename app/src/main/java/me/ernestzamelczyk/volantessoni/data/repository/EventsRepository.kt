package me.ernestzamelczyk.volantessoni.data.repository

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class EventsRepository @Inject constructor(
        private val databaseReference: DatabaseReference
) {

    fun fetchEvents() {

        databaseReference.child("event").push()

    }

}